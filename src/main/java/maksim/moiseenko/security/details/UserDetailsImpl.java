package maksim.moiseenko.security.details;

import maksim.moiseenko.models.Account;
import maksim.moiseenko.models.State;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements UserDetails {
    private Account account;
    public UserDetailsImpl(Account account){
        this.account=account;
    }
    public Account getAccount(){
        return  this.account;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String userRole=account.getRole().name();
        SimpleGrantedAuthority grantedAuthority=new SimpleGrantedAuthority(userRole);
        return Collections.singletonList(grantedAuthority);
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !account.getStatus().equals(State.BANNED);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return account.getStatus().equals(State.ACTIVE);
    }
}
