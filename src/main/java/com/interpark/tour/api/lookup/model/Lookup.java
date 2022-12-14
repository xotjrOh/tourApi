package com.interpark.tour.api.lookup.model;

import com.interpark.tour.api.city.model.City;
import com.interpark.tour.api.user.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Lookup {
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

    public Lookup(User user, City city) {
        this.user = user;
        this.city = city;
    }
}
