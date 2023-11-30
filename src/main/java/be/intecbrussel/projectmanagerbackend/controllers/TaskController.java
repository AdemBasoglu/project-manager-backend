package be.intecbrussel.projectmanagerbackend.controllers;

import be.intecbrussel.projectmanagerbackend.models.Task;
import be.intecbrussel.projectmanagerbackend.models.dto.TaskDto;
import be.intecbrussel.projectmanagerbackend.services.impl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    private final TaskServiceImpl taskService;

    @Autowired
    public TaskController(TaskServiceImpl taskService) {

        this.taskService = taskService;
    }

    @PostMapping("/add")
    public ResponseEntity<Task> addTask(@RequestBody TaskDto taskDto,
                                        @RequestParam("boardId") Long boardId) {

        Task task = taskService.addTask(taskDto, boardId);
        return ResponseEntity.ok(task);
    }


    @GetMapping("/get/{taskId}")
    public ResponseEntity<Task> getTask(@PathVariable("taskId") Long taskId) {

        Task task = taskService.getTask(taskId);
        return ResponseEntity.ok(task);
    }

    @GetMapping("/get-by-user/{email}")
    public ResponseEntity<List<Task>> getTaskByUserEmail(@PathVariable("email") String email) {

        List<Task> taskByUser = taskService.getTaskByUserEmail(email);
        return ResponseEntity.ok(taskByUser);
    }

    @GetMapping("/get-by-board/{boardId}")
    public ResponseEntity<List<Task>> getTaskByUserEmail(@PathVariable("boardId") Long boardId) {

        List<Task> taskByUser = taskService.getTaskByBoardId(boardId);
        return ResponseEntity.ok(taskByUser);
    }

    @PutMapping("update/{taskId}")
    public ResponseEntity<Task> updateTask(@RequestBody Task task,
                                           @PathVariable("taskId") Long taskId) {

        Task updatedTask = taskService.updateTask(task, taskId);
        return ResponseEntity.ok(updatedTask);
    }

    @PutMapping("/add-user")
    public ResponseEntity<Task> addUserToTask(@RequestParam("taskId") Long taskId,
                                              @RequestParam("email") String email) {

        Task task = taskService.addUserToTask(taskId, email);
        return ResponseEntity.ok(task);
    }

    @PutMapping("/change-board")
    public ResponseEntity<Task> changeBoard(@RequestParam("taskId") Long taskId,
                                            @RequestParam("boardId") Long boardId) {
        
        Task task = taskService.changeBoard(taskId, boardId);
        return ResponseEntity.ok(task);
    }

    @PutMapping("/add-board")
    public ResponseEntity<Task> addToBoard(@RequestParam("taskId") Long taskId,
                                           @RequestParam("boardId") Long boardId) {

        Task task = taskService.addToBoard(taskId, boardId);
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/delete/{taskId}")
    public void deleteTask(@PathVariable("taskId") Long taskId) {

        taskService.deleteTask(taskId);
    }

}
