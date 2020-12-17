package maksim.moiseenko.repositories;

import maksim.moiseenko.models.Organization_Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizationCoachRepository extends JpaRepository<Organization_Coach,Long> {
    Organization_Coach findByCoach_IdAndOrganization_Id(Long organizationId,Long coachId);
    List<Organization_Coach> findAllByOrganization_Id(Long organizationId);
    List<Organization_Coach> findAllByCoach_Id(Long coachId);
}
