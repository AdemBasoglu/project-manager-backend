package be.intecbrussel.projectmanagerbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "projects")
    private Set<User> users;

    // NOTE - Bidirectional declarations can be redundant
    // @JsonIgnoreProperties("project")
    // @OneToMany(fetch = FetchType.EAGER, mappedBy = "project")
    // private List<Board> boards;

    protected Project() {
    }

    public Project(String name, User user) {
        this.name = name;
        // this.boards = new ArrayList<>();
        this.users = new HashSet<>();
        users.add(user);
        // System.out.println("USER ADDED");
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    // public List<Board> getBoards() {
    //     return boards;
    // }
    //
    // public void setBoards(List<Board> boards) {
    //     this.boards = boards;
    // }
}
