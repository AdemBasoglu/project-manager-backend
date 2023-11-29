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
    public ResponseEntity<Task> addTask(
            @RequestBody TaskDto taskDto, @RequestParam("boardID") Long boardID) {
        Task task = taskService.addTask(taskDto, boardID);

        return ResponseEntity.ok(task);
    }


    @GetMapping("/get/{taskID}")
    public ResponseEntity<Task> getTask(@PathVariable("taskID") Long taskID) {
        Task task = taskService.getTask(taskID);

        return ResponseEntity.ok(task);
    }

    @PutMapping("update/{taskID}")
    public ResponseEntity<Task> updateTask(
            @RequestBody Task task,
            @PathVariable("taskID") Long taskID
    ) {
        Task updatedTask = taskService.updateTask(task, taskID);

        return ResponseEntity.ok(updatedTask);
    }

    @PutMapping("/add-user")
    public ResponseEntity<Task> addUserToTask(
            @RequestParam("taskID") Long taskID,
            @RequestParam("email") String email) {
        Task task = taskService.addUserToTask(taskID, email);

        return ResponseEntity.ok(task);
    }

    @DeleteMapping("delete/{taskID}")
    public void deleteTask(@PathVariable("taskID") Long taskID) {
        taskService.deleteTask(taskID);
    }

    @DeleteMapping("delete-by-board/{boardID}")
    public void deleteTaskByBoard(@PathVariable("boardID") Long boardID) {
        taskService.deleteAllByBoardId(boardID);
    }
}
