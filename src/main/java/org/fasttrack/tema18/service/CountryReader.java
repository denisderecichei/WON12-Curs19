package org.fasttrack.tema18.service;

import org.fasttrack.tema18.model.Country;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CountryReader {

    public static List<Country> getAllFromFile() {
        List<Country> allCountries = new ArrayList<>();
        String filepath = "countries.txt";
        FileReader reader;
        try {
            reader = new FileReader(filepath);
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }
        Scanner fileScanner = new Scanner(reader);

        while (fileScanner.hasNext()) {
            String currentLine = fileScanner.nextLine();
            String[] partsOfCountries = currentLine.split("\\|");
            if (partsOfCountries.length < 5) {
                continue;
            } else {
                String name = partsOfCountries[0];
                String capital = partsOfCountries[1];
                int population = Integer.parseInt(partsOfCountries[2]);
                int area = Integer.parseInt(partsOfCountries[3]);
                String continent = partsOfCountries[4];
                List<String> neighbours = new ArrayList<>();
                if(partsOfCountries.length == 6) {
                    String allNeighboursAsString = partsOfCountries[5];
                    neighbours.addAll(List.of(allNeighboursAsString.split("~")));
                }
                Country currentCountry = new Country(name, capital, population, area, continent, neighbours);
                allCountries.add(currentCountry);
            }
        }
        return allCountries;
    }
}
