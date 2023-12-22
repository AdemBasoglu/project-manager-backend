package be.intecbrussel.projectmanagerbackend.repositories;

import be.intecbrussel.projectmanagerbackend.models.Board;
import be.intecbrussel.projectmanagerbackend.models.Project;
import be.intecbrussel.projectmanagerbackend.models.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BoardRepositoryTest {

    private final BoardRepository boardRepository;
    private final TestEntityManager testEntityManager;

    private Board board;
    private User user;
    private Project project;
    private Board board1;
    private Board board2;


    @Autowired
    public BoardRepositoryTest(BoardRepository boardRepository, TestEntityManager testEntityManager) {
        this.boardRepository = boardRepository;
        this.testEntityManager = testEntityManager;
    }


    @BeforeEach
    void setUp() {
        this.user = new User("a@b.com", "123","adem","Bas");
        this.project = new Project("new project", user);
        this.board1 = new Board("new first board", project);
        this.board2 = new Board("new second board", project);


    }


    @AfterEach
    void tearDown() {
        boardRepository.deleteAll();
    }

    @Test
    @Order(1)
    public void givenBoardId_whenFindByBoard_thenReturnBoardObj() {

        //given
        testEntityManager.merge(user);
        testEntityManager.merge(project);
        testEntityManager.merge(board1);
        testEntityManager.merge(board2);
        testEntityManager.flush();
        testEntityManager.clear();

        //when
        Board foundBoard = boardRepository.findById(board1.getId()).get();

        //then
        assertThat(foundBoard).isNotNull();
        assertThat(foundBoard.getName()).isEqualTo("new first board");
        assertThat(foundBoard.getId()).isEqualTo(1L);
        assertThat(foundBoard.getProject().getName()).isEqualTo("new project");

    }

    @Test
    @Order(2)
    public void givenProjectId_whenFindAllByBoard_thenReturnBoard() {

        //given
        testEntityManager.merge(user);
        testEntityManager.merge(project);
        testEntityManager.merge(board1);
        testEntityManager.flush();
        testEntityManager.clear();

        //when
        Board foundBoard = boardRepository.findAllByProjectId(project.getId()).get(0);

        //then
        assertThat(foundBoard).isNotNull();
        assertThat(foundBoard.getName()).isEqualTo("new first board");
        assertThat(foundBoard.getId()).isEqualTo(1L);
        assertThat(foundBoard.getProject().getName()).isEqualTo("new project");
    }

    @Test
    @Order(3)
    public void givenBoardId_whenFindByBoardId_thenReturnUpdatedBoardObj() {

        //given
        testEntityManager.merge(user);
        testEntityManager.merge(project);
        testEntityManager.merge(board1);
        testEntityManager.flush();
        testEntityManager.clear();

        //when
        Board foundBoard = boardRepository.findById(board1.getId()).get();
        foundBoard.setName("Updated Board name");
        Board updatedBoard = boardRepository.save(foundBoard);
        //then
        assertThat(updatedBoard).isNotNull();
        assertThat(updatedBoard.getName()).isEqualTo("Updated Board name");
    }

    @Test
    @Order(4)
    public void givenBoardId_whenFindByBoardId_thenReturnEmptyObject() {
        // Given
        testEntityManager.merge(user);
        testEntityManager.merge(project);
        testEntityManager.merge(board1);
        testEntityManager.flush();
        testEntityManager.clear();

        // When
        boardRepository.deleteById(board1.getId());

        Optional<Board> foundBoard = boardRepository.findById(board1.getId());

        // Then
        Assertions.assertThat(foundBoard).isEmpty();
    }

}