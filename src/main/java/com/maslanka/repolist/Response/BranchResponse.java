package com.maslanka.repolist.Response;

public class BranchResponse {
    public String name;
    public String sha;
    public BranchResponse(String name, String sha){
        this.name = name;
        this.sha = sha;
    }
}
