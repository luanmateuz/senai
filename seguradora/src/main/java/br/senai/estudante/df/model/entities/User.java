package br.senai.estudante.df.model.entities;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Value
@Builder
public class User {

    String username;
    String password;
}
