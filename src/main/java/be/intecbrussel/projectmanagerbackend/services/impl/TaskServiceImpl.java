package be.intecbrussel.projectmanagerbackend.services.impl;

import be.intecbrussel.projectmanagerbackend.models.Task;
import be.intecbrussel.projectmanagerbackend.services.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Override
    public Task addTask() {
        return null;
    }

    @Override
    public Task getTask() {
        return null;
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
        return null;
    }

    @Override
    public void deleteTask(Long taskID) {

    }
}
