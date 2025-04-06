package com.maslanka.repolist.Service;

import com.maslanka.repolist.ApiClient.GithubApiClient;
import com.maslanka.repolist.Model.GithubBranch;
import com.maslanka.repolist.Model.GithubRepo;
import com.maslanka.repolist.Model.GithubUser;
import com.maslanka.repolist.Response.RepoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<GithubRepo> getReposByUsername(String username){ return githubApiClient.fetchRepo(username);}
    public List<GithubBranch> getBranchesByRepo(String username, String reponame){return githubApiClient.fetchBranch(username, reponame);}
    public List<RepoResponse> getReposWithBranches(String username){

                List<GithubRepo> repoList = githubApiClient.fetchRepo(username);
                List<CompletableFuture<RepoResponse>> futures = repoList.stream()
                        .filter(repo->!repo.isFork())
                        .map(repo->CompletableFuture.supplyAsync(()->{
                            try {
                                return new RepoResponse(repo, githubApiClient.fetchBranch(username, repo.getName()));
                            } catch(Exception e){
                                System.err.println("Error fetching branch for: " + repo.getName() + " - " + e.getMessage());
                                return null;
                            }
                        }
                        ))
                        .toList();

        return futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

    }
}
