package com.example.imdb.models;

import java.util.ArrayList;

/**
 * Created by Matt on 6/17/2017.
 */
public class FilmData {

    static ArrayList<Film> films = new ArrayList<>();
    private static FilmData instance;

    public static ArrayList<Film> getAll() {
        return films;
    }

    public static void add(Film newFilm) {
        films.add(newFilm);
    }

    public static Film getById(int id) {
        Film theFilm = null;

        for (Film candidateFilm : films) {
            if (candidateFilm.getFilmId() == id) {
                theFilm = candidateFilm;
            }
        }
        return theFilm;
    }
}
