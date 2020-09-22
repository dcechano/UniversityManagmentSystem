package com.example.ums.service.impl;

import com.example.ums.entities.person.Person;
import com.example.ums.enums.RoleEnum;
import com.example.ums.repos.PersonRepo;
import com.example.ums.repos.RoleRepo;
import com.example.ums.service.PersonService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    Logger logger = Logger.getLogger(PersonServiceImpl.class);

    private PasswordEncoder passwordEncoder;

    private PersonRepo personRepo;

    private RoleRepo roleRepo;

    private DaoAuthenticationProvider authenticationProvider;



    @Override
    public Person findByUsername(String username) {
        return personRepo.findByUsername(username);
    }

    @Override
    @Transactional
    public void save(Person person) {
        logger.info("Inside the PersonService save method!");
        person.setUsername(person.getFirstName().charAt(0) + person.getLastName().toLowerCase());
        person.setRoles(List.of(roleRepo.getRoleByName(RoleEnum.ROLE_STUDENT.name())));
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setVersion(1);
        personRepo.save(person);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //        if (username.equals("Smiller")) {
        //            logger.info("Implimenting hack!");
        //            return new User(username, passwordEncoder.encode("password"), List.of(new SimpleGrantedAuthority(RoleEnum.ROLE_STUDENT.name())));
        //        }

        Person person = this.findByUsername(username);
        if (person == null) {
            throw new UsernameNotFoundException("Person with username: " + username + " could not be found!");
        }
        Collection<GrantedAuthority> grantedAuthorityRoles = person.getRoles().stream().map(
                role -> new SimpleGrantedAuthority(role.getRole().name())).collect(Collectors.toList());

        User user = new User(person.getUsername(), person.getPassword(), grantedAuthorityRoles);
        if (authenticationProvider == null) {
            throw new RuntimeException("DaoAuthenticationProvider was null!");
        } else {
            authenticationProvider.getUserCache().putUserInCache(user);
        }
        return user;
    }

    @Autowired
    public void setRoleRepo(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setPersonRepo(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    public void setAuthenticationProvider(DaoAuthenticationProvider provider) {
        this.authenticationProvider = provider;
    }
}
