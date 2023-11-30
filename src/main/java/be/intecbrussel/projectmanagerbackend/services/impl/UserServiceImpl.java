package be.intecbrussel.projectmanagerbackend.services.impl;

import be.intecbrussel.projectmanagerbackend.exceptions.DataNotFoundException;
import be.intecbrussel.projectmanagerbackend.models.User;
import be.intecbrussel.projectmanagerbackend.repositories.UserRepository;
import be.intecbrussel.projectmanagerbackend.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public User addUser(User user) {

        return userRepository.save(user);
    }

    @Override
    public User getUser(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new DataNotFoundException("User", "email", email));
    }

    @Override
    public User updateUser(User user, String email) {

        User foundUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new DataNotFoundException("User", "email", email));

        foundUser.setFirstName(user.getFirstName());
        foundUser.setLastName(user.getLastName());
        foundUser.setPassword(user.getPassword());
        foundUser.setTasks(user.getTasks());

        return foundUser;
    }

    @Override
    public void deleteUser(String email) {
        
        getUser(email);
        userRepository.deleteById(email);
    }
}
