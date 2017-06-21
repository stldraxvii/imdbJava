package com.example.imdb.models;

/**
 * Created by Matt on 6/17/2017.
 */
public class FilmForm2 extends FilmForm {

    public FilmForm2 (FilmForm filmForm) {
        this();
        this.title=filmForm.getTitle();
        this.year=filmForm.getYear();
        this.imdbRating =filmForm.getImdbRating();
        this.metaRating=filmForm.getMetaRating();
        this.imdbId=filmForm.getImdbId();
    }

    public FilmForm2 () {

    }


}
