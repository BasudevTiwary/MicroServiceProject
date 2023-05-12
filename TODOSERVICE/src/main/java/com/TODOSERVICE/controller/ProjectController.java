package com.TODOSERVICE.controller;

import com.TODOSERVICE.entites.Project;
import com.TODOSERVICE.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @PostMapping("/addProject")
    public ResponseEntity<String> addProject(@RequestBody Project project){
        projectService.addProject(project);
        ResponseEntity<String> recordHasBeenAdded = new ResponseEntity<>("record has been added", HttpStatus.CREATED);
        return recordHasBeenAdded;
    }
    @GetMapping("/allProjects")
    public ResponseEntity<List<Project>> getAllProject(){
        List<Project> projectList = projectService.getAllProject();
        ResponseEntity<List<Project>> listResponseEntity = new ResponseEntity<>(projectList, HttpStatus.OK);
        return listResponseEntity;

    }
    @GetMapping("/{userName}")
    public ResponseEntity<Project> getOneProject(@PathVariable("userName") String userName){
        Project project = projectService.getOneProject(userName);
//     http://localhost:8082/user/Basu Tewary
        ResponseEntity<Project> projectResponseEntity = new ResponseEntity<>(project, HttpStatus.OK);
        return projectResponseEntity;
    }





}
