package org.logistica.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;
import org.json.simple.JSONObject;
import org.logistica.bean.Equipo;
import org.logistica.factory.EquipoFactory;

public class equipo extends HttpServlet {
    
    private final EquipoFactory equipoFactory = new EquipoFactory();
    String codigo, detalle, observaciones;
    Integer idEquipo;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        String menu = request.getParameter("menu");
        if (menu.equalsIgnoreCase("Equipo")) {
            switch (accion) {
                case "ListarEquipo" -> {
                    List entityListEquipo = equipoFactory.ListarEquipo();
                    request.setAttribute("entityListEquipo", entityListEquipo);
                    request.getRequestDispatcher("view/Equipo.jsp").forward(request, response);
                }

                case "RegistrarEquipo" -> {
                    codigo = request.getParameter("codigo");
                    detalle = request.getParameter("detalle");
                    observaciones = request.getParameter("observaciones");
                    Equipo equipo = new Equipo(codigo, detalle, observaciones);
                    equipoFactory.RegistrarEquipo(equipo);
                    request.getRequestDispatcher("equipo?menu=Equipo&accion=ListarEquipo").forward(request, response);
                }

                case "EditarEquipo" -> {
                    idEquipo = Integer.valueOf(request.getParameter("idEquipo"));
                    Equipo equipo = equipoFactory.BuscarEquipo(idEquipo);
                    request.setAttribute("entityEquipo", equipo);
                    request.getRequestDispatcher("equipo?menu=Equipo&accion=ListarEquipo").forward(request, response);
                }

                case "ActualizarEquipo" -> {
                    idEquipo = Integer.valueOf(request.getParameter("idEquipo"));
                    codigo = request.getParameter("codigo");
                    detalle = request.getParameter("detalle");
                    observaciones = request.getParameter("observaciones");
                    Equipo equipo = new Equipo(idEquipo, codigo, detalle, observaciones);
                    equipoFactory.ActulizarEquipo(equipo);
                    request.getRequestDispatcher("equipo?menu=Equipo&accion=ListarEquipo").forward(request, response);
                }

                case "EliminarEquipo" -> {
                    PrintWriter outE = response.getWriter();
                    idEquipo = Integer.valueOf(request.getParameter("getIdEquipo"));
                    int e = equipoFactory.EliminarEquipo(idEquipo);
                    JSONObject jsonE = new JSONObject();
                    jsonE.put("IntegridadReferencia", e);
                    outE.print(jsonE);
                }

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
        processRequest(request, response);
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
        processRequest(request, response);
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
