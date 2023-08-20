package com.Amadeus.flight.DTO;

import com.Amadeus.flight.Entity.Airport;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class MockFlightDTO implements Serializable {

    private Airport from;

    private Airport to;

    private Date departure;

    private Date returnt;
}
