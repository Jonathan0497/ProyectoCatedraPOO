package Controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


@WebServlet(name = "JaspertReport2", value = "/JaspertReport2")
public class JaspertReport2 extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conexion = null;
        try {

            response.setContentType("application/pdf");
            //Nombre del reporte
            response.setHeader("Content-Disposition", "attachment; filename=\"ReporteSucursal.pdf\";");
            //Devuelve un objeto de la clase ServletOutputStream que modela un flujo de salida que permite la escritura de datos a nivel de bytes
            ServletOutputStream out = response.getOutputStream();

            String pathReporte;
            //Conexion BD
            /*Context init = new InitialContext();
            Context context = (Context) init.lookup("java:comp/env");
            DataSource dataSource =(DataSource)context.lookup("jdbc/mysql");
            conexion = dataSource.getConnection();*/


            try {
//obtenemos el driver de para mysql
                Class.forName("com.mysql.jdbc.Driver");
// Se obtiene una conexión con la base de datos. 2
                conexion = DriverManager.getConnection
                        ("jdbc:mysql://localhost/primecinema", "root", "");
// Permite ejecutar sentencias SQL sin parámetros

            } catch (ClassNotFoundException e1) {
//Error si no puedo leer el driver de MySQL
                System.out.println("ERROR:No encuentro el driver de la BD: " + e1.getMessage());
            }


            //Ruta del archivo .jasper
            pathReporte = getServletContext().getRealPath("/ReportePrimeCinema2.jasper");
            //Pasar de parametro la ruta del .jasper
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(pathReporte);
            //Se necesitan 3 parametros el reporte,los parametros si el sql los necesitara y la conexion a la BD
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, conexion);
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
            exporter.exportReport();
        } catch (SQLException | JRException ex) {
            Logger.getLogger(JaspertReport.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(JaspertReport.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        processRequest(request, response);
    }
}