package maksim.moiseenko.services;

import maksim.moiseenko.models.*;
import maksim.moiseenko.repositories.AccountRepository;
import maksim.moiseenko.repositories.CoachDisciplineRepository;
import maksim.moiseenko.repositories.OrganizationCoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CoachService {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private DisciplineService disciplineService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private OrganizationCoachRepository organizationCoachRepository;
    @Autowired
    private CoachDisciplineRepository coachDisciplineRepository;
    public void save(String login,String password,String firstname,String lastname){
        Account account= new Account(login,password, Role.USER, State.ACTIVE,null,null,null);
        Coach coach=new Coach(firstname,lastname);
        account.setCoach(coach);
        accountRepository.save(account);
    }
    public List<Account> findAllCoachs(){
        List<Account> list=accountRepository.findAll();
        List<Account> coachs=new ArrayList<>();
        for(Account account:list){
            if(account.getCoach()!=null)
                coachs.add(account);
        }
        return coachs;
    }
    public void deleteById(Long id){
        if(accountRepository.existsById(id)){
            accountRepository.deleteById(id);
        }
    }
    public String coachAddDisciplines(Long id,Model model){
        if(!accountRepository.existsById(id)) return "redirect:/coachs";
        List<Discipline> addedDisciplines=new ArrayList<>();
        List<Discipline> notAddedDisciplines=disciplineService.findAllDisciplines();
        List<Coach_Discipline> coach_disciplines=accountRepository.findById(id)
                .get()
                .getCoach()
                .getCoach_disciplines();
        for(Coach_Discipline coach_discipline:coach_disciplines ){
            addedDisciplines.add(coach_discipline.getDiscipline());
            if(notAddedDisciplines.contains(coach_discipline.getDiscipline())){
                notAddedDisciplines.remove(coach_discipline.getDiscipline());
            }
        }
        model.addAttribute("id",id);

        model.addAttribute("notAddedDisciplines",notAddedDisciplines);
        model.addAttribute("addedDisciplines",addedDisciplines);
        return "coachAddDiscipline";
    }
    public String editCoachForm(Long id,Model model){
        if(!accountRepository.existsById(id)) return "redirect:/coachs";
        Optional<Account> coach=accountRepository.findById(id);
        ArrayList<Account> coach2=new ArrayList<>();
        coach.ifPresent(coach2::add);
        model.addAttribute("coach",coach2);
        return "coachEdit";
    }
    public void editCoach(Long id,String login,String password,String firstname,String lastname){
        Optional<Account> account2=accountRepository.findById(id);
        if(account2.isPresent()){
            Account account= new Account(id,login,password, Role.USER, State.ACTIVE,null,null,null);
            account2.get().getCoach().setLastname(lastname);
            account2.get().getCoach().setFirstname(firstname);
            account.setCoach(account2.get().getCoach());
            accountRepository.save(account);
        }
    }

    public void addDiscipline(Long id, Long disciplineId) {
        Optional<Account> account2=accountRepository.findById(id);
        Optional<Discipline>discipline=disciplineService.getDiscipline(disciplineId);
        if(account2.isPresent() || discipline.isPresent()){
            Coach_Discipline coach_discipline=new Coach_Discipline(account2.get().getCoach(),discipline.get());
            coachDisciplineRepository.save(coach_discipline);
        }

    }
    public void deleteDiscipline(Long id, Long disciplineId) {
        coachDisciplineRepository.delete(coachDisciplineRepository.findByCoach_IdAndDiscipline_Id(id,disciplineId));
    }

    public String coachAddOrganizations(Long id, Model model) {
        if(!accountRepository.existsById(id)) return "redirect:/coach/"+id+"/organizations";
        List<Account> addedOrganizations=new ArrayList<>();
        List<Account> notAddedOrganizations=organizationService.findAllOrganizations();
        Account coach=accountRepository.findById(id).get();
        List<Organization_Coach> organization_coaches=accountRepository.findById(id)
                .get()
                .getCoach()
                .getOrganization_coach();
        for(Organization_Coach organization_coach:organization_coaches ){
            addedOrganizations.add(organization_coach.getOrganization());
            if(notAddedOrganizations.contains(organization_coach.getOrganization())){
                notAddedOrganizations.remove(organization_coach.getOrganization());
            }
        }
        model.addAttribute("coach",coach);

        model.addAttribute("notAddedOrganizations",notAddedOrganizations);
        model.addAttribute("addedOrganizations",addedOrganizations);
        return "coachAddOrganizations";
    }

    public void addOrganization(Long id, Long organizationId) {
        Optional<Account> account2=accountRepository.findById(id);
        Optional<Account>organization=accountService.getAccount(organizationId);
        if(account2.isPresent() || organization.isPresent()){
            Organization_Coach organization_coach=new Organization_Coach(account2.get(),organization.get());
            organizationCoachRepository.save(organization_coach);
        }
    }

    public void deleteOrganization(Long id, Long organizationId) {
        organizationCoachRepository.delete(organizationCoachRepository.findByCoach_IdAndOrganization_Id(id,organizationId));
    }
}
