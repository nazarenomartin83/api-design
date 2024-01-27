package com.example.apidesign.service;

import com.example.apidesign.dto.CountryDTO;
import com.example.apidesign.model.Country;
import com.example.apidesign.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;
    public Country create(Country country) {
        return countryRepository.save(country);
    }

    public Optional<Country> getById(Long id) {
        return countryRepository.findById(id);
    }

    public List<Country> getAll() {
        return countryRepository.findAll();
    }
    public void deleteById(Long id) {
        countryRepository.deleteById(id);
    }

    public Country update(Country entity) {
        return null;
    }
    private List<CountryDTO> createCountry()
    {
        var countryDTO = new ArrayList<CountryDTO>();

        countryDTO.add(CountryDTO.builder()
                .id(1L)
                .name("Argentina")
                .continent("America")
                .build());
        countryDTO.add(CountryDTO.builder()
                .id(2L)
                .name("España")
                .continent("Europa")
                .build());
        return countryDTO;
    }
}
