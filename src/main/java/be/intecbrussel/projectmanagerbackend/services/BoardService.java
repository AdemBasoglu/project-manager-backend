package be.intecbrussel.projectmanagerbackend.services;

import be.intecbrussel.projectmanagerbackend.models.Board;

import java.util.List;

public interface BoardService {

    Board addBoard(String name, Long boardId);

    Board getBoard(Long boardId);

    List<Board> getAllBoardsByProject(Long projectId);

    Board updateBoard(Board board, Long boardId);

    void deleteBoard(Long id);

}
