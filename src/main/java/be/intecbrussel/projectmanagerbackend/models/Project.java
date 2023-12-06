package be.intecbrussel.projectmanagerbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // NOTE - @NotNull @NotEmpty
    private String name;
    private String descritption;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "projects")
    private Set<User> users;

    // NOTE - Bidirectional declarations can be redundant
    // @JsonIgnoreProperties("project")
    // @OneToMany(fetch = FetchType.EAGER, mappedBy = "project")
    // private List<Board> boards;

    protected Project() {
    }

    public Project(String name, String descritption, Set<User> users) {
        this.name = name;
        this.descritption = descritption;
        this.users = users;
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

    public String getDescritption() {
        return descritption;
    }

    public void setDescritption(String descritption) {
        this.descritption = descritption;
    }

    // public List<Board> getBoards() {
    //     return boards;
    // }
    //
    // public void setBoards(List<Board> boards) {
    //     this.boards = boards;
    // }
}
