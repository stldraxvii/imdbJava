package com.example.imdb.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matt on 6/23/2017.
 */
@Entity
public class Genre {
    @Id
    @GeneratedValue
    public int id;

    @NotNull
    public String name;

    @ManyToMany (mappedBy="genres")
    List<Film> films;

    public Genre () { }

    public Genre (String name) {
        this();
        this.name=name;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public List<Film> getFilms() {return films;}
    public void addFilm(Film item) {
        if (this.films == null) {
            this.films = new ArrayList<>();
            films.add(item);
        }
        else {
            films.add(item);
        }
    }

    public boolean equals(String name) {
        boolean retVal;
        retVal = this.name.equals(name);
        return retVal;
    }
}
