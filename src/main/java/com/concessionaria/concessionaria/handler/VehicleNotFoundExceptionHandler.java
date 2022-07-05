package com.concessionaria.concessionaria.handler;

import com.concessionaria.concessionaria.exception.VehicleNotFoundException;
import com.concessionaria.concessionaria.exception.VehicleNotFoundExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class VehicleNotFoundExceptionHandler {

    @ExceptionHandler(VehicleNotFoundException.class)
    public ResponseEntity<VehicleNotFoundExceptionDetails> vehicleNotFoundExceptionDetails(Exception e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(VehicleNotFoundExceptionDetails.builder()
                .title("Veiculo não encontrado")
                .status(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build());
    }
}
