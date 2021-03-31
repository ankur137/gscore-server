package com.example.gscoreapp.user_data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@RestController
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    private static final HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();


    @RequestMapping("/gituser/{username}")
    public void addUser(@PathVariable String username) throws ExecutionException, InterruptedException, JsonProcessingException {
        System.out.println("Api hit");
        HttpRequest req = HttpRequest.newBuilder(URI.create("https://api.github.com/users/" + username))
                .GET().build();

        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());

        response.thenAccept(res -> System.out.println(res));

        ObjectMapper objectMapper = new ObjectMapper();
        UserInfo user = objectMapper.readValue(response.get().body(), UserInfo.class);
        userInfoService.addUser(user);
    }

    @GetMapping("/getuserinfo/{username}")
    public UserInfo getUserInfo(@PathVariable String username){
        UserInfo tes = userInfoService.getUser(username);
        return tes;
    }

}

