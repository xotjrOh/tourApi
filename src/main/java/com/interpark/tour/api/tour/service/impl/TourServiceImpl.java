package com.interpark.tour.api.tour.service.impl;

import com.interpark.tour.api.city.model.City;
import com.interpark.tour.api.city.repository.CityRepository;
import com.interpark.tour.api.tour.model.Tour;
import com.interpark.tour.api.tour.model.TourDTO;
import com.interpark.tour.api.tour.repository.TourRepository;
import com.interpark.tour.api.tour.service.TourService;
import com.interpark.tour.api.user.model.User;
import com.interpark.tour.api.user.repository.UserRepository;
import com.interpark.tour.common.exception.TourException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TourServiceImpl implements TourService {

    private final TourRepository tourRepository;
    private final CityRepository cityRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Tour> tourAll() {

        return tourRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Tour tourById(Long tourId) {

        Tour tour = tourRepository.findById(tourId).orElseThrow(() -> new TourException(tourId + "에 해당하는 여행이 없습니다"));

        return tour;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Tour tourCreate(TourDTO tourDTO) {

        String departuresName = tourDTO.getDeparturesName();
        String arrivalsName = tourDTO.getArrivalsName();
        LocalDateTime startDate = tourDTO.getStartDate();
        LocalDateTime endDate = tourDTO.getEndDate();
        Long userId = tourDTO.getUserId();
        if (departuresName == null || arrivalsName == null || startDate == null || endDate == null || userId == null){
            throw new TourException("departuresName, arrivalsName, startDate, endDate, userId 에 해당하는 json데이터를 보내주세요");
        }

        City departuresCity = cityRepository.findByName(departuresName).orElseThrow(() -> new TourException(departuresName + "(이)라는 도시는 없습니다"));
        City arrivalsCity = cityRepository.findByName(arrivalsName).orElseThrow(() -> new TourException(arrivalsName + "(이)라는 도시는 없습니다"));
        User user = userRepository.findById(userId).orElseThrow(() -> new TourException(userId + "에 해당하는 유저가 없습니다"));

        // 여행 종료일은 미래만 허용
        if (endDate.isBefore(startDate)){
            throw new TourException("여행 종료일은 미래만 허용");
        }

        Tour tour = tourRepository.save(new Tour(departuresCity,arrivalsCity,startDate,endDate,user));

        return tour;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Tour tourUpdate(Long tourId, TourDTO tourDTO) {

        Tour tour = tourRepository.findById(tourId).orElseThrow(() -> new TourException(tourId + "에 해당하는 여행이 없습니다"));

        // null 값을 제외하고 수정
        String departuresName = tourDTO.getDeparturesName();
        if (departuresName != null){
            City departuresCity = cityRepository.findByName(departuresName).orElseThrow(() -> new TourException(departuresName + "(이)라는 도시는 없습니다"));
            tour.setDepartures(departuresCity);
        }
        String arrivalsName = tourDTO.getArrivalsName();
        if (arrivalsName != null){
            City departuresCity = cityRepository.findByName(departuresName).orElseThrow(() -> new TourException(departuresName + "(이)라는 도시는 없습니다"));
            tour.setDepartures(departuresCity);
        }
        LocalDateTime startDate = tourDTO.getStartDate();
        if (startDate != null){
            tour.setStartDate(startDate);
        }
        LocalDateTime endDate = tourDTO.getEndDate();
        if (endDate != null){
            tour.setEndDate(endDate);
        }
        Long userId = tourDTO.getUserId();
        if (userId != null){
            User user = userRepository.findById(userId).orElseThrow(() -> new TourException(userId + "에 해당하는 유저가 없습니다"));
            tour.setUser(user);
        }

        // 여행 종료일은 미래만 허용
        if (tour.getEndDate().isBefore(tour.getStartDate())){
            throw new TourException("여행 종료일은 미래만 허용");
        }

        return tour;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean tourDelete(Long tourId) {

        Tour tour = tourRepository.findById(tourId).orElseThrow(() -> new TourException(tourId + "에 해당하는 여행이 없습니다"));

        tourRepository.delete(tour);

        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getTravelingCities(Long userId){

        List<String> cityNames = tourRepository.findAllByTravelingCity(userId)
                                                .stream().map(cityRepository::findById)
                                                .map(opt->opt.get())
                                                .map(city->city.getName())
                                                .collect(Collectors.toList());

        return cityNames;
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getPlanCities(Long userId){

        List<String> cityNames = tourRepository.findAllByPlanCity(userId)
                                                .stream().map(cityRepository::findById)
                                                .map(opt->opt.get())
                                                .map(city->city.getName())
                                                .collect(Collectors.toList());
        return cityNames;
    }
}
