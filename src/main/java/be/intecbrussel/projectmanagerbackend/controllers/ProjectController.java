package be.intecbrussel.projectmanagerbackend.controllers;

import be.intecbrussel.projectmanagerbackend.exceptions.ProjectException;
import be.intecbrussel.projectmanagerbackend.models.Project;
import be.intecbrussel.projectmanagerbackend.services.impl.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
public class ProjectController {
    private final ProjectServiceImpl projectService;

    @Autowired
    public ProjectController(ProjectServiceImpl projectService) {
        this.projectService = projectService;

    }
@PostMapping("/add-project")
    public ResponseEntity<Project> addProject(@RequestBody Project project) {
        project = projectService.addProject(project);
        return new ResponseEntity<>(project, HttpStatus.CREATED);
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<Project> getProject(@PathVariable("id") long projectId) {
        Project foundProject = projectService.getProjectById(projectId);
        return ResponseEntity.ok(foundProject);
    }
}
