package com.example.gscoreapp.user_repo_info;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.UUID;

public class UserRepoDeserializer extends StdDeserializer<UserRepoInfo> {
    public UserRepoDeserializer(){
        this(null);
    }
    public UserRepoDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public UserRepoInfo deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        JsonNode node = p.getCodec().readTree(p);
        String urid= UUID.randomUUID().toString();
        String repoName = node.get("name").asText();
        int gid = (int) ( node.get("id")).numberValue();
        boolean is_forked = node.get("fork").booleanValue();
        int size= (Integer) node.get("size").numberValue();
        int forks = (Integer) node.get("forks").numberValue();
        int stargazers_count = (Integer) node.get("stargazers_count").numberValue();
        int watchers= (Integer) node.get("watchers_count").numberValue();
        int open_issues= (Integer) node.get("open_issues_count").numberValue();


        return new UserRepoInfo(urid,repoName,gid, size,forks,stargazers_count,watchers,open_issues);
    }
}
