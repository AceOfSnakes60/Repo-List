package com.maslanka.repolist.controllers;

import com.maslanka.repolist.Model.GithubBranch;
import com.maslanka.repolist.Model.GithubRepo;
import com.maslanka.repolist.Response.Response;
import com.maslanka.repolist.Response.RepoResponse;
import com.maslanka.repolist.Service.RepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class PublicController {
    private final RepoService repoService;

    @Autowired
    public PublicController(RepoService repoService){
        this.repoService = repoService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<Response<List<RepoResponse>>> getRepoWithBranches(@PathVariable String username) {
        try {
            List<RepoResponse> repoResponses = repoService.getReposWithBranches(username);
            if (repoResponses.isEmpty()) {
                Response<List<RepoResponse>> response = new Response<>(HttpStatus.NOT_FOUND.value(), "No repositories found for user: " + username);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            // Success case: Send actual data along with status and message
            Response<List<RepoResponse>> response = new Response<>(HttpStatus.OK.value(), "Repositories with branches fetched successfully.", repoResponses);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            Response<List<RepoResponse>> response = new Response<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            Response<List<RepoResponse>> response = new Response<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Unexpected error: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{username}/repo")
    public List<GithubRepo> getRepos(@PathVariable String username){
        return repoService.getReposByUsername(username);
    }

    @GetMapping("/{username}/branch")
    public List<GithubBranch> getBranches(@PathVariable String username){
        return repoService.getBranchesByRepo(username, "Twatter_Client");
    }
}
