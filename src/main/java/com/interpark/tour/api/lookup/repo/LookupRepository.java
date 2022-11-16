package com.interpark.tour.api.lookup.repo;

import com.interpark.tour.api.lookup.model.Lookup;
import com.interpark.tour.api.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LookupRepository extends JpaRepository<Lookup,Long> {

    List<Lookup> findAll();
    List<Lookup> findByUser(User user);

    Optional<Lookup> findById(Long id);

//    @Query(value = "SELECT * FROM tour WHERE userId = :userId", nativeQuery = true)
//    List<City> findAllByTravelingCity(Long userId);
}
