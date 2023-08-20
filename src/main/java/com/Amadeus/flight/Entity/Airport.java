package com.Amadeus.flight.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//@ApiModel(value = "Airport Api model documentation", description = "Model")
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@ApiModelProperty("Unique id field of Airport object")
    private long id;
    //@ApiModelProperty("City field of Airport object")
    private String city;
    @OneToMany(mappedBy = "from",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Flight> from_list;
    @OneToMany(mappedBy = "to",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Flight> to_list;
}
