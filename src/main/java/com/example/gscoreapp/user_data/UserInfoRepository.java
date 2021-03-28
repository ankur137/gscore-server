package com.example.gscoreapp.user_data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends CrudRepository<UserInfo, Integer> {
    public UserInfo findByLogin(String login);
}
