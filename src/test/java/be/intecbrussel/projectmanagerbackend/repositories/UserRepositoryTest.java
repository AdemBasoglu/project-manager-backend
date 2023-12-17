package be.intecbrussel.projectmanagerbackend.repositories;

import be.intecbrussel.projectmanagerbackend.models.Project;
import be.intecbrussel.projectmanagerbackend.models.User;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserRepositoryTest {
    private final UserRepository userRepository;
    private final TestEntityManager testEntityManager;

    private User user;

    @Autowired
    UserRepositoryTest(UserRepository userRepository, TestEntityManager testEntityManager) {
        this.userRepository = userRepository;
        this.testEntityManager = testEntityManager;
    }

    @BeforeEach
    void setUp() {
        user = new User("a@b.com", "12345");

    }

    @AfterEach
    void tearDown() {
        user = null;
    }

    @Test
    public void givenUserEmail_whenFindByUser_thenReturnUser() {
        //given
        User savedUser = userRepository.save(user);

        //when
        Optional<User> foundUser = userRepository.findByEmail(savedUser.getEmail());

        //then
        assertThat(foundUser.filter(s -> s.getEmail().equals("a@b.com")));
    }

    @Test
    public void givenuserEmail_whenFoundUser_thenReturnUpdatedUser() {
        //given
        User savedUser = userRepository.save(user);

        //when
        User foundUser = userRepository.findByEmail(savedUser.getEmail()).get();
        foundUser.setPassword("54321");
        foundUser.setFirstName("adem");
        foundUser.setLastName("bas");
        User updatedUser = userRepository.save(foundUser);
        System.out.println("updatedUser = " + updatedUser);

        //then
        assertThat(updatedUser.getPassword().equals("54321"));
        assertThat(updatedUser.getFirstName().equals("adem"));
        assertThat(updatedUser.getLastName().equals("bas"));

    }

    @Test
    @Transactional
    public void givenUserEmail_whenFindUser_thenDeleteUser() {
        //given
        User savedUser = testEntityManager.merge(user);
        testEntityManager.flush();
        testEntityManager.clear();

        // When
        userRepository.deleteUserByEmail(savedUser.getEmail());

        // Then
        Optional<User> deletedUser = userRepository.findByEmail(savedUser.getEmail());
        assertThat(deletedUser).isEmpty();

    }

}