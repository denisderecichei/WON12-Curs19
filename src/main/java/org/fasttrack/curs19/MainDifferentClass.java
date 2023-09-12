package org.fasttrack.curs19;

import org.fasttrack.tema18.model.Country;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class MainDifferentClass {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        Country randomCountry = restTemplate.getForObject("http://localhost:8080/countries/random", Country.class);
        System.out.println(randomCountry.getName());

        ResponseEntity<Country> forEntity = restTemplate.getForEntity("http://localhost:8080/countries/random", Country.class);
        System.out.println(forEntity.getStatusCode());

//        restTemplate.postForObject("http://localhost:8080/countries", new Country(), Country.class);

        List<Country> countries = restTemplate.exchange("http://localhost:8080/countries", HttpMethod.GET,
                new HttpEntity<>(null), new ParameterizedTypeReference<List<Country>>() {}).getBody();

        System.out.println(countries.size());
        System.out.println(countries.get(0).getName());
    }
}
