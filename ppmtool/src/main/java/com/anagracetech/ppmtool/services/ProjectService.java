package com.anagracetech.ppmtool.services;

import com.anagracetech.ppmtool.domain.Project;
import com.anagracetech.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){

        //ToDo: Logics

        return projectRepository.save(project);
    }
}
