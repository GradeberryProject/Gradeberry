package content.com.ua.service.impl;

import content.com.ua.entities.DBUser;
import content.com.ua.service.DBUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private DBUserService userService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        DBUser dbUser = userService.findByLogin(login);
        if (dbUser == null)
            throw new UsernameNotFoundException(login + " not found");

        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(dbUser.getRole().toString()));

        return new User(dbUser.getLogin(), dbUser.getPassword(), roles);
    }
}