package be.intecbrussel.projectmanagerbackend.services;

import be.intecbrussel.projectmanagerbackend.models.Project;

public interface ProjectService {

    Project addProject(Project project);
    Project getProjectById(long id);


}
