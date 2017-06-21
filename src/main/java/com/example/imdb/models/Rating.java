package com.example.imdb.models;

/**
 * Created by Matt on 6/19/2017.
 */
public abstract class Rating {
    public String rating;

    public double mathRating;

    public String getRating() {return rating;}
    public void setRating(String rating) {this.rating = rating;}

    public double getMathRating() {return mathRating;}
    public void setMathRating(double mathRating) {this.mathRating = mathRating;}

    @Override
    public String toString() {
        return rating;
    }

    public Rating () {

    }
}
