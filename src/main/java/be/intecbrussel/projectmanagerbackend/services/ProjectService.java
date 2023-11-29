package be.intecbrussel.projectmanagerbackend.services;

import be.intecbrussel.projectmanagerbackend.models.Project;

import java.util.List;

public interface ProjectService {

    Project addProject(String projectName, String userEmail);

    Project getProject(Long projectId);

    List<Project> getAllProjects();

    Project updateProject(String newName, Long projectId);

    void deleteProject(Long id);

}
