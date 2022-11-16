package com.interpark.tour.api.tour.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class TourDTO {
    String departuresName;
    String arrivalsName;

    LocalDateTime startDate;
    LocalDateTime endDate;

    Long userId;
}
