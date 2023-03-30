package com.anagracetech.ppmtool.controller;

import com.anagracetech.ppmtool.domain.Project;
import com.anagracetech.ppmtool.exceptions.ProjectIdException;
import com.anagracetech.ppmtool.services.MapValidationErrorService;
import com.anagracetech.ppmtool.services.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private MapValidationErrorService mapValidationErrorService;


    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){

        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
        if(errorMap!=null) return errorMap;

        Project project1 = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(project1,HttpStatus.CREATED);
    }

    @GetMapping("/{projectIdentifier}")
    public ResponseEntity<?> findProjectByProjectIdentifier(@PathVariable String projectIdentifier){

        Project project = projectService.findProjectByProjectIdentifier(projectIdentifier);

        return new ResponseEntity<Project>(project, HttpStatus.OK) ;
    }

    @GetMapping("/")
    public Iterable<Project> getAllProjects(){return projectService.getAllProjects();}

}
