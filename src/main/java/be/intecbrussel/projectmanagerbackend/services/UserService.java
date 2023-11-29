package be.intecbrussel.projectmanagerbackend.services;


import be.intecbrussel.projectmanagerbackend.models.User;

public interface UserService {


    User addUser(User user);


    User updateUser(User user);


    void deleteUser(String email);


    User getUser(long id);


    User getUser(String email);

    User findByEmail(String email);
}






