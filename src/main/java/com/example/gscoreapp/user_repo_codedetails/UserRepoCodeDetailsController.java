package com.example.gscoreapp.user_repo_codedetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserRepoCodeDetailsController {

    @Autowired
    UserRepoCodeDetailsService service;

    @RequestMapping("/user/{uid}/repocodedetails")
    public UserRepoCodeDetails getUserRepoCodeDetails(@PathVariable int uid) { return service.getAllUserRepoCodeDetails(uid);
}
}
