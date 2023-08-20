package com.Amadeus.flight.Service;

import com.Amadeus.flight.DTO.*;
import com.Amadeus.flight.Entity.Airport;
import com.Amadeus.flight.Entity.Flight;
import com.Amadeus.flight.Repositories.AirportRepository;
import com.Amadeus.flight.Repositories.FlightRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MapperService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private AirportRepository airportRepository;

    public AirportResponseDTO AirportToAirportResponse(Airport airport){
        AirportResponseDTO airportResponseDTO = this.mapper.map(airport,AirportResponseDTO.class);
        return airportResponseDTO;
    }

    public Airport AirportRequestToAirport(AirportRequestDTO airportRequestDTO){
        Airport airport = this.mapper.map(airportRequestDTO,Airport.class);
        return airport;
    }

    public FlightResponseDTO FlightToFlightResponse(Flight flight){
        FlightResponseDTO flightResponseDTO = this.mapper.map(flight,FlightResponseDTO.class);
        return flightResponseDTO;
    }

    /*public Flight FlightRequestToFlight(FlightRequestDTO flightRequestDTO){
        Flight flight = this.mapper.map(flightRequestDTO,Flight.class);
        return flight;
    }*/

    public Flight MockFlightDtoToFlight(MockFlightDTO mockFlightDTO){
        Flight flight = this.mapper.map(mockFlightDTO,Flight.class);
        return flight;
    }

}
