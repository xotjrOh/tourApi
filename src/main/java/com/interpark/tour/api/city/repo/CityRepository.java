package com.interpark.tour.api.city.repo;

import com.interpark.tour.api.city.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City,Long> {

    List<City> findAll();

    Optional<City> findById(Long id);
    Optional<City> findByName(String name);

    @Query(value = "SELECT * FROM city WHERE datediff(regDate, SYSDATE()) > -2 order by regDate desc", nativeQuery = true)
    List<City> findAllByRegDateAfterOrderByRegDateDesc();
}
