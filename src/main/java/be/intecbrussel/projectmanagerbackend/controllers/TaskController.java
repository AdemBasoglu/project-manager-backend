package be.intecbrussel.projectmanagerbackend.controllers;

import be.intecbrussel.projectmanagerbackend.models.Task;
import be.intecbrussel.projectmanagerbackend.models.dto.TaskDto;
import be.intecbrussel.projectmanagerbackend.services.impl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskController {
    private final TaskServiceImpl taskService;

    @Autowired
    public TaskController(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/add")
    public ResponseEntity<Task> addTask(@RequestBody TaskDto taskDto, Long boardID) {
        return null;
    }
}
