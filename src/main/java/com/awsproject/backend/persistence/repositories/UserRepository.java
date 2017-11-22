package com.awsproject.backend.persistence.repositories;

import com.awsproject.backend.persistence.domain.backend.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by singeev on 21/11/2017.
 */
@Repository
@Transactional(readOnly = true)
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Returns a User by their username or null if not found.
     * @param username The username
     * @return a User by their username or null if not found.
     */
    User findByUsername(String username);

    /**
     * Returns a User by their email or null if not found.
     * @param email The user email
     * @return a User by their email or null if not found.
     */
    User findByEmail(String email);

    @Modifying
    @Query("update User u set u.password = :password where u.id = :userId")
    void updateUserPassword(@Param("userId") long userId, @Param("password") String password);
}
