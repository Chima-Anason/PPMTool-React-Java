package com.anagracetech.ppmtool.services;

import com.anagracetech.ppmtool.domain.Backlog;
import com.anagracetech.ppmtool.domain.Project;
import com.anagracetech.ppmtool.exceptions.ProjectIdException;
import com.anagracetech.ppmtool.repositories.BacklogRepository;
import com.anagracetech.ppmtool.repositories.ProjectRepository;
import org.springframework.stereotype.Service;


@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final BacklogRepository backlogRepository;

    public ProjectService(ProjectRepository projectRepository, BacklogRepository backlogRepository) {
        this.projectRepository = projectRepository;
        this.backlogRepository = backlogRepository;
    }

    public Project saveOrUpdateProject(Project project){

        try{

            if(project.getId() == null){
                Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            }

            if(project.getId()!= null){
                project.setBacklog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
            }
            return projectRepository.save(project);
        }catch (Exception ex){
           throw new ProjectIdException("Project ID '"+ project.getProjectIdentifier()+"' already exist!");
        }

    }


    public Project findProjectByProjectIdentifier(String projectIdentifier){

        Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
        if(project == null){
            throw new ProjectIdException("Project with ID '"+projectIdentifier+"' does not exist!");
        }
        return project;
    }

    public Iterable<Project> getAllProjects(){return projectRepository.findAll();}

    public void deleteProjectByProjectIdentifier(String projectIdentifier){
        Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
        if(project == null){
            throw new ProjectIdException("Cannot delete Project with ID: '"+projectIdentifier+"'. This project does not exist!");
        }

        projectRepository.delete(project);
    }
}
