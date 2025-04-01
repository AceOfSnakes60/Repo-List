package com.maslanka.repolist.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubUser {
    private String login;

    public String getLogin(){return login;}
}
