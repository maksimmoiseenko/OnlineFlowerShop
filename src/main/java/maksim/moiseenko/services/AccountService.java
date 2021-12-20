package maksim.moiseenko.services;

import maksim.moiseenko.models.Account;
import maksim.moiseenko.models.Flower;
import maksim.moiseenko.models.Role;
import maksim.moiseenko.models.State;
import maksim.moiseenko.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Account> getAccount(Long id){
        if(!accountRepository.existsById(id)) return null;
        if(id == 1) return accountRepository.findAll();
        List<Account> list = new ArrayList<>();
        list.add(accountRepository.findById(id).get());
        return list;
    }
    public Account save(String login, String password, String role){
        String hashPassword = passwordEncoder.encode(password);
        Role role1 = null;
        if(role.equals("user"))
            role1 = Role.USER;
        if(role.equals("courier"))
            role1 = Role.COURIER;
        Account account = new Account(login,hashPassword, role1, State.ACTIVE);
        return accountRepository.save(account);
    }
    public void deleteById(long id){
        accountRepository.deleteById(id);
    }
    public String editUserForm(Long id, Model model){
        if(!accountRepository.existsById(id)) return "redirect:/users";
        Optional<Account> account=accountRepository.findById(id);
        ArrayList<Account> account1=new ArrayList<>();
        account.ifPresent(account1 :: add);
        model.addAttribute("account",account1);
        return "accountEdit";
    }

    public void editAccount(Long id,String login,String password){
        if(!accountRepository.existsById(id)) return;
        Account account=accountRepository.findById(id).get();
        account.setLogin(login);
        account.setPassword(passwordEncoder.encode(password));
        accountRepository.save(account);
    }
}
