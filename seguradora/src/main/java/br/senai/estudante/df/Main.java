package br.senai.estudante.df;

import br.senai.estudante.df.view.LoginView;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Main {

    public static void main(String[] args) {
        log.info("Booted system");
        new LoginView().setVisible(true);
    }
}
