<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <%@ include file="../http/https.jsp"%>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/css.css"/>      
        <script type="text/javascript">
            $(document).ready(function () {
                $("#ValidarEnvio").validate({
                    rules: {
                        usuario: {
                            required: {
                                depends: function () {
                                    $(this).val($.trim($(this).val()));
                                    return true;
                                }
                            }
                        },
                        clave: {
                            required: {
                                depends: function () {
                                    $(this).val($.trim($(this).val()));
                                    return true;
                                }
                            }
                        },
                        acceso: {required: true}
                    },
                    messages: {
                        usuario: {required: "CAMPO OBLIGATORIO"},
                        clave: {required: "CAMPO OBLIGATORIO"},
                        acceso: {required: "CAMPO OBLIGATORIO"}
                    },
                    errorElement: "span"
                });
            });
        </script>
        <style>
            body {
                background-color: #f7f7f7;
                font-family: Arial, sans-serif;
            }
            .container {
                max-width: 400px;
                margin: auto;
            }
            .card {
                border-radius: 10px;
                box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
            }
            .card-header {
                background-color: #17a2b8;
                color: white;
                font-size: 1.25rem;
                border-radius: 10px 10px 0 0;
                text-align: center;
            }
            .card-body {
                background-color: #fff;
                padding: 2rem;
            }
            .card-footer {
                background-color: #17a2b8;
                text-align: right;
                padding: 1rem;
                border-radius: 0 0 10px 10px;
            }
            .btn {
                border-radius: 5px;
                padding: 10px 20px;
            }
            .btn-light {
                background-color: #f8f9fa;
                color: #343a40;
                border: none;
            }
            .btn-light:hover {
                background-color: #e2e6ea;
            }
            .input-group-text {
                background-color: #e9ecef;
                border-right: 1px solid #ddd;
            }
            .form-text {
                font-size: 0.9rem;
                color: #6c757d;
            }
            .error {
                color: red;
            }
            .nav-tabs .nav-item .nav-link {
                background-color: #e9ecef;
                color: #17a2b8;
            }
            .nav-tabs .nav-item .nav-link.active {
                color: white;
                background-color: #17a2b8;
            }
            .form-control {
                border-radius: 5px;
                border: 1px solid #ccc;
            }
        </style>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/login" method="post" id="ValidarEnvio">
            <div class="container mt-5">
                <div class="card border-info">
                    <div class="card-header fs-3">MUNICIPALIDAD DISTRITAL DE YARABAMBA</div>
                    <div class="card-body">
                        <ul class="nav nav-tabs">
                            <li class="nav-item">
                                <a class="nav-link active" data-bs-toggle="tab" href="#acceso"><i class="bi bi-check-all"></i>&nbsp;CONTROL DE ACCESO</a>
                            </li>
                        </ul>
                        <div class="tab-content mt-1">
                            <div class="tab-pane container active" id="acceso">
                                <h3 class="text-center" style="color: #17a2b8;">BIENVENIDO</h3>
                                <div class="mb-3">
                                    <div class="input-group">
                                        <span class="input-group-text"><i class="bi bi-person-fill"></i></span>
                                        <input type="text" name="usuario" id="usuario" class="form-control text-uppercase" placeholder="USUARIO" required />
                                    </div>
                                    <div class="form-text">Usuario registrado</div>
                                </div>
                                <div class="mb-3">
                                    <div class="input-group">
                                        <span class="input-group-text"><i class="bi bi-list-check"></i></span>
                                        <select class="form-select" name="acceso">
                                            <option value="">NIVEL DE ACCESO</option>
                                            <option value="ADMINISTRADOR">ADMINISTRADOR</option>
                                            <option value="USUARIO">USUARIO</option>
                                        </select>
                                    </div>
                                    <div class="form-text">Seleccione nivel de acceso</div>
                                </div>
                                <div class="mb-3">
                                    <div class="input-group">
                                        <span class="input-group-text"><i class="bi bi-box-arrow-in-right"></i></span>
                                        <input type="password" name="clave" id="clave" class="form-control text-uppercase" placeholder="CLAVE DE USUARIO" maxlength="8" required />
                                    </div>
                                    <div class="form-text">Clave de acceso</div>
                                </div>
                                <div class="mb-3">
                                    <center><p style="color: red;">${error}</p></center>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card-footer">
                        <button type="submit" name="accion" id="BtnLogin" value="Acceso" class="btn btn-light btn-sm"><i class="bi bi-box-arrow-in-right"></i>&nbsp;Aceptar</button>
                        <button type="reset" class="btn btn-light btn-sm"><i class="bi bi-box-arrow-left"></i>&nbsp;Salir</button>
                    </div>
                </div>
            </div>
        </form>
    </body>
</html>
