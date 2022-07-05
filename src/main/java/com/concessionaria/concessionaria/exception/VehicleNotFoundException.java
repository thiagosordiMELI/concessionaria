package com.concessionaria.concessionaria.exception;

import com.concessionaria.concessionaria.model.Vehicle;


public class VehicleNotFoundException extends RuntimeException{
    public VehicleNotFoundException(String message){
        super(message);
    }
}
