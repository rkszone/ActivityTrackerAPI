package com.activity.tracker.repository;

import com.activity.tracker.entities.Record;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * IRecordRepository is responsible for Record CRUD operations
 */
@Repository
public interface IRecordRepository extends PagingAndSortingRepository<Record,Long> {
}
