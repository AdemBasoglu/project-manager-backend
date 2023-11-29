package be.intecbrussel.projectmanagerbackend.services.impl;

import be.intecbrussel.projectmanagerbackend.exceptions.DataNotFoundException;
import be.intecbrussel.projectmanagerbackend.models.Board;
import be.intecbrussel.projectmanagerbackend.models.Task;
import be.intecbrussel.projectmanagerbackend.models.User;
import be.intecbrussel.projectmanagerbackend.models.dto.TaskDto;
import be.intecbrussel.projectmanagerbackend.repositories.TaskRepository;
import be.intecbrussel.projectmanagerbackend.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final BoardServiceImpl boardService;
    private final UserServiceImpl userService;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, BoardServiceImpl boardService, UserServiceImpl userService) {
        this.taskRepository = taskRepository;
        this.boardService = boardService;
        this.userService = userService;
    }


    @Override
    public Task addTask(TaskDto taskDto, Long boardId) {

        Board foundBoard = boardService.getBoard(boardId);
        Task task = new Task(taskDto.name(), taskDto.description(), foundBoard);

        return taskRepository.save(task);
    }

    @Override
    public Task getTask(Long taskId) {
        return taskRepository.findById(taskId).orElseThrow(
                () -> new DataNotFoundException("Task", "Id", taskId.toString()));
    }

    @Override
    public List<Task> getTaskByUser() {
        return null;
    }

    @Override
    public List<Task> getTaskByBoard() {
        return null;
    }

    @Override
    public Task updateTask(Task task, Long taskId) {
        Task foundTask = taskRepository.findById(taskId).orElseThrow(
                () -> new DataNotFoundException("Task", "Id", taskId.toString()));

        foundTask.setName(task.getName());
        foundTask.setBoard(task.getBoard());
        foundTask.setUsers(task.getUsers());
        foundTask.setDescription(task.getDescription());
        foundTask.setLabel(task.getLabel());

        // for (User user : task.getUsers()) {
        //     userService.updateUser(user, user.getEmail());
        // }

        return taskRepository.save(foundTask);
    }

    @Override
    public Task addUserToTask(Long taskId, String email) {
        Task foundTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new DataNotFoundException("Task", "Id", taskId.toString()));

        User foundUser = userService.getUser(email);
        foundUser.getTasks().add(foundTask);
        foundTask.getUsers().add(foundUser);

        return foundTask;
    }

    @Override
    public Task changeBoard(Long boardId) {
        return null;
    }

    @Override
    public void deleteTask(Long taskId) {
        Task task = getTask(taskId);
        Set<User> users = task.getUsers();

        for (User user : users) {
            user.getTasks().remove(task);
        }

        taskRepository.deleteById(taskId);
    }

    @Override
    public void deleteAllByBoardId(Long boardId) {
        taskRepository.deleteAllByBoardId(boardId);
    }
}
