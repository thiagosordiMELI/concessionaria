package com.concessionaria.concessionaria.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Service {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private int kilometers;
    private String descriptions;
}
