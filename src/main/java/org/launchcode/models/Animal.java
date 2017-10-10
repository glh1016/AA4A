package org.launchcode.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LaunchCode
 */
@Entity
public class Animal {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @ManyToOne
    private Category species;

    @NotNull
    @Size(min=1, message = "Breed must not be empty")
    private String breed;

    @NotNull
    @Size(min=1, message = "Age must not be empty")
    private String age;

    @NotNull
    @Size(min=1, message = "Description must not be empty")
    private String description;



    public Animal(String name, String breed, String age, String description) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.description = description;
    }



    public Animal() { }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() { return breed;}

    public void setBreed(String breed) { this.breed = breed;}

    public String getAge() { return age;}

    public void setAge(String age) { this.age = age;}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return species;
    }

    public void setCategory(Category species) {
        this.species = species;
    }
}

