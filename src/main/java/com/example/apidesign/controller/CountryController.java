package com.example.apidesign.controller;

import com.example.apidesign.model.Continent;
import com.example.apidesign.model.Country;
import com.example.apidesign.service.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/countries")
@AllArgsConstructor
public class CountryController {
    private final CountryService countryService;
    @Operation(summary = "Retorna todos los paises.", description = "Tiene que ser un sustantivo en plural. Ej: /countries ; /users ; /employees ")
    @GetMapping
    public List<Country> getAll() {
        return countryService.getAll();
    }
    @Operation(summary = "Retorna un país determinado por ID.", description = "Se agrega sobre el root del recurso el id. Ej: /1 ; Mal ejemplo: /id/1 ")
    @GetMapping("/{id}")
    public ResponseEntity<Country> getById(@PathVariable("id") Long id) {
       return countryService.getById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Operation(summary = "Crea y retorna el nuevo país creado con estado 201. No es idempotente, diferentes request pueden tener resultado distinto.", description = "Sobre el root del recurso se utiliza el verbo post y se envía el objeto en el body.")
    @PostMapping
    public ResponseEntity<Country> create(@RequestBody Country country) {
        Country countryCreate = countryService.create(country);
        return ResponseEntity.status(HttpStatus.CREATED).body(countryCreate);
        //return new ResponseEntity<CountryDTO>(countryDTO, CREATED);      // Create OK
    }
    @Operation(summary = "Elimina el país determinado por ID, retorna estado 200. Idempotente (segunda request debe retornar un 404)", description = "Sobre el root del recurso y el id se utiliza el verbo delete. Ej: /1")
    @DeleteMapping("/{id}")
    public ResponseEntity<Country> delete(@PathVariable("id") Long id) {
        countryService.deleteById(id);
        return ResponseEntity.ok().build(); // Create OK
    }

    @Operation(summary = "Actualiza el país determinado o lo crea si no existe, retorna estado 200. Idempotente solo a partir de la segunda request porque debe retornar un 404.", description = "Sobre el root del recurso y el id se utiliza el verbo delete. Ej: /1")
    @PutMapping("/{id}")
    public ResponseEntity<Country> update(@RequestBody Country country) {
        return ResponseEntity.ok(country);      // Create OK
    }
    private List<Country> createCountry()
    {
        var country = new ArrayList<Country>();

        country.add(Country.builder()
                .id(1L)
                .name("Argentina")
                .continent(Continent.builder().name("America").build())
                .build());
        country.add(Country.builder()
                .id(2L)
                .name("España")
                .continent(Continent.builder().name("Europa").build())
                .build());
        return country;
    }
}
