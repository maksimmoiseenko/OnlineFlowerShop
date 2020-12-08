package maksim.moiseenko.services;

import maksim.moiseenko.models.*;
import maksim.moiseenko.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CoachService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private DisciplineService disciplineService;
    public void save(String login,String password,String firstname,String lastname){
        Account account= new Account(login,password, Role.USER, State.ACTIVE,null,null,null);
        Coach coach=new Coach(firstname,lastname);
        account.setCoach(coach);
        accountRepository.save(account);
    }
    public void findAllCoachs(Model model){
        List<Account> list=accountRepository.findAll();
        List<Account> coachs=new ArrayList<>();
        for(Account account:list){
            if(account.getCoach()!=null)
                coachs.add(account);
        }
        model.addAttribute("coachs",coachs);
    }
    public void deleteById(Long id){
        if(accountRepository.existsById(id)){
            accountRepository.deleteById(id);
        }
    }
    public String coachAddDisciplines(Long id,Model model){
        if(!accountRepository.existsById(id)) return "redirect:/coachs";
        List<Discipline> disciplines=disciplineService.findAllDisciplines();
        model.addAttribute("id",id);
        model.addAttribute("disciplines",disciplines);
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
}
