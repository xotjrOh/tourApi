package com.interpark.tour.api.lookup.svc;

import com.interpark.tour.api.lookup.model.Lookup;
import com.interpark.tour.api.lookup.model.LookupDTO;

import java.util.List;

public interface LookupService {

    List<Lookup> lookupAll();

    Lookup lookupById(Long lookupId);

    Lookup lookupCreate(LookupDTO lookupDTO);

    Lookup lookupUpdate(Long lookupId, LookupDTO lookupDTO);

    boolean lookupDelete(Long lookupId);

    List<String> viewedCityInWeek(Long userId);
}
