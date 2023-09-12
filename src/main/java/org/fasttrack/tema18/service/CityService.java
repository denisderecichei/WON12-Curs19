package org.fasttrack.tema18.service;

import org.fasttrack.tema18.exceptions.EntityNotFoundException;
import org.fasttrack.tema18.model.City;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    private List<City> cities;

    public CityService() {
        this.cities = generateCities();
    }

    private List<City> generateCities() {
        City oradea = new City(1, "Oradea", "Romania");
        City paris = new City(2, "Paris", "Franta");
        City newYork = new City(3, "New York", "SUA");
        return List.of(oradea, paris, newYork);
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public City getCityById(int cityId) {
        return cities.stream()
                .filter(c -> c.getId() == cityId)
                .findFirst().orElseThrow(() -> new EntityNotFoundException("Nu am gasit orasul cu id-ul " + cityId, cityId));
    }
}
