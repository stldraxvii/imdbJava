package com.example.imdb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Matt on 7/5/2017.
 */
@Controller
public class ListController {

    @RequestMapping(value="lists", method = RequestMethod.GET)
    public String listIndex (Model model) {
        model.addAttribute("title","Return the database in different orders");
        return "lists/index";
    }

}
