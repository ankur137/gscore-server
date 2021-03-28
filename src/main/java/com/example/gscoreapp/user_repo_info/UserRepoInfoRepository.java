package com.example.gscoreapp.user_repo_info;

import com.example.gscoreapp.user_data.UserInfoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepoInfoRepository extends CrudRepository<UserRepoInfo, Integer> {
    public UserRepoInfo findUserRepoInfoByUid(int uid);
}
