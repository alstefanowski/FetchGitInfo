package com.example.atiper_github_api.controller;

import com.example.atiper_github_api.dto.GitRequest;
import com.example.atiper_github_api.dto.GitResponse;
import com.example.atiper_github_api.exception.ErrorResponse;
import com.example.atiper_github_api.exception.UserNotFoundException;
import com.example.atiper_github_api.service.GitService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/git")
@RequiredArgsConstructor
public class GitController {
    private final GitService gitService;

    @GetMapping
    public ResponseEntity<?> getReposInfo(@RequestBody GitRequest gitRequest) {
        try {
            List<GitResponse> response = gitService.getReposInfo(gitRequest.getOwner());
            if (response.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(response);
        } catch(UserNotFoundException e) {
            ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }
}
