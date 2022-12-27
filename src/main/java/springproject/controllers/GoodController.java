package springproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springproject.models.Good;
import springproject.models.Person;
import springproject.services.GoodService;
import springproject.services.PersonService;
import springproject.services.impl.GoodServiceImpl;
import springproject.services.impl.PersonServiceImpl;

@Controller
@RequestMapping("/goods")
public class GoodController {


    private GoodService goodService;
    private PersonService personService;

    public PersonService getPersonService() {
        return personService;
    }

    @Autowired
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    public GoodService getGoodService() {
        return goodService;
    }

    @Autowired
    public void setGoodService(GoodService goodService) {
        this.goodService = goodService;
    }

    @GetMapping()
    public String getAllGoods(Model model){
        model.addAttribute("goodsList", goodService.getAllGoods());
        return "goods/goods";
    }
    @GetMapping("/{id}")
    public String getGoodById(@PathVariable("id") int id, Model model){
        model.addAttribute("good", goodService.getGoodById(id).get());
        return "goods/good";
   }
    @GetMapping("/new")
    public String openFormCreateGood(Model model){
        Good good = new Good();
        good.setPerson(new Person());
        model.addAttribute("newGood", good);
        return "goods/new_good";
    }
    @GetMapping("/{id}/edit")
    public String openFormUpdateGood(@PathVariable("id") int id, Model model){
        model.addAttribute("editGood", goodService.getGoodById(id).get());
        return "goods/edit_good";
    }
    @PostMapping()
    public String createGood(@ModelAttribute("newGood") Good good){
        good.setPerson(personService.getPersonByLogin(good.getPerson().getLogin()).get());
        goodService.createGood(good);
        return "redirect:/goods";
    }
    @PostMapping("/{id}/edit")
    public String updateGood(@ModelAttribute("editGood") Good good){
        good.setPerson(personService.getPersonByLogin(good.getPerson().getLogin()).get());
        goodService.updateGood(good);
        return "redirect:/goods";
    }
    @PostMapping("/{id}")
    public String deleteGood(@PathVariable("id") int id){
        goodService.deleteGood(id);
        return "redirect:/goods";
    }
}
