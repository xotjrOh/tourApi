package com.interpark.tour.api.city.model;

import com.interpark.tour.api.lookup.model.ViewedCity;
import com.interpark.tour.api.tour.model.Tour;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(updatable=false)
    private String name;

    @OneToMany(mappedBy = "departures")
    private List<Tour> startCities = new ArrayList<>();
    @OneToMany(mappedBy = "arrivals")
    private List<Tour> arriveCities = new ArrayList<>();

    @OneToMany(mappedBy = "city")
    private List<ViewedCity> viewedCities = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime regDate;

    public City(String name) {
        this.name = name;
    }
}
