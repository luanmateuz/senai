package br.senai.estudante.df.model.entities;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Value
@Builder
public class Client {

    int id;
    String name;
    String rg;
    String cpf;
    String phone;
}
