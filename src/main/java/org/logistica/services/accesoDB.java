package org.logistica.services;

import java.sql.Connection;
import java.sql.DriverManager;

public class accesoDB {

    private static Connection conectar = null;

    public static Connection getConnection() throws Exception {
        String driver = "org.firebirdsql.jdbc.FBDriver";
        destinos rutaData = new destinos();
        destinos usuario = new destinos();
        destinos clave = new destinos();
        if (conectar == null) {
            Class.forName(driver);
            conectar = DriverManager.getConnection(rutaData.data(), usuario.usuarios(), clave.claves());
        }
        return conectar;
    }
}
