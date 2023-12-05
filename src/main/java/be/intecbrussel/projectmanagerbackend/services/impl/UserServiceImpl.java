package be.intecbrussel.projectmanagerbackend.services.impl;

import be.intecbrussel.projectmanagerbackend.exceptions.DataNotFoundException;
import be.intecbrussel.projectmanagerbackend.models.User;
import be.intecbrussel.projectmanagerbackend.models.dto.LoginRequest;
import be.intecbrussel.projectmanagerbackend.models.dto.LoginResponse;
import be.intecbrussel.projectmanagerbackend.repositories.UserRepository;
import be.intecbrussel.projectmanagerbackend.security.JwtUtil;
import be.intecbrussel.projectmanagerbackend.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {

        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public User addUser(User user) {
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    @Override
    public User getUser(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new DataNotFoundException("User", "email", email));
    }

    @Override
    public List<String> getAllUserEmailsByProject(Long projectId) {
        return userRepository.findAllUserEmailsByProjectId(projectId);
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

    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        String email = authentication.getName();

        User user = new User(email, "");
        String token = jwtUtil.createToken(user);

        return new LoginResponse(email, token);
    }

}
