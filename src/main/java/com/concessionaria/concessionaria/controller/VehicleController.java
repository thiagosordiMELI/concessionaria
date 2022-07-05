package com.concessionaria.concessionaria.controller;

import com.concessionaria.concessionaria.model.Vehicle;
import com.concessionaria.concessionaria.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/api/veiculos")
public class VehicleController {

    @Autowired
    private VehicleRepository repository;

    @PostMapping
    public ResponseEntity<Void> createVehicle(@RequestBody Vehicle vehicle){
        repository.createVehicle(vehicle);
        return new ResponseEntity(null, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> listVehicles(){
        List<Vehicle> vehicles = repository.getAllVehicles();
        return new ResponseEntity(vehicles, HttpStatus.OK);
    }

    @GetMapping("/dates")
    public ResponseEntity<List<Vehicle>> listVehicles(@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date since,
                                                      @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date to){
        List<Vehicle> vehicles = repository.filterVehicles(since, to);
        return new ResponseEntity(vehicles, HttpStatus.OK);
    }

    @GetMapping("/prices")
    public ResponseEntity<List<Vehicle>> listVehicles(@RequestParam float since,
                                                      @RequestParam  float to){
        List<Vehicle> vehicles = repository.filterVehicles(since, to);
        return new ResponseEntity(vehicles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicle(@PathVariable int id){
        Vehicle vehicle = repository.get(id);
        return new ResponseEntity(vehicle, HttpStatus.OK);
    }

}
