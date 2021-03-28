package com.example.gscoreapp.user_repo_info;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class UserRepoInfo {
    @Id
    private int urid;
    private int uid;
    private long gid;
    private boolean is_forked;
    private int commits;
    private int pulls;
    private int releases;
    private int issues;
    private int contributors;
    private long size;
    private int forks;
    private int stargazers_count;
    private int watchers;
    private int open_issues;
    //build trigger
}
