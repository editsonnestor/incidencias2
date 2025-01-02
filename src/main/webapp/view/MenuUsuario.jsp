<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="../http/https.jsp"%>
        <%@ include file="../File/Files.jsp"%>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/css.css"/>  
        <style>
            body {
                background-color: #f7f7f7;
                font-family: Arial, sans-serif;
                font-size: 0.9rem;
                color: #6c757d;
                margin: 0;
                padding: 0;
            }
            /* Sidebar */
            .sidebar {
                height: 100vh;
                width: 250px;
                position: fixed;
                top: 0;
                left: 0;
                background-color: #343a40;
                color: white;
                padding-top: 20px;
            }
            .sidebar a {
                padding: 12px 20px;
                text-decoration: none;
                color: white;
                display: flex;
                align-items: center;
                font-size: 1rem;
                border-bottom: 1px solid #ccc;
                transition: background-color 0.3s ease, transform 0.3s ease;
            }
            .sidebar a:hover {
                background-color: #007b8f;
                transform: scale(1.05);
            }
            .sidebar a i {
                margin-right: 10px;
                font-size: 1.2rem;
            }
            .content {
                margin-left: 270px;
                padding: 20px;
            }
            .content-header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 20px;
            }
            .content-header h3 {
                font-size: 24px;
                font-weight: 500;
            }
            .content-header button {
                font-size: 16px;
                font-weight: 500;
                background-color: #007b8f;
                color: white;
                border: none;
                padding: 10px 20px;
                border-radius: 4px;
                cursor: pointer;
                transition: background-color 0.3s;
            }
            .content-header button:hover {
                background-color: #005f6b;
            }
            .fixed-bottom {
                background-color: #343a40;
                color: white;
                position: fixed;
                bottom: 0;
                width: 100%;
                text-align: center;
                padding: 10px;
            }
        </style>
    </head>
    <body>
        <form>
            <div class="sidebar">
                <h3 class="text-center">MUNICIPALIDAD DISTRITAL DE YARABAMBA</h3>
                <hr/>
                <p class="text-center">USUARIO ACTUAL: ${usuario}</p>
                <hr/>
                <a href="incidencia?menu=Incidencia&accion=ListarIncidencia" target="marco" id="incidenciasLink"><i class="bi bi-exclamation-circle"></i> Incidencias</a>
                <a href="${pageContext.request.contextPath}/logout" id="logoutLink"><i class="bi bi-box-arrow-right"></i> Logout</a>
            </div>
            <div class="fixed-bottom">
                <span class="fs-5 p-1">© 2024 Municipalidad Distrital de Yarabamba.</span>
            </div>
            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
            <div style="margin-left: 220px; margin-right: 1px; margin-top: 1px; margin-bottom: 1px; height: 900px;">
                <iframe name="marco" style=" height: 100%; width: 100%; border: none;"></iframe>
            </div>  
        </form>
    </body>
</html>
