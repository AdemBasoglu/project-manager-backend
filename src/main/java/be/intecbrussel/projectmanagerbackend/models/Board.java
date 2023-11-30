package be.intecbrussel.projectmanagerbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

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
    @JsonIgnore
    // @Transient
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "board")
    private List<Task> tasks;

    protected Board() {
    }

    public Board(String name, Project project) {
        this.name = name;
        this.project = project;
        this.tasks = new ArrayList<>();
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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> task) {
        this.tasks = task;
    }
}
