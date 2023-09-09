package com.atipera.githubintegration.service.impl;

import com.atipera.githubintegration.exception.GitHubUserNotFoundException;
import com.atipera.githubintegration.model.dto.BranchDTO;
import com.atipera.githubintegration.model.dto.RepositoryDTO;
import com.atipera.githubintegration.service.GitHubService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GitHubServiceImpl implements GitHubService {
    private final String githubToken = "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";
    private final ObjectMapper objectMapper;

    @Override
    public List<RepositoryDTO> getGitHubRepositories(String githubUsername) throws IOException {
        String apiUrl = "https://api.github.com/users/" + githubUsername + "/repos";

        ResponseEntity<String> response = setHeaders(apiUrl, githubUsername);

        if (response.getStatusCode() == HttpStatus.OK) {
            JsonNode jsonNode = objectMapper.readTree(response.getBody());
            List<RepositoryDTO> repositories = new ArrayList<>();

            for (JsonNode repoNode : jsonNode) {
                if (!repoNode.get("fork").asBoolean()) {
                    RepositoryDTO repositoryDTO = new RepositoryDTO();
                    repositoryDTO.setName(repoNode.get("name").asText());
                    repositoryDTO.setOwnerLogin(repoNode.get("owner").get("login").asText());
                    List<BranchDTO> branches = getBranchesForRepository(repositoryDTO.getName(), githubUsername);
                    repositoryDTO.setBranches(branches);
                    repositories.add(repositoryDTO);
                }
            }
            return repositories;
        } else {
            throw new RuntimeException("Failed to fetch GitHub repositories");
        }
    }

    private List<BranchDTO> getBranchesForRepository(String repositoryName, String githubUsername) throws IOException {
        String apiUrl = "https://api.github.com/repos/" + githubUsername + "/" + repositoryName + "/branches";

        ResponseEntity<String> response = setHeaders(apiUrl, githubUsername);
        JsonNode jsonNode = objectMapper.readTree(response.getBody());

        List<BranchDTO> branches = new ArrayList<>();

        for (JsonNode branchNode : jsonNode) {
            BranchDTO branchDTO = new BranchDTO();
            branchDTO.setName(branchNode.get("name").asText());
            String lastCommitSha = getLastCommitShaForBranch(githubUsername, repositoryName, branchDTO.getName());
            branchDTO.setLastCommitSha(lastCommitSha);

            branches.add(branchDTO);
        }
        return branches;
    }

    private String getLastCommitShaForBranch(String githubUsername, String repositoryName, String branchName) throws IOException {
        String apiUrl = "https://api.github.com/repos/" + githubUsername + "/" + repositoryName + "/branches/" + branchName;

        ResponseEntity<String> response = setHeaders(apiUrl, githubUsername);
        JsonNode jsonNode = objectMapper.readTree(response.getBody());
        return jsonNode.get("commit").get("sha").asText();
    }

    private ResponseEntity<String> setHeaders(String apiUrl, String githubUsername) throws HttpClientErrorException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "application/json");
            headers.set("Authorization", "token " + githubToken);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            return new RestTemplate().exchange(apiUrl, HttpMethod.GET, entity, String.class);

        } catch (HttpClientErrorException.NotFound ex) {
            throw new GitHubUserNotFoundException(githubUsername);
        }
    }
}

