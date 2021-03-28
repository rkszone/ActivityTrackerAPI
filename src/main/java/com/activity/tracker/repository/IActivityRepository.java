package com.activity.tracker.repository;

import com.activity.tracker.entities.Activity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * IActivityRepository is responsible for Activity CRUD operations
 */
@Repository
public interface IActivityRepository extends PagingAndSortingRepository<Activity,Long> {
}
