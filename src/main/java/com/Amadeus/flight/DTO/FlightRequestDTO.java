package com.Amadeus.flight.DTO;

import com.Amadeus.flight.Entity.Airport;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Data
public class FlightRequestDTO {

    private Airport from;

    private Airport to;

    private Date departure;

    private Date returnt;

    private double price;
}
