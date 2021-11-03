package br.senai.estudante.df.controller;

import br.senai.estudante.df.model.dao.impl.UserDaoJDBC;
import br.senai.estudante.df.model.entities.User;
import br.senai.estudante.df.view.MainView;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class LoginController {

    public boolean makeLogin(String username, String password) {
        log.info("Login");
        User user = User.builder()
                .username(username)
                .password(password)
                .build();

        boolean result = new UserDaoJDBC().login(user);
        if (result) {
            log.info("Logged with user {}", user.getUsername());
            new MainView().setVisible(true);
            return true;
        }

        return false;
    }
}
