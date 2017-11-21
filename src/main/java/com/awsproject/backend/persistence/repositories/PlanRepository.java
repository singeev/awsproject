package com.awsproject.backend.persistence.repositories;

import com.awsproject.backend.persistence.domain.backend.Plan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by singeev on 21/11/2017.
 */
@Repository
public interface PlanRepository extends CrudRepository<Plan, Integer> {
}
