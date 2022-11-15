package com.interpark.tour.api.lookup.model;

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
public class ViewedCity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId", updatable=false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "cityId", updatable=false)
    private City city;

    @CreationTimestamp
    private LocalDateTime recentViewed;
}
