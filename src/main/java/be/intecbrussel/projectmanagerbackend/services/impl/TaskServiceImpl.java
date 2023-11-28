package be.intecbrussel.projectmanagerbackend.services.impl;

import be.intecbrussel.projectmanagerbackend.models.Board;
import be.intecbrussel.projectmanagerbackend.models.Task;
import be.intecbrussel.projectmanagerbackend.models.dto.TaskDto;
import be.intecbrussel.projectmanagerbackend.repositories.TaskRepository;
import be.intecbrussel.projectmanagerbackend.services.BoardService;
import be.intecbrussel.projectmanagerbackend.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final BoardServiceImpl boardService;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, BoardServiceImpl boardService) {
        this.taskRepository = taskRepository;
        this.boardService = boardService;
    }

    @Override
    public Task addTask(TaskDto taskDto, Long boardID) {
        Board board = boardService.getBoardById(boardID);
        return null;
    }
]
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
