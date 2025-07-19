package com.example.atiper_github_api.dto;

import com.example.atiper_github_api.model.Branch;
import com.example.atiper_github_api.model.Owner;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GitResponse {
    private String name;
    @JsonIgnore
    private boolean fork;
    private Owner owner;
    private List<Branch> branches;
}
