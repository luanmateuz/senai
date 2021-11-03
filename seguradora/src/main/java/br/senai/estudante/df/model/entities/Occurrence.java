package br.senai.estudante.df.model.entities;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.time.LocalDate;

@Data
@Value
@Builder
public class Occurrence {

    int id;
    Client client;
    Car car;
    LocalDate localDate;
    String local;
    String desc;
}
