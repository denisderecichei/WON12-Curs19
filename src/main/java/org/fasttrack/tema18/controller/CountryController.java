package org.fasttrack.tema18.controller;

import org.fasttrack.tema18.model.Country;
import org.fasttrack.tema18.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("countries")
public class CountryController {
    private CountryService service;
    @Value("${my.string}")
    private String myStringFromConfiguration;
    @Value("${my.number}")
    private int myNumber;

    @Autowired
    public CountryController(CountryService service) {
        this.service = service;
    }

    @GetMapping("hello")
    public String sayHello() {
        System.out.println("Apelat din endpoint");
        return "Bine ati venit pe serverul meu!";
    }

    @GetMapping
    public List<Country> getAllCountries() {
        System.out.println(myStringFromConfiguration);
        System.out.println("NUmarul meu este: " + myNumber);
        return service.getAllCountries();
    }

    @GetMapping("{countryId}")
    public Country getCountryById(@PathVariable int countryId) {
        return service.getCountryById(countryId);
    }

    @PostMapping("add")
    public Country addCountry(@RequestBody Country country) {
        return service.addACountry(country);
    }

    @PutMapping("add")
    public Country putCountry(@RequestBody Country country) {
        return service.putCountry(country);
    }

    @GetMapping("/random")
    public Country getRandomCountry() {
        return service.getRandomCountry();
    }

//    @GetMapping("{continentName}/countries")
//    public List<Country> getCountriesByContinent(@PathVariable(name = "continentName") String continentName) {
//        return service.getAllCountriesByContinent(continentName);
//    }

    @GetMapping("{continentName}/countries")
    public List<Country> getCountriesByContinentAndMinPopulation(@PathVariable(name = "continentName") String continentName,
                                                                 @RequestParam(name = "minPopulation", required = false) Integer minPopulation) {
        return service.getAllCountriesByContinentAndPopulation(continentName, minPopulation);
    }

    @GetMapping("byNeighbour")
    public List<Country> getByNeighExcludeByNeighbour(@RequestParam String includeNeighbour, @RequestParam String excludeNeighbour) {
        return service.getAllByIncludeExclude(includeNeighbour, excludeNeighbour);
    }
}
