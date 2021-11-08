package br.senai.estudante.df.util;

import br.senai.estudante.df.controller.OccurrenceController;
import br.senai.estudante.df.model.entities.Occurrence;
import lombok.extern.log4j.Log4j2;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import java.util.List;

@Log4j2
public class Report {

    private Report() {
        throw new IllegalStateException("Utility class");
    }

    public static void generateReport() {
        log.info("Generating report");
        try {
            List<Occurrence> occurrenceList = new OccurrenceController().showList();
            String sourceFileName = "src/main/resources/template.jrxml";

            JasperReport report = JasperCompileManager.compileReport(sourceFileName);
            JasperPrint print = JasperFillManager
                    .fillReport(report, null, new JRBeanCollectionDataSource(occurrenceList));
            JasperViewer.viewReport(print, false);
        } catch (JRException e) {
            log.error("Error while trying to generating report", e);
        }

    }
}
