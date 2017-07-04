package com.example.imdb.models.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Matt on 6/17/2017.
 */
public class AddForm {

    @NotNull
    @Size(min=1, message = "Name may not be empty")
    private String name;

    private String year;

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getYear() {return year;}
    public void setYear(String year) {this.year = year;}

    public AddForm() { }
}
