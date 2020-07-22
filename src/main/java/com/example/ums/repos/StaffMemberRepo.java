package com.example.ums.repos;

import com.example.ums.entities.person.impl.StaffMember;

public interface StaffMemberRepo extends AbstractRepo<StaffMember> {

    long count();
}
