package be.intecbrussel.projectmanagerbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.DataAmount;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // NOTE - @NotBlank
    private String name;

    // @JsonIgnoreProperties("boards")
    @NotNull
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

    public Board(Long id,String name, Project project) {
        this.name = name;
        this.project = project;
        this.id=id;
        this.tasks = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setTasks(List<Task> task) {
        this.tasks = task;
    }

    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", project=" + project +
                ", tasks=" + tasks +
                '}';
    }
}
