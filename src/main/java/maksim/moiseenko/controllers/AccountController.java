package maksim.moiseenko.controllers;

import maksim.moiseenko.DTO.AccountDto;
import maksim.moiseenko.models.*;
import maksim.moiseenko.repositories.AccountRepository;
import maksim.moiseenko.security.details.UserDetailsImpl;
import maksim.moiseenko.services.AccountService;
import maksim.moiseenko.services.FlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private FlowerService flowerService;

    @GetMapping("/")
    public String main(Authentication authentication, Model model){
        if(authentication==null){
            return "redirect:/signIn";
        }
        UserDetailsImpl userDetails=(UserDetailsImpl)authentication.getPrincipal();
        AccountDto accountDto=AccountDto.from(userDetails.getAccount());
        model.addAttribute("account",accountDto);
        model.addAttribute("flowers",flowerService.findAllFlowers());
        return "Main";
    }

    @GetMapping("/signIn")
    public String sign_inPage(Authentication authentication){
        if(authentication!=null){
            return "redirect:/";
        }
        return "sign_in";
    }
    @GetMapping("/users")
    public String orders(Authentication authentication, Model model){
        if(authentication==null){
            return "redirect:/signIn";
        }
        UserDetailsImpl userDetails=(UserDetailsImpl)authentication.getPrincipal();
        AccountDto accountDto=AccountDto.from(userDetails.getAccount());
        model.addAttribute("account",accountDto);
        model.addAttribute("users", accountService.getAccount(accountDto.getId()));
        return "users";
    }
    @GetMapping("/register")
    public String registerPage(Authentication authentication){
        if(authentication!=null){
            return "redirect:/";
        }
        return "register";
    }

    @GetMapping("/user/{id}/delete")
    public String deleteUser(@PathVariable Long id,Authentication authentication){
        if(authentication==null){
            return "redirect:/signIn";
        }
        UserDetailsImpl userDetails=(UserDetailsImpl)authentication.getPrincipal();
        accountService.deleteById(id);
        if(userDetails.getAccount().getId().equals(id)){
            return "redirect:/logout";
        }
        return "redirect:/users";
    }
    @GetMapping("/user/{id}/edit")
    public String editFormUser(@PathVariable Long id,Model model){
        return accountService.editUserForm(id,model);
    }
    @PostMapping("/user/{id}/edit")
    public String editUser(@PathVariable Long id,
                           @RequestParam String login,
                           @RequestParam String password) {
        accountService.editAccount(id, login, password);
        return "redirect:/users";
    }
    @PostMapping("/register")
    public String sign_upPageFromUser(@RequestParam String login,
                                      @RequestParam String password){
        accountService.save(login,password);
        return "redirect:/signIn";
    }

}
