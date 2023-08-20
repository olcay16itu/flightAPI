package com.Amadeus.flight.Service;

import com.Amadeus.flight.DTO.AirportRequestDTO;
import com.Amadeus.flight.DTO.FlightRequestDTO;
import com.Amadeus.flight.DTO.FlightResponseDTO;
import com.Amadeus.flight.DTO.MockFlightDTO;
import com.Amadeus.flight.Entity.Airport;
import com.Amadeus.flight.Entity.Flight;
import com.Amadeus.flight.Repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightService {
    @Autowired
    FlightRepository flightRepository;

    @Autowired
    MapperService mapperService;

    @Value("${mock.api.request.url}")
    private String requestUrl;

    public List<FlightResponseDTO> getAllFlights(){
        return flightRepository.findAll().stream().map(mapperService::FlightToFlightResponse)
                .collect(Collectors.toList());
    }

    public List<Flight> getAllFlightByDeparture(Date departure){
        return flightRepository.findAllByDeparture(departure);


                /*.stream()
                .map(mapperService::FlightToFlightResponse).collect(Collectors.toList());*/
    }

    public List<Flight> getAllFlightByReturn(Date returnt){
        return flightRepository.findAllByReturnt(returnt);
    }


   /* public List<FlightResponseDTO> getFlightByReturnt(Date returnt){
        return flightRepository.findAllByReturnt(returnt).stream()
                .map(mapperService::FlightToFlightResponse).collect(Collectors.toList());
    }*/
    public List<Flight> getFlightByFrom(String from){
        return flightRepository.AllByFrom(from);
    }
    public List<Flight> getFlightByTo(String to) {
        return flightRepository.AllByTo(to);
    }

    public void addFlight(FlightRequestDTO flightRequestDTO){

        //Buralar constructor ile de yapılabilir ancak sonradan set etmeyi tercih ettim.
        Flight flight = new Flight();
        flight.setTo(flightRequestDTO.getTo());
        flight.setFrom(flightRequestDTO.getFrom());
        flight.setPrice(flightRequestDTO.getPrice());
        flight.setReturnt(flightRequestDTO.getReturnt());
        flight.setDeparture(flightRequestDTO.getDeparture());
        flightRepository.save(flight);
    }

    public void deleteFlight(Long id) throws Exception {
        if(flightRepository.existsById(id)) {
            flightRepository.deleteById(id);
        }
        else{
            throw new Exception("Flight not found");
        }

    }
    //Same here i will update from id.

    public Flight updateFlight(FlightRequestDTO flightRequestDTO,Long id){
        Flight flight = flightRepository.findById(id).get();
        flight.setTo(flightRequestDTO.getTo());
        flight.setFrom(flightRequestDTO.getFrom());
        flight.setPrice(flightRequestDTO.getPrice());
        flight.setReturnt(flightRequestDTO.getReturnt());
        flight.setDeparture(flightRequestDTO.getDeparture());
        return flightRepository.save(flight);
    }

    public List<Flight> getFlightBetweenDates(Date kalkis,String from,String to){
        Calendar date = Calendar.getInstance();
        Date kalkishesap = new Date();
        //DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        date.setTime(kalkis);
        date.add(Calendar.DATE,1);
        kalkishesap = date.getTime();
        return flightRepository.getFlightsBetweenDatesandfrom(kalkis,kalkishesap,from,to);
    }

    public List<Flight> getFlightsWithDepartureandReturn(Date kalkis,String from,Date inis,String to){
        Calendar date = Calendar.getInstance();
        Date kalkishesap = new Date();
        //DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        date.setTime(kalkis);
        date.add(Calendar.DATE,1);
        kalkishesap = date.getTime();
        Calendar date2 = Calendar.getInstance();
        Date inishesap = new Date();
        //DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        date2.setTime(inis);
        date2.add(Calendar.DATE,1);
        inishesap = date2.getTime();
        return flightRepository.getFlightsBetweenDatesandto(kalkis,kalkishesap,inis,inishesap,from,to);
    }
    public void getMockFlights(){
        try {
            RestTemplate restTemplate = new RestTemplate();
            MockFlightDTO mockFlightDTO = restTemplate.getForObject(requestUrl, MockFlightDTO.class);
            addScheduledFlights(mockFlightDTO);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    public void addScheduledFlights(MockFlightDTO mockFlightDTO){
        Flight flight = mapperService.MockFlightDtoToFlight(mockFlightDTO);
        flightRepository.save(flight);
    }




}
