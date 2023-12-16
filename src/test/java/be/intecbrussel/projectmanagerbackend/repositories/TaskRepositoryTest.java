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
    private Task task;
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
        task = new Task("new task", "new", board);

    }

    @AfterEach
    void tearDown() {
        task = null;
    }

    @Test
    @Order(1)
    public void givenTaskId_whenFindByTask_thenReturnTaskObj() {
        //given
        Task savedTask = taskRepository.save(task);

        //when
        Optional<Task> foundTaskOptional = taskRepository.findById(savedTask.getId());

        //then
        assertTrue(foundTaskOptional.isPresent());
        Task foundTask = foundTaskOptional.get();
        assertThat(foundTask).isNotNull();
        assertThat(foundTask.getName()).isEqualTo(task.getName());
        assertThat(foundTask.getBoard().getId()).isEqualTo(board.getId());
    }

    @Test
    @Order(2)
    public void givenTaskId_whenFindByTask_thenUpdatedTaskObj() {
        //given
        Task savedTask = taskRepository.save(task);

        //when
        Optional<Task> updatedTask = taskRepository.findById(savedTask.getId());
        updatedTask.ifPresent(p -> p.setName("Updated project"));
        updatedTask.ifPresent(p -> p.setLabel(TaskLabel.GREEN));
        updatedTask.ifPresent(p -> p.setDescription("updated description"));
        System.out.println("updatedTask = " + updatedTask);

        //then
        assertThat(updatedTask.stream().filter(s -> s.getName().equals("Updated project")));
        assertThat(updatedTask.stream().filter(s -> s.getLabel().equals(TaskLabel.GREEN)));
        assertThat(updatedTask.stream().filter(s -> s.getDescription().equals("Updated description")));

    }


    @Test
    @Order(3)
    public void givenTaskId_whenFindByTask_thenDeleteTask() {
        //given
        Task savedTask = taskRepository.save(task);

        //when
        taskRepository.deleteById(savedTask.getId());
        Optional<Task> foundTask = taskRepository.findById(savedTask.getId());
        System.out.println("foundTask = " + foundTask);
        //then
        assertThat(foundTask).isEmpty();
    }
}