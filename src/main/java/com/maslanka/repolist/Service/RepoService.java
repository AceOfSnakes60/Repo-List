package com.maslanka.repolist.Service;

import com.maslanka.repolist.ApiClient.GithubApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepoService {
    private  final GithubApiClient githubApiClient;

    @Autowired
    public RepoService(GithubApiClient githubApiClient){
    this.githubApiClient = githubApiClient;
    }
    public void getReposByUsername(){

    }
}
