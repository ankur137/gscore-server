package com.example.gscoreapp.user_data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "github_user_basic_info")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = UserInfoDeserializer.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(length = 36, nullable = false, updatable = false)
    private String uid;
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
}
