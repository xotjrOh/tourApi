package com.interpark.tour.api.tour.repository;

import com.interpark.tour.api.city.model.City;
import com.interpark.tour.api.tour.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TourRepository extends JpaRepository<Tour,Long> {

    List<Tour> findAll();

    Optional<Tour> findById(Long id);

    List<Tour> findAllByDepartures(City city);
    List<Tour> findAllByArrivals(City city);

    @Query(value = "SELECT arriveCity FROM tour WHERE SYSDATE() between startDate and endDate and userId = :userId order by startDate", nativeQuery = true)
    List<Long> findAllByTravelingCity(Long userId);

    @Query(value = "SELECT arriveCity FROM tour WHERE SYSDATE() < startDate and userId = :userId order by startDate", nativeQuery = true)
    List<Long> findAllByPlanCity(Long userId);
}
