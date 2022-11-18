package com.interpark.tour.api.city.repository;

import com.interpark.tour.api.city.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City,Long> {

    List<City> findAll();

    Optional<City> findById(Long id);
    Optional<City> findByName(String name);

    @Query(value = "SELECT name FROM city WHERE datediff(regDate, SYSDATE()) > -2 order by regDate desc", nativeQuery = true)
    List<String> findAllByRegDateAfterOrderByRegDateDesc();
}
