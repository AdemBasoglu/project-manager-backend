package be.intecbrussel.projectmanagerbackend.repositories;

import be.intecbrussel.projectmanagerbackend.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    // @Query("SELECT p FROM user_task p WHERE " +
    //         "LOWER(p.name) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
    //         "LOWER(p.description) LIKE LOWER(CONCAT('%', :query, '%'))")
    // List<Task> findAllByUserEmail(String email);

    void deleteAllByBoardId(Long boardId);

}
