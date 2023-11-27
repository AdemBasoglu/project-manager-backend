package be.intecbrussel.projectmanagerbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

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

    @JsonIgnoreProperties("project")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "project")
    private List<Team> team;

}
