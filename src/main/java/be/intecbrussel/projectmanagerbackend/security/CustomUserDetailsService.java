package be.intecbrussel.projectmanagerbackend.security;

import be.intecbrussel.projectmanagerbackend.models.User;
import be.intecbrussel.projectmanagerbackend.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Implementing this method allows customization of user retrieval logic, such as fetching
    // user details from a database or external source.
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> oUser = userRepository.findById(email);

        if (oUser.isEmpty()) {
            throw new UsernameNotFoundException(email + " not found.");
        }

        User user = oUser.get();

        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles("ADMIN")
                .build();

        return userDetails;
    }
}
