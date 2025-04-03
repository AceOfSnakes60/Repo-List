package com.maslanka.repolist.controllers;

import com.maslanka.repolist.Model.GithubRepo;
import com.maslanka.repolist.Model.GithubUser;
import com.maslanka.repolist.Service.RepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class PublicController {
    private final RepoService repoService;

    @Autowired
    public PublicController(RepoService repoService){
        this.repoService = repoService;
    }

    @GetMapping("/{username}")
    public GithubUser getUser(@PathVariable String username){
        return repoService.getReposByUsername(username);
    }

    @GetMapping("/{username}/repo")
    public GithubRepo getRepos(@PathVariable String username){
        return null;
    }
}
