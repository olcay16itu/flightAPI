package com.Amadeus.flight.Repositories;

import com.Amadeus.flight.DTO.AirportResponseDTO;
import com.Amadeus.flight.Entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AirportRepository extends JpaRepository<Airport,Long> {
    List<Airport> findAllByCity(String Sehir);
}
