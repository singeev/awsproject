package com.awsproject.backend.persistence.repositories;

import com.awsproject.backend.persistence.domain.backend.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by singeev on 21/11/2017.
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
}
