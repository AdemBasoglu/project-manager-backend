package be.intecbrussel.projectmanagerbackend.repositories;

import be.intecbrussel.projectmanagerbackend.models.Board;
import be.intecbrussel.projectmanagerbackend.models.Project;
import be.intecbrussel.projectmanagerbackend.models.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
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

    @Autowired
    public BoardRepositoryTest(BoardRepository boardRepository, TestEntityManager testEntityManager) {
        this.boardRepository = boardRepository;
        this.testEntityManager = testEntityManager;
    }


    @BeforeEach
    void setUp() {
        user = new User("a@b.com", "123");
        project = new Project("new project", user);
        board = new Board("new board", project);
    }


    @AfterEach
    void tearDown() {
        board = null;
    }

    @Test
    @Order(1)
    public void givenBoardId_whenFindByBoard_thenReturnBoardObj() {

        //given
        boardRepository.save(board);

        //when
        Board foundBoard = boardRepository.findById(board.getId()).get();

        //then
        assertThat(foundBoard).isNotNull();

    }

    @Test
    @Order(2)
    public void givenBoardId_whenFindByBoard_thenReturnBoardName() {

        //given
        boardRepository.save(board);


        //when
        Board foundBoard = boardRepository.findById(board.getId()).get();


        //then
        assertThat(foundBoard.getName()).isEqualTo("new board");
    }

    @Test
    @Order(3)
    public void givenBoardId_whenFindByBoardId_thenReturnUpdatedBoardObj() {

        //given
        boardRepository.save(board);
        //when
        Board findBoard = boardRepository.findById(board.getId()).get();
        findBoard.setName("Updated Board name");
        Board updatedBoard = boardRepository.save(findBoard);
        //then
        assertThat(updatedBoard).isNotNull();
        assertThat(updatedBoard.getName()).isEqualTo("Updated Board name");
    }

    @Test
    @Order(4)
    public void givenBoardId_whenFindByBoardId_thenReturnEmptyObject() {
        // Given
        boardRepository.save(board);

        // When
        boardRepository.deleteById(board.getId());

        Optional<Board> foundBoard = boardRepository.findById(board.getId());

        // Then
        Assertions.assertThat(foundBoard).isEmpty();
    }

}