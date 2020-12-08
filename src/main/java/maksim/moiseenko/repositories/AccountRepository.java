package maksim.moiseenko.repositories;

import maksim.moiseenko.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    public Account findAccountByLoginAndPassword(String login,String password);
    //public List<Account> findAllByCoachExists();
  //  public List<Account> findAllByOrganizationExists();
}
