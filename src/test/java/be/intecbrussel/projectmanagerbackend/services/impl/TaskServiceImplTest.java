package be.intecbrussel.projectmanagerbackend.services.impl;

import be.intecbrussel.projectmanagerbackend.models.Board;
import be.intecbrussel.projectmanagerbackend.models.Project;
import be.intecbrussel.projectmanagerbackend.models.Task;
import be.intecbrussel.projectmanagerbackend.models.User;
import be.intecbrussel.projectmanagerbackend.models.dto.TaskDto;
import be.intecbrussel.projectmanagerbackend.repositories.TaskRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private BoardServiceImpl boardService;

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private TaskServiceImpl taskService;

    private User user;
    private Project project;
    private Board board;
    private Task task1;
    private Task task2;
    private TaskDto taskDto;

    @BeforeEach
    void setUp() {
        user = new User("john@intec.be", "1q2w3e4r", "John", "Doe");
        project = new Project("Week planner", user);
        board = new Board("Twice per week", project);
        task1 = new Task("Put the trash outside", "2 times per week", board);
        task2 = new Task("Check the post", "1 time per day", board);
        taskDto = new TaskDto("Put the trash outside", "2 times per week");
    }

    @AfterEach
    void tearDown() {
        taskRepository.deleteAll();
    }

    @Test
    public void givenTask_whenDeleteTask_thenReturnEmpty() {

        // given
        given(taskRepository.findById(task1.getId())).willReturn(Optional.ofNullable(task1));
        willDoNothing().given(taskRepository).deleteById(task1.getId());

        // when
        taskService.deleteTask(task1.getId());

        // then
        Mockito.verify(taskRepository, times(1)).deleteById(task1.getId());
        Mockito.verifyNoMoreInteractions(taskRepository);
    }
}
