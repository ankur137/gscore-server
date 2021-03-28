package com.example.gscoreapp.user_data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "github_user_basic_info")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfo {
    @Id
    private int uid;
    private String username;
    private String name;
    private long gid;
    private String avatar_url;
    private int followers;
    private int following;
    private String location;
    private String created_at;
    private String updated_at;
    private int public_repos;
    private int public_gists;


    public UserInfo(@JsonProperty("id") int gid,
                    @JsonProperty("name") String name, @JsonProperty("avatar_url") String avatar_url,
                    @JsonProperty("login") String username, @JsonProperty("followers") int followers,
                    @JsonProperty("following") int following, @JsonProperty("location") String location,
                    @JsonProperty("created_at") String created_at, @JsonProperty("updated_at") String updated_at,
                    @JsonProperty("public_repos") int public_repos,
                    @JsonProperty("public_gists") int public_gists) {
//    public UserInfo(int uid, String login, String name, long gid, String avatar_url, int followers,
//                    int following, String location, String created_at, String updated_at, int public_repos,
//                    int public_gists) {
        this.uid = uid;
        this.username = username;
        this.name = name;
        this.gid = gid;
        this.avatar_url = avatar_url;
        this.followers = followers;
        this.following = following;
        this.location = location;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.public_repos = public_repos;
        this.public_gists = public_gists;
    }

    public UserInfo(){

    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getGid() {
        return gid;
    }

    public void setGid(long gid) {
        this.gid = gid;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public int getPublic_repos() {
        return public_repos;
    }

    public void setPublic_repos(int public_repos) {
        this.public_repos = public_repos;
    }

    public int getPublic_gists() {
        return public_gists;
    }

    public void setPublic_gists(int public_gists) {
        this.public_gists = public_gists;
    }
}
