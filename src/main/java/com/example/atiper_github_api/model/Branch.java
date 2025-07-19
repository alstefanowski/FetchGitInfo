package com.example.atiper_github_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Branch {
    private String name;
    private Commit commit;
}
