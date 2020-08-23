package com.example.ums.service;

import com.example.ums.entities.Role;
import com.example.ums.entities.person.Person;
import com.example.ums.enums.RoleEnum;
import com.example.ums.ex.EntityNotFoundException;
import com.example.ums.repos.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    private PasswordEncoder passwordEncoder;

    private PersonRepo personRepo;

    @Override
    public Person findByUsername(String username) {
        return personRepo.findByUsername(username);
    }

    @Override
    public void save(Person person) {
        personRepo.save(person);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepo.findByUsername(username);
        if (person == null) {
            throw new UsernameNotFoundException("Could not find Person with username " + username);
        }

        Collection<GrantedAuthority> grantedAuthorityRoles = person.getRoles().stream().map(
                role -> new SimpleGrantedAuthority(role.getRole().name())).collect(Collectors.toList());

        return new User(person.getUsername(), person.getPassword(), grantedAuthorityRoles);
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
