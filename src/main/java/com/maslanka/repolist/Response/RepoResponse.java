package com.maslanka.repolist.Response;

import com.maslanka.repolist.Model.GithubBranch;
import com.maslanka.repolist.Model.GithubRepo;

import java.util.ArrayList;
import java.util.List;

public class RepoResponse {
    public String repository_name;
    public String login;
    public List<BranchResponse> branch;

    public RepoResponse(GithubRepo repo, List<GithubBranch> branches){
        this.repository_name = repo.name;
        this.login = repo.owner.login;
        branch = new ArrayList<>();

        branches.forEach(element->{
            branch.add(new BranchResponse(element.name, element.commit.sha));
        });
    }
}
