package com.example.ums.repos.impl;

import com.example.ums.entities.Tour;
import com.example.ums.repos.TourRepo;
import org.springframework.stereotype.Repository;

@Repository
public class TourRepoImpl extends AbstractRepoImpl<Tour> implements TourRepo {

    public TourRepoImpl() {
        super(Tour.class);
    }
}
