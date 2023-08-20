package com.Amadeus.flight.Controller;

import com.Amadeus.flight.DTO.*;
import com.Amadeus.flight.Entity.Airport;
import com.Amadeus.flight.Entity.Flight;
import com.Amadeus.flight.Service.FlightService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/1.0")
@EnableScheduling
@Tag(name="Flight Api Documentation")
public class FlightController {

    @Autowired
    FlightService flightService;

    @Operation(description = "get all flights",summary = "get all flights")
    @GetMapping("/flights")
    public List<FlightResponseDTO> getAllFlights(){
        return flightService.getAllFlights();
    }
    // localhost:8080/api/1.0/flights/departure?departure=2023-08-04T17:00:00Z

    @Operation(description = "get flight by departure date",summary = "get flight by departure date")
    @GetMapping("/flights/departure")
    public List<Flight> getAllFlightByDeparture(@RequestParam(value = "departure") @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") Date date){
        return flightService.getAllFlightByDeparture(date);
        /*return flightService.getAllFlightByDeparture(date)
                .stream().map((flight) -> {
            FlightResponseDTO flightResponseDTO = new FlightResponseDTO();
            flightResponseDTO.setDeparture(flight.getDeparture());
            flightResponseDTO.setTo(flight.getTo());
            flightResponseDTO.setFrom(flight.getFrom());
            flightResponseDTO.setPrice(flight.getPrice());
            flightResponseDTO.setReturnt(flight.getReturnt());
            return flightResponseDTO;
        });*/
    }

    @Operation(description = "get flight by return date",summary = "get flight by return date")
    @GetMapping("/flights/return")
    public List<Flight> getAllFlightByReturn(@RequestParam(value = "return") @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") Date date) {
        return flightService.getAllFlightByReturn(date);
    }

    @Operation(description = "get flight by departure",summary = "get flight by departure")
    @GetMapping("/flights/from")
    public List<Flight> getAllFlightByFrom(@RequestParam(value = "from") String airportcity) {
        return flightService.getFlightByFrom(airportcity);
    }

    @Operation(description = "get flight by destination",summary = "get flight by destination")
    @GetMapping("/flights/to")
    public List<Flight> getAllFlightByTo(@RequestParam(value = "to") String airportcity) {
        return flightService.getFlightByTo(airportcity);
    }

    @Operation(description = "create flight with new flight infos which is sended with body",
            summary = "create flight with new flight infos which is sended with body")
    //Bu kısım geliştirebilir.Flight nesnesi Airport barındırdıgı için foreign key ile yollamak gerekiyor istegi service
    //kısmında metod oluşturup düzenlebilir id ve city eşleşmese bile id ye göre create işlemi yapıyor.Hashleyip
    // nesneler kontrol edilip çözülebilir ama yetişmeyecek sanırım :D hızlı davranmam gerekli.
    @PostMapping("/addflight")
    @ResponseStatus(HttpStatus.CREATED)
    public void createFlight(@Valid @RequestBody FlightRequestDTO flightRequestDTO){
        flightService.addFlight(flightRequestDTO);
    }
    @Operation(description = "update flight by id with new flight infos which is sended with body",
            summary = "update flight by id with new flight infos which is sended with body")
    @PutMapping("/updateflight/{id}")
    public Flight updateFlight(@Valid @RequestBody FlightRequestDTO flightRequestDTO, @PathVariable Long id){
        return flightService.updateFlight(flightRequestDTO,id);
    }

    @Operation(description = "Delete flight by id",
            summary = "Delete flight by id")
    @DeleteMapping("/deleteflight/{id}")
    public ResponseEntity<?> deleteFlight(@PathVariable Long id) {
        try {
            flightService.deleteFlight(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @Operation(description = "A scheduled job that receives information by making a request to a third-party API every day and saves it to the database.",
            summary = "A scheduled job that receives information by making a request to a third-party API every day and saves it to the database.")
    @Scheduled(cron = "0 08 20 * * ?") // Works everday at 19:38
    public void Schedule(){
        //MockApi Request
        flightService.getMockFlights();
    }


}
