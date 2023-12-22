package be.intecbrussel.projectmanagerbackend.repositories;

import be.intecbrussel.projectmanagerbackend.models.Project;
import be.intecbrussel.projectmanagerbackend.models.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProjectRepositoryTest {
    private final ProjectRepository projectRepository;
    private final TestEntityManager testEntityManager;
    private Project project;
    private User user;
    @Autowired
    public ProjectRepositoryTest(ProjectRepository projectRepository,
                                 TestEntityManager testEntityManager) {
        this.projectRepository = projectRepository;
        this.testEntityManager = testEntityManager;
    }

    @BeforeEach
    void setUp() {
        user=new User("a@b.com","12344");
        project=new Project("new project",user);
    }

    @AfterEach
    void tearDown() {
        projectRepository.deleteAll();
    }
    @Test
    @Order(1)
    public void givenProjectId_whenFindByProject_thenReturnProjectObj() {

        //given
        testEntityManager.merge(user);
        testEntityManager.merge(project);
        testEntityManager.flush();
        testEntityManager.clear();

        //when
        Project foundProject = projectRepository.findById(project.getId()).get();

        //then
        assertThat(foundProject).isNotNull();

    }

    @Test
    @Order(2)
    public void givenProjectId_whenFindByProject_thenReturnProjectName() {

        //given
        testEntityManager.merge(user);
        testEntityManager.merge(project);
        testEntityManager.flush();
        testEntityManager.clear();

        //when
        Project foundProject = projectRepository.findById(project.getId()).get();

        //then
        assertThat(foundProject.getName()).isEqualTo("new project");
    }

    @Test
    @Order(3)
    public void givenProjectId_whenFindByProjectId_thenReturnUpdatedProjectObj() {

        //given
        testEntityManager.merge(user);
        testEntityManager.merge(project);
        testEntityManager.flush();
        testEntityManager.clear();

        //when
        Optional<Project> updatedProject = projectRepository.findById(project.getId());
        updatedProject.ifPresent(p -> p.setName("Updated project"));
        //then
        assertTrue(updatedProject.isPresent());
        assertThat(updatedProject.get().getName()).isEqualTo("Updated project");


    }


    @Test
    @Order(4)
    public void givenProjectId_whenDeleteByProjectId_thenReturnEmptyObject() {
        // Given
        projectRepository.save(project);

        // When
        projectRepository.deleteById(project.getId());

        Optional<Project> foundProject = projectRepository.findById(project.getId());

        // Then
        assertThat(foundProject).isEmpty();
    }

}