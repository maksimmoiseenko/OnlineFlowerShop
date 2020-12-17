package maksim.moiseenko.repositories;

import maksim.moiseenko.models.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LessonRepository extends JpaRepository<Lesson,Long> {
    List<Lesson> findAllByCoach_Id(Long id);
}
