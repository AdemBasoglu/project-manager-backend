package be.intecbrussel.projectmanagerbackend.repositories;

import be.intecbrussel.projectmanagerbackend.models.Board;
import be.intecbrussel.projectmanagerbackend.models.Project;
import be.intecbrussel.projectmanagerbackend.models.Task;
import be.intecbrussel.projectmanagerbackend.models.User;
import be.intecbrussel.projectmanagerbackend.models.enums.TaskLabel;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TaskRepositoryTest {
    private final TaskRepository taskRepository;
    private final TestEntityManager testEntityManager;
    private Task task1;
    private Task task2;
    private User user;
    private Board board;
    private Project project;

    @Autowired
    public TaskRepositoryTest(TaskRepository taskRepository, TestEntityManager testEntityManager) {
        this.taskRepository = taskRepository;
        this.testEntityManager = testEntityManager;
    }

    @BeforeEach
    void setUp() {
        user = new User("a@b.com", "1234");
        project = new Project("new project", user);
        board = new Board("new board", project);
        task1 = new Task("first task", "first", board);
        task2 = new Task("second task", "second", board);

    }

    @AfterEach
    void tearDown() {
        taskRepository.deleteAll();
    }

    @Test
    @Order(1)
    public void givenTaskId_whenFindByTask_thenReturnTaskObj() {
        //given
        testEntityManager.merge(user);
        testEntityManager.merge(project);
        testEntityManager.merge(board);
        testEntityManager.merge(task1);
        testEntityManager.merge(task2);
        testEntityManager.flush();
        testEntityManager.clear();
        //when
        Optional<Task> foundTaskOptional = taskRepository.findById(task1.getId());

        //then
        assertTrue(foundTaskOptional.isPresent());
        Task foundTask = foundTaskOptional.get();
        assertThat(foundTask).isNotNull();
        assertThat(foundTask.getName()).isEqualTo(task1.getName());
        assertThat(foundTask.getBoard().getId()).isEqualTo(board.getId());
    }

    @Test
    @Order(2)
    public void givenTaskId_whenFindByTask_thenUpdatedTaskObj() {
        //given
        testEntityManager.merge(user);
        testEntityManager.merge(project);
        testEntityManager.merge(board);
        testEntityManager.merge(task1);
        testEntityManager.merge(task2);
        testEntityManager.flush();
        testEntityManager.clear();
        //when
        Task foundTask=taskRepository.findById(task1.getId()).get();
        foundTask.setName("Updated Task");
        foundTask.setLabel(TaskLabel.ORANGE);
        foundTask.setDescription("Updated Task Description");
        Task updatedTask=taskRepository.save(foundTask);

        //then
        assertThat(updatedTask.getName()).isEqualTo("Updated Task");
        assertThat(updatedTask.getDescription()).isEqualTo("Updated Task Description");
        assertThat(updatedTask.getLabel()).isEqualTo(TaskLabel.ORANGE);

    }


    @Test
    @Order(3)
    public void givenTaskId_whenFindByTask_thenDeleteTask() {
        //given
        testEntityManager.merge(user);
        testEntityManager.merge(project);
        testEntityManager.merge(board);
        testEntityManager.merge(task1);
        testEntityManager.flush();
        testEntityManager.clear();

        //when
        taskRepository.deleteById(task1.getId());
        Optional<Task> foundTask = taskRepository.findById(task1.getId());

        //then
        assertThat(foundTask).isEmpty();
    }
}