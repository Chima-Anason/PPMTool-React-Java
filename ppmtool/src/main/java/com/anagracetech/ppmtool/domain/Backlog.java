package com.anagracetech.ppmtool.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Backlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer pTSequence = 0;
    private String projectIdentifier;

    //OneToOne with Backlog
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id", nullable = false)
    @JsonIgnore
    private Project project;

    //OneToMany with projectTask
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "backlog")
    private List<ProjectTask> projectTaskList = new ArrayList<>();

    public Backlog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getpTSequence() {
        return pTSequence;
    }

    public void setpTSequence(Integer pTSequence) {
        this.pTSequence = pTSequence;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<ProjectTask> getProjectTaskList() {
        return projectTaskList;
    }

    public void setProjectTaskList(List<ProjectTask> projectTaskList) {
        this.projectTaskList = projectTaskList;
    }
}
