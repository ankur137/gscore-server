package com.example.gscoreapp.user_repo_info;

import com.example.gscoreapp.user_data.UserInfo;
import com.example.gscoreapp.user_data.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRepoInfoService {

    @Autowired
    UserRepoInfoRepository userRepoInfoRepository;

    @Autowired
    UserInfoRepository userInfoRepository;

    public UserRepoInfo getAllUserRepos(String username){
        UserInfo userInfo = userInfoRepository.findByUsername(username);
        return userRepoInfoRepository.findUserRepoInfoByUid(userInfo.getUid());
    }
}
