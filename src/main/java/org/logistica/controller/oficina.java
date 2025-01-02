package org.logistica.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;
import org.json.simple.JSONObject;
import org.logistica.bean.Oficina;
import org.logistica.factory.OficinaFactory;

public class oficina extends HttpServlet {

    private final OficinaFactory oficinaFactory = new OficinaFactory();
    String nombre, responsable, observaciones;
    Integer idOficina;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        String menu = request.getParameter("menu");
        if (menu.equalsIgnoreCase("Oficina")) {
            switch (accion) {
                case "ListarOficina" -> {
                    List entityListOficina = oficinaFactory.ListarOficina();
                    request.setAttribute("entityListOficina", entityListOficina);
                    request.getRequestDispatcher("view/Oficina.jsp").forward(request, response);
                }

                case "RegistrarOficina" -> {
                    nombre = request.getParameter("nombre");
                    responsable = request.getParameter("responsable");
                    observaciones = request.getParameter("observaciones");
                    Oficina oficina = new Oficina(nombre, responsable, observaciones);
                    oficinaFactory.RegistrarOficina(oficina);
                    request.getRequestDispatcher("oficina?menu=Oficina&accion=ListarOficina").forward(request, response);
                }

                case "EditarOficina" -> {
                    idOficina = Integer.valueOf(request.getParameter("idOficina"));
                    Oficina oficina = oficinaFactory.BuscarOficina(idOficina);
                    request.setAttribute("entityOficina", oficina);
                    request.getRequestDispatcher("oficina?menu=Oficina&accion=ListarOficina").forward(request, response);
                }

                case "ActualizarOficina" -> {
                    idOficina = Integer.valueOf(request.getParameter("idOficina"));
                    nombre = request.getParameter("nombre");
                    responsable = request.getParameter("responsable");
                    observaciones = request.getParameter("observaciones");
                    Oficina oficina = new Oficina(idOficina, nombre, responsable, observaciones);
                    oficinaFactory.ActulizarOficina(oficina);
                    request.getRequestDispatcher("oficina?menu=Oficina&accion=ListarOficina").forward(request, response);
                }

                case "EliminarOficina" -> {
                    PrintWriter outE = response.getWriter();
                    idOficina = Integer.valueOf(request.getParameter("getIdOficina"));
                    int e = oficinaFactory.EliminarOficina(idOficina);
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
