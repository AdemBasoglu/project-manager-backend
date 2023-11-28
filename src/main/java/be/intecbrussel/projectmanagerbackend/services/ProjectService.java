package be.intecbrussel.projectmanagerbackend.services;

import be.intecbrussel.projectmanagerbackend.models.Project;

import java.util.List;

public interface ProjectService {

    Project addProject(Project project);
    Project getProjectById(long id);
    List<Project> getAllProject();
    Project updateProject(Project project, long id);
    void deleteProject(long id);


}
