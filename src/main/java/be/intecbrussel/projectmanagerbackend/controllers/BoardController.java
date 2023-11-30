package be.intecbrussel.projectmanagerbackend.controllers;

import be.intecbrussel.projectmanagerbackend.models.Board;
import be.intecbrussel.projectmanagerbackend.models.Task;
import be.intecbrussel.projectmanagerbackend.services.impl.BoardServiceImpl;
import be.intecbrussel.projectmanagerbackend.services.impl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {

    private final BoardServiceImpl boardService;
    private final TaskServiceImpl taskService;

    @Autowired
    public BoardController(BoardServiceImpl boardService, TaskServiceImpl taskService) {
        this.boardService = boardService;
        this.taskService = taskService;
    }

    @PostMapping("/add")
    public ResponseEntity<Board> addBoard(@RequestParam("boardName") String name,
                                          @RequestParam("projectId") Long projectId) {

        Board board = boardService.addBoard(name, projectId);
        return new ResponseEntity<>(board, HttpStatus.CREATED);
    }

    @GetMapping("/get/{boardId}")
    public ResponseEntity<Board> getBoard(@PathVariable Long boardId) {

        Board foundBoard = boardService.getBoard(boardId);
        return ResponseEntity.ok(foundBoard);
    }

    @GetMapping("/get-by-project/{projectId}")
    public ResponseEntity<List<Board>> getAllBoardByProjectId(@PathVariable("projectId") Long projectId) {

        List<Board> foundBoards = boardService.getAllBoardsByProject(projectId);
        return ResponseEntity.ok(foundBoards);
    }

    @PutMapping("/update/{boardId}")
    public ResponseEntity<Board> updateBoard(@RequestBody Board board,
                                             @PathVariable("boardId") Long boardId) {

        Board updatedBoard = boardService.updateBoard(board, boardId);
        return ResponseEntity.ok(updatedBoard);
    }

    @DeleteMapping("/delete/{boardId}")
    public void deleteBoard(@PathVariable("boardId") Long boardId) {
        Board board = boardService.getBoard(boardId);

        List<Task> tasks = board.getTasks();
        for (Task task : tasks) {
            taskService.deleteTask(task.getId());
        }
        
        boardService.deleteBoard(boardId);
    }
}





