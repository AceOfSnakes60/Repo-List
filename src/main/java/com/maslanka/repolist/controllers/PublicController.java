package com.maslanka.repolist.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class PublicController {
    public PublicController(){

    }

    @GetMapping()
    public void getRepos(@PathVariable String username){

    }
}
