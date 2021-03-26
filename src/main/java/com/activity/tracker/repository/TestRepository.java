package com.activity.tracker.repository;

import com.activity.tracker.models.entities.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {
}
