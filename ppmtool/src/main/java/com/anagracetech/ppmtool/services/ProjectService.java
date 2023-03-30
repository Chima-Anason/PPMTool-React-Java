package com.anagracetech.ppmtool.services;

import com.anagracetech.ppmtool.domain.Project;
import com.anagracetech.ppmtool.exceptions.ProjectIdException;
import com.anagracetech.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){

        try{
            return projectRepository.save(project);
        }catch (Exception ex){
           throw new ProjectIdException("Project ID '"+ project.getProjectIdentifier()+"' already exist!");
        }

    }
}
