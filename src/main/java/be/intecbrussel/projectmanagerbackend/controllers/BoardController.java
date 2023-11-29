package be.intecbrussel.projectmanagerbackend.controllers;

import be.intecbrussel.projectmanagerbackend.models.Board;
import be.intecbrussel.projectmanagerbackend.models.Project;
import be.intecbrussel.projectmanagerbackend.services.impl.BoardServiceImpl;
import be.intecbrussel.projectmanagerbackend.services.impl.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
public class BoardController {
    private final BoardServiceImpl boardService;


    @Autowired
    public BoardController(BoardServiceImpl boardService) {
        this.boardService = boardService;

    }


    //POSTMAN OK

    @PostMapping("/add")
    public ResponseEntity<Board> addBoard(
            @RequestParam("boardName") String name,
            @RequestParam("projectID") Long projectId) {
        Board board = boardService.addBoard(name, projectId);
        return new ResponseEntity<>(board, HttpStatus.CREATED);

    }

    //POSTMAN OK

    @GetMapping("/get/{id}")
    public ResponseEntity<Board> getBoardById(@PathVariable Long id) {
        Board foundBoard = boardService.getBoardById(id);
        return new ResponseEntity<>(foundBoard, HttpStatus.OK);
    }

    @PutMapping("/update/{boardId}")
    public ResponseEntity<Board> updateBoard(
            @PathVariable("boardId") Long boardId,
            @RequestBody Board board) {
        Board updatedBoard = boardService.updateBoard(board, boardId);
        return new ResponseEntity<>(updatedBoard, HttpStatus.OK);

    }


}





