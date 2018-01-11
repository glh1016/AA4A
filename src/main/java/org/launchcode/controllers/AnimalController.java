package org.launchcode.controllers;

import org.launchcode.models.Animal;
import org.launchcode.models.Category;
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
        model.addAttribute("animals", speciesDao.findAll());
        model.addAttribute("title", "Animals Available for Adoption");
        return "animal/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddAnimalForm(Model model) {
        model.addAttribute("title", "Add Animal");
        model.addAttribute(new Animal());
        model.addAttribute("categories", animalDao.findAll());
        return "animal/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddAnimalForm(@ModelAttribute  @Valid Animal newAnimal,
                                       Errors errors,
                                       @RequestParam int categoryId,
                                       Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Animal");
            model.addAttribute("categories", animalDao.findAll());
            return "animal/add";
        }

        Category cat = animalDao.findOne(categoryId);
        newAnimal.setCategory(cat);
        speciesDao.save(newAnimal);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveAnimalForm(Model model) {
        model.addAttribute("animals", speciesDao.findAll());
        model.addAttribute("title", "Remove Animal");
        return "animal/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveAnimalForm(@RequestParam int[] animalIds) {

        for (int animalId : animalIds) {
            speciesDao.delete(animalId);
        }

        return "redirect:";
    }

    @RequestMapping(value= "species", method = RequestMethod.GET)
    public String category(Model model, @RequestParam int id){

        Category cat = animalDao.findOne(id);
        List<Animal> animals = cat.getAnimals();
        model.addAttribute("animals",animals);
        model.addAttribute("title", "Species " + cat.getName());
        return "animal/index";

    }

}
