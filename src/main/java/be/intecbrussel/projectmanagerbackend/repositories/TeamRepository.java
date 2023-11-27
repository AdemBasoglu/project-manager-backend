package be.intecbrussel.projectmanagerbackend.repositories;

import be.intecbrussel.projectmanagerbackend.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
