package br.senai.estudante.df.model.dao;

import br.senai.estudante.df.model.entities.Occurrence;

import java.util.List;

public interface OccurrenceDao {

    void insert(Occurrence occurrence);
    void update(Occurrence occurrence);
    void deleteById(Integer id);
    List<Occurrence> findAll();
}
