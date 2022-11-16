package com.interpark.tour.api.tour.repo;

import com.interpark.tour.api.tour.model.Tour;
import com.interpark.tour.api.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TourRepository extends JpaRepository<Tour,Long> {

    List<Tour> findAll();
    List<Tour> findByUser(User user);

    Optional<Tour> findById(Long id);

//    @Query(value = "SELECT * FROM tour WHERE userId = :userId", nativeQuery = true)
//    List<City> findAllByTravelingCity(Long userId);
}
