package com.interpark.tour.api.lookup.svc.impl;

import com.interpark.tour.api.city.model.City;
import com.interpark.tour.api.city.repo.CityRepository;
import com.interpark.tour.api.lookup.model.Lookup;
import com.interpark.tour.api.lookup.model.LookupDTO;
import com.interpark.tour.api.lookup.repo.LookupRepository;
import com.interpark.tour.api.lookup.svc.LookupService;
import com.interpark.tour.api.user.model.User;
import com.interpark.tour.api.user.repo.UserRepository;
import com.interpark.tour.cmm.exception.LookupException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LookupServiceImpl implements LookupService {

    private final LookupRepository lookupRepository;
    private final UserRepository userRepository;
    private final CityRepository cityRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Lookup> lookupAll() {

        return lookupRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Lookup lookupById(Long lookupId) {

        Lookup lookup = lookupRepository.findById(lookupId).orElseThrow(() -> new LookupException(lookupId + "에 해당하는 Lookup이 없습니다"));

        return lookup;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Lookup lookupCreate(LookupDTO lookupDTO) {

        Long userId = lookupDTO.getUserId();
        Long cityId = lookupDTO.getCityId();
        if (userId == null || cityId == null){
            throw new LookupException("userId, cityId 에 해당하는 json데이터를 보내주세요");
        }

        User user = userRepository.findById(userId).orElseThrow(() -> new LookupException(userId + "에 해당하는 유저가 없습니다"));
        City city = cityRepository.findById(cityId).orElseThrow(() -> new LookupException(cityId + "에 해당하는 도시가 없습니다"));

        Lookup lookup = lookupRepository.save(new Lookup(user, city));

        return lookup;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Lookup lookupUpdate(Long lookupId, LookupDTO lookupDTO) {

        Lookup lookup = lookupRepository.findById(lookupId).orElseThrow(() -> new LookupException(lookupId + "에 해당하는 Lookup이 없습니다"));

        Long userId = lookupDTO.getUserId();
        if (userId != null){
            User user = userRepository.findById(userId).orElseThrow(() -> new LookupException(userId + "에 해당하는 유저가 없습니다"));
            lookup.setUser(user);
        }
        Long cityId = lookupDTO.getCityId();
        if (cityId != null){
            City city = cityRepository.findById(cityId).orElseThrow(() -> new LookupException(cityId + "에 해당하는 도시가 없습니다"));
            lookup.setCity(city);
        }

        return lookup;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean lookupDelete(Long lookupId) {

        Lookup lookup = lookupRepository.findById(lookupId).orElseThrow(() -> new LookupException(lookupId + "에 해당하는 Lookup이 없습니다"));

        lookupRepository.delete(lookup);

        return true;
    }
}
