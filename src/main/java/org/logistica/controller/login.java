package org.logistica.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.logistica.bean.Usuario;
import org.logistica.factory.LoginFactory;

public class login extends HttpServlet {

    private final LoginFactory loginFactory = new LoginFactory();
    private Usuario usu = new Usuario();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String accion = request.getParameter("accion");
        String usuario = request.getParameter("usuario");
        String clave = request.getParameter("clave");
        String acceso = request.getParameter("acceso");
        if (accion.equalsIgnoreCase("Acceso")) {
            usu = loginFactory.ValidarUsuario(usuario, acceso, clave);
            if (usu == null) {
                String error = "CREDENCIALES INCORRECTAS";
                request.setAttribute("error", error);
                request.getRequestDispatcher("view/Login.jsp").forward(request, response);
            } else {
                if ((usu.getUsuario().equals(usuario)) && (usu.getClave().equals(clave)) && (usu.getNivelAceso().equals(acceso))) {
                    session.setAttribute("idUsuario", usu.getIdUsuario());
                    session.setAttribute("usuario", usu.getUsuario());
                    session.setAttribute("clave", usu.getClave());
                    session.setAttribute("nivelAcceso", usu.getNivelAceso());
                    if (usu.getNivelAceso().equalsIgnoreCase("ADMINISTRADOR")) {
                        request.getRequestDispatcher("view/Menu.jsp").forward(request, response);
                    }
                    if (usu.getNivelAceso().equalsIgnoreCase("USUARIO")) {
                        request.getRequestDispatcher("view/MenuUsuario.jsp").forward(request, response);
                    }
                } else {
                    String error = "CREDENCIALES INCORRECTAS";
                    request.setAttribute("error", error);
                    request.getRequestDispatcher("view/Login.jsp").forward(request, response);
                }
            }
        } else {
            request.getRequestDispatcher("view/Login.jsp").forward(request, response);
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
