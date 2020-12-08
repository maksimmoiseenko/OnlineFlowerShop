package maksim.moiseenko.controllers;

import maksim.moiseenko.models.*;
import maksim.moiseenko.repositories.AccountRepository;
import maksim.moiseenko.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/main")
    public String main(Model model){
        accountService.main(model);
        return "Main";
    }

    @PostMapping("/sign_in")
    public String sign_in(@RequestParam String login,
                          @RequestParam String password){
        return accountService.sign_in(login,password);
    }

    @GetMapping("/sign_in")
    public String sign_inPage(){
        return "sign_in";
    }

    @GetMapping("/register")
    public String registerPage(){
        return "register";
    }

}
