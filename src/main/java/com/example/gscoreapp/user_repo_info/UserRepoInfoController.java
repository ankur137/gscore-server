package com.example.gscoreapp.user_repo_info;

import com.example.gscoreapp.user_data.UserInfo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class UserRepoInfoController {
    @Autowired
    UserRepoInfoService service;



    @GetMapping("/users/{username}/")
    public UserRepoInfo getUserRepoInfo(@PathVariable String username){



        return service.getAllUserRepos(username);
    }

    @GetMapping("/users/addfromgit/{username}/")
    public void addUserRepoInfo(@PathVariable String username) throws ExecutionException, InterruptedException, JsonProcessingException {
        System.out.println("Api hit");

                service.addUserRepo(username);
    }
}
