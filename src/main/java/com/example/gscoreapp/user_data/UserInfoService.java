package com.example.gscoreapp.user_data;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    public List<UserInfo> getAllUsers(){
        List<UserInfo> users = new ArrayList<>();
        userInfoRepository.findAll().forEach(users::add);
        return users;
    }

    public void addUser(UserInfo userInfo){
        userInfoRepository.save(userInfo);
        System.out.println("User information added");
    }

    public void getUser(Integer id){
        userInfoRepository.findById(id);
    }

    public void updateUser(int id, UserInfo userInfo){
        userInfoRepository.save(userInfo);
    }

    public void deleteUser(int id){
        userInfoRepository.deleteById(id);
    }
}
