package com.jopov.schoolportal.secutity.repository;

import com.jopov.schoolportal.secutity.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String userName);

    boolean existsByUsername(String username);

    /*@Query("SELECT u FROM User u JOIN FETCH u.roles r WHERE u.name =:name")
    UserDetails findUserByName(@Param("name")String name);*/
}
