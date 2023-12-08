package be.intecbrussel.projectmanagerbackend.repositories;

import be.intecbrussel.projectmanagerbackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);

    @Query(value = "select users_email from user_projects up where up.projects_id = :ID",
            nativeQuery = true)
    List<String> findAllUserEmailsByProjectId(@Param("ID") Long projectId);

    @Query(value = "select users_email from user_tasks up where up.tasks_id = :ID",
            nativeQuery = true)
    List<String> findAllUserEmailsByTaskId(@Param("ID") Long taskId);
}
