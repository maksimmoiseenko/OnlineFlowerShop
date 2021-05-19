package maksim.moiseenko.controllers;

import maksim.moiseenko.DTO.AccountDto;
import maksim.moiseenko.security.details.UserDetailsImpl;
import maksim.moiseenko.services.FlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FlowerController {

    @Autowired
    private FlowerService flowerService;


    @PostMapping("/flower/add")
    public String flowerAdd(@RequestParam String name, @RequestParam int cost){
        flowerService.save(name,cost);
        return "redirect:/";
    }
    @GetMapping("/flower/{id}/delete")
    public String deleteFlower(@PathVariable Long id){
        flowerService.delete(id);
        return "redirect:/";
    }
    @GetMapping("/flower/{id}/edit")
    public String editFlowerForm(@PathVariable Long id,Model model){
        return flowerService.editFlowerForm(id,model);
    }
    @PostMapping("/flower/{id}/edit")
    public String editFlower(@PathVariable Long id,@RequestParam String name,@RequestParam int cost){
        flowerService.editFlower(id,name,cost);
        return "redirect:/";
    }
}
