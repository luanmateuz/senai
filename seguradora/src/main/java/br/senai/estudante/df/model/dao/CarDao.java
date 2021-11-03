package br.senai.estudante.df.model.dao;

import br.senai.estudante.df.model.entities.Car;

import java.util.List;

public interface CarDao {

    void insert(Car car);
    void update(Car car);
    void deleteById(Integer id);
    List<Car> findByModel(String model);
    List<Car> findAll();
}
