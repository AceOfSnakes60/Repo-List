package com.maslanka.repolist.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubBranch {
    public String name;
    public GithubCommit commit;
}

