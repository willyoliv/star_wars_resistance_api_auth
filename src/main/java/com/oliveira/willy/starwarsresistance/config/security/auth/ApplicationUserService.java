package com.oliveira.willy.starwarsresistance.config.security.auth;

import com.oliveira.willy.starwarsresistance.model.Rebel;
import com.oliveira.willy.starwarsresistance.repository.RebelRepository;
import lombok.extern.slf4j.Slf4j;
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

    public ApplicationUserService(RebelRepository rebelRepository) {
        this.rebelRepository = rebelRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Rebel rebel = rebelRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found in the database"));

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(rebel.getRole().name()));

        ApplicationUser applicationUser = new ApplicationUser(
                username,
                rebel.getPassword(),
                authorities,
                true,
                true,
                true,
                true
        );

        return applicationUser;
    }
}
