# FetchGitInfo
Spring Boot application that provides a REST API to fetch **non-forked GitHub repositories** and their **branches** for a given user.  
It uses the public GitHub REST API v3 and supports token-based authentication to avoid request limits.

---

## Stack

- Java 21
- Spring Boot 3.5.3
- Maven
- GitHub REST API v3

---

## Authentication Required

This application **requires your own GitHub Personal Access Token** (PAT) to work.

1. Go to: [https://github.com/settings/tokens](https://github.com/settings/tokens)
2. Generate a **classic** token (scopes: `public_repo`)
3. Add the token in the file:

```properties
# src/main/resources/application.properties
github.api.url=https://api.github.com
github.token=ghp_your_token_here
