package com.maslanka.repolist.ApiClient;

import com.maslanka.repolist.Model.GithubUser;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GithubApiClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String GITHUB_API_URL = "https://api.github.com/users/";
    public GithubUser fetchUser(String username){
        return restTemplate.getForObject(GITHUB_API_URL + username, GithubUser.class);
    }

    public void fetchRepo(String name){

    }
}
