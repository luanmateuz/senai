package br.senai.estudante.df.model.dao.impl;

import br.senai.estudante.df.db.ConnectionFactory;
import br.senai.estudante.df.model.dao.UserDao;
import br.senai.estudante.df.model.entities.User;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Log4j2
public class UserDaoJDBC implements UserDao {

    @Override
    public boolean login(User user) {
        log.info("Trying to login for user [username] '{}'", user.getUsername());
        try {
            Connection conn = ConnectionFactory.getConnection();
            try (PreparedStatement ps = createPreparedStatementLogin(conn, user);
                ResultSet rs = ps.executeQuery()) {

                return rs.next();
            }

        } catch (SQLException exception) {
            log.error("Error while trying login for user {}", user.getUsername(), exception);
        }

        return false;
    }

    private PreparedStatement createPreparedStatementLogin(Connection conn, User user) throws SQLException {
        String sql = "SELECT * FROM E5_USUARIOS WHERE LOGIN = ? AND SENHA = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());

        return ps;
    }
}
