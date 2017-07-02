package com.harsain.hateoas.Repository;

import com.harsain.hateoas.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by harsain on 3/7/17.
 */
public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByName(String name);
    Optional<User> findById(String name);
}
