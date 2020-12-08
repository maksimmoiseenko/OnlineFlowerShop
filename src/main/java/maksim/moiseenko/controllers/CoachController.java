package maksim.moiseenko.controllers;

import maksim.moiseenko.models.Account;
import maksim.moiseenko.models.Coach;
import maksim.moiseenko.models.Role;
import maksim.moiseenko.models.State;
import maksim.moiseenko.repositories.AccountRepository;
import maksim.moiseenko.repositories.CoachRepository;
import maksim.moiseenko.services.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CoachController {

    @Autowired
    private CoachService coachService;
    @GetMapping("/coachs")
    public String coachs(Model model){
        coachService.findAllCoachs(model);
        return "coachs";
    }
    @GetMapping("/coach/{id}/disciplines")
    public String coachAddDisciplines(@PathVariable Long id,Model model){

        return coachService.coachAddDisciplines(id,model);
    }
    @PostMapping("/coach/{id}/disciplines")
    public void addDisciplines(@PathVariable Long id){

    }
    @GetMapping("/coach/{id}/delete")
    public String deleteCoach(@PathVariable Long id){
        coachService.deleteById(id);
        return "redirect:/coachs";
    }
    @PostMapping("/register/coach")
    public String signInPageFromCoach(@RequestParam String login,
                                       @RequestParam String password,
                                       @RequestParam String firstname,
                                       @RequestParam String lastname){
        coachService.save(login,password,firstname,lastname);
        return "redirect:/sign_in";
    }
    @GetMapping("/coach/{id}/edit")
    public String editFormCoach(@PathVariable Long id,Model model){
        return coachService.editCoachForm(id,model);

    }
    @PostMapping("/coach/{id}/edit")
    public String editCoach(@PathVariable Long id,
                                   @RequestParam String login,
                                   @RequestParam String password,
                                   @RequestParam String firstname,
                                   @RequestParam String lastname){
        coachService.editCoach(id,login,password,firstname,lastname);
        return "redirect:/coachs";
    }
}
