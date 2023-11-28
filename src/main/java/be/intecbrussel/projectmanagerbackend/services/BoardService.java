package be.intecbrussel.projectmanagerbackend.services;

import be.intecbrussel.projectmanagerbackend.models.Board;

import java.util.List;

public interface BoardService {

    public void addBoard(Board board);

    Board getBoardById(Long id);

    Board updateBoard(Board board);

    void deleteBoard(Long id);

    List<Board> getAllBoardsByProject(Long projectId);






}
