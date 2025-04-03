package com.maslanka.repolist.ApiClient;

import com.maslanka.repolist.Model.GithubBranch;
import com.maslanka.repolist.Model.GithubRepo;
import com.maslanka.repolist.Model.GithubUser;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class GithubApiClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String GITHUB_API_URL = "https://api.github.com/users/";
    private static final String GITHUB_API_REPO_URL = "https://api.github.com/repos/";
    public GithubUser fetchUser(String username){
        return restTemplate.getForObject(GITHUB_API_URL + username, GithubUser.class);
    }

    public List<GithubRepo> fetchRepo(String username){
        ResponseEntity<List<GithubRepo>> response = restTemplate.exchange(
                GITHUB_API_URL + username+"/repos",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<GithubRepo>>() {}
        );

        return response.getBody();
    }
    public List<GithubBranch> fetchBranch(String username, String reponame){
        ResponseEntity<List<GithubBranch>> response = restTemplate.exchange(
                GITHUB_API_REPO_URL + username + "/" + reponame + "/branches",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<GithubBranch>>(){}
        );
        return response.getBody();
    }
}
