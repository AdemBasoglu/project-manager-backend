package be.intecbrussel.projectmanagerbackend.services.impl;

import be.intecbrussel.projectmanagerbackend.exceptions.DataNotFoundException;
import be.intecbrussel.projectmanagerbackend.models.User;
import be.intecbrussel.projectmanagerbackend.models.dto.LoginRequest;
import be.intecbrussel.projectmanagerbackend.models.dto.LoginResponse;
import be.intecbrussel.projectmanagerbackend.repositories.UserRepository;
import be.intecbrussel.projectmanagerbackend.security.JwtUtil;
import be.intecbrussel.projectmanagerbackend.services.impl.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private UserServiceImpl userService;

    private User existingUser;

    @BeforeEach
    void setUp() {
        existingUser = new User("john@intec.be", "password", "John", "Doe");
    }

    @AfterEach
    void tearDown() {
        reset(userRepository);
    }

    @Test
    public void givenValidUser_whenAddUser_thenUserIsSaved() {
        // given
        User user = new User("john@intec.be", "password", "John", "Doe");
        given(bCryptPasswordEncoder.encode(user.getPassword())).willReturn("encodedPassword");
        given(userRepository.save(any(User.class))).willReturn(user);

        // when
        User savedUser = userService.addUser(user);

        // then
        assertNotNull(savedUser);
        assertEquals("encodedPassword", savedUser.getPassword());
        assertEquals("John", savedUser.getFirstName());
        assertEquals("Doe", savedUser.getLastName());
        assertEquals("john@intec.be", savedUser.getEmail());
    }

    @Test
    public void givenExistingEmail_whenGetUser_thenUserIsReturned() {
        // given
        given(userRepository.findByEmail("john@intec.be")).willReturn(Optional.of(existingUser));

        // when
        User foundUser = userService.getUser("john@intec.be");

        // then
        assertNotNull(foundUser);
        assertEquals("John", foundUser.getFirstName());
        assertEquals("Doe", foundUser.getLastName());
        assertEquals("john@intec.be", foundUser.getEmail());
    }

    @Test
    public void givenNonExistingEmail_whenGetUser_thenThrowDataNotFoundException() {
        // given
        given(userRepository.findByEmail("nonexistent@intec.be")).willReturn(Optional.empty());

        // when, then
        assertThrows(DataNotFoundException.class, () -> userService.getUser("nonexistent@intec.be"));
    }

    @Test
    public void givenValidUser_whenUpdateUser_thenUserIsUpdated() {
        // given
        User userToUpdate = new User("john@intec.be", "newpassword", "Updated", "User");
        given(userRepository.findByEmail("john@intec.be")).willReturn(Optional.of(existingUser));
        given(userRepository.save(existingUser)).willReturn(existingUser);

        // when
        User updatedUser = userService.updateUser(userToUpdate, "john@intec.be");

        // then
        assertNotNull(updatedUser);
        assertEquals("Updated", updatedUser.getFirstName());
        assertEquals("User", updatedUser.getLastName());
        assertEquals("john@intec.be", updatedUser.getEmail());
        // Diğer özellikleri kontrol etmek için ekleyebilirsiniz.
    }

    @Test
    public void givenExistingEmail_whenDeleteUser_thenUserIsDeleted() {
        // given
        given(userRepository.findByEmail("john@intec.be")).willReturn(Optional.of(existingUser));

        // when
        assertDoesNotThrow(() -> userService.deleteUser("john@intec.be"));

        // then
        verify(userRepository, times(1)).deleteById("john@intec.be");
    }

    @Test
    public void givenNonExistingEmail_whenDeleteUser_thenThrowDataNotFoundException() {
        // given
        given(userRepository.findByEmail("nonexistent@intec.be")).willReturn(Optional.empty());

        // when, then
        assertThrows(DataNotFoundException.class, () -> userService.deleteUser("nonexistent@intec.be"));
    }

    @Test
    public void givenValidLoginRequest_whenLogin_thenLoginResponseIsReturned() {
        // given
        LoginRequest loginRequest = new LoginRequest("john@intec.be", "password");
        Authentication authentication = mock(Authentication.class);
        UserDetails userDetails = mock(UserDetails.class);
        given(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).willReturn(authentication);
        given(authentication.getName()).willReturn("john@intec.be");
        given(jwtUtil.createToken(any(User.class))).willReturn("token");

        // when
        LoginResponse loginResponse = userService.login(loginRequest);

        // then
        assertNotNull(loginResponse);
        assertEquals("john@intec.be", loginResponse.getEmail());
        assertEquals("token", loginResponse.getToken());
    }


}
