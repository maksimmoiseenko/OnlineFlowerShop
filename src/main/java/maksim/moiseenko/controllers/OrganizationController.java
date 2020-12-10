package maksim.moiseenko.controllers;

import maksim.moiseenko.models.Account;
import maksim.moiseenko.models.Organization;
import maksim.moiseenko.models.Role;
import maksim.moiseenko.models.State;
import maksim.moiseenko.repositories.AccountRepository;
import maksim.moiseenko.services.OrganizationService;
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
public class OrganizationController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private OrganizationService organizationService;
    @GetMapping("/organization/{id}/delete")
    public String deleteOrganization(@PathVariable Long id){
        organizationService.deleteOrganization(id);
        return "redirect:/organizations";
    }
    @GetMapping("/organization/{id}/coachs")
    public String coachAddOrganizations(@PathVariable Long id,Model model){
        return organizationService.organizationAddCoachs(id,model);
    }
    @GetMapping("/organization/{organizationId}/coach/{coachId}/add")
    public String addCoach(@PathVariable Long organizationId,@PathVariable Long coachId){
        organizationService.addCoach(organizationId,coachId);
        return "redirect:/organization/"+organizationId+"/coachs";
    }
    @GetMapping("/organization/{organizationId}/coach/{coachId}/delete")
    public String deleteCoach(@PathVariable Long organizationId,@PathVariable Long coachId){
        organizationService.deleteCoach(coachId,organizationId);
        return "redirect:/organization/"+organizationId+"/coachs";
    }
    @PostMapping("/register/organization")
    public String sign_inPageFromOrganization(@RequestParam String login,
                                              @RequestParam String password,
                                              @RequestParam String name,
                                              @RequestParam String location){
        organizationService.save(login,password,name,location);
        return "redirect:/sign_in";
    }
    @GetMapping("/organization/{id}/edit")
    public String editFormOrganization(@PathVariable Long id,Model model){
        return organizationService.editOrganizationForm(id,model);
    }
    @PostMapping("/organization/{id}/edit")
    public String editOrganization(@PathVariable Long id,
                                   @RequestParam String login,
                                   @RequestParam String password,
                                   @RequestParam String name,
                                   @RequestParam String location){
        organizationService.editOrganization(id,login,password,name,location);
        return "redirect:/organizations";
    }
    @GetMapping("/organizations")
    public String organizations(Model model){
         model.addAttribute("organizations",organizationService.findAllOrganizations());
        return "organizations";
    }
}
