package com.example.imdb.controllers;

import com.example.imdb.models.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.json.*;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Matt on 6/17/2017.
 */
@Controller
public class FilmController {

    @RequestMapping(value="add", method = RequestMethod.GET)
    public String displayAddFilmForm (Model model) {
        model.addAttribute("title", "Add a movie");
        model.addAttribute(new AddForm());
        return("add");
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public String checkImdb (Model model, @Valid AddForm addForm, Errors errors) throws IOException {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add a movie");
            return "add";
        }
        String searchTitle = addForm.getName().replace(" ","+");
        URL url = new URL("http://www.omdbapi.com/?t="+ searchTitle +"&plot=full&apikey=350f8c5c");
        try (InputStream is = url.openStream();
             JsonReader rdr = Json.createReader(is)) {

            JsonObject obj = rdr.readObject();;
            String title = obj.getString("Title");
            String year = obj.getString("Year");
            String imdbId = obj.getString("imdbID");
            String plot = obj.getString("Plot");
            String poster = obj.getString("Poster");

            RatingImdb imdbRating = new RatingImdb(obj.getString("imdbRating"));
            RatingMeta metaRating = null;
            RatingRotten rottenRating = null;

            JsonArray ratings = obj.getJsonArray("Ratings");
            for (JsonValue rating : ratings) {
                String test = rating.toString();
                if (test.contains("Metacritic")) {metaRating = new RatingMeta(test);}
                if (test.contains("Rotten")) {rottenRating = new RatingRotten(test);}
            }

            if (metaRating == null) {metaRating = new RatingMeta("N/A");}
            if (rottenRating == null) {rottenRating = new RatingRotten("N/A");}

            FilmForm filmForm = new FilmForm(title,year,plot,poster,imdbRating,metaRating,rottenRating,imdbId);
            model.addAttribute("filmForm", filmForm);
            return ("step2");
        }
    }
    @RequestMapping(value = "step2", method = RequestMethod.GET)
    public String confirmImdb (@RequestParam FilmForm filmForm, Model model)  {
        model.addAttribute("filmForm", filmForm);
        return ("step2");
    }

    @RequestMapping(value = "step2", method = RequestMethod.POST)
    public String callTmdb(Model model, @Valid FilmForm filmForm, Errors errors) throws IOException {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add a movie");
            model.addAttribute("errors", errors);
            return "step2";
        }

        URL url = new URL("https://api.themoviedb.org/3/find/" + filmForm.getImdbId() + "?api_key=25879c34855c16b1d1e71076dc10f991&language=en-US&external_source=imdb_id");
        try (InputStream is = url.openStream();
             JsonReader rdr = Json.createReader(is)) {

            JsonObject obj = rdr.readObject();
            JsonObject results = obj.getJsonArray("movie_results").getJsonObject(0);
            RatingImdb tmdbRating = new RatingImdb(results.getJsonNumber("vote_average").toString());

            Film newFilm = new Film(filmForm.getTitle(), filmForm.getYear(), filmForm.getPlot(), filmForm.getPoster(),
                    filmForm.getImdbRating(), filmForm.getMetaRating(), filmForm.getRottenRating(), filmForm.getImdbId(),
                    tmdbRating);
            newFilm.setAverage();
            FilmData.add(newFilm);
            return "redirect:film/"+newFilm.getFilmId();
        }
    }

    @RequestMapping(value="film/{filmId}", method= RequestMethod.GET)
    public String displayFilm (Model model, @PathVariable int filmId) {
        model.addAttribute("film", FilmData.getById(filmId));
        return "film";
    }
}
