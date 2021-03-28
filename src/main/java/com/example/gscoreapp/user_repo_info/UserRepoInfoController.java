package com.example.gscoreapp.user_repo_info;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRepoInfoController {
    @Autowired
    UserRepoInfoService service;

    @GetMapping("/users/{username}/repo")
    public UserRepoInfo getUserRepoInfo(@PathVariable String username){
        return service.getAllUserRepos(username);
    }
}
