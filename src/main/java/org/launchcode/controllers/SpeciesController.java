package org.launchcode.controllers;

import org.launchcode.models.Category;
import org.launchcode.models.data.AnimalDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * Created by t420-11 on 8/7/2017.
 */

@Controller
@RequestMapping("species")
public class SpeciesController {

    @Autowired
    private AnimalDao animalDao;

    // Request path: /species
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("categories", animalDao.findAll());
        model.addAttribute("title", "Species");

        return "species/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCategoryForm(Model model) {
        model.addAttribute("title", "Add Species");
        model.addAttribute(new Category());
        return "species/add";

    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model,
                      @ModelAttribute @Valid Category category, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Species");
            return "species/add";
        }

        animalDao.save(category);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveSpeciesForm(Model model) {
        model.addAttribute("species", animalDao.findAll());
        model.addAttribute("title", "Remove Species");
        return "species/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveSpeciesForm(@RequestParam int[] speciesIds) {

        for (int speciesId : speciesIds) {
            animalDao.delete(speciesId);
        }

        return "redirect:";
    }

}


