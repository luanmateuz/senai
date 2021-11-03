package br.senai.estudante.df.controller;

import br.senai.estudante.df.model.dao.CarDao;
import br.senai.estudante.df.model.dao.impl.CarDaoJDBC;
import br.senai.estudante.df.model.entities.Car;
import br.senai.estudante.df.view.CarView;

import java.time.Year;
import java.util.List;

public class CarController {

    private CarView view;
    private CarDao dao;

    public CarController() {
        dao = new CarDaoJDBC();
    }
    public CarController(CarView view) {
        this();
        this.view = view;
        this.view.enableFields();
    }

    public void insertCar(String licensePlate, String reindeer, String manufacturer,
                          String model, String year, String color) {
        Car car = Car.builder()
                .licensePlate(licensePlate)
                .reindeer(reindeer)
                .manufacturer(manufacturer)
                .model(model)
                .year(Year.parse(year))
                .color(color)
                .build();

        dao.insert(car);
        view.cleanFields();
        view.disableFields();
        view.showList();
    }

    public void updateCar(int id, String licensePlate, String reindeer,
                             String manufacturer, String model, String year,
                             String color) {
        if (view.showConfirm("Atualizar cliente?")) {
            Car car = Car.builder()
                    .id(id)
                    .licensePlate(licensePlate)
                    .reindeer(reindeer)
                    .manufacturer(manufacturer)
                    .model(model)
                    .year(Year.parse(year))
                    .color(color)
                    .build();

            dao.update(car);
            view.cleanFields();
            view.disableFields();
            view.showList();
        }
    }

    public void deleteCar(Integer id) {
        if (view.showConfirm("Deletar registro?")) {
            dao.deleteById(id);
            view.cleanFields();
            view.disableFields();
            view.showList();
        }
    }

    public List<Car> showList() {
        return dao.findAll();
    }

    public List<Car> showListSearch(String search) {
        return dao.findByModel(search);
    }
}
