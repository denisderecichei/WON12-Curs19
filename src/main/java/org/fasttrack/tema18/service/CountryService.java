package org.fasttrack.tema18.service;

import org.fasttrack.tema18.model.Country;
import org.fasttrack.tema18.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class CountryService {
    private List<Country> allCountries;
    private final static Random random = new Random();

    public CountryService() {
        this.allCountries = CountryReader.getAllFromFile();
    }

    public List<Country> getAllCountries() {
        return allCountries;
    }

    public void setAllCountries(List<Country> allCountries) {
        this.allCountries = allCountries;
    }

    public List<Country> getAllCountriesByContinent(String continentName) {
        return allCountries.stream()
                .filter(country -> country.getContinent().equals(continentName))
                .collect(Collectors.toList());
    }

    public List<Country> getAllCountriesByContinentAndPopulation(String continentName, Integer minPopulation) {
        int finalMinPopulation = minPopulation == null ? 0 : minPopulation;
        return getAllCountriesByContinent(continentName)
                .stream()
                .filter(c -> c.getPopulation() >= finalMinPopulation)
                .collect(Collectors.toList());

    }

    public List<Country> getAllByIncludeExclude(String includeNeighbour, String excludeNeighbour) {
        return getAllCountries().stream()
                .filter(c -> c.getNeighbours().contains(includeNeighbour))
                .filter(c -> !c.getNeighbours().contains(excludeNeighbour))
                .collect(Collectors.toList());
    }

    public Country addACountry(Country country) {
        allCountries.add(country);
        return country;
    }

    public Country getRandomCountry() {
        int randomIndex = random.nextInt(allCountries.size());
        return allCountries.get(randomIndex);
    }

    public Country getCountryById(int countryId) {
        return allCountries.stream()
                .filter(c -> c.getId() == countryId)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Nu a fost gasita o tara cu id " + countryId, countryId));
    }

    public Country putCountry(Country country) {
        //find if exists, and replace
        //if not, add
        return country;
    }
}
