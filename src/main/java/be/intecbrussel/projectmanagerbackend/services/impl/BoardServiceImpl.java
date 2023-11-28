package be.intecbrussel.projectmanagerbackend.services.impl;

import be.intecbrussel.projectmanagerbackend.models.Board;
import be.intecbrussel.projectmanagerbackend.models.Project;
import be.intecbrussel.projectmanagerbackend.repositories.BoardRepository;
import be.intecbrussel.projectmanagerbackend.services.BoardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    private  final BoardRepository boardRepository;

    private TaskServiceImpl taskService;
    private ProjectServiceImpl projectService;

    public BoardServiceImpl(BoardRepository boardRepository, TaskServiceImpl taskService, UserServiceImpl userService, ProjectServiceImpl projectService) {
        this.boardRepository = boardRepository;
        this.taskService = taskService;
        this.projectService = projectService;
    }


    @Override
    public Board addBoard(String name, Long projectId) {
        final var project = projectService.getProjectById(projectId);
        Board board = new Board(name, project);
        return boardRepository.save(board);


    }

    @Override
    public Board getBoardById(Long id) {
        return null;
    }

    @Override
    public Board updateBoard(Board board) {
        return null;
    }

    @Override
    public void deleteBoard(Long id) {

    }

    @Override
    public List<Board> getAllBoardsByProject(Long projectId) {
        return null;
    }
}






