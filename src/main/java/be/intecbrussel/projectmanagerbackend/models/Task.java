package be.intecbrussel.projectmanagerbackend.models;

import be.intecbrussel.projectmanagerbackend.models.enums.TaskLabel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
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
    
    @JsonIgnoreProperties("tasks")
    @ManyToMany(mappedBy = "tasks")
    private Set<User> users;

    protected Task() {
    }

    public Task(String name, String description, Board board) {
        this.name = name;
        this.description = description;
        this.createdDate = LocalDate.now();
        this.label = TaskLabel.DEFAULT;
        this.board = board;
        this.users = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public TaskLabel getLabel() {
        return label;
    }

    public void setLabel(TaskLabel label) {
        this.label = label;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}


