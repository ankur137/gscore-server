package com.example.gscoreapp.user_repo_codedetails;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepoCodeDetailsRepository extends CrudRepository<UserRepoCodeDetails, Integer> {
    public UserRepoCodeDetails findUserRepoCodeDetailsBy(int urid);

}




