package co.edu.cue.easy_vote.security;

import co.edu.cue.easy_vote.domain.entities.Administrator;
import co.edu.cue.easy_vote.infrastructure.repository.AdministratorRepository;
import co.edu.cue.easy_vote.infrastructure.util.Validation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserSecurityService implements UserDetailsService {
    @Autowired
    private AdministratorRepository administratorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Administrator user = administratorRepository.findAdministratorByUsername(username);
        if (!Validation.isNull(user)){
           return org.springframework.security.core.userdetails.User.builder().username(user.getUsername()).password(user.getPassword()).roles(getRole(user))
                    .build();
        }else throw new UsernameNotFoundException(username);
    }

    private String getRole(Administrator user) {
        return "Administrator";
    }
}
