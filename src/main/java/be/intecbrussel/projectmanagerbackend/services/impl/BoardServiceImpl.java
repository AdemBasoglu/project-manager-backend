package be.intecbrussel.projectmanagerbackend.services.impl;

import be.intecbrussel.projectmanagerbackend.exceptions.DataNotFoundException;
import be.intecbrussel.projectmanagerbackend.models.Board;
import be.intecbrussel.projectmanagerbackend.models.Project;
import be.intecbrussel.projectmanagerbackend.repositories.BoardRepository;
import be.intecbrussel.projectmanagerbackend.services.BoardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private ProjectServiceImpl projectService;

    public BoardServiceImpl(BoardRepository boardRepository, ProjectServiceImpl projectService) {
        this.boardRepository = boardRepository;

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
        Board foundboard = boardRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("board", "id", id.toString()));
        return foundboard;
    }

    @Override
    public Board updateBoard(Board board, long boardId) {
        Board foundBoard = boardRepository
                .findById(boardId)
                .orElseThrow(() -> new DataNotFoundException("board", "id", board.getId().toString()));
        foundBoard.setName(board.getName());
        return boardRepository.save(foundBoard);

    }


    @Override
    public void deleteBoard(Long boardId) {
        Board foundBoard = boardRepository.findById(boardId)
                .orElseThrow(() -> new DataNotFoundException("board", "id", boardId.toString()));

        boardRepository.delete(foundBoard);
    }

    @Override
    public List<Board> getAllBoardsByProject(Long projectId) {
        return null;
    }
}






