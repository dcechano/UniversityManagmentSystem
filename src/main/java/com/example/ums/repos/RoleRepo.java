package com.example.ums.repos;

import com.example.ums.entities.Role;

import java.util.List;

public interface RoleRepo {

    Role getRoleByName(String name);

    List<Role> getRolesList();

}
