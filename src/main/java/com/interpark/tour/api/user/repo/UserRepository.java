package com.interpark.tour.api.user.repo;

import com.interpark.tour.api.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findAll();

    Optional<User> findById(Long id);
    Optional<User> findByName(String name);

}
