package com.example.gscoreapp.user_repo_info;

import com.example.gscoreapp.user_data.UserInfo;
import com.example.gscoreapp.user_data.UserInfoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.json.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class UserRepoInfoService {
    private static final HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();

    @Autowired
    UserRepoInfoRepository userRepoInfoRepository;

    @Autowired
    UserInfoRepository userInfoRepository;


    public UserRepoInfo getAllUserRepos(String username){
        UserInfo userInfo = userInfoRepository.findByUsername(username);
        return userRepoInfoRepository.findUserRepoInfoByUid(userInfo.getUid());
    }

    public void addUserRepo(String userName) throws ExecutionException, InterruptedException, JsonProcessingException {

        HttpRequest req = HttpRequest.newBuilder(URI.create(String.format("https://api.github.com/users/"+userName+"/repos"))).setHeader("Authorization","token ed6128f8fbe95ad2fd353a7cbd9b50c7da17a866")
                .GET().build();

        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());

//        response.thenAccept(res -> System.out.println(res));

        ObjectMapper objectMapper = new ObjectMapper();
        List<UserRepoInfo> userReps = objectMapper.readValue(response.get().body(), new TypeReference<List<UserRepoInfo>>(){});
//        UserRepoInfo userRep = objectMapper.readValue(response.get().body(), UserRepoInfo.class);

        for(UserRepoInfo repo: userReps){
            repo.setCommits(getCommitCount(userName,repo));
            repo.setUser_ID(userInfoRepository.findByUsername(userName).getUid());
            int[] counts = getCount(userName,repo,State.closed);
            repo.setPulls(counts[0]);
            repo.setClosed_issues(counts[1]);
            int[] countsOpen = getCount(userName,repo,State.open);
            repo.setPulls(repo.getPulls()+countsOpen[0]);
            repo.setOpen_issues(countsOpen[1]);
            repo.setContributors(getAPICount(userName,repo,GitAPI.contributors));


        }


        for(UserRepoInfo repo: userReps){
            System.out.println(repo.toString());
        }

        userRepoInfoRepository.saveAll(userReps);
    }


    private int getCommitCount(String userName, UserRepoInfo repo){

        ProcessBuilder processBuilder = new ProcessBuilder("sh", "-c", "curl -u username:ed6128f8fbe95ad2fd353a7cbd9b50c7da17a866 -I -k \"https://api.github.com/repos/"+userName+"/"+repo.getRepoName()+"/commits?per_page=1\"|sed -n '/^[Ll]ink:/ s/.*\"next\".*page=\\([0-9]*\\).*\"last\".*/\\1/p'");
        // processBuilder.command(cmdList);//,"cloc-git git@github.com:ankur137/gscore-server.git"
        Process process;
        try {
            process = processBuilder.start();
            StringBuilder output = new StringBuilder();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            String line = reader.readLine();

            if(line.equals("null")){
                return 0;
            }
            else{
            return Integer.parseInt(line);
        }

        }
        catch (IOException e){
            e.printStackTrace();
        }
        return -1;
    }


    private enum State {
        closed, open
}

    private enum GitAPI {
        commits, contributors
    }



    private int getAPICount(String userName, UserRepoInfo repo, GitAPI gitAPI) throws ExecutionException, InterruptedException, JsonProcessingException {
        int apiCount = 0;
        int pageNumber=1;
        while(true) {
            HttpRequest req = HttpRequest.newBuilder(URI.create(String.format("https://api.github.com/repos/" + userName + "/" + repo.getRepoName() + "/"+gitAPI+"?per_page=100&page="+pageNumber))).setHeader("Authorization", "token ed6128f8fbe95ad2fd353a7cbd9b50c7da17a866")
                    .GET().build();

            CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());

//            response.thenAccept(res -> System.out.println(res));


            JSONArray jsonArray = new JSONArray(response.get().body());
            if(response.get().body().equals("[]")){
                break;
            }
            apiCount=apiCount+jsonArray.length();

            pageNumber++;
        }

        return apiCount;

    }

    private  int[] getCount(String userName, UserRepoInfo repo, State state) throws ExecutionException, InterruptedException {
        int pageNumber=1;
        int pullCount=0;
        int closedCount=0;
        while(true) {
            HttpRequest req = HttpRequest.newBuilder(URI.create(String.format("https://api.github.com/repos/"+userName+"/"+repo.getRepoName()+"/issues?state="+state+"&per_page=100&page="+pageNumber))).setHeader("Authorization","token ed6128f8fbe95ad2fd353a7cbd9b50c7da17a866")
                    .GET().build();

            CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());

//            response.thenAccept(res -> System.out.println(res));
            if(response.get().body().equals("[]")){
                break;
            }

            JSONArray jsonArray = new JSONArray(response.get().body());



            for(int i=0;i<jsonArray.length();i++){
                try {
                    JSONObject jsonObject= (JSONObject) jsonArray.getJSONObject(i).get("pull_request");
                    pullCount++;
                }
                catch (JSONException e){
                    closedCount++;
                }
            }

            pageNumber++;
        }
        int [] counts=new int[2];
        counts[0]=pullCount;
        counts[1]= closedCount;
        return counts;
    }


//
//    private int[] getCount1(String userName, UserRepoInfo repo, State state) throws ExecutionException, InterruptedException, JsonProcessingException {
//        HttpRequest req = HttpRequest.newBuilder(URI.create(String.format("https://api.github.com/repos/"+userName+"/"+repo.getRepoName()+"/issues?state="+state+"&per_page=100"))).setHeader("Authorization","token ed6128f8fbe95ad2fd353a7cbd9b50c7da17a866")
//                .GET().build();
//
//        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());
//
//        response.thenAccept(res -> System.out.println(res));
//
//
//        JSONArray jsonArray = new JSONArray(response.get().body());
//
//
//        int pullCount=0;
//        int closedCount=0;
//        for(int i=0;i<jsonArray.length();i++){
//            try {
//               JSONObject jsonObject= (JSONObject) jsonArray.getJSONObject(i).get("pull_request");
//               pullCount++;
//            }
//            catch (JSONException e){
//                closedCount++;
//            }
//        }
//        int [] counts=new int[2];
//        counts[0]=pullCount;
//        counts[1]= closedCount;
//        return counts;
//    }
}


