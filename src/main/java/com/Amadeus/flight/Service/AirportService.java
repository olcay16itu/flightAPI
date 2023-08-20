package com.Amadeus.flight.Service;

import com.Amadeus.flight.DTO.AirportRequestDTO;
import com.Amadeus.flight.DTO.AirportResponseDTO;
import com.Amadeus.flight.Entity.Airport;
import com.Amadeus.flight.Repositories.AirportRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AirportService {
    @Autowired
    AirportRepository airportRepository;
    @Autowired
    MapperService mapperService;
    public List<AirportResponseDTO> getAllAirports(){
        return airportRepository.findAll().stream().map(mapperService::AirportToAirportResponse).collect(Collectors.toList());
    }

    public List<AirportResponseDTO> findAirportByCity(String sehir){
        return airportRepository.findAllByCity(sehir).stream().map(mapperService::AirportToAirportResponse).collect(Collectors.toList());
    }

    public Airport findAirportByID(Long id){
        return airportRepository.findById(id).orElse(null);
    }

    public void addAirport(AirportRequestDTO airportRequestDTO){
        Airport airport = new Airport();
        airport.setCity(airportRequestDTO.getCity());
        airportRepository.save(airport);
    }

    //I will use id here because there is no another option if i will try to delete a city from db it will delete
    // the first airport which is same city with request which i sended.And also , admin who has role to delete
    // can delete easily because he or she has id information.
    public void deleteAirport(Long id) throws Exception {
        if(airportRepository.existsById(id)) {
            airportRepository.deleteById(id);
        }
        else{
            throw new Exception("Airport not found");
        }

    }
    //Same here i will update from id.

    public Airport updateAirport(AirportRequestDTO airportRequestDTO,Long id){
        Airport airport = airportRepository.findById(id).get();
        airport.setCity(airportRequestDTO.getCity());
        return airportRepository.save(airport);
    }



}
