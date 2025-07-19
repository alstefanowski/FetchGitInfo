package com.example.atiper_github_api;

import com.example.atiper_github_api.dto.GitResponse;
import com.example.atiper_github_api.model.Branch;
import com.example.atiper_github_api.service.GitService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class AtiperGithubApiApplicationTests {

	@Autowired
	private GitService gitService;

	@Test
	void shouldReturnNonForkReposWithBranches_whenUserExists() {
		String owner = "alstefanowski";
		List<GitResponse> result = gitService.getReposInfo(owner);

		assertNotNull(result);
		assertFalse(result.isEmpty());

		for (GitResponse repo : result) {
			assertFalse(repo.isFork(), "Repo should not be a fork");
			assertNotNull(repo.getBranches(), "Branches should not be null");
			assertFalse(repo.getBranches().isEmpty(), "Branches list should not be empty");

			for (Branch branch : repo.getBranches()) {
				assertNotNull(branch.getName(), "Branch name should not be null");
				assertNotNull(branch.getCommit(), "Commit should not be null");
				assertNotNull(branch.getCommit().getSha(), "Commit SHA should not be null");
			}
		}
	}
}
