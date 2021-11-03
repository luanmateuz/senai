package br.senai.estudante.df.model.dao;

import br.senai.estudante.df.model.entities.Client;

import java.util.List;

public interface ClientDao {

    void insert(Client client);
    void update(Client client);
    void deleteById(Integer id);
    List<Client> findByName(String search);
    List<Client> findAll();
}
