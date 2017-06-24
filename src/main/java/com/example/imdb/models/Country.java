package com.example.imdb.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Matt on 6/22/2017.
 */

@Entity
public class Country {
    @Id
    @GeneratedValue
    public int id;

    @NotNull
    public String name;

    @ManyToMany (mappedBy="countries")
    List<Film> films;

    public Country() { }

    public Country(String name) {
        this();
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<Film> getFilms() {
        return films;
    }
    public void addFilm(Film item) {films.add(item);}

    public boolean equals(String name) {
        boolean retVal;
        retVal = this.name.equals(name);
        return retVal;
    }

}