package com.example.imdb.models;

/**
 * Created by Matt on 6/19/2017.
 */
public class RatingImdb extends Rating {

    public RatingImdb(String rating) {
        this.rating = rating;
        this.mathRating = Double.parseDouble(rating);
    }

}
