package com.Amadeus.flight.Entity;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//@ApiModel(value = "Flight Api model documentation", description = "Model")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@ApiModelProperty("Unique id field of Flight object")
    private long id;
    @ManyToOne
    @JoinColumn(name = "airport_from_id",nullable = false)
    //@ApiModelProperty("Foreign key (airport object ) of Flight object where flight will start ")
    private Airport from;
    @ManyToOne
    @JoinColumn(name = "airport_to_id",nullable = true)
    //@ApiModelProperty("Foreign key (airport object ) of Flight object where flight will finish")
    private Airport to;
    //@ApiModelProperty("Date of Flight object where flight will start")
    private Date departure;
    //@ApiModelProperty("Foreign key (airport object ) of Flight object where flight return")
    private Date returnt;
    //@ApiModelProperty("Price of flight ticket")
    private double price;

}
