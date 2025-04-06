package com.maslanka.repolist.ApiClient;

import com.maslanka.repolist.Model.GithubBranch;
import com.maslanka.repolist.Model.GithubRepo;
import com.maslanka.repolist.Model.GithubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Component
public class GithubApiClient {
    private final RestTemplate restTemplate;
    private static final String GITHUB_API_URL = "https://api.github.com/users/";
    private static final String GITHUB_API_REPO_URL = "https://api.github.com/repos/";
    public GithubUser fetchUser(String username){
        return restTemplate.getForObject(GITHUB_API_URL + username, GithubUser.class);
    }
    @Autowired
    public GithubApiClient(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    public List<GithubRepo> fetchRepo(String username){
        String url = UriComponentsBuilder
                .fromHttpUrl(GITHUB_API_URL)
                .pathSegment(username, "repos")
                .toUriString();
    try {
        ResponseEntity<List<GithubRepo>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<GithubRepo>>() {
                }
        );
    if(response.getStatusCode() == HttpStatus.NOT_FOUND){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }
        return response.getBody();

    } catch (ResponseStatusException ex) {
        throw ex;
    } catch (Exception e) {
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error: " + e.getMessage());
    }
    }
    public List<GithubBranch> fetchBranch(String username, String reponame){
        String url = UriComponentsBuilder
                .fromHttpUrl(GITHUB_API_REPO_URL)
                .pathSegment(username, reponame, "branches")
                .toUriString();

        ResponseEntity<List<GithubBranch>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<GithubBranch>>(){}
        );
        return response.getBody();
    }
}
