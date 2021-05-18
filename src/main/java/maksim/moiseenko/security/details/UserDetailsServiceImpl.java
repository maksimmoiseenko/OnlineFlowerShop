package maksim.moiseenko.security.details;

import maksim.moiseenko.models.Account;
import maksim.moiseenko.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Account> userCandidate=accountRepository.findOneByLogin(s);
        if(userCandidate.isPresent()){
            return new UserDetailsImpl(userCandidate.get());
        }
        else throw  new IllegalStateException("User not found");
    }

}
