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
    @JoinColumn(name = "start_city")
    private City departures;
    @ManyToOne
    @JoinColumn(name = "arrive_city")
    private City arrivals;

    private LocalDateTime start_date;
    private LocalDateTime end_date;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

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
