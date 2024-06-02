package com.jovellanos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JsonDataSource;

public class ControladorInformes {
    public static final String JASPER_FILE = "./Plantilla_UniversalBank.jrxml";
    public static final String REPORT_FILE = "./informe.pdf";
    public static final String DATASET_FILE = "./meses.json";

    public static void generarInforme() {
        JasperReport jasperReport;
        try {
            // Compilar el archivo JRXML
            jasperReport = JasperCompileManager.compileReport(JASPER_FILE);

            // Crear el datasource del JSON
            FileInputStream inputStream = new FileInputStream(DATASET_FILE);
            JRDataSource dataSource = new JsonDataSource(inputStream);

            // Llenar el reporte
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);

            // Exportar el reporte a PDF
            JasperExportManager.exportReportToPdfFile(jasperPrint, REPORT_FILE);

        } catch (JRException e) {
            System.err.println("Fallo al generar el fichero");
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.err.println("Fallo al abrir el dataSet JSON");
            e.printStackTrace();
        }
    }
}
