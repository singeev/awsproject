package com.awsproject.backend.persistence.repositories;

import com.awsproject.backend.persistence.domain.backend.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by singeev on 21/11/2017.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Returns a User by their username or null if not found.
     * @param username The username
     * @return a User by their username or null if not found.
     */
    public User findByUsername(String username);
}
