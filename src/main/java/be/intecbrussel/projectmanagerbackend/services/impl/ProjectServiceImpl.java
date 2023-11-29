package be.intecbrussel.projectmanagerbackend.services.impl;

import be.intecbrussel.projectmanagerbackend.exceptions.DataNotFoundException;
import be.intecbrussel.projectmanagerbackend.models.Project;
import be.intecbrussel.projectmanagerbackend.repositories.ProjectRepository;
import be.intecbrussel.projectmanagerbackend.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project addProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project getProjectById(long id) {
        return projectRepository.findById(id).orElseThrow(() ->
                new DataNotFoundException("project", "id", "id"));
    }

    @Override
    public List<Project> getAllProject() {
        return projectRepository.findAll();
    }

    @Override
    public Project updateProject(Project project, long id) {
        Project foundProject = projectRepository
                .findById(id).orElseThrow(() -> new DataNotFoundException("project", "id", "id"));
        foundProject.setName(project.getName());
        foundProject.setUsers(project.getUsers());
       // foundProject.setBoards(project.getBoards());

        return projectRepository.save(foundProject);
    }

    @Override
    public void deleteProject(long id) {
        projectRepository.deleteById(id);
    }
}
