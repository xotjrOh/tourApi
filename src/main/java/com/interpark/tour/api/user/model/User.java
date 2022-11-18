package com.interpark.tour.api.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.interpark.tour.api.lookup.model.Lookup;
import com.interpark.tour.api.tour.model.Tour;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(updatable=false)
    private String name;

    @NotNull
    @Column(unique = true)
    private String nickName;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Tour> tours = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Lookup> viewedCities = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime regDate;

    public User(String name, String nickName) {
        this.name = name;
        this.nickName = nickName;
    }
}
