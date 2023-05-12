package com.TODOSERVICE.service;

import com.TODOSERVICE.entites.Project;

import java.util.List;

public interface ProjectService {
    void addProject(Project project);

    List<Project> getAllProject();

    Project getOneProject(String userName);
}
