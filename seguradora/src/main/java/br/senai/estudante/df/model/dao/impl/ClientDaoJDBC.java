package br.senai.estudante.df.model.dao.impl;

import br.senai.estudante.df.db.ConnectionFactory;
import br.senai.estudante.df.model.dao.ClientDao;
import br.senai.estudante.df.model.entities.Client;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class ClientDaoJDBC implements ClientDao {

    @Override
    public void insert(Client client) {
        log.info("Inserting Client '{}'", client);
        try {
            String sql = "INSERT INTO E2_CLIENTES (NOME,RG,CPF,TEL) VALUES (?,?,?,?)";
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, client.getName());
            ps.setString(2, client.getRg());
            ps.setString(3, client.getCpf());
            ps.setString(4, client.getPhone());

            ps.execute();
        } catch (SQLException exception) {
            log.error("Error while trying to insert Client '{}'", client, exception);
        }
    }

    @Override
    public void update(Client client) {
        log.info("Updating Client '{}'", client);
        try {
            String sql = "UPDATE E2_CLIENTES SET NOME=?,RG=?,CPF=?,TEL=?" +
                    " WHERE COD=?";
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, client.getName());
            ps.setString(2, client.getRg());
            ps.setString(3, client.getCpf());
            ps.setString(4, client.getPhone());
            ps.setInt(5, client.getId());

            ps.execute();
        } catch (SQLException exception) {
            log.error("Error while trying to update Client '{}'", client, exception);
        }
    }

    @Override
    public void deleteById(Integer id) {
        try {
            String sql = "DELETE FROM E2_CLIENTES WHERE cod=?;";
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ps.execute();
            log.info("Deleted client '{}' from the database", id);
        } catch (SQLException exception) {
            log.error("Error while trying to delete client '{}'", id, exception);
        }
    }

    @Override
    public List<Client> findByName(String search) {
        log.info("Fing by name on table clients");
        List<Client> clients = new ArrayList<>();
        try {
            clients = this.findBy(search);
        } catch (SQLException e) {
            log.error("Error while trying to find client {}", search, e);
        }

        return clients;
    }

    @Override
    public List<Client> findAll() {
        log.info("Find all Clients in database");
        List<Client> clients = new ArrayList<>();
        try {
            clients = this.findBy("");
        } catch (SQLException e) {
            log.error("Error while trying to find all clients", e);
        }

        return clients;
    }

    private List<Client> findBy(String search) throws SQLException {
        String sql = "SELECT * FROM E2_CLIENTES WHERE nome LIKE ?";
        List<Client> clients = new ArrayList<>();

            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, "%"+search+"%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Client client = Client.builder()
                        .id(rs.getInt("COD"))
                        .name(rs.getString("NOME"))
                        .rg(rs.getString("RG"))
                        .cpf(rs.getString("CPF"))
                        .phone(rs.getString("TEL"))
                        .build();

                clients.add(client);
            }

            return clients;
    }
}
