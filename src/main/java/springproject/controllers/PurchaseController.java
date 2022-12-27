package springproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springproject.models.Good;
import springproject.models.Person;
import springproject.models.Purchase;
import springproject.services.GoodService;
import springproject.services.PersonService;
import springproject.services.PurchaseService;

@Controller
@RequestMapping("/purchases")
public class PurchaseController {

    private PurchaseService purchaseService;
    private PersonService personService;
    private GoodService goodService;

    public PurchaseService getPurchaseService() {
        return purchaseService;
    }

    @Autowired
    public void setPurchaseService(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

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
    public String getAllPurchases(Model model){
        model.addAttribute("purchasesList", purchaseService.getAllPurchases());
        return "purchases/purchases";
    }

    @GetMapping("/{number}")
    public String getPurchaseByNumber(@PathVariable("number") int number, Model model){
        model.addAttribute("purchase", purchaseService.getPurchaseByNumber(number).get());
        return "purchases/purchase";
    }
    @GetMapping("/new")
    public String openFormCreatePurchase(Model model){
        Purchase purchase = new Purchase();
        purchase.setPerson(new Person());
        purchase.setGood(new Good());
        model.addAttribute("newPurchase", purchase);
        return "purchases/new_purchase";
    }
    @GetMapping("/{number}/edit")
    public String openFormUpdatePurchase(@PathVariable("number") int number, Model model){
        model.addAttribute("editPurchase", purchaseService.getPurchaseByNumber(number).get());
        return "purchases/edit_purchase";
    }
    @PostMapping()
    public String createPurchase(@ModelAttribute("newPurchase") Purchase purchase){
        purchase.setPerson(personService.getPersonByLogin(purchase.getPerson().getLogin()).get());
        purchase.setGood(goodService.getGoodById(purchase.getGood().getId()).get());
        purchaseService.createPurchase(purchase);
        return "redirect:/purchases";
    }
    @PostMapping("/{number}/edit")
    public String updatePurchase(@ModelAttribute("editPurchase") Purchase purchase){
        purchase.setPerson(personService.getPersonByLogin(purchase.getPerson().getLogin()).get());
        purchase.setGood(goodService.getGoodById(purchase.getGood().getId()).get());
        purchaseService.updatePurchase(purchase);
        return "redirect:/purchases";
    }
    @PostMapping("/{number}")
    public String deletePurchase(@PathVariable("number") int number){
        purchaseService.deletePurchase(number);
        return "redirect:/purchases";
    }
}
