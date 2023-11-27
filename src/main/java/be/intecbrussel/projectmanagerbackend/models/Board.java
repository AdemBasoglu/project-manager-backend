package be.intecbrussel.projectmanagerbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

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
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "board")
    private List<Task> task;

}
