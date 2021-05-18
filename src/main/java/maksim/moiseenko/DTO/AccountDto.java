package maksim.moiseenko.DTO;

import lombok.Builder;
import lombok.Data;
import maksim.moiseenko.models.Account;
import maksim.moiseenko.models.Role;
import maksim.moiseenko.security.details.UserDetailsImpl;
@Builder
@Data
public class AccountDto {
    private Role role;
    private Long id;
    public static AccountDto from(Account account){
        return AccountDto.builder()
                .id(account.getId())
                .role(account.getRole())
                .build();
    }
}
