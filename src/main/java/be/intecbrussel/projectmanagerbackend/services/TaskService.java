package be.intecbrussel.projectmanagerbackend.services;

import be.intecbrussel.projectmanagerbackend.models.Task;

import java.util.List;

public interface TaskService {
    Task addTask();

    Task getTask();

    List<Task> getTaskByUser();

    List<Task> getTaskByBoard();

    Task updateTask(Task task, Long taskID);

    void deleteTask(Long taskID);


}
