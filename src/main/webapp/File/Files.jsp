<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="jakarta.servlet.http.HttpSession"%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");

    if (session.getAttribute("usuario") == null) {
        request.getRequestDispatcher("./view/Login.jsp").forward(request, response);
    }

    Date date = new Date();
    DateFormat fecha = new SimpleDateFormat("dd.MMM.yyyy");
    Date ahora = new Date();
    SimpleDateFormat formateador = new SimpleDateFormat("hh:mm:ss");

%>
<meta charset="UTF-8">
<title>LOGISTICA</title>
<link rel="icon" type="imagen/png" href="imagen/logistica.png" />
