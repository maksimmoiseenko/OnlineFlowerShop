package maksim.moiseenko.controllers;

import maksim.moiseenko.models.Discipline;
import maksim.moiseenko.repositories.DisciplineRepository;
import maksim.moiseenko.services.DisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DisciplineController {

    @Autowired
    private DisciplineService disciplineService;

    @GetMapping("/disciplines")
    public String disciplines(Model model){
        model.addAttribute("disciplines",disciplineService.findAllDisciplines());
        return "disciplines";
    }

    @PostMapping("/discipline/add")
    public String disciplineAdd(@RequestParam String name){
        disciplineService.save(name);
        return "redirect:/disciplines";
    }
    @GetMapping("/discipline/{id}/delete")
    public String deleteDiscipline(@PathVariable Long id){
        disciplineService.delete(id);
        return "redirect:/disciplines";
    }
    @GetMapping("/discipline/{id}/edit")
    public String editDisciplineForm(@PathVariable Long id,Model model){
        return disciplineService.editDisciplineForm(id,model);
    }
    @PostMapping("/discipline/{id}/edit")
    public String editDiscipline(@PathVariable Long id,@RequestParam String name){
        disciplineService.editDiscipline(id,name);
        return "redirect:/disciplines";
    }
}
