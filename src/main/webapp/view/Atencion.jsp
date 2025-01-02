<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="../http/https.jsp"%>
        <%@ include file="../File/Files.jsp"%>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/css.css"/>      
    </head>
    <script type="text/javascript">
        $(document).ready(function () {
            $(function () {
                $('#fecha').datepicker({
                    firstDay: 1,
                    dateFormat: 'd/mm/yy',
                    changeMonth: true,
                    changeYear: true,
                    monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio',
                        'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
                    monthNamesShort: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun',
                        'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'],
                    dayNames: ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado'],
                    dayNamesShort: ['Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sa'],
                    dayNamesMin: ['Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sa']
                });
            });
            $(function () {
                $('#fecha').keypress(function () {
                    var fecha = document.getElementById('fecha');
                    if (fecha.value.length === 2 || fecha.value.length === 5) {
                        fecha.value += "/";
                    }
                });
            });
        });
        $(document).ready(function () {
            $("tr #AjaxWeb").click(function () {
                var getIdEquipo = $(this).parent().find("#getIdEquipo").val();
                var getDetalle = $(this).parent().find("#getDetalle").val();
                Swal.fire({
                    position: "center",
                    title: 'ELIMINAR REGISTRO.',
                    text: 'REGISTRO: ' + getDetalle,
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: 'ACEPTAR'
                }).then((result) => {
                    if (result.isConfirmed) {
                        $.ajax({
                            type: 'POST',
                            url: '${pageContext.request.contextPath}/equipo?menu=Equipo&accion=EliminarEquipo',
                            data: 'getIdEquipo=' + getIdEquipo,
                            success: function (data) {
                                var e = $.parseJSON(data);
                                if (e.IntegridadReferencia === 0) {
                                    Swal.fire({
                                        position: "center",
                                        icon: "secondary",
                                        title: "ADVERTENCIA",
                                        text: "EL REGISTRO NO SE PUEDE ELIMINAR, ESTA RELACIONADO",
                                        showConfirmButton: false,
                                        timer: 3000,
                                        timerProgressBar: true
                                    });
                                    return false;
                                }
                                location.target = "marco";
                                location.href = "${pageContext.request.contextPath}/equipo?menu=Equipo&accion=ListarEquipo";
                            }
                        });
                    }
                });
            });
        });
        $(document).ready(function () {
            var detalle = $("#detalle").val();
            if (detalle === '') {
                $("#Registrar").attr("disabled", false);
                $("#Actulizar").attr("disabled", true);
            } else {
                $("#Registrar").attr("disabled", true);
                $("#Actulizar").attr("disabled", false);
            }
        });
        $(document).ready(function () {
            $("#Formulario").validate({
                rules: {
                    prioridad: {required: true},
                    fecha: {required: true},
                    detalle: {required: true},
                    idEquipo: {required: true},
                    idOficina: {required: true}
                },
                messages: {
                    prioridad: {required: "CAMPO OBLIGATORIO"},
                    fecha: {required: "CAMPO OBLIGATORIO"},
                    detalle: {required: "CAMPO OBLIGATORIO"},
                    idEquipo: {required: "CAMPO OBLIGATORIO"},
                    idOficina: {required: "CAMPO OBLIGATORIO"}
                },
                errorElement: "span"
            });
        });
        $(document).ready(function () {
            $(".VistaPrevia").click(function () {
                Swal.fire({
                    position: "center",
                    icon: 'success',
                    title: 'CONFIRMACION',
                    text: 'PREPARANDO REPORTE PARA IMPRESION',
                    timer: 3000,
                    timerProgressBar: true
                });
                return true;
            });
        });
        $(document).ready(function () {
            $("#tRegistroTabla").DataTable({
                processing: true,
                language: {
                    search: "BUSCAR: ",
                    lengthMenu: "LISTAR: _MENU_",
                    secondary: "TOTAL DE REGISTROS: _TOTAL_",
                    sInfoFiltered: "  DE: _MAX_ REGISTROS ",
                    paginate: {
                        "first": "PRIMERO",
                        "last": "ULTIMO",
                        "next": "SIGUIENTE",
                        "previous": "ANTERIOR"
                    }
                },
                responsive: "true",
                /*dom: "Bfirtlp",*/
                dom: "<'row'<'col-sm-2'B><'col-sm-2'l><'col-sm-8'f>>" +
                        "<'row'<'col-sm-12'tr>>" +
                        "<'row'<'col-sm-5'i><'col-sm-7'p>>",
                buttons: [
                    {
                        extend: "excelHtml5",
                        text: "<i class='bi bi-file-excel'></i>",
                        titleAttr: "EXPORTAR A EXCEL",
                        className: "btn btn-secondary btn-dm"
                    },
                    {
                        extend: "pdfHtml5",
                        text: "<i class='bi bi-file-earmark-pdf'></i>",
                        titleAttr: "EXPORTAR A PDF",
                        className: "btn btn-secondary btn-dm"
                    },
                    {
                        extend: "print",
                        text: "<i class='bi bi-printer'></i></span>",
                        titleAttr: "IMPRESION DIRECTA",
                        className: "btn btn-secondary btn-dm"
                    }
                ]
            });
        });
    </script>
    <body> 
        <br/><br/>
        <form action="${pageContext.request.contextPath}/incidencia?menu=Incidencia" method="post" id="Formulario">
            <div class="container-fluid mt-2 col-md-10">
                <div class="card border-secondary">
                    <div class="card card-header text-center text-white bg-secondary p-3 fs-3">FORMULARIO DE ATENCIONES</div>
                    <div class="card card-body">
                        <ul class="nav nav-tabs">
                            <li class="nav-item">
                                <a class="nav-link active p-3 fs-6" style="color: #17a2b8;" data-bs-toggle="tab" href="#listadoPrincipal"><i class="bi bi-list-ol"></i>&nbsp;LISTADO DE ATENCIONES</a>
                            </li>
                        </ul>
                        <div class="tab-content mt-1">
                            <div class="tab-pane container-fluid active" id="listadoPrincipal">
                                <div class="row">
                                    <h4 class="text-center mt-2 mb-3">LISTADO DE ATENCIONES</h4>
                                    <table class="table table-striped table-bordered table-sm table-hover"
                                           style="font-size: 12px; cursor: pointer; width:100%;" id="tRegistroTabla">
                                        <thead>
                                            <tr>
                                                <th class="text-center bg-secondary text-white p-2 fs-6">ID</th>
                                                <th class="text-center bg-secondary text-white p-2 fs-6">ESTADO</th>
                                                <th class="text-center bg-secondary text-white p-2 fs-6">FECHA</th>
                                                <th class="text-center bg-secondary text-white p-2 fs-6">OFICINA</th>
                                                <th class="text-center bg-secondary text-white p-2 fs-6">GLOSA</th>
                                                <th class="text-center bg-secondary text-white p-2 fs-6">PRIORIDAD</th>
                                                <th class="text-center bg-secondary text-white p-2 fs-6"><i class="bi bi-clipboard-data" style="font-size: 16px;"></i></th>
                                            </tr> 
                                        </thead>
                                        <tbody>
                                            <c:forEach var="Lista" items="${entityListAtencion}">
                                                <tr>
                                                    <td class="text-center align-middle text-secondary" style="width: 4%;">${Lista.getIdIncidencia()}</td>
                                                    <td class="text-center align-middle text-secondary" style="width: 7%;">
                                                        <c:if test="${Lista.getEstado()=='N'}">
                                                            <span style="font-weight: bold; color: red;" >FALTA</span>
                                                        </c:if>
                                                        <c:if test="${Lista.getEstado()=='S'}">
                                                            <span style="font-weight: bold; color: purple;" >ATENDIDO</span>
                                                        </c:if>
                                                    </td>
                                                    <td class="text-center align-middle text-secondary" style="width: 7%;"><fmt:formatDate pattern="dd/MM/yyyy" value="${Lista.getFecha()}"/></td>
                                                    <td class="text-star align-middle text-secondary" style="width: 10%;">${Lista.getNombre()}</td>
                                                    <td class="text-star align-middle text-secondary" style="width: 40%;">${Lista.getGlosa()}</td>
                                                    <td class="text-center align-middle text-secondary" style="width: 10%;">
                                                        <c:if test="${Lista.getPrioridad()=='URGENTE'}">
                                                            <span style="font-weight: bold; color: red;" >URGENTE</span>
                                                        </c:if>
                                                        <c:if test="${Lista.getPrioridad()=='INTERMEDIA'}">
                                                            <span style="font-weight: bold; color: yellowgreen;" >INTERMEDIA</span>
                                                        </c:if>
                                                        <c:if test="${Lista.getPrioridad()=='NORMAL'}">
                                                            <span style="font-weight: bold; color: purple;" >NORMAL</span>
                                                        </c:if>
                                                    </td>
                                                    <td class="text-center align-middle text-secondary" style="width: 5%; text-align: center;">
                                                        <a class="SeleccionarRegistro" style="text-decoration: none;" href="${pageContext.request.contextPath}/incidencia?menu=Incidencia&accion=CambiarEstado&idIncidencia=${Lista.getIdIncidencia()}"
                                                           data-toggle="tooltip" data-placement="top" title="CAMBIAR ESTADO">
                                                            <i class="bi bi-pencil-square" style="font-size: 16px; color: teal;"></i>
                                                        </a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card-footer bg-secondary text-star text-white">
                        <div class="row">
                            <div class="col col-md-12 text-center">
                                <p class="mt-1 mb-1">MUNICIPALIDAD DISTRITAL DE YARABAMBA</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </body>
</html>

