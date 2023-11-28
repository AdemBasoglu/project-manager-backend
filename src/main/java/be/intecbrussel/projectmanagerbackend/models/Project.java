package be.intecbrussel.projectmanagerbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    private User user;

    // NOTE - Bidirectional declarations can be redundant
    @JsonIgnoreProperties("project")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "project")
    private List<Board> boards;

    protected Project() {
    }

    public Project(String name, User user) {
        this.name = name;
        this.user = user;
        this.boards = new ArrayList<>();
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Board> getBoards() {
        return boards;
    }

    public void setBoards(List<Board> boards) {
        this.boards = boards;
    }
}
