package com.example.imdb.models;

/**
 * Created by Matt on 6/19/2017.
 */
public class RatingMeta extends Rating {

    public RatingMeta(String rating) {
        this.rating = rating.replace("\"","").replace("{Source:Metacritic,Value:","").replace("/100}","");

        if (!rating.equals("N/A")) {
            this.mathRating = Double.parseDouble(this.rating)/10;
        }
    }
}
