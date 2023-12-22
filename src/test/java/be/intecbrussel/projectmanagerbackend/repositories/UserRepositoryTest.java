package be.intecbrussel.projectmanagerbackend.repositories;

import be.intecbrussel.projectmanagerbackend.models.Board;
import be.intecbrussel.projectmanagerbackend.models.Project;
import be.intecbrussel.projectmanagerbackend.models.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserRepositoryTest {
    private final UserRepository userRepository;
    private final TestEntityManager testEntityManager;

    private User user1;
    private User user2;
    private Project project;
    private Board board;
    private final Long projectId = 1L;

    @Autowired
    UserRepositoryTest(UserRepository userRepository, TestEntityManager testEntityManager) {
        this.userRepository = userRepository;
        this.testEntityManager = testEntityManager;
    }

    @BeforeEach
    void setUp() {
        user1 = new User("a@b.com", "12345");
        user2 = new User("a@b.com", "12345");
        project = new Project(projectId, "new project", "new", user1);
        board = new Board("new board", project);

    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    @Order(1)
    public void givenUserEmail_whenFindByUser_thenReturnUser() {
        //given
        testEntityManager.merge(user1);
        testEntityManager.flush();
        testEntityManager.clear();

        //when
        User foundUser = userRepository.findByEmail(user1.getEmail()).get();

        //then
        assertThat(foundUser.getEmail()).isEqualTo(user1.getEmail());
    }

    @Test
    @Order(2)
    public void givenuserEmail_whenFoundUser_thenReturnUpdatedUser() {
        //given
        testEntityManager.merge(user1);
        testEntityManager.flush();
        testEntityManager.clear();

        //when
        User foundUser = userRepository.findByEmail(user1.getEmail()).get();
        foundUser.setPassword("54321");
        foundUser.setFirstName("adem");
        foundUser.setLastName("bas");
        User updatedUser = userRepository.save(foundUser);
        System.out.println("updatedUser = " + updatedUser);

        //then
        assertThat(updatedUser.getPassword()).isEqualTo("54321");
        assertThat(updatedUser.getFirstName()).isEqualTo("adem");
        assertThat(updatedUser.getLastName()).isEqualTo("bas");

    }

    @Test
    @Order(3)
    public void givenUserEmail_whenFindUser_thenDeleteUser() {
        //given
        User savedUser = testEntityManager.merge(user1);
        testEntityManager.flush();
        testEntityManager.clear();

        // When
        userRepository.deleteUserByEmail(savedUser.getEmail());

        // Then
        Optional<User> deletedUser = userRepository.findByEmail(savedUser.getEmail());
        assertThat(deletedUser).isEmpty();

    }

    @Test
    @Order(4)
    public void givenProjectId_whenFoundProject_thenReturnUserEmails() {
        //given
        testEntityManager.merge(user1);
        testEntityManager.merge(project);
        testEntityManager.merge(board);
        testEntityManager.flush();
        testEntityManager.clear();

        // When
        given(userRepository.findAllUserEmailsByProjectId(project.getId())).willReturn(List.of("a@b.com"));

        List<String> userEmails = userRepository.findAllUserEmailsByProjectId(project.getId());

        // then
        then(userEmails).isNotNull().isNotEmpty().contains("a@b.com");
    }

}