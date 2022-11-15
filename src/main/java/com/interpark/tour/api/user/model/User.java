package com.interpark.tour.api.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(updatable=false)
    private String name;

    @OneToOne(mappedBy = "user")
//    @JsonIgnore
    private Tour tour;


    @OneToMany(mappedBy = "user")
    private List<ViewedCity> viewedCities = new ArrayList<>();

    private String reg_date_f;

    @CreationTimestamp
    private LocalDateTime reg_date;

    @PrePersist
    public void createdAt(){
        LocalDateTime now = LocalDateTime.now();
        String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));
        this.reg_date_f = formatedNow;
    }
}
