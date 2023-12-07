package be.intecbrussel.projectmanagerbackend.controllers;

import be.intecbrussel.projectmanagerbackend.models.User;
import be.intecbrussel.projectmanagerbackend.models.dto.LoginRequest;
import be.intecbrussel.projectmanagerbackend.models.dto.LoginResponse;
import be.intecbrussel.projectmanagerbackend.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {

        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<User> createUser(@RequestBody User user) {

        user = userService.addUser(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/get/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {

        User foundUser = userService.getUser(email);
        return ResponseEntity.ok(foundUser);
    }

    @GetMapping("/get-by-project/{projectId}")
    public ResponseEntity<List<String>> getAllUserEmailsByProject(@PathVariable Long projectId) {

        List<String> foundUsers = userService.getAllUserEmailsByProject(projectId);
        return ResponseEntity.ok(foundUsers);
    }

    @GetMapping("/get-by-task/{taskId}")
    public ResponseEntity<List<String>> getAllUserEmailsByTask(@PathVariable Long taskId) {

        List<String> foundUsers = userService.getAllUserEmailsByTask(taskId);
        return ResponseEntity.ok(foundUsers);
    }

    @PutMapping("/update/{email}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable String email) {

        User updatedUser = userService.updateUser(user, email);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete/{email}")
    public void deleteUser(@PathVariable String email) {

        userService.deleteUser(email);
    }

}
