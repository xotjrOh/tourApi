package com.interpark.tour.api.city.model;

import com.interpark.tour.api.lookup.model.Lookup;
import com.interpark.tour.api.tour.model.Tour;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "departures", fetch = FetchType.LAZY)
    private List<Tour> startCities = new ArrayList<>();
    @OneToMany(mappedBy = "arrivals", fetch = FetchType.LAZY)
    private List<Tour> arriveCities = new ArrayList<>();

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    private List<Lookup> viewedCities = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime regDate;

    public City(String name) {
        this.name = name;
    }
}
