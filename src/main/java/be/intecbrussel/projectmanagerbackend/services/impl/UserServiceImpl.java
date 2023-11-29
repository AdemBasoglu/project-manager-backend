package be.intecbrussel.projectmanagerbackend.services.impl;

import be.intecbrussel.projectmanagerbackend.exceptions.DataNotFoundException;
import be.intecbrussel.projectmanagerbackend.exceptions.GlobalExceptionHandler;
import be.intecbrussel.projectmanagerbackend.models.User;
import be.intecbrussel.projectmanagerbackend.repositories.UserRepository;
import be.intecbrussel.projectmanagerbackend.services.UserService;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User addUser(User user) {

        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }


        User savedUser = userRepository.save(user);
        return savedUser;


    }

    @Override
    public User updateUser(User user) {
        return null;
    }


    @Override
    public void deleteUser(String email) {

        if (email == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }
        if (userRepository.existsById(email)) {
            userRepository.deleteById(email);
        } else {
            throw new IllegalArgumentException("Email does not exist");
        }
    }

    @Override
    public User getUser(long id) {
        return null;
    }

    @Override
    public User getUser(String email) {
        return null;
    }


    @Override
    public User findByEmail(String email) {
        // User foundUser = userRepository.findByEmail(email)
        //         .orElseThrow(() -> new DataNotFoundException("User not found" + email));
        // return foundUser;

        return null;
    }
}
