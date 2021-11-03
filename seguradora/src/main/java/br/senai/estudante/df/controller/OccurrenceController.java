package br.senai.estudante.df.controller;

import br.senai.estudante.df.model.dao.impl.OccurrenceDaoJDBC;
import br.senai.estudante.df.model.entities.Car;
import br.senai.estudante.df.model.entities.Client;
import br.senai.estudante.df.model.entities.Occurrence;
import br.senai.estudante.df.view.OccurrenceView;

import java.time.LocalDate;
import java.util.List;

public class OccurrenceController {

    private OccurrenceView view;
    private OccurrenceDaoJDBC dao;

    public OccurrenceController() {
        this.dao = new OccurrenceDaoJDBC();
    }

    public OccurrenceController(OccurrenceView view) {
        this();
        this.view = view;
//        this.view.enableFields();
    }

    public void insertOccurrence(int idClient, int idCar,
                                 LocalDate localDate, String local, String desc) {
        Occurrence occurrence = Occurrence.builder()
                .client(Client.builder().id(idClient).build())
                .car(Car.builder().id(idCar).build())
                .localDate(localDate)
                .local(local)
                .desc(desc)
                .build();

        dao.insert(occurrence);
        view.cleanFields();
        view.disableFields();
        view.showList();
    }

    public void updateOccurrence(int id, int idClient, int idCar,
                                 LocalDate localDate, String local, String desc) {
        Occurrence occurrence = Occurrence.builder()
                .id(id)
                .client(Client.builder().id(idClient).build())
                .car(Car.builder().id(idCar).build())
                .localDate(localDate)
                .local(local)
                .desc(desc)
                .build();

        dao.update(occurrence);
        view.cleanFields();
        view.disableFields();
        view.showList();
    }

    public void deleteOccurrence(int id) {
        if (view.showConfirm("Deletar registro?")) {
            dao.deleteById(id);
            view.cleanFields();
            view.disableFields();
            view.showList();
        }
    }

    public List<Occurrence> showList() {
        return dao.findAll();
    }
}
