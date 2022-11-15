package com.interpark.tour.api.city.repo;

import com.interpark.tour.api.city.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City,Long> {

    List<City> findAll();

    Optional<City> findById(Long id);
    Optional<City> findByName(String name);


}
