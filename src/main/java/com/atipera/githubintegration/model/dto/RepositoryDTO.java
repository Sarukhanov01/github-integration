package com.atipera.githubintegration.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RepositoryDTO {
    private String name;
    private String ownerLogin;
    private List<BranchDTO> branches;
}
