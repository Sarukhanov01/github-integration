package com.atipera.githubintegration.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BranchDTO {
    private String name;
    private String lastCommitSha;
}
