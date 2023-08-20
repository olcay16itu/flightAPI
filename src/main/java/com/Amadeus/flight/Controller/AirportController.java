package com.Amadeus.flight.Controller;

import com.Amadeus.flight.DTO.AirportRequestDTO;
import com.Amadeus.flight.DTO.AirportResponseDTO;
import com.Amadeus.flight.Entity.Airport;
import com.Amadeus.flight.Service.AirportService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/1.0")
@SecurityRequirement(name = "basicAuth")
@Tag(name = "Airport Api Documentation")
public class AirportController {
    @Autowired
    AirportService airportService;

    @Operation(description = "get all airports",summary = "get all airports")
    @GetMapping("/airports")
    public List<AirportResponseDTO> getAllAirports(){
       return airportService.getAllAirports();
    }

    @Operation(description = "get all airports from city",summary = "get all airports from city")
    @GetMapping("/airports/{sehir}")
    public List<AirportResponseDTO> getAllAirports(@PathVariable String sehir){
        return airportService.findAirportByCity(sehir);
    }

    @Operation(description = "get spesific airport by id",summary = "get spesific airport by id")
    @GetMapping("/airports/getByID/{id}")
    public Airport getAirportByID(@PathVariable Long id){
        return airportService.findAirportByID(id);
    }

    @Operation(description = "update airport by id with sended new airport",summary = "update airport by id with sended new airport")
    @PutMapping("/updateairport/{id}")
    public AirportResponseDTO updateAirport(@Valid @RequestBody AirportRequestDTO airportRequestDTO,@PathVariable Long id){
        Airport airport = airportService.updateAirport(airportRequestDTO,id);
        AirportResponseDTO airportResponseDTO = new AirportResponseDTO();
        airportResponseDTO.setCity(airport.getCity());
        return airportResponseDTO;
    }

    @Operation(description = "add airport with sended new airport" ,summary = "add airport with sended new airport")
    @PostMapping("/addairport")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAirport(@Valid @RequestBody AirportRequestDTO airportRequestDTO){
        airportService.addAirport(airportRequestDTO);
    }

    @Operation(description = "delete airport by id" ,summary = "delete airport by id")
    @DeleteMapping("deleteairport/{id}")
    public ResponseEntity<?> deleteAirport(@PathVariable Long id) {
        try {
            airportService.deleteAirport(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }


}
