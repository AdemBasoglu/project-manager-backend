package be.intecbrussel.projectmanagerbackend.controllers;

import be.intecbrussel.projectmanagerbackend.models.Board;
import be.intecbrussel.projectmanagerbackend.services.impl.BoardServiceImpl;
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

    @PostMapping("/add")
    public ResponseEntity<Board> addBoard(
            @RequestParam("boardName") String name,
            @RequestParam("projectID") Long projectId) {
        Board board = boardService.addBoard(name, projectId);
        return new ResponseEntity<>(board, HttpStatus.CREATED);

    }


    @PostMapping("/add-path/board{boardName}/project/{projectID}")
    public ResponseEntity<Board> addBoardPath(
            @PathVariable("boardName") String name,
            @PathVariable("projectID") Long projectId) {
        Board board = boardService.addBoard(name, projectId);
        return new ResponseEntity<>(board, HttpStatus.CREATED);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Board> getBoardById(@PathVariable Long id) {
        Board foundBoard = boardService.getBoardById(id);
        return new ResponseEntity<>(foundBoard, HttpStatus.OK);
    }
}
