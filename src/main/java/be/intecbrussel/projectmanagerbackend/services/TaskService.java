package be.intecbrussel.projectmanagerbackend.services;

import be.intecbrussel.projectmanagerbackend.models.Task;
import be.intecbrussel.projectmanagerbackend.models.dto.TaskDto;

import java.util.List;

public interface TaskService {

    Task addTask(TaskDto taskDto, Long boardId);

    Task getTask(Long taskId);

    List<Task> getTaskByUserEmail(String email);

    List<Task> getTaskByBoardId(Long boardId);

    Task updateTask(Task task, Long taskId);

    Task addUserToTask(Long taskId, String email);

    Task changeBoard(Long taskId, Long boardId);
    
    void deleteTask(Long taskId);

}
