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
        model.addAttribute("coachs",coachService.findAllCoachs());
        return "coachs";
    }
    @GetMapping("/coach/{id}/disciplines")
    public String coachAddDisciplines(@PathVariable Long id,Model model){

        return coachService.coachAddDisciplines(id,model);
    }
    @GetMapping("/coach/{id}/discipline/{disciplineId}/add")
    public String addDiscipline(@PathVariable Long id,@PathVariable Long disciplineId){
        coachService.addDiscipline(id,disciplineId);
        return "redirect:/coach/"+id+"/disciplines";
    }
    @GetMapping("/coach/{id}/discipline/{disciplineId}/delete")
    public String deleteDiscipline(@PathVariable Long id,@PathVariable Long disciplineId){
        coachService.deleteDiscipline(id,disciplineId);
        return "redirect:/coach/"+id+"/disciplines";
    }
    @GetMapping("/coach/{id}/organizations")
    public String coachAddOrganizations(@PathVariable Long id,Model model){
        return coachService.coachAddOrganizations(id,model);
    }
    @GetMapping("/coach/{id}/organization/{organizationId}/add")
    public String addOrganization(@PathVariable Long id,@PathVariable Long organizationId){
        coachService.addOrganization(id,organizationId);
        return "redirect:/coach/"+id+"/organizations";
    }
    @GetMapping("/coach/{id}/organization/{organizationId}/delete")
    public String deleteOrganization(@PathVariable Long id,@PathVariable Long organizationId){
        coachService.deleteOrganization(id,organizationId);
        return "redirect:/coach/"+id+"/organizations";
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
