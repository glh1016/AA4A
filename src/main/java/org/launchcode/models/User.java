package org.launchcode.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by t420-11 on 1/19/2018.
 */
@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String user;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Animal> animals = new ArrayList<>();

    public User(String name){
        this.user = name;
    }

    public User() { }
}
