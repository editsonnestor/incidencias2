package org.logistica.controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import org.json.simple.JSONObject;
import org.logistica.bean.Usuario;
import org.logistica.factory.LoginFactory;

public class usuario extends HttpServlet {
    
    private final LoginFactory LoginFactory = new LoginFactory();
    String nombre, clave, acceso;
    Integer idUsuario;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        String menu = request.getParameter("menu");
        if (menu.equalsIgnoreCase("Usuario")) {
            switch (accion) {
                case "ListarUsuario" -> {
                    List entityListUsuario = LoginFactory.ListarUsuario();
                    request.setAttribute("entityListUsuario", entityListUsuario);
                    request.getRequestDispatcher("view/Usuario.jsp").forward(request, response);
                }

                case "RegistrarUsuario" -> {
                    nombre = request.getParameter("nombre");
                    clave = request.getParameter("clave");
                    acceso = request.getParameter("acceso");
                    Usuario u = new Usuario(nombre, acceso, clave);
                    LoginFactory.RegistrarUsuario(u);
                    request.getRequestDispatcher("usuario?menu=Usuario&accion=ListarUsuario").forward(request, response);
                }

                case "EliminarUsuario" -> {
                    PrintWriter outE = response.getWriter();
                    idUsuario = Integer.valueOf(request.getParameter("getIdUsuario"));
                    int e = LoginFactory.EliminarUsuario(idUsuario);
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
