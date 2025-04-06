package com.maslanka.repolist.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubBranch {
    private String name;
    private GithubCommit commit;

    public GithubCommit getCommit() {
        return commit;
    }

    public GithubBranch(String name){
        this.name = name;
    }
    public void setCommit(GithubCommit commit) {
        this.commit = commit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

