package com.Amadeus.flight.DTO;

import com.Amadeus.flight.Entity.Airport;
import lombok.Data;

import java.util.Date;

@Data
public class FlightResponseDTO {
    //Kullanmayı tercih etmedim.Gereksiz gibi gözükebilir ama normalde kullanılması lazım diye düşünüyorum.Ayrıca
    //DTO lar sadece request response olarak degil endpointlere göre de tasarlabilir.
    private Airport from;

    private Airport to;

    private Date departure;

    private Date returnt;

    private double price;
}
