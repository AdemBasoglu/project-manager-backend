package be.intecbrussel.projectmanagerbackend.services;

import be.intecbrussel.projectmanagerbackend.models.User;

import java.util.List;

public interface UserService {

    User addUser(User user);

    User getUser(String email);

    List<String> getAllUserEmailsByProject(Long projectId);

    List<String> getAllUserEmailsByTask(Long taskId);

    User updateUser(User user, String email);

    void deleteUser(String email);
}






