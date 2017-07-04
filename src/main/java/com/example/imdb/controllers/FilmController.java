package com.example.imdb.controllers;

import com.example.imdb.models.*;
import com.example.imdb.models.data.*;
import com.example.imdb.models.forms.AddForm;
import com.example.imdb.models.forms.FilmForm;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.ArrayList;

/**
 * Created by Matt on 6/17/2017.
 */
@Controller
public class FilmController {
    @Autowired
    private FilmDao filmDao;
    @Autowired
    private CountryDao countryDao;
    @Autowired
    private DirectorDao directorDao;
    @Autowired
    private ActorDao actorDao;
    @Autowired
    private GenreDao genreDao;

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

        URL url = new URL("http://www.omdbapi.com/?t="+ searchTitle +"&y="+addForm.getYear()+"&plot=full&apikey=350f8c5c");
        try (InputStream is = url.openStream();
             JsonReader rdr = Json.createReader(is)) {
             JsonObject obj = rdr.readObject();

            if (obj.getString("Response").equals("False")) {
                model.addAttribute("title", "Add a movie");
                model.addAttribute("badName", "Sorry, but there were no results for that title.");
                return "add";
            }

            String imdbId = obj.getString("imdbID");
            for(Film film : filmDao.findAll()) {
                if (film.getImdbId().equals(imdbId)) {
                    model.addAttribute("title", "Add a movie");
                    model.addAttribute("duplicate", "You can find it here!");
                    model.addAttribute("id", film.getId());
                    return "add";
                }
            }
            String title = obj.getString("Title");
            String year = obj.getString("Year");
            String plot = obj.getString("Plot");
            String poster = obj.getString("Poster");

            String imdbRatingString = obj.getString("imdbRating");
            String metaRatingString = null;
            String rottenRatingString = null;

            String countryStrings = obj.getString("Country");
            String directorStrings = obj.getString("Director");
            String actorStrings = obj.getString("Actors");
            String genreStrings = obj.getString("Genre");

            JsonArray ratings = obj.getJsonArray("Ratings");
            for (JsonValue rating : ratings) {
                String test = rating.toString();
                if (test.contains("Metacritic")) {
                    metaRatingString = test.replace("\"","").replace("{Source:Metacritic,Value:","").replace("/100}","");
                }
                if (test.contains("Rotten")) {
                    rottenRatingString = test.replace("\"","").replace("{Source:Rotten Tomatoes,Value:","").replace("%}","");
                }
            }

            if (metaRatingString == null) {metaRatingString = "N/A";}
            if (rottenRatingString == null) {rottenRatingString = "N/A";}

            FilmForm filmForm = new FilmForm(title,year,plot,poster,countryStrings,directorStrings,actorStrings,
                    genreStrings,imdbRatingString,metaRatingString,rottenRatingString,imdbId);
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
            String tmdbRating = results.getJsonNumber("vote_average").toString();

            //Country
            ArrayList<Country> countriesToAdd = new ArrayList<>();
            String countryStringsArray[] = filmForm.countriesToAdd.replace(", ",":").split(":");

            for (String countryString : countryStringsArray) {
                ArrayList<Country> existingCountries = (ArrayList<Country>) countryDao.findAll();
                int size = existingCountries.size();
                int addSize = countriesToAdd.size();
                if (size == 0) {
                    Country newCountry = new Country(countryString);
                    countryDao.save(newCountry);
                    countriesToAdd.add(newCountry);
                }
                if(size !=0) {
                    for (Country country : existingCountries) {
                        if (country.equals(countryString)) {
                            countriesToAdd.add(country);
                            break;
                        }
                    }
                    ArrayList<Country> existingCountriesCheck = (ArrayList<Country>) countryDao.findAll();
                    if (size == existingCountriesCheck.size() && addSize==countriesToAdd.size()) {
                        Country newCountry = new Country(countryString);
                        countryDao.save(newCountry);
                        countriesToAdd.add(newCountry);
                    }
                }
            }

            //Director
            ArrayList<Director> directorsToAdd = new ArrayList<>();
            String directorStringsArray[] = filmForm.directorsToAdd.replace(", ",":").split(":");

            for (String directorString : directorStringsArray) {
                ArrayList<Director> existingDirectors = (ArrayList<Director>) directorDao.findAll();
                int size = existingDirectors.size();
                int addSize = directorsToAdd.size();
                if (size==0) {
                    Director newDirector = new Director(directorString);
                    directorDao.save(newDirector);
                    directorsToAdd.add(newDirector);
                }
                if (size != 0) {
                    for (Director director : existingDirectors) {
                        if (director.equals(directorString)) {
                            directorsToAdd.add(director);
                            break;
                        }
                    }
                    ArrayList<Director> existingDirectorsCheck = (ArrayList<Director>) directorDao.findAll();
                    if (size == existingDirectorsCheck.size() && addSize == directorsToAdd.size()) {
                        Director newDirector =  new Director(directorString);
                        directorDao.save(newDirector);
                        directorsToAdd.add(newDirector);
                    }
                }
            }

            //Actor
            ArrayList<Actor> actorsToAdd = new ArrayList<>();
            String actorStringsArray[] = filmForm.actorsToAdd.replace(", ",":").split(":");

            for (String actorString : actorStringsArray) {
                ArrayList<Actor> existingActors = (ArrayList<Actor>) actorDao.findAll();
                int size = existingActors.size();
                int addSize = actorsToAdd.size();
                if (size == 0) {
                    Actor newActor = new Actor(actorString);
                    actorDao.save(newActor);
                    actorsToAdd.add(newActor);
                }
                if (size!=0) {
                    for (Actor actor : existingActors) {
                        if (actor.equals(actorString)) {
                            actorsToAdd.add(actor);
                            break;
                        }
                    }
                    ArrayList<Actor> existingActorsCheck = (ArrayList<Actor>) actorDao.findAll();
                    if (size == existingActorsCheck.size() && addSize == actorsToAdd.size()) {
                        Actor newActor = new Actor(actorString);
                        actorDao.save(newActor);
                        actorsToAdd.add(newActor);
                    }
                }
            }

            //Genre
            ArrayList<Genre> genresToAdd = new ArrayList<>();
            String genreStringsArray[] = filmForm.genresToAdd.replace(", ", ":").split(":");

            for (String genreString : genreStringsArray) {
                ArrayList<Genre> existingGenres = (ArrayList<Genre>) genreDao.findAll();
                int size = existingGenres.size();
                int addSize = genresToAdd.size();
                if (size == 0) {
                    Genre newGenre = new Genre(genreString);
                    genreDao.save(newGenre);
                    genresToAdd.add(newGenre);
                }
                if (size!=0) {
                    for (Genre genre : existingGenres) {
                        if (genre.equals(genreString)) {
                            genresToAdd.add(genre);
                            break;
                        }
                    }
                    ArrayList<Genre> existingGenresCheck = (ArrayList<Genre>) genreDao.findAll();
                    if (size == existingGenresCheck.size() && addSize == genresToAdd.size()) {
                        Genre newGenre = new Genre(genreString);
                        genreDao.save(newGenre);
                        genresToAdd.add(newGenre);
                    }
                }
            }

            Film newFilm = new Film(filmForm.getTitle(), filmForm.getYear(), filmForm.getPlot(), filmForm.getPoster(),
                    countriesToAdd, directorsToAdd, actorsToAdd, genresToAdd, filmForm.getImdbRatingString(),
                    filmForm.getMetaRatingString(), filmForm.getRottenRatingString(), filmForm.getImdbId(), tmdbRating);
            newFilm.setRatings();
            newFilm.setAverage();
            filmDao.save(newFilm);

            for (Country country : newFilm.getCountries()) {
                country.addFilm(newFilm);
                countryDao.save(country);
            }
            for (Director director : newFilm.getDirectors()) {
                director.addFilm(newFilm);
                directorDao.save(director);
            }
            for (Actor actor : newFilm.getActors()) {
                actor.addFilm(newFilm);
                actorDao.save(actor);
            }
            for (Genre genre : newFilm.getGenres()) {
                genre.addFilm(newFilm);
                genreDao.save(genre);
            }
            return "redirect:film/"+newFilm.getId();
        }
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String remove (Model model) {
        model.addAttribute("films", filmDao.findAll());
        model.addAttribute("title", "Remove Film");
        return "remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String remove (@RequestParam int[] filmIds) {
        for (int filmId : filmIds) {filmDao.delete(filmId);}
        return "redirect:";
    }

    @RequestMapping(value="film/{filmId}", method= RequestMethod.GET)
    public String displayFilm (Model model, @PathVariable int filmId) {
        model.addAttribute("film", filmDao.findOne(filmId));
        return "film";
    }
}
