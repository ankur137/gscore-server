package com.example.gscoreapp.user_data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;

import java.io.IOException;
import java.util.UUID;

public class UserInfoDeserializer extends StdDeserializer<UserInfo> {

  public UserInfoDeserializer() {
    this(null);
  }

  public UserInfoDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override public UserInfo deserialize(JsonParser jp, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    JsonNode node = jp.getCodec().readTree(jp);
    int gid = (Integer) ((IntNode) node.get("id")).numberValue();
    String name = node.get("name").asText();
    String login = node.get("login").asText();
    String avatar_url = node.get("avatar_url").asText();
    int followers = (Integer) ((IntNode) node.get("followers")).numberValue();
    int following = (Integer) ((IntNode) node.get("following")).numberValue();
    String location = node.get("location").asText();
    String created_at = node.get("created_at").asText();
    String updated_at = node.get("updated_at").asText();
    int public_repos = (Integer) ((IntNode) node.get("public_repos")).numberValue();
    int public_gists = (Integer) ((IntNode) node.get("public_gists")).numberValue();

    return new UserInfo(UUID.randomUUID().toString(),login,name,gid,avatar_url,followers,following,location,created_at,updated_at,public_repos,public_gists);
  }
}
