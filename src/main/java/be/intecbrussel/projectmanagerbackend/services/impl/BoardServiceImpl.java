package be.intecbrussel.projectmanagerbackend.services.impl;

import be.intecbrussel.projectmanagerbackend.exceptions.DataNotFoundException;
import be.intecbrussel.projectmanagerbackend.models.Board;
import be.intecbrussel.projectmanagerbackend.models.Project;
import be.intecbrussel.projectmanagerbackend.repositories.BoardRepository;
import be.intecbrussel.projectmanagerbackend.services.BoardService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final ProjectServiceImpl projectService;

    public BoardServiceImpl(BoardRepository boardRepository, ProjectServiceImpl projectService) {

        this.boardRepository = boardRepository;
        this.projectService = projectService;
    }

    @Override
    public Board addBoard(String name, Long projectId) {

        Project foundProject = projectService.getProject(projectId);
        Board board = new Board(name, foundProject);
        return boardRepository.save(board);
    }

    @Override
    public Board getBoard(Long boardId) {

        return boardRepository.findById(boardId)
                .orElseThrow(() -> new DataNotFoundException("Board", "Id", boardId.toString()));
    }

    @Override
    public List<Board> getAllBoardsByProject(Long projectId) {

        return boardRepository.findAllByProjectId(projectId);
    }

    @Override
    public Board updateBoard(Board board, Long boardId) {

        Board foundBoard = boardRepository.findById(boardId)
                .orElseThrow(() -> new DataNotFoundException("Board", "Id", boardId.toString()));

        foundBoard.setName(board.getName());
        foundBoard.setTasks(board.getTasks());
        foundBoard.setProject(board.getProject());

        return boardRepository.save(foundBoard);
    }

    @Override
    public void deleteBoard(Long boardId) {

        getBoard(boardId);
        boardRepository.deleteById(boardId);
    }
}






