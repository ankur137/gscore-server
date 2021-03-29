package com.example.gscoreapp.user_repo_codedetails;

import com.example.gscoreapp.user_repo_info.UserRepoInfo;
import com.example.gscoreapp.user_repo_info.UserRepoInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRepoCodeDetailsService {

    @Autowired
    UserRepoCodeDetailsRepository UserRepoCodeDetailsRepository;

    @Autowired
    UserRepoInfoRepository UserRepoInfoRepository;

    public UserRepoCodeDetails getAllUserRepoCodeDetails(int urid) {
        UserRepoInfo userRepoInfo = UserRepoInfoRepository.findUserRepoInfoByUid(urid);
        return UserRepoCodeDetailsRepository.findUserRepoCodeDetailsBy(userRepoInfo.getUrid());
    }
}