package be.intecbrussel.projectmanagerbackend.repositories;

import be.intecbrussel.projectmanagerbackend.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    // @Query("SELECT t FROM Task t WHERE " +
    //         "LOWER(b.author) LIKE LOWER(CONCAT('%',:query, '%') ) OR " +
    //         "LOWER(b.title) LIKE LOWER(CONCAT('%' , :query ,'%') )")
    // List<Book> searchBook(@Param("query") String query);
    // void deleteAllByBoardId(Long boardID);


    @Override
    void deleteById(Long aLong);
}
