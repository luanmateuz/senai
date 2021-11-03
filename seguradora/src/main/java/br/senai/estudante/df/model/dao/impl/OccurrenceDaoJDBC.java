package br.senai.estudante.df.model.dao.impl;

import br.senai.estudante.df.db.ConnectionFactory;
import br.senai.estudante.df.model.dao.OccurrenceDao;
import br.senai.estudante.df.model.entities.Car;
import br.senai.estudante.df.model.entities.Client;
import br.senai.estudante.df.model.entities.Occurrence;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class OccurrenceDaoJDBC implements OccurrenceDao {

    @Override
    public void insert(Occurrence occurrence) {
        log.info("Inserting Occurrence '{}'", occurrence);
        try {
            String sql = "INSERT INTO E4_OCORRENCIAS (DATA,LOCAL,DESCRICAO,ID_CLIENTE,ID_AUTOMOVEL) VALUES (?,?,?,?,?)";
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setDate(1, Date.valueOf(occurrence.getLocalDate()));
            ps.setString(2, occurrence.getLocal());
            ps.setString(3, occurrence.getDesc());
            ps.setInt(4, occurrence.getClient().getId());
            ps.setInt(5, occurrence.getCar().getId());

            ps.execute();
        } catch (SQLException exception) {
            log.error("Error while trying to insert Occurrence '{}'", occurrence, exception);
        }
    }

    @Override
    public void update(Occurrence occurrence) {
        log.info("Updating Occurrence '{}'", occurrence);
        try {
            String sql = "UPDATE E4_OCORRENCIAS SET DATA=?,LOCAL=?,DESCRICAO=?,ID_CLIENTE=?,ID_AUTOMOVEL=? WHERE ID=?";
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setDate(1, Date.valueOf(occurrence.getLocalDate()));
            ps.setString(2, occurrence.getLocal());
            ps.setString(3, occurrence.getDesc());
            ps.setInt(4, occurrence.getClient().getId());
            ps.setInt(5, occurrence.getCar().getId());
            ps.setInt(6, occurrence.getId());

            ps.execute();
        } catch (SQLException exception) {
            log.error("Error while trying to update Occurrence '{}'", occurrence, exception);
        }
    }

    @Override
    public void deleteById(Integer id) {
        try {
            String sql = "DELETE FROM E4_OCORRENCIAS WHERE ID=?";
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ps.execute();
            log.info("Deleted occurrence '{}' from the database", id);
        } catch (SQLException exception) {
            log.error("Error while trying to delete client '{}'", id, exception);
        }
    }

    @Override
    public List<Occurrence> findAll() {
        log.info("Find all Occurrences in database");
        List<Occurrence> occurrences = new ArrayList<>();
        try {
            String sql = "SELECT * FROM E4_OCORRENCIAS";
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Occurrence occurrence = Occurrence.builder()
                        .id(rs.getInt("ID"))
                        .localDate(rs.getDate("DATA").toLocalDate())
                        .local(rs.getString("LOCAL"))
                        .desc(rs.getString("DESCRICAO"))
                        .client(Client.builder().id(rs.getInt("ID_CLIENTE")).build())
                        .car(Car.builder().id(rs.getInt("ID_AUTOMOVEL")).build())
                        .build();

                occurrences.add(occurrence);
            }
        } catch (SQLException e) {
            log.error("Error while trying to find all occurrences", e);
        }
        return occurrences;
    }
}
