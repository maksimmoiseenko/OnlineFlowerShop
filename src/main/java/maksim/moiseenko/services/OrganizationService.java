package maksim.moiseenko.services;

import maksim.moiseenko.models.*;
import maksim.moiseenko.repositories.AccountRepository;
import maksim.moiseenko.repositories.OrganizationCoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrganizationService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CoachService coachService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private OrganizationCoachRepository organizationCoachRepository;
    public void save(String login,String password,String name,String location){
        Account account= new Account(login,password, Role.USER, State.ACTIVE,null,null,null);
        Organization organization=new Organization(name,location);
        account.setOrganization(organization);
        accountRepository.save(account);
    }
    public void deleteOrganization(Long id){
        if(accountRepository.existsById(id)){
            accountRepository.deleteById(id);
        }
    }
    public String editOrganizationForm(Long id, Model model){
        if(!accountRepository.existsById(id)) return "redirect:/organizations";
        Optional<Account> organization=accountRepository.findById(id);
        ArrayList<Account> org=new ArrayList<>();
        organization.ifPresent(org::add);
        model.addAttribute("organization",org);
        return "organizationEdit";
    }
    public void editOrganization(Long id,String login,String password,String name,String location){
        Optional<Account> account2=accountRepository.findById(id);
        if(account2.isPresent()){
            Account account= new Account(id,login,password, Role.USER, State.ACTIVE,null,null,null);
            account2.get().getOrganization().setLocation(location);
            account2.get().getOrganization().setName(name);
            account.setOrganization(account2.get().getOrganization());

            accountRepository.save(account);
        }
    }
    public List<Account> findAllOrganizations(){
        List<Account> list=accountRepository.findAll();
        List<Account> orgs=new ArrayList<>();
        for(Account account: list) {
            if (account.getOrganization() != null)
                orgs.add(account);
        }
        return orgs;
    }
    public String organizationAddCoachs(Long id, Model model) {
        if(!accountRepository.existsById(id)) return "redirect:/organization/"+id+"/coachs";
        List<Account> addedCoachs=new ArrayList<>();
        List<Account> notAddedCoachs=coachService.findAllCoachs();
        Account organization=accountRepository.findById(id).get();
        List<Organization_Coach> organization_coaches=organizationCoachRepository.findAllByOrganization_Id(id);
        for(Organization_Coach organization_coach:organization_coaches ){
            addedCoachs.add(organization_coach.getCoach());
            if(notAddedCoachs.contains(organization_coach.getCoach())){
                notAddedCoachs.remove(organization_coach.getCoach());
            }
        }
        model.addAttribute("organization",organization);

        model.addAttribute("notAddedCoachs",notAddedCoachs);
        model.addAttribute("addedCoachs",addedCoachs);
        return "organizationAddCoach";
    }

    public void addCoach(Long organizationId, Long coachId) {
        Optional<Account> organization=accountRepository.findById(organizationId);
        Optional<Account>coach=accountService.getAccount(coachId);
        if(organization.isPresent() || coach.isPresent()){
            Organization_Coach organization_coach=new Organization_Coach(coach.get(),organization.get());
            organizationCoachRepository.save(organization_coach);
        }
    }

    public void deleteCoach(Long coachId, Long organizationId) {
        organizationCoachRepository.delete(organizationCoachRepository.findByCoach_IdAndOrganization_Id(coachId,organizationId));
    }
}
