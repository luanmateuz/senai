package br.senai.estudante.df.model.dao;

import br.senai.estudante.df.model.entities.User;

public interface UserDao {

    boolean login(User user);
}
