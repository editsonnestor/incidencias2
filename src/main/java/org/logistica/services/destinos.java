package org.logistica.services;

public class destinos {

    public String data() {
//        String rutaData = "jdbc:firebirdsql:127.0.0.1/3050:D:/Proyectos/Editson/logistica/data/INCIDENCIA.FDB?encoding=UTF8";
      String rutaData = "jdbc:firebirdsql://127.0.0.1:3050//home/renzo/data/INCIDENCIA.FDB?encoding=UTF8";
        return rutaData;
    }

    public String reportes() {
//        String rutaReporte = "C:/Users/Renzo/JaspersoftWorkspace/Logistica/";
        String rutaReporte = "/home/renzo/Incidencia/report/";

        return rutaReporte;
    }

    public String usuarios() {
        String usuario = "sysdba";  
        return usuario;
    }

    public String claves() {
        String clave = "masterkey";
        return clave;
    }
}
