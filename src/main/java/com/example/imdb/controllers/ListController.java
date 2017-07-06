package com.example.imdb.controllers;

import com.example.imdb.models.data.FilmDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Matt on 7/5/2017.
 */
@Controller
public class ListController {
    @Autowired
    private FilmDao filmDao;

    @RequestMapping(value="lists", method = RequestMethod.GET)
    public String listIndex (Model model) {
        model.addAttribute("title","Return the database in different orders");
        return "lists/index";
    }

    @RequestMapping(value="lists/top100", method = RequestMethod.GET)
    public String top100 (Model model) {
        model.addAttribute("title","The Top 100 Films");
        model.addAttribute("films", filmDao.findTop100ByOrderByAverageDesc());
        return "lists/table";
    }

    @RequestMapping(value="lists/top250", method = RequestMethod.GET)
    public String top250 (Model model) {
        model.addAttribute("title","The Top 250 Films");
        model.addAttribute("films", filmDao.findTop250ByOrderByAverageDesc());
        return "lists/table";
    }

    @RequestMapping(value="lists/allBest", method = RequestMethod.GET)
    public String allBest (Model model) {
        model.addAttribute("title","All Films Ordered by the Best");
        model.addAttribute("films", filmDao.findAllByOrderByAverageDesc());
        return "lists/table";
    }

    @RequestMapping(value="lists/bottom100", method = RequestMethod.GET)
    public String bottom100 (Model model) {
        model.addAttribute("title","The Bottom 100 Films");
        model.addAttribute("films", filmDao.findTop100ByOrderByAverageAsc());
        return "lists/table";
    }

    @RequestMapping(value="lists/bottom250", method = RequestMethod.GET)
    public String bottom250 (Model model) {
        model.addAttribute("title","The Bottom 250 Films");
        model.addAttribute("films", filmDao.findTop250ByOrderByAverageAsc());
        return "lists/table";
    }

    @RequestMapping(value="lists/allWorst", method = RequestMethod.GET)
    public String allWorst (Model model) {
        model.addAttribute("title","All Films Ordered by the Worst");
        model.addAttribute("films", filmDao.findAllByOrderByAverageAsc());
        return "lists/table";
    }

    @RequestMapping(value="lists/oldest", method = RequestMethod.GET)
    public String oldest (Model model) {
        model.addAttribute("title","All Films Ordered by the Oldest");
        model.addAttribute("films", filmDao.findAllByOrderByYearAsc());
        return "lists/table";
    }

    @RequestMapping(value="lists/newest", method = RequestMethod.GET)
    public String newest (Model model) {
        model.addAttribute("title","All Films Ordered by the Newest");
        model.addAttribute("films", filmDao.findAllByOrderByYearDesc());
        return "lists/table";
    }

    @RequestMapping(value="lists/alpha", method = RequestMethod.GET)
    public String alpha (Model model) {
        model.addAttribute("title","All Films Ordered by Title");
        model.addAttribute("films", filmDao.findAllByOrderByNameAsc());
        return "lists/table";
    }

}
