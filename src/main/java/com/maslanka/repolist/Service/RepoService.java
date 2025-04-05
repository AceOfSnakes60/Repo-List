package com.maslanka.repolist.Service;

import com.maslanka.repolist.ApiClient.GithubApiClient;
import com.maslanka.repolist.Model.GithubBranch;
import com.maslanka.repolist.Model.GithubRepo;
import com.maslanka.repolist.Model.GithubUser;
import com.maslanka.repolist.Response.RepoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class RepoService {
    private final GithubApiClient githubApiClient;

    @Autowired
    public RepoService(GithubApiClient githubApiClient){
    this.githubApiClient = githubApiClient;
    }
    public GithubUser getUser(String username){
        return githubApiClient.fetchUser(username);
    }
    public List<GithubRepo> getReposByUsername(String username){ return githubApiClient.fetchRepo(username);}
    public List<GithubBranch> getBranchesByRepo(String username, String reponame){return githubApiClient.fetchBranch(username, reponame);}
    public List<RepoResponse> getReposWithBranches(String username){

                List<GithubRepo> repoList = githubApiClient.fetchRepo(username);
                List<CompletableFuture<RepoResponse>> futures = repoList.stream()
                        .filter(repo->!repo.fork)
                        .map(repo->CompletableFuture.supplyAsync(()->
                                new RepoResponse(repo, githubApiClient.fetchBranch(username, repo.name))
                        ))
                        .toList();

        return futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

    }
}
