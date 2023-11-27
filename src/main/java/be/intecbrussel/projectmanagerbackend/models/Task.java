package be.intecbrussel.projectmanagerbackend.models;

import be.intecbrussel.projectmanagerbackend.models.enums.TaskLabel;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private LocalDate createdDate;

    @Enumerated(EnumType.STRING)
    private TaskLabel label;

    @ManyToOne
    private Board board;

    @ManyToMany(mappedBy = "tasks")
    private Set<User> users;
}


