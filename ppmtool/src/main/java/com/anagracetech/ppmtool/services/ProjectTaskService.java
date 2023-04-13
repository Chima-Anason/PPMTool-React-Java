package com.anagracetech.ppmtool.services;

import com.anagracetech.ppmtool.domain.Backlog;
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

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask){

        //PT to be added to a specific Project, project != null, BL exist
        Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
        //Set BL to PT
        projectTask.setBacklog(backlog);
        //Get the backlog sequence
        Integer backlogSequence = backlog.getpTSequence();
        //Increase and Update backlog sequence
        backlogSequence++;
        backlog.setpTSequence(backlogSequence);

        //Add a ProjectTask sequence based on backlogSequence - We want our projectTask sequence to be like: IDPRO-1, IDPRO-2 ....100 101
        projectTask.setProjectSequence(projectIdentifier+"-"+backlogSequence);
        projectTask.setProjectIdentifier(projectIdentifier);

        //INITIAL priority when priority is null
        if(projectTask.getPriority() == null){
            projectTask.setPriority(3);
        }
        //INITIAL status when status is null
        if(projectTask.getStatus() == null || projectTask.getStatus() == ""){
            projectTask.setStatus("TO_DO");
        }
        return projectTaskRepository.save(projectTask);
    }
}
