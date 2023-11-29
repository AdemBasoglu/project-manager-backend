package be.intecbrussel.projectmanagerbackend.services;

import be.intecbrussel.projectmanagerbackend.models.Task;
import be.intecbrussel.projectmanagerbackend.models.dto.TaskDto;

import java.util.List;

public interface TaskService {
    Task addTask(TaskDto taskDto, Long boardID);

    Task getTask();

    List<Task> getTaskByUser();

    List<Task> getTaskByBoard();

    Task updateTask(Task task, Long taskID);

    void deleteTask(Long taskID);


}
