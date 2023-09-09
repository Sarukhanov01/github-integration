package com.atipera.githubintegration.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GitHubUserNotFoundException extends RuntimeException {
    public GitHubUserNotFoundException(String message) {
        super("GitHub user not found: " + message);
    }
}
