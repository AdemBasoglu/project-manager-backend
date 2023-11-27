package be.intecbrussel.projectmanagerbackend.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class User {
    @Id
    private String email;

    private String password;
    private String firstName;
    private String lastName;

    @ManyToOne
    private Team team;

    @ManyToMany
    private Set<Task> tasks;
    
    @OneToOne
    private Project project;

}
