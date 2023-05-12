package com.TODOSERVICE.entites;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.beans.Transient;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Project {
    private int projectId;
    private String domain;
    private String projectName;
    private String projectDuration;
    private String userName;

    private User users;
}
