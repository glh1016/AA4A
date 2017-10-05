package org.launchcode.controllers;

import org.launchcode.models.Category;
import org.launchcode.models.Cheese;
import org.launchcode.models.data.AnimalDao;
import org.launchcode.models.data.SpeciesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("animal")
public class AnimalController {

    @Autowired
    private SpeciesDao speciesDao;

    @Autowired
    private AnimalDao animalDao;

    // Request path: /animal
    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("cheeses", speciesDao.findAll());
        model.addAttribute("title", "Animals Available for Adoption");
        return "animal/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Animal");
        model.addAttribute(new Cheese());
        model.addAttribute("categories", animalDao.findAll());
        return "animal/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute  @Valid Cheese newCheese,
                                       Errors errors,
                                       @RequestParam int categoryId,
                                       Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Animal");
            model.addAttribute("categories", animalDao.findAll());
            return "animal/add";
        }

        Category cat = animalDao.findOne(categoryId);
        newCheese.setCategory(cat);
        speciesDao.save(newCheese);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("cheeses", speciesDao.findAll());
        model.addAttribute("title", "Remove Animal");
        return "animal/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam int[] cheeseIds) {

        for (int cheeseId : cheeseIds) {
            speciesDao.delete(cheeseId);
        }

        return "redirect:";
    }

    @RequestMapping(value= "species", method = RequestMethod.GET)
    public String category(Model model, @RequestParam int id){

        Category cat = animalDao.findOne(id);
        List<Cheese> cheeses = cat.getCheeses();
        model.addAttribute("cheeses",cheeses);
        model.addAttribute("title", "Cheeses in Category: " + cat.getName());
        return "animal/index";

    }

}
