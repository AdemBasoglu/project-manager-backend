package be.intecbrussel.projectmanagerbackend.repositories;

import be.intecbrussel.projectmanagerbackend.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
