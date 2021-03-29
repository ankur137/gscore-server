package com.example.gscoreapp.user_repo_codedetails;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class UserRepoCodeDetails {
    @Id
    private int urcid;
    private int urid;
    private int uid;
    private long gid;
    private String programming_language;
    private int lines_of_code_repo;
    private String comment_repo;
    private int file_size;
    private String files_by_language;
    private String code_language;

}
