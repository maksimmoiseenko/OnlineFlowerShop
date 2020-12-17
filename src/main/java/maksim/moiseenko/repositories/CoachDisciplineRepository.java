package maksim.moiseenko.repositories;

import maksim.moiseenko.models.Coach_Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoachDisciplineRepository extends JpaRepository<Coach_Discipline,Long> {
    Coach_Discipline findByCoach_IdAndDiscipline_Id(Long coachId,Long disciplineId);
    List<Coach_Discipline> findAllByCoach_Id(Long id);
}
