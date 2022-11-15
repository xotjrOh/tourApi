package com.interpark.tour.api.tour.model;

import com.interpark.tour.api.city.model.City;
import com.interpark.tour.api.user.model.User;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "startCity")
    private City departures;
    @ManyToOne
    @JoinColumn(name = "arriveCity")
    private City arrivals;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private String regDate_f;

    @CreationTimestamp
    private LocalDateTime regDate;

    @PrePersist
    public void createdAt(){
        LocalDateTime now = LocalDateTime.now();
        String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));
        this.regDate_f = formatedNow;
    }

    public Tour(City departures, City arrivals, LocalDateTime startDate, LocalDateTime endDate, User user) {
        this.departures = departures;
        this.arrivals = arrivals;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
    }
}
