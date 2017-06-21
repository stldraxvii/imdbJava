package com.example.imdb.models;

/**
 * Created by Matt on 6/19/2017.
 */
public class RatingRotten extends Rating {

    @Override
    public void setRating (String rating) {
        this.rating = rating.replace("\"","").replace("{Source:Rotten Tomatoes,Value:","").replace("%}","");
    }

    public void setMathRating () {this.mathRating = Double.parseDouble(this.rating)/10;}

    public RatingRotten(String rating) {
        this.rating = rating.replace("\"","").replace("{Source:Rotten Tomatoes,Value:","").replace("%}","");

        if (!rating.equals("N/A")) {
            this.mathRating = Double.parseDouble(this.rating)/10;
        }

    }
}
