package com.interpark.tour.api.city.model;

import com.interpark.tour.api.lookup.model.ViewedCity;
import com.interpark.tour.api.tour.model.Tour;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    private String regDate_f;

    @CreationTimestamp
    private LocalDateTime regDate;

    @PrePersist
    public void createdAt(){
        LocalDateTime now = LocalDateTime.now();
        String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));
        this.regDate_f = formatedNow;
    }

    public City(String name) {
        this.name = name;
    }
}
