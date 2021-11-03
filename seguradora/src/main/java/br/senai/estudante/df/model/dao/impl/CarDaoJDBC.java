package br.senai.estudante.df.model.dao.impl;

import br.senai.estudante.df.db.ConnectionFactory;
import br.senai.estudante.df.model.dao.CarDao;
import br.senai.estudante.df.model.entities.Car;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class CarDaoJDBC implements CarDao {

    @Override
    public void insert(Car car) {
        log.info("Inserting Car '{}'", car);
        try {
            String sql ="INSERT INTO E1_AUTOMOVEL (PLACA,RENAVAM,FABRICANTE,MODELO,ANO,COR) VALUES (?,?,?,?,?,?)";
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, car.getLicensePlate());
            ps.setString(2, car.getReindeer());
            ps.setString(3, car.getManufacturer());
            ps.setString(4, car.getModel());
            ps.setString(5, car.getYear().toString());
            ps.setString(6, car.getColor());

            ps.execute();
        } catch (SQLException exception) {
            log.error("Error while trying to insert Car '{}'", car, exception);
        }
    }

    @Override
    public void update(Car car) {
        log.info("Updating Car '{}'", car);
        try {
            String sql = "UPDATE E1_AUTOMOVEL SET PLACA=?,MODELO=?,COR=?,ANO=?,FABRICANTE=?,RENAVAM=? WHERE COD=?";
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, car.getLicensePlate());
            ps.setString(2, car.getModel());
            ps.setString(3, car.getColor());
            ps.setString(4, car.getYear().toString());
            ps.setString(5, car.getManufacturer());
            ps.setString(6, car.getReindeer());
            ps.setInt(7, car.getId());

            ps.execute();
        } catch (SQLException exception) {
            log.error("Error while trying to update Car '{}'", car, exception);
        }
    }

    @Override
    public void deleteById(Integer id) {
        try {
            String sql = "DELETE FROM E1_AUTOMOVEL WHERE cod=?;";
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ps.execute();
            log.info("Deleted car '{}' from the database", id);
        } catch (SQLException exception) {
            log.error("Error while trying to delete car '{}'", id, exception);
        }
    }

    @Override
    public List<Car> findByModel(String model) {
        log.info("Find by Model on table cars");
        List<Car> cars = new ArrayList<>();
        try {
            cars = this.findBy(model);
        } catch (SQLException e) {
            log.error("Error while trying to find all cars", e);
        }

        return cars;
    }

    @Override
    public List<Car> findAll() {
        log.info("Find all Cars in database");
        List<Car> cars = new ArrayList<>();
        try {
            cars = this.findBy("");
        } catch (SQLException e) {
            log.error("Error while trying to find all cars", e);
        }

        return cars;
    }

    private List<Car> findBy(String search) throws SQLException {
        String sql = "SELECT * FROM E1_AUTOMOVEL WHERE modelo LIKE ?";
        List<Car> cars = new ArrayList<>();

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, "%"+search+"%");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Car car = Car.builder()
                    .id(rs.getInt("COD"))
                    .licensePlate(rs.getString("PLACA"))
                    .reindeer(rs.getString("RENAVAM"))
                    .manufacturer(rs.getString("FABRICANTE"))
                    .model(rs.getString("MODELO"))
                    .year(Year.of(LocalDate.parse(rs.getString("ANO")).getYear()))
                    .color(rs.getString("COR"))
                    .build();

            cars.add(car);
        }

        return cars;
    }
}
