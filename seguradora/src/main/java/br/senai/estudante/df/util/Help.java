package br.senai.estudante.df.util;

import lombok.extern.log4j.Log4j2;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

@Log4j2
public class Help {

    private Help() {
        throw new IllegalStateException("Utility class");
    }

    public static void help() {
        log.info("Help");
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File("src/main/resources/help_pt_BR.pdf");
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                log.error("Error while trying to open help file", ex);
            }
        }
    }
}
