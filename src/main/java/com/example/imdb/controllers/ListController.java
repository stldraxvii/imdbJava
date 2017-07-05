package com.example.imdb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Matt on 7/5/2017.
 */
@Controller(value="list")
public class ListController {

    @RequestMapping(method = RequestMethod.GET)
    public String listIndex (Model model) {
        return "list/index";
    }

}
