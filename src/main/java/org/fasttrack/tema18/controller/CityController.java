package org.fasttrack.tema18.controller;

import org.fasttrack.tema18.model.City;
import org.fasttrack.tema18.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("cities")
public class CityController {
    private CityService service;

    @Autowired
    public CityController(CityService service) {
        this.service = service;
    }

    @GetMapping
    public List<City> getAllCities() {
        return service.getCities();
    }

    @GetMapping("{cityId}")
    public City getCityById(@PathVariable int cityId) {
        return service.getCityById(cityId);
    }
}
