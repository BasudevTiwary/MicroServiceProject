package com.userService2.UserService2.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.userService2.UserService2.entities.User;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public void addUser(User user) {
        ArrayList<User> users = readJsonData();
//        User user1 = users.stream().filter(usr -> usr.getUserName().equals(user.getUserName())).findAny().orElse(null);
        users.add(user);
        writeJsonData(users);
    }

    @Override
    public List<User> getAllUsers() {
        ArrayList<User> users = readJsonData();
        return users;
    }

//    @Override
//    public User getOneUser(int userId) {
//
//        ArrayList<User> users = readJsonData();
//        User user = users.stream().filter(ur -> ur.getId() == userId).findAny().orElse(null);
//        return user;
//    }

    @Override
    public User getOneUserByUserName(String userName) {
        ArrayList<User> users = readJsonData();
        User user = users.stream().filter(ur -> ur.getUserName().equals(userName)).findAny().orElse(null);
        return user;
    }


    private ArrayList<User> readJsonData() {
        ArrayList<User> users = new ArrayList<>();
        try {
            String jsonData = new String(Files.readAllBytes(Paths.get("user.json")));
            ObjectMapper objectMapper = new ObjectMapper();
            users=objectMapper.readValue(jsonData, new TypeReference<ArrayList<User>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;

    }
    private void writeJsonData(ArrayList<User> arrayList){
        try{
            FileWriter file = new FileWriter("user.json");
            file.write(new Gson().toJson(arrayList));
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
