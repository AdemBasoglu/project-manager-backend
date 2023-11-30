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

    @PostMapping("/add")
    public ResponseEntity<Project> addProject(@RequestParam("projectName") String projectName,
                                              @RequestParam("userEmail") String email) {

        Project project = projectService.addProject(projectName, email);
        return new ResponseEntity<>(project, HttpStatus.CREATED);
    }


    @GetMapping("/get/{projectId}")
    public ResponseEntity<Project> getProject(@PathVariable Long projectId) {

        Project foundProject = projectService.getProject(projectId);
        return ResponseEntity.ok(foundProject);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Project>> getAllProject() {

        List<Project> foundProjects = projectService.getAllProjects();
        return ResponseEntity.ok(foundProjects);
    }

    @PutMapping("/update")
    public ResponseEntity<Project> updateProject(@RequestParam("newName") String newName,
                                                 @RequestParam("id") Long projectId) {
        Project updatedProject = projectService.updateProject(newName, projectId);
        return ResponseEntity.ok(updatedProject);
    }

    @PutMapping("/update/add-user")
    public ResponseEntity<Project> addUserToProject(@RequestParam("id") Long projectId,
                                                    @RequestParam("email") String email) {

        Project updatedProject = projectService.addUserToProject(projectId, email);
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/delete/{projectId}")
    public void deleteProject(@PathVariable Long projectId) {

        projectService.deleteProject(projectId);
    }

}
