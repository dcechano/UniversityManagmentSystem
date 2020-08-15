package com.example.ums.repos.impl;

import com.example.ums.entities.person.impl.StaffMember;
import com.example.ums.repos.StaffMemberRepo;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class StaffMemberRepoImpl extends AbstractRepoImpl<StaffMember> implements StaffMemberRepo {

    public StaffMemberRepoImpl() {
        super(StaffMember.class);
    }

    @Override
    public long count() {
        return (long) entityManager.createQuery("SELECT COUNT(s) FROM StaffMember s").getSingleResult();
    }

}
