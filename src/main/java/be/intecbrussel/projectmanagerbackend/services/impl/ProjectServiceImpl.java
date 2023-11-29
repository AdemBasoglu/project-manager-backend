package be.intecbrussel.projectmanagerbackend.services.impl;

import be.intecbrussel.projectmanagerbackend.exceptions.DataNotFoundException;
import be.intecbrussel.projectmanagerbackend.models.Project;
import be.intecbrussel.projectmanagerbackend.models.User;
import be.intecbrussel.projectmanagerbackend.repositories.ProjectRepository;
import be.intecbrussel.projectmanagerbackend.services.ProjectService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserServiceImpl userService;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, UserServiceImpl userService) {

        this.projectRepository = projectRepository;
        this.userService = userService;
    }

    @Override
    public Project addProject(String projectName, String userEmail) {

        User user = userService.getUser(userEmail);
        Project project = new Project(projectName, user);
        user.getProjects().add(project);

        return projectRepository.save(project);
    }

    @Override
    public Project getProject(Long projectId) {

        return projectRepository.findById(projectId)
                .orElseThrow(() -> new DataNotFoundException("Project", "Id", projectId.toString()));
    }

    @Override
    public List<Project> getAllProjects() {

        return projectRepository.findAll();
    }

    @Override
    public Project updateProject(String newName, Long projectId) {

        Project foundProject = projectRepository
                .findById(projectId).orElseThrow(() -> new DataNotFoundException("Project", "Id", projectId.toString()));

        foundProject.setName(newName);

        return projectRepository.save(foundProject);
    }

    @Override
    public void deleteProject(Long projectId) {

        Project foundProject = getProject(projectId);

        // Removing the project from user where user contains the project.
        for (User user : foundProject.getUsers()) {
            user.getProjects().remove(foundProject);
        }

        projectRepository.deleteById(projectId);
    }

}
