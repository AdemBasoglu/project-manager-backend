package be.intecbrussel.projectmanagerbackend.repositories;

import be.intecbrussel.projectmanagerbackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
