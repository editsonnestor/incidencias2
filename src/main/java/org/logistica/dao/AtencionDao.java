package org.logistica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import org.logistica.services.accesoDB;

public class AtencionDao {

    PreparedStatement ps;
    int r;

    public int CambiarEstado(Integer idVariable) {
        String sqlActualizar = " UPDATE INCIDENCIA  "
                + " SET "
                + " ESTADO = ? "
                + " WHERE ID_INCIDENCIA = ? ";  
        try { 
            Connection con;
            con = accesoDB.getConnection();
            ps = con.prepareStatement(sqlActualizar);
            ps.setString(1, "S");
            ps.setInt(2, idVariable);
            r = ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }

}
