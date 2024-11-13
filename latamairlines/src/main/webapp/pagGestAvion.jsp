<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>Gestión de Aviones</title>
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
                                        <h5 class="card-title"><i class="flaticon-list"></i> Gestión de Aviones</h5>
                                    </div>
                                    <div class="card-body">
                                        <a href="avion?accion=nuevo" class="btn btn-success btn-sm">
                                            <i class="fa fa-plus-circle"></i> Nuevo
                                        </a>

                                        <div class="table-responsive mt-2">
                                            <table id="tabla" class="table table-bordered table-striped data_tabla">
                                                <thead class="bg-primary">
                                                    <tr>
                                                        <th class="text-white">Codigo</th>
                                                        <th class="text-white">Modelo</th>
                                                        <th class="text-white">Capacidad</th>
                                                        <th class="text-white">Fabricante</th>
                                                        <th class="text-white">Año de Fabricación</th>
                                                        <th class="text-white">Acción</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${aviones}" var="item">
                                                        <tr>
                                                            <td>${item.idAvion}</td>
                                                            <td>${item.modelo}</td>
                                                            <td>${item.capacidad}</td>
                                                            <td>${item.fabricante}</td>
                                                            <td>${item.annioFabricacion}</td>
                                                            <td>
                                                                <a href="avion?accion=editar&id=${item.idAvion}"
                                                                   class="btn btn-info btn-sm">
                                                                    <i class="fa fa-edit"></i>
                                                                </a>
                                                                <a href="#"
                                                                   onclick="confirmEliminar(${item.idAvion})"
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
                        text: '¿Desea eliminar el avión con id ' + id + '?',
                        icon: 'warning',
                        showCancelButton: true,
                        confirmButtonColor: '#28a745',
                        cancelButtonColor: '#dc3545',
                        confirmButtonText: 'Sí, eliminarlo!',
                        cancelButtonText: 'Cancelar',
                        background: '#f8f9fa'
                    }).then((result) => {
                        if (result.isConfirmed) {
                            window.location.href = 'avion?accion=eliminar&id=' + id;
                        }
                    });
                }
            </script>
    </body>
</html>
