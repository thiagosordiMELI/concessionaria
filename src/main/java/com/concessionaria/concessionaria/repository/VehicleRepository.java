package com.concessionaria.concessionaria.repository;

import com.concessionaria.concessionaria.exception.VehicleNotFoundException;
import com.concessionaria.concessionaria.model.Vehicle;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class VehicleRepository {

    public void createVehicle(Vehicle vehicle){
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Vehicle> vehicles = Arrays.asList(mapper.readValue(new File("src/main/resources/veiculos.json"), Vehicle[].class));
            if(vehicles.size() > 0) {
                vehicle.setId(vehicles.get(vehicles.size() - 1).getId() + 1); // id increment
            }
            vehicles = new ArrayList(vehicles);
            vehicles.add(vehicle);
            mapper.writeValue(new File("src/main/resources/veiculos.json"), vehicles);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Vehicle> getAllVehicles() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Vehicle> vehicles = Arrays.asList(mapper.readValue(new File("src/main/resources/veiculos.json"), Vehicle[].class));
            vehicles = new ArrayList(vehicles);
            return vehicles;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Vehicle> filterVehicles(Date since, Date to) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Vehicle> vehicles = Arrays.asList(mapper.readValue(new File("src/main/resources/veiculos.json"), Vehicle[].class));
            vehicles = new ArrayList(vehicles);
            return vehicles.stream().filter(vehicle -> vehicle.getManufacturingDate().compareTo(since) > 0 &&
                    vehicle.getManufacturingDate().compareTo(to) < 0).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new VehicleNotFoundException("Veiculos com datas entre "+since+" e "+to+" não foram encontrados");
    }

    public List<Vehicle> filterVehicles(float since, float to) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Vehicle> vehicles = Arrays.asList(mapper.readValue(new File("src/main/resources/veiculos.json"), Vehicle[].class));
            vehicles = new ArrayList(vehicles);
            return vehicles.stream().filter(vehicle -> vehicle.getPrice() >= since &&
                    vehicle.getPrice() <= to).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new VehicleNotFoundException("Veiculos com valores entre "+since+" e "+to+" não foram encontrados");
    }

    public Vehicle get(int id) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Vehicle> vehicles = Arrays.asList(mapper.readValue(new File("src/main/resources/veiculos.json"), Vehicle[].class));
            vehicles = new ArrayList(vehicles);
            for(Vehicle v:vehicles){
                if(v.getId() == id){
                    return v;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new VehicleNotFoundException("Veiculo com id "+id+" não encontrado");
    }
}
