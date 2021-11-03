package br.senai.estudante.df.controller;

import br.senai.estudante.df.model.dao.ClientDao;
import br.senai.estudante.df.model.dao.impl.ClientDaoJDBC;
import br.senai.estudante.df.model.entities.Client;
import br.senai.estudante.df.view.ClientView;

import java.util.List;

public class ClientController {

    private ClientView view;
    private ClientDao dao;

    public ClientController() {
        this.dao = new ClientDaoJDBC();
    }

    public ClientController(ClientView view) {
        this();
        this.view = view;
        this.view.enableFields();
    }

    public void insertClient(String name, String rg, String cpf, String telefone) {
        Client client = Client.builder()
                    .name(name)
                    .rg(rg)
                    .cpf(cpf)
                    .phone(telefone)
                    .build();

        dao.insert(client);
        view.cleanFields();
        view.disableFields();
        view.showList();
    }

    public void updateClient(int id, String name, String rg, String cpf, String telefone) {
        if (view.showConfirm("Atualizar cliente?")) {
            Client client = Client.builder()
                    .id(id)
                    .name(name)
                    .rg(rg)
                    .cpf(cpf)
                    .phone(telefone)
                    .build();
            dao.update(client);
            view.cleanFields();
            view.disableFields();
            view.showList();
        }
    }

    public void deleteClient(int id) {
        if (view.showConfirm("Deletar registro?")) {
            dao.deleteById(id);
            view.cleanFields();
            view.disableFields();
            view.showList();
        }
    }

    public List<Client> showList() {
        return dao.findAll();
    }

    public List<Client> showListSearch(String search) {
        return dao.findByName(search);
    }
}
