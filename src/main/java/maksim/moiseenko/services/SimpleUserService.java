package maksim.moiseenko.services;

import maksim.moiseenko.models.Account;
import maksim.moiseenko.models.Role;
import maksim.moiseenko.models.SimpleUser;
import maksim.moiseenko.models.State;
import maksim.moiseenko.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SimpleUserService {
    @Autowired
    private AccountRepository accountRepository;
    public void save(String login,String password,String firstname,String lastname){
        Account account= new Account(login,password, Role.USER, State.ACTIVE,null,null,null);
        SimpleUser simpleUser=new SimpleUser(firstname,lastname);
        account.setUser(simpleUser);
        accountRepository.save(account);
    }
    public void findAllUsers(Model model){
        List<Account> list=accountRepository.findAll();
        List<Account> users=new ArrayList<>();
        for(Account account:list){
            if(account.getUser()!=null)
                users.add(account);
        }
        model.addAttribute("users",users);
    }
    public void deleteById(Long id){
        if(accountRepository.existsById(id)){
            accountRepository.deleteById(id);
        }
    }
    public String EditUserForm(Long id,Model model){
        if(!accountRepository.existsById(id)) return "redirect:/users";
        Optional<Account> user=accountRepository.findById(id);
        ArrayList<Account> user2=new ArrayList<>();
        user.ifPresent(user2::add);
        model.addAttribute("user",user2);
        return "userEdit";
    }
    public void EditUser(Long id,String login,String password,String firstname,String lastname){
        Optional<Account> account2=accountRepository.findById(id);
        if(account2.isPresent()){
            Account account= new Account(id,login,password, Role.USER, State.ACTIVE,null,null,null);
            account2.get().getUser().setLastname(lastname);
            account2.get().getUser().setFirstname(firstname);
            account.setUser(account2.get().getUser());
            accountRepository.save(account);
        }
    }
}
