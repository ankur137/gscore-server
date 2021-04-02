package com.example.gscoreapp.user_repo_info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = UserRepoDeserializer.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRepoInfo {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    private String uid;
    private String repoName;
    private String user_ID;
    private int gid;
    private int commits;
    private int pulls;
    private int contributors;
    private int size;
    private int forks;
    private int stargazers_count;
    private int watchers;
    private int open_issues;
    private int closed_issues;

    @Override
    public String toString() {
        return "UserRepoInfo{" +

                ", repoName='" + repoName + '\'' +
                ", uid='" + uid + '\'' +
                ", gid=" + gid +
                ", user_ID" + user_ID+
                ", commits=" + commits +
                ", pulls=" + pulls +
                ", contributors=" + contributors +
                ", size=" + size +
                ", forks=" + forks +
                ", stargazers_count=" + stargazers_count +
                ", watchers=" + watchers +
                ", open_issues=" + open_issues +
                ", closed_issues=" + closed_issues +
                '}';
    }

    public UserRepoInfo(String urid, String repoName, int gid, int size, int forks, int stargazers_count, int watchers, int open_issues) {
        this.uid = urid;
        this.repoName = repoName;
        this.gid = gid;
        this.size = size;
        this.forks = forks;
        this.stargazers_count = stargazers_count;
        this.watchers = watchers;
        this.open_issues = open_issues;
    }


//
//    @JsonCreator
//    public UserRepoInfo( @JsonProperty ("fork") boolean is_forked, @JsonProperty ("name") String name, @JsonProperty("commits_url") String commitsURL){
//        super();
//        this.is_forked = is_forked;
//        this.repoName= name;
//
//
//    }
//
//    public UserRepoInfo(int urid, int uid, long gid, @JsonProperty ("fork") boolean is_forked, int commits, int pulls, int issues, int contributors, long size, int forks, int stargazers_count, int watchers, int open_issues) {
//        this.urid = urid;
//        this.uid = uid;
//        this.gid = gid;
//        this.is_forked = is_forked;
//        this.commits = commits;
//        this.pulls = pulls;
//
//        this.issues = issues;
//        this.contributors = contributors;
//        this.size = size;
//        this.forks = forks;
//        this.stargazers_count = stargazers_count;
//        this.watchers = watchers;
//        this.open_issues = open_issues;
//    }



}
