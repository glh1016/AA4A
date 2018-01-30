package org.launchcode.controllers;

import org.launchcode.models.data.AnimalDao;
import org.launchcode.models.data.SpeciesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("")
public class HomeController {

    @Autowired
    private SpeciesDao speciesDao;

    @Autowired
    private AnimalDao animalDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("animals", speciesDao.findAll());
        model.addAttribute("title", "Welcome to AA4A");
        return "index";
    }



}