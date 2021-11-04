package br.senai.estudante.df.util;

import br.senai.estudante.df.model.entities.Occurrence;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import java.util.List;

public class Report {

    public void generateReport(List<Occurrence> occurrenceList) throws JRException {
        JasperReport report = JasperCompileManager.compileReport("src/main/java/br/senai/estudante/df/util/template.jrxml");
        JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(occurrenceList));
        JasperViewer.viewReport(print, false);
    }
}
