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
import java.util.Optional;

@Service
public class OrganizationService {
    @Autowired
    private AccountRepository accountRepository;

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
    public void findAllOrganizations(Model model){
        List<Account> list=accountRepository.findAll();
        List<Account> orgs=new ArrayList<>();
        for(Account account: list) {
            if (account.getOrganization() != null)
                orgs.add(account);
        }
        model.addAttribute("organizations",orgs);
    }
}
