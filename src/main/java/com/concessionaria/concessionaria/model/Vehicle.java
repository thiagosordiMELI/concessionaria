package com.concessionaria.concessionaria.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
    private int id;
    private String brand;
    private String model;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date manufacturingDate;
    private int numberOfKilometers;
    private String doors;
    private float price;
    private String currency;
    @JsonProperty("services")
    private List<Service> serviceList;
    private int countOfOwners;
}
