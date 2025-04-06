package com.maslanka.repolist.Response;

import com.maslanka.repolist.Model.GithubBranch;
import com.maslanka.repolist.Model.GithubRepo;

import java.util.ArrayList;
import java.util.List;

public class RepoResponse {
    private String repository_name;
    private String login;
    public List<BranchResponse> branch;

    public RepoResponse(GithubRepo repo, List<GithubBranch> branches){
        this.repository_name = repo.getName();
        this.login = repo.getOwner().getLogin();
        branch = new ArrayList<>();

        branches.forEach(element->{
            branch.add(new BranchResponse(element.getName(), element.getCommit().getSha()));
        });
    }

    public String getRepository_name() {
        return repository_name;
    }

    public void setRepository_name(String repository_name) {
        this.repository_name = repository_name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
