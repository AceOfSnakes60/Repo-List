package com.maslanka.repolist.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubRepo {

    public String name;
    public GithubOwner owner;
    public boolean fork;
}
