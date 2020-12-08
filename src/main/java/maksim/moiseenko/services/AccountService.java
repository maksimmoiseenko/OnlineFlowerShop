package maksim.moiseenko.services;

import maksim.moiseenko.models.Account;
import maksim.moiseenko.models.Organization;
import maksim.moiseenko.models.Role;
import maksim.moiseenko.models.State;
import maksim.moiseenko.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public void main(Model model){
        List<Account> accountList= accountRepository.findAll();
        List<Account> organizations=new ArrayList<>();
        List<Account> coachs=new ArrayList<>();
        int count1=0,count2=0;
        for(Account account:accountList) {
            if(count1==3 && count2==3) break;
            if(account.getCoach()!=null && count2<=3){
                coachs.add(account);
                count2++;
            }
            if (account.getOrganization() != null &&count1<=3) {
                organizations.add(account);
                count1++;
            }
        }
        model.addAttribute("organizations",organizations);
        model.addAttribute("coachs",coachs);
    }
    public String sign_in(String login,String password){
        if(accountRepository.findAccountByLoginAndPassword(login,password)!=null)
            return "redirect:/main";
        return "redirect:/sign_in";
    }

}
