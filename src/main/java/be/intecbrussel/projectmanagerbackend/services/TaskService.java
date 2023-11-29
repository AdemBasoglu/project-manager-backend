package be.intecbrussel.projectmanagerbackend.services;

import be.intecbrussel.projectmanagerbackend.models.Task;
import be.intecbrussel.projectmanagerbackend.models.dto.TaskDto;

import java.util.List;

public interface TaskService {
    Task addTask(TaskDto taskDto, Long boardID);

    Task getTask(Long taskID);

    List<Task> getTaskByUser();

    List<Task> getTaskByBoard();

    Task updateTask(Task task, Long taskID);

    Task addUserToTask(Long taskID, String email);


    Task changeBoard(Long boardID);

    void deleteTask(Long taskID);

    void deleteAllByBoardId(Long boardID);

}
