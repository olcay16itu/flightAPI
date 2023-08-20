package com.Amadeus.flight.Controller;

import com.Amadeus.flight.Entity.Flight;
import com.Amadeus.flight.Service.AirportService;
import com.Amadeus.flight.Service.FlightService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/1.0")
@Tag(name = "Search Api Documentation")
public class SearchController {
    @Autowired
    FlightService flightService;
    @Autowired
    AirportService airportService;
    @Operation(description = "find flights according to search",
            summary = "Lists the flights suitable for the given place of departure, destination, departure date and return date." +
             "If no return date is given, it is a one-way flight, if given, it is a two-way flight.\n" +
            "Single flight information should be given for a one-way flight, and two flight information should be given for a two-way flight.")
    //Dönüş tarihi verilmediyse tek yönlü bilet , verildiyse çift yönlü bilet şeklinde çalışıyor.
    @GetMapping("/flight-infos")
    public List<Flight> getFlights(
            @RequestParam(value = "departure") @DateTimeFormat(pattern="yyyy-MM-dd") Date departure ,
            @RequestParam(value = "returnt" , required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date returnt,
            @RequestParam(value = "from") String airportfrom,
            @RequestParam(value = "to" ) String airportto
    ){
        if(Objects.isNull(returnt)){
            return flightService.getFlightBetweenDates(departure,airportfrom,airportto);
        }
        else {
            return flightService.getFlightsWithDepartureandReturn(departure,airportfrom,returnt,airportto);
        }

    }
}
