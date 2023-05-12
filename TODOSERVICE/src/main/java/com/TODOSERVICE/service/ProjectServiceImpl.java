package com.TODOSERVICE.service;

import com.TODOSERVICE.entites.Project;
import com.TODOSERVICE.entites.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void addProject(Project project) {
        try {
            ArrayList<Project> projects = readJsonFile();
            projects.add(project);
            writeJsonData(projects);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public List<Project> getAllProject() {
        ArrayList<Project> projects = readJsonFile();
//        http://localhost:8082/user/all
        User[] forObject = restTemplate.getForObject("http://localhost:5000/user/all", User[].class);
        List<User> collect = Arrays.stream(forObject).collect(Collectors.toList());
        ArrayList<Project> resultList = new ArrayList<>();
        for (Project project: projects
             ) {
            for (User user: collect
                 ) {
                if(user.getUserName().equals(project.getUserName())){
                    project.setUsers(user);
                    resultList.add(project);
                }
            }
        }

        return resultList;
    }

    @Override
    public Project getOneProject(String userName) {
        ArrayList<Project> projects = readJsonFile();
        Project project = (Project) projects.stream().filter(prj -> prj.getUserName().equals(userName)).findAny().orElse(null);
//       http://localhost:5000/user/Basu Tewary

        User forObject = restTemplate.getForObject("http://localhost:5000/user/"+userName, User.class);
        project.setUserName(forObject.getUserName());
        project.setUsers(forObject);
        return project;
    }

    public ArrayList<Project> readJsonFile(){
        ArrayList<Project> projects = new ArrayList<>();
        try {
            String jsonData = new String(Files.readAllBytes(Paths.get("project.json")));
            ObjectMapper objectMapper = new ObjectMapper();
            projects = objectMapper.readValue(jsonData, new TypeReference<ArrayList<Project>>() {
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
        return projects;
    }

    public void writeJsonData(List<Project> projectList){
        try{
            FileWriter file = new FileWriter("project.json");
            file.write(new Gson().toJson(projectList));
            file.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
