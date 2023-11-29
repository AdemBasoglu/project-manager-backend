package be.intecbrussel.projectmanagerbackend.services;

import be.intecbrussel.projectmanagerbackend.models.User;

public interface UserService {

    User addUser(User user);

    User getUser(String email);

    User updateUser(User user, String email);

    void deleteUser(String email);
}






