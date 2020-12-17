package maksim.moiseenko.services;

import maksim.moiseenko.DateChecker;
import maksim.moiseenko.models.Account;
import maksim.moiseenko.models.Coach;
import maksim.moiseenko.models.Lesson;
import maksim.moiseenko.repositories.AccountRepository;
import maksim.moiseenko.repositories.CoachRepository;
import maksim.moiseenko.repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class LessonService {
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private AccountRepository coachRepository;

    public void create(Long coachId,String date,int hours,int minutes){
        Optional<Account> coach=coachRepository.findById(coachId);
        if(coach.isPresent() ) {
            try {
                Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
                date1.setHours(hours);
                date1.setMinutes(minutes);
                if(DateChecker.dateChecker(date1))
                    lessonRepository.save(new Lesson(coach.get(), date1));

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
    public List<Lesson> findAllByCoachId(Long id){
        return lessonRepository.findAllByCoach_Id(id);
    }
}
