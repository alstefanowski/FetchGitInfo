package com.example.atiper_github_api.service;

import com.example.atiper_github_api.dto.GitResponse;
import com.example.atiper_github_api.exception.UserNotFoundException;
import com.example.atiper_github_api.model.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GitService {
    @Value("${github.api.url}")
    private String API;

    @Autowired
    private RestTemplate restTemplate;

    public List<GitResponse> getReposInfo(String owner) {
        try {
            String url = API + "/users/" + owner + "/repos";
            GitResponse[] repos = restTemplate.getForObject(url, GitResponse[].class);
            if (repos == null) return Collections.emptyList();

            return Arrays.stream(repos)
                    .filter(repo -> !repo.isFork())
                    .peek(repo -> repo.setBranches(getBranches(owner, repo.getName())))
                    .collect(Collectors.toList());
        } catch(RestClientResponseException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new UserNotFoundException("User: " + owner + " was not found on GitHub");
            }
            throw new RuntimeException("Failed to fetch repos", e);
        }

    }

    private List<Branch> getBranches(String owner, String repo) {
        String branchesUrl = API + "/repos/" + owner + "/" + repo + "/branches";
        Branch[] branches = restTemplate.getForObject(branchesUrl, Branch[].class);
        return branches != null ? Arrays.asList(branches) : Collections.emptyList();
    }
}
