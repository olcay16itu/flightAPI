package com.Amadeus.flight.DTO;

import com.Amadeus.flight.Entity.Airport;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
@Data
public class AirportRequestDTO {
    @NotNull
    private String city;
}
