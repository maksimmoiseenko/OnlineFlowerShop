package maksim.moiseenko.controllers;

import maksim.moiseenko.models.Account;
import maksim.moiseenko.models.Role;
import maksim.moiseenko.models.SimpleUser;
import maksim.moiseenko.models.State;
import maksim.moiseenko.repositories.AccountRepository;
import maksim.moiseenko.services.SimpleUserService;
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
public class SimpleUserController {
    @Autowired
    private SimpleUserService simpleUserService;
    @GetMapping("/users")
    public String users(Model model){
       simpleUserService.findAllUsers(model);
        return "users";
    }
    @GetMapping("/user/{id}/delete")
    public String deleteUser(@PathVariable Long id){
        simpleUserService.deleteById(id);
        return "redirect:/users";
    }
    @GetMapping("/user/{id}/edit")
    public String editFormUser(@PathVariable Long id,Model model){
        return simpleUserService.EditUserForm(id,model);
    }
    @PostMapping("/user/{id}/edit")
    public String editUser(@PathVariable Long id,
                            @RequestParam String login,
                            @RequestParam String password,
                            @RequestParam String firstname,
                            @RequestParam String lastname){
        simpleUserService.EditUser(id,login,password,firstname,lastname);
        return "redirect:/users";
    }
    @PostMapping("/register/user")
    public String sign_inPageFromUser(@RequestParam String login,
                                      @RequestParam String password,
                                      @RequestParam  String firstname,
                                      @RequestParam String lastname){
        simpleUserService.save(login,password,firstname,lastname);
        return "redirect:/sign_in";
    }
}
