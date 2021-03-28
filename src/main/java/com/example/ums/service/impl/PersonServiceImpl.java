package com.example.ums.service.impl;

import com.example.ums.entities.person.Person;
import com.example.ums.repos.PersonRepo;
import com.example.ums.repos.RoleRepo;
import com.example.ums.service.PersonService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    Logger logger = Logger.getLogger(PersonServiceImpl.class);

    private PasswordEncoder passwordEncoder;

    private PersonRepo personRepo;

    private RoleRepo roleRepo;

    @Override
    public Person findByUsername(String username) {
        return personRepo.findByUsername(username);
    }

    @Override
    @Transactional
    public void save(Person person) {
        logger.info("Inside the PersonService save method!");
        person.setUsername(person.getFirstName().charAt(0) + person.getLastName().toLowerCase());
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        personRepo.save(person);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = this.findByUsername(username);
        if (person == null) {
            throw new UsernameNotFoundException("Person with username: " + username + " could not be found!");
        }
        Collection<GrantedAuthority> grantedAuthorityRoles = person.getRoles().stream().map(
                role -> new SimpleGrantedAuthority(role.getRole().name())).collect(Collectors.toList());

        return new User(person.getUsername(), person.getPassword(), grantedAuthorityRoles);
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

}
