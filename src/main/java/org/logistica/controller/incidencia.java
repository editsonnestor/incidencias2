package org.logistica.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.logistica.bean.Equipo;
import org.logistica.bean.Incidencia;
import org.logistica.bean.Oficina;
import org.logistica.dao.AtencionDao;
import org.logistica.factory.IncidenciaFactory;

public class incidencia extends HttpServlet {

    private final IncidenciaFactory incidenciaFactory = new IncidenciaFactory();
    private final AtencionDao atencionDao = new AtencionDao();
    
    String prioridad, detalle, observaciones;
    Integer idEquipo, idOficina, idIncidencia;
    java.util.Date fechaI;
    java.sql.Date fechaO;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        String menu = request.getParameter("menu");
        if (menu.equalsIgnoreCase("Incidencia")) {
            switch (accion) {
                case "ListarAtencion" -> {
                    List entityListEquipo = incidenciaFactory.ListarEquipo();
                    request.setAttribute("entityListEquipo", entityListEquipo);
                    List entityListOficina = incidenciaFactory.ListarOficina();
                    request.setAttribute("entityListOficina", entityListOficina);
                    List entityListAtencion = incidenciaFactory.ListarAtencion();
                    request.setAttribute("entityListAtencion", entityListAtencion);
                    request.getRequestDispatcher("view/Atencion.jsp").forward(request, response);
                }
                case "CambiarEstado" -> { 
                    idIncidencia = Integer.valueOf(request.getParameter("idIncidencia"));
                    atencionDao.CambiarEstado(idIncidencia);
                    request.getRequestDispatcher("incidencia?menu=Incidencia&accion=ListarAtencion").forward(request, response);
                }

                case "ListarIncidencia" -> {
                    List entityListEquipo = incidenciaFactory.ListarEquipo();
                    request.setAttribute("entityListEquipo", entityListEquipo);
                    List entityListOficina = incidenciaFactory.ListarOficina();
                    request.setAttribute("entityListOficina", entityListOficina);
                    List entityListIncidencia = incidenciaFactory.ListarIncidencia();
                    request.setAttribute("entityListIncidencia", entityListIncidencia);
                    request.getRequestDispatcher("view/Incidencia.jsp").forward(request, response);
                }

                case "RegistrarIncidencia" -> {
                    prioridad = request.getParameter("prioridad");
                    detalle = request.getParameter("detalle");
                    observaciones = request.getParameter("observaciones");
                    idEquipo = Integer.valueOf(request.getParameter("idEquipo"));
                    idOficina = Integer.valueOf(request.getParameter("idOficina"));
                    fechaI = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("fecha"));
                    fechaO = new java.sql.Date(fechaI.getTime());
                    Equipo equipo = incidenciaFactory.BuscarEquipoID(idEquipo);
                    Oficina oficina = incidenciaFactory.BuscarOficinaID(idOficina);
                    Incidencia incidencia = new Incidencia(fechaO, detalle, prioridad, "N", observaciones, equipo, oficina);
                    incidenciaFactory.RegistrarIncidencia(incidencia);
                    request.getRequestDispatcher("incidencia?menu=Incidencia&accion=ListarIncidencia").forward(request, response);
                }

//                case "ActualizarEquipo" -> {
//                    idEquipo = Integer.valueOf(request.getParameter("idEquipo"));
//                    codigo = request.getParameter("codigo");
//                    detalle = request.getParameter("detalle");
//                    observaciones = request.getParameter("observaciones");
//                    Equipo equipo = new Equipo(idEquipo, codigo, detalle, observaciones);
//                    equipoFactory.ActulizarEquipo(equipo);
//                    request.getRequestDispatcher("equipo?menu=Equipo&accion=ListarEquipo").forward(request, response);
//                }
//
//                case "EliminarEquipo" -> {
//                    PrintWriter outE = response.getWriter();
//                    idEquipo = Integer.valueOf(request.getParameter("getIdEquipo"));
//                    int e = equipoFactory.EliminarEquipo(idEquipo);
//                    JSONObject jsonE = new JSONObject();
//                    jsonE.put("IntegridadReferencia", e);
//                    outE.print(jsonE);
//                }
                default ->
                    throw new AssertionError();
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(incidencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(incidencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
