package com.anagracetech.ppmtool.services;

import com.anagracetech.ppmtool.domain.ProjectTask;
import com.anagracetech.ppmtool.repositories.BacklogRepository;
import com.anagracetech.ppmtool.repositories.ProjectTaskRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {

    private final ProjectTaskRepository projectTaskRepository;
    private final BacklogRepository backlogRepository;

    public ProjectTaskService(ProjectTaskRepository projectTaskRepository, BacklogRepository backlogRepository) {
        this.projectTaskRepository = projectTaskRepository;
        this.backlogRepository = backlogRepository;
    }

    public ProjectTask addProjectTask(){

        //PT to be added to a specific Project, project != null, BL exist
        //Set BL to PT
        //We want our project sequence to be like: IDPRO-1, IDPRO-2 ....100 101
        //Update Backlog sequence

        //INITIAL priority when priority is null
        //INITIAL status when status is null
        return null;
    }
}
