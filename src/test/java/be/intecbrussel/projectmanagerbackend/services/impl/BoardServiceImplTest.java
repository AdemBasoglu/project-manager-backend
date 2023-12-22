package be.intecbrussel.projectmanagerbackend.services.impl;

import be.intecbrussel.projectmanagerbackend.models.Board;
import be.intecbrussel.projectmanagerbackend.models.Project;
import be.intecbrussel.projectmanagerbackend.models.User;
import be.intecbrussel.projectmanagerbackend.repositories.BoardRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BoardServiceImplTest {
    @Mock
    private BoardRepository boardRepository;
    @Mock
    private ProjectServiceImpl projectService;
    @InjectMocks
    private BoardServiceImpl boardService;

    private Board board;
    private Project project;
    private final Long projectId = 1L;
    private final Long boardId = 1L;
    private final String boardName = "Test Board";

    User user = new User("a@b.com", "123", "adem", "bas");
    Project mockProject = new Project("new project", user);
    Board mockBoard;

    @BeforeEach
    void setUp() {
        mockBoard = new Board(boardId,"Test Board", mockProject);

    }

    @AfterEach
    void tearDown() {
        boardRepository.deleteAll();
    }

    @Test
    public void givenValidNameAndProjectId_whenAddBoard_thenBoardSavedSuccessfully() {
        // Given

        given(projectService.getProject(projectId)).willReturn(mockProject);

        Board savedBoard = new Board(boardName, mockProject);
        given(boardRepository.save(any(Board.class))).willReturn(savedBoard);

        // When
        Board resultBoard = boardService.addBoard(boardName, projectId);

        // Then
        verify(projectService).getProject(projectId);
        verify(boardRepository).save(any(Board.class));

        assertThat(resultBoard).isNotNull();
        assertThat(resultBoard.getName()).isEqualTo(boardName);
        assertThat(resultBoard.getProject()).isEqualTo(mockProject);
        assertThat(user.getEmail()).isEqualTo("a@b.com");
        System.out.println("mockProject = " + resultBoard);
    }

    @Test
    public void givenBoardId_whenGetBoard_thenReturnBoard() {
        // Given
        given(boardRepository.findById(boardId)).willReturn(Optional.of(mockBoard));

        // When
        Board resultBoard = boardService.getBoard(boardId);

        // Then
        verify(boardRepository).findById(boardId);
        assertThat(resultBoard).isNotNull();
        assertThat(resultBoard.getName()).isEqualTo(boardName);
        assertThat(resultBoard.getProject()).isEqualTo(mockProject);
    }

    @Test
    public void givenProjectId_whenGetAllBoardsByProject_thenReturnListOfBoards() {
        // Given
        given(boardRepository.findAllByProjectId(projectId)).willReturn(List.of(mockBoard));

        // When
        List<Board> resultBoards = boardService.getAllBoardsByProject(projectId);

        // Then
        verify(boardRepository).findAllByProjectId(projectId);
        assertThat(resultBoards).isNotNull().isNotEmpty();
        assertThat(resultBoards.get(0).getName()).isEqualTo(boardName);
        assertThat(resultBoards.get(0).getProject()).isEqualTo(mockProject);
    }

    @Test
    public void givenUpdatedBoardAndBoardId_whenUpdateBoard_thenBoardUpdatedSuccessfully() {
        // Given
        given(boardRepository.findById(mockBoard.getId())).willReturn(Optional.of(mockBoard));

        // When

        mockBoard.setName("Updated Board");
        given(boardRepository.save(any(Board.class))).willReturn(mockBoard);
        Board updatedBoard = boardService.updateBoard(mockBoard, mockBoard.getId());

        // Then
        verify(boardRepository).findById(mockBoard.getId());
        verify(boardRepository).save(any(Board.class));
        System.out.println("updatedBoard = " + updatedBoard);

        assertThat(updatedBoard).isNotNull();
        assertThat(updatedBoard.getName()).isEqualTo("Updated Board");
    }

    @Test
    public void givenBoardId_whenDeleteBoard_thenBoardDeletedSuccessfully() {
        // Given
        given(boardRepository.findById(mockBoard.getId())).willReturn(Optional.of(mockBoard));

        // When
        boardService.deleteBoard(mockBoard.getId());
        System.out.println("mockBoard = " + mockBoard);

        // Then
       // assertThat(mockBoard)
    }





}