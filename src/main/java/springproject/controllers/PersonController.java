package springproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springproject.models.Person;
import springproject.services.PersonService;
import springproject.services.impl.PersonServiceImpl;

@Controller
@RequestMapping("/people")
public class PersonController {

    private PersonService personService;

    public PersonService getPersonService() {
        return personService;
    }

    @Autowired
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    public String getAllPeople(Model model){
        model.addAttribute("peopleList", personService.getAllPeople());
        return "people/people";
    }
    @GetMapping("/{login}")
    public String getPersonByLogin(@PathVariable("login") String login, Model model){
        model.addAttribute("person", personService.getPersonByLogin(login).get());
        return "people/person";
    }
    @GetMapping("/new")
    public String openFormCreatePerson(Model model){
        model.addAttribute("newPerson", new Person());
        return "people/new_person";
    }
    @GetMapping("/{login}/edit")
    public String openFormUpdatePerson(@PathVariable("login") String login, Model model){
        model.addAttribute("editPerson", personService.getPersonByLogin(login).get());
        return "people/edit_person";
    }
    @PostMapping()
    public String createPerson(@ModelAttribute("newPerson") Person person){
        personService.createPerson(person);
        return "redirect:/people";
    }
    @PostMapping("/{login}/edit")
    public String updatePerson(@ModelAttribute("editPerson") Person person){
        personService.updatePerson(person);
        return "redirect:/people";
    }
    @PostMapping("/{login}")
    public String deletePerson(@PathVariable("login") String login){
        personService.deletePerson(login);
        return "redirect:/people";
    }
}
