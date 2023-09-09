package com.atipera.githubintegration.controller;

import com.atipera.githubintegration.model.dto.RepositoryDTO;
import com.atipera.githubintegration.service.GitHubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/github")
@RequiredArgsConstructor
public class GitHubController {

    private final GitHubService gitHubService;

    @GetMapping(value = "/repositories/{username}", produces = "application/json")
    public ResponseEntity<List<RepositoryDTO>> listGitHubRepositories(@PathVariable String username) throws IOException {
        List<RepositoryDTO> repositories = gitHubService.getGitHubRepositories(username);
        return ResponseEntity.ok(repositories);
    }
}
