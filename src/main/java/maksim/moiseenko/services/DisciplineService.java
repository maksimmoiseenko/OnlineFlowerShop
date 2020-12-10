package maksim.moiseenko.services;

import maksim.moiseenko.models.Account;
import maksim.moiseenko.models.Discipline;
import maksim.moiseenko.repositories.DisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DisciplineService {
    @Autowired
    private DisciplineRepository disciplineRepository;
    public List<Discipline> findAllDisciplines(){
        return disciplineRepository.findAll();
    }
    public void save(String name){
        disciplineRepository.save(new Discipline(name));
    }
    public void delete(Long id){
        if(disciplineRepository.existsById(id))
            disciplineRepository.deleteById(id);
    }
    public Optional<Discipline> getDiscipline(Long id){
        if(!disciplineRepository.existsById(id)) return null;
        return disciplineRepository.findById(id);

    }
    public String editDisciplineForm(Long id, Model model){
        if(!disciplineRepository.existsById(id)) return "redirect:/disciplines";
        Optional<Discipline> discipline=disciplineRepository.findById(id);
        ArrayList<Discipline> discipline1=new ArrayList<>();
        discipline.ifPresent(discipline1 :: add);
        model.addAttribute("discipline",discipline1);
        return "disciplineEdit";
    }

    public void editDiscipline(Long id,String name){
        Discipline discipline=new Discipline();
        discipline.setId(id);
        discipline.setName(name);
        disciplineRepository.save(discipline);
    }
}
