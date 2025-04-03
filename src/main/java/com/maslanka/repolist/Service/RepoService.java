package com.maslanka.repolist.Service;

import com.maslanka.repolist.ApiClient.GithubApiClient;
import com.maslanka.repolist.Model.GithubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepoService {
    private final GithubApiClient githubApiClient;

    @Autowired
    public RepoService(GithubApiClient githubApiClient){
    this.githubApiClient = githubApiClient;
    }
    public GithubUser getReposByUsername(String username){
        return githubApiClient.fetchUser(username);
    }
}
