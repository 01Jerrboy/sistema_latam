    <%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>Gestión de Aeropuertos</title>
        <meta content='width=device-width, initial-scale=1.0, shrink-to-fit=no' name='viewport' />
        <jsp:include page="includes/css.jsp" />
    </head>
    <body>
        <div class="wrapper">
            <jsp:include page="includes/navegacion.jsp" />
            <jsp:include page="includes/mensaje.jsp" />

            <div class="main-panel">
                <div class="content">
                    <div class="page-inner">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="card">
                                    <div class="card-header">
                                        <h5 class="card-title"><i class="flaticon-list"></i> Gestión de Aeropuertos</h5>
                                    </div>
                                    <div class="card-body">
                                        <a href="aeropuerto?accion=nuevo" class="btn btn-success btn-sm">
                                            <i class="fa fa-plus-circle"></i> Nuevo
                                        </a>

                                        <div class="table-responsive mt-2">
                                            <table id="tabla" class="table table-bordered table-striped data_tabla">
                                                <thead class="bg-primary">
                                                    <tr>
                                                        <th class="text-white">ID</th>
                                                        <th class="text-white">Código</th>
                                                        <th class="text-white">Nombre</th>
                                                        <th class="text-white">Acción</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${aeropuertos}" var="item">
                                                        <tr>
                                                            <td>${item.idAeropuerto}</td>
                                                            <td>${item.codigo}</td>
                                                            <td>${item.nombre}</td>
                                                            <td>
                                                                <a href="aeropuerto?accion=editar&id=${item.idAeropuerto}"
                                                                   class="btn btn-info btn-sm">
                                                                    <i class="fa fa-edit"></i>
                                                                </a>
                                                                <a href="#"
                                                                   onclick="confirmEliminar(${item.idAeropuerto})"
                                                                   class="btn btn-danger btn-sm">
                                                                    <i class="fa fa-trash"></i>
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
                        </div>
                    </div>
                </div>
            </div>

            <jsp:include page="includes/js.jsp" />

            <script>
                function confirmEliminar(id) {
                    Swal.fire({
                        title: '¿Está seguro?',
                        text: '¿Desea eliminar el aeropuerto con id ' + id + '?',
                        icon: 'warning',
                        showCancelButton: true,
                        confirmButtonColor: '#28a745',
                        cancelButtonColor: '#dc3545',
                        confirmButtonText: 'Sí, eliminarlo!',
                        cancelButtonText: 'Cancelar',
                        background: '#f8f9fa'
                    }).then((result) => {
                        if (result.isConfirmed) {
                            window.location.href = 'aeropuerto?accion=eliminar&id=' + id;
                        }
                    });
                }
            </script>
    </body>
</html>
