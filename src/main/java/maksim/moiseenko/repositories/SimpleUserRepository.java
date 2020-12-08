package maksim.moiseenko.repositories;

import maksim.moiseenko.models.SimpleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimpleUserRepository extends JpaRepository<SimpleUser,Long> {
}
