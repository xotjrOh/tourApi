package com.interpark.tour.api.tour.model;

import com.interpark.tour.api.city.model.City;
import com.interpark.tour.api.user.model.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "startCity")
    private City departures;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "arriveCity")
    private City arrivals;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

    @CreationTimestamp
    private LocalDateTime regDate;

    public Tour(City departures, City arrivals, LocalDateTime startDate, LocalDateTime endDate, User user) {
        this.departures = departures;
        this.arrivals = arrivals;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
    }
}
