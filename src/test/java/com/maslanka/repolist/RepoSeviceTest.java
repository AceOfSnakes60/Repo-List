package com.maslanka.repolist;

import com.maslanka.repolist.ApiClient.GithubApiClient;
import com.maslanka.repolist.Model.GithubBranch;
import com.maslanka.repolist.Model.GithubRepo;
import com.maslanka.repolist.Model.GithubUser;
import com.maslanka.repolist.Response.RepoResponse;
import com.maslanka.repolist.Service.RepoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class RepoSeviceTest {
    @Mock
    private GithubApiClient githubApiClient;

    private RepoService repoService;

    @BeforeEach
    void setup(){
        repoService = new RepoService(githubApiClient);
    }


    @Test
    void testGetReposByUsername(){
        List<GithubRepo> repos = List.of(new GithubRepo("repo1"), new GithubRepo("repo2"));

        Mockito.when(githubApiClient.fetchRepo("testuser")).thenReturn(repos);

        List<GithubRepo> result = repoService.getReposByUsername("testuser");

        assertEquals(2, result.size());
        assertEquals("repo1", result.get(0).getName());

    }

    @Test
    void testGetBranchesByRepo(){
        List<GithubBranch>branches = List.of(new GithubBranch("main"));

        Mockito.when(githubApiClient.fetchBranch("testuser", "repo1")).thenReturn(branches);
        List<GithubBranch> result = repoService.getBranchesByRepo("testuser", "repo1");
        assertEquals(1, result.size());
        assertEquals("main", result.get(0).getName());
    }

    @Test
    void testGetReposWithBranches_ExcludesForks(){
        GithubRepo repo1 = new GithubRepo("repo1");
        repo1.setFork(false);
        GithubRepo repo2 = new GithubRepo("repo2");
        repo2.setFork(true);

        List<GithubRepo> repos = List.of(repo1, repo2);
        List<GithubBranch> branches = List.of(new GithubBranch("main"));

        Mockito.when(githubApiClient.fetchRepo("testuser")).thenReturn(repos);
        Mockito.when(githubApiClient.fetchBranch("testuser", "repo1")).thenReturn(branches);

        List<RepoResponse> result = repoService.getReposWithBranches("testuser");

        assertEquals(1, result.size());
        assertEquals("repo1", result.get(0).getRepository_name());

    }


}
