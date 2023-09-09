package com.atipera.githubintegration.service;

import com.atipera.githubintegration.model.dto.RepositoryDTO;

import java.io.IOException;
import java.util.List;

public interface GitHubService {
    List<RepositoryDTO> getGitHubRepositories(String username) throws IOException;
}
