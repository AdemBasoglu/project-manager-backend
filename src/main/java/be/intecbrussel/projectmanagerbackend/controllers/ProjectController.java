package be.intecbrussel.projectmanagerbackend.controllers;

import be.intecbrussel.projectmanagerbackend.models.Project;
import be.intecbrussel.projectmanagerbackend.services.impl.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/get-all")
    public ResponseEntity<List<Project>> getAllProject() {
        List<Project> foundProjects = projectService.getAllProject();
        return ResponseEntity.ok(foundProjects);
    }

    @PutMapping("/update-project/{id}")
    public ResponseEntity<Project> updateProject(@RequestBody Project project, long id) {
        Project updatedProject = projectService.updateProject(project, id);
        return ResponseEntity.ok(updatedProject);
    }
    @DeleteMapping("/delete-project/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable("id") long Id) {
        projectService.deleteProject(Id);
        return ResponseEntity.ok("Project is deleted succesfully with id : " + Id);
    }}
