package com.example.imdb.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Matt on 6/17/2017.
 */
public class AddForm {

    @NotNull
    @Size(min=1, message = "Name may not be empty")
    private String name;


    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public AddForm() {

    }
}
