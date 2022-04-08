package com.oliveira.willy.starwarsresistence.auth;

import com.oliveira.willy.starwarsresistence.model.Rebel;
import com.oliveira.willy.starwarsresistence.repository.RebelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
@Slf4j
public class ApplicationUserService implements UserDetailsService {

    private final RebelRepository rebelRepository;

    @Autowired
    public ApplicationUserService(RebelRepository rebelRepository) {
        this.rebelRepository = rebelRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Rebel rebel = rebelRepository.findByUsername(username);

        if (rebel == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database: {}", username);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(rebel.getRole().name()));

        return new ApplicationUser(
                username,
                rebel.getName(),
                authorities,
                true,
                true,
                true,
                true
        );
    }
}
