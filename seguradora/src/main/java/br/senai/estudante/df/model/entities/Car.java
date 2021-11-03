package br.senai.estudante.df.model.entities;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.time.Year;

@Data
@Value
@Builder
public class Car {

    int id;
    String licensePlate;
    String reindeer;
    String manufacturer;
    String model;
    Year year;
    String color;
}
