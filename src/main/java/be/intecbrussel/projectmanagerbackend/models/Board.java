package be.intecbrussel.projectmanagerbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // @JsonIgnoreProperties("boards")
    @ManyToOne
    private Project project;

    // NOTE - Bidirectional declarations can be redundant
    @JsonIgnoreProperties("board")
    @Transient
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Task> task;

    protected Board() {
    }

    public Board(String name, Project project) {
        this.name = name;
        this.project = project;
        this.task = new ArrayList<>();
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<Task> getTask() {
        return task;
    }

    public void setTask(List<Task> task) {
        this.task = task;
    }
}
