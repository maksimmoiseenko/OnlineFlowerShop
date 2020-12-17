package maksim.moiseenko.controllers;

import maksim.moiseenko.services.CoachService;
import maksim.moiseenko.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class LessonController {
    @Autowired
    private LessonService lessonService;
    @Autowired
    private CoachService coachService;

    @PostMapping("/lessons/{coachId}")
    public String create(@PathVariable Long coachId,
                         @RequestParam String date,
                         @RequestParam int hours,
                         @RequestParam int minutes){
        System.out.println(date);
        System.out.println(hours);
        System.out.println(minutes);



        if(coachService.existsById(coachId)){
            lessonService.create(coachId,date,hours,minutes);
            return "redirect:/lessons/"+coachId;
        }
        else return "redirect:/coachs";

    }
   @GetMapping("/lessons/{id}")
    public String lessons(@PathVariable Long id,Model model){
        if(coachService.existsById(id)){
            model.addAttribute("lessons",lessonService.findAllByCoachId(id));
            return "lessons";
        }
        else return "redirect:/coachs";

    }
}
