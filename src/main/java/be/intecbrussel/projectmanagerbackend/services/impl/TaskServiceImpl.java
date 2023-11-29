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

import java.util.List;

@Service
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
    public Task addTask(TaskDto taskDto, Long boardID) {
        Board foundBoard = boardService.getBoardById(boardID);
        Task task = new Task(taskDto.name(), taskDto.description(), foundBoard);

        return taskRepository.save(task);
    }

    @Override
    public Task getTask(Long taskID) {
        return taskRepository.findById(taskID).orElseThrow(
                () -> new DataNotFoundException("Task", "ID", taskID.toString()));
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
    public Task updateTask(Task task, Long taskID) {
        Task foundTask = taskRepository.findById(taskID).orElseThrow(
                () -> new DataNotFoundException("Task", "ID", taskID.toString()));

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
    public Task addUserToTask(Long taskID, String email) {
        Task foundTask = taskRepository.findById(taskID).orElseThrow(
                () -> new DataNotFoundException("Task", "ID", taskID.toString()));

        User foundUser = userService.findByEmail(email);
        foundUser.getTasks().add(foundTask);

        return null;
    }

    @Override
    public void deleteTask(Long taskID) {
        taskRepository.deleteById(taskID);
    }
}
