<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>Gestión de Vuelos</title>
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
                                        <h5 class="card-title"><i class="flaticon-list"></i> Gestión de Vuelos</h5>
                                    </div>
                                    <div class="card-body">
                                        <a href="vuelo?accion=nuevo" class="btn btn-success btn-sm">
                                            <i class="fa fa-plus-circle"></i> Nuevo
                                        </a>

                                        <div class="table-responsive mt-2">
                                            <table id="tabla" class="table table-bordered table-striped data_tabla">
                                                <thead class="bg-primary">
                                                    <tr>
                                                        <th class="text-white">ID Vuelo</th>
                                                        <th class="text-white">Aeropuerto</th>
                                                        <th class="text-white">Clase de Vuelo</th>
                                                        <th class="text-white">Avión</th>
                                                        <th class="text-white">Destino Origen</th>
                                                        <th class="text-white">Destino Regreso</th>
                                                        <th class="text-white">Fecha Salida</th>
                                                        <th class="text-white">Hora Salida</th>
                                                        <th class="text-white">Fecha Regreso</th>
                                                        <th class="text-white">Hora Regreso</th>
                                                        <th class="text-white">Tiempo Vuelo</th>
                                                        <th class="text-white">Tipo Vuelo</th>
                                                        <th class="text-white">Costo Vuelo</th>
                                                        <th class="text-white">Acción</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="vuelo" items="${vuelos}">
                                                        <tr>
                                                            <td>${vuelo.idVuelo}</td>
                                                            <td>${vuelo.aeropuerto.nombre}</td>
                                                            <td>${vuelo.claseVuelo.descripcion}</td>
                                                            <td>${vuelo.avion.modelo}</td>
                                                            <td>${vuelo.destinoOrigen.ciudad}</td>
                                                            <td>${vuelo.destinoRegreso == null? "": vuelo.destinoRegreso.ciudad}</td>
                                                            <td>${vuelo.fechaSalida}</td>
                                                            <td>${vuelo.horaSalida}</td>
                                                            <td>${vuelo.fechaRegreso}</td>
                                                            <td>${vuelo.horaRegreso}</td>
                                                            <td>${vuelo.tiempoVuelo}</td>
                                                            <td>${vuelo.tipoVuelo}</td>
                                                            <td>${vuelo.costoVuelo}</td>
                                                            <td>
                                                                <a href="vuelo?accion=editar&id=${vuelo.idVuelo}" class="btn btn-primary btn-sm">
                                                                    <i class="fa fa-edit"></i>
                                                                </a>
                                                                <a href="vuelo?accion=eliminar&id=${vuelo.idVuelo}" class="btn btn-danger btn-sm" onclick="return confirm('¿Está seguro de eliminar este vuelo?');">
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
                        text: '¿Desea eliminar el vuelo con ID ' + id + '?',
                        icon: 'warning',
                        showCancelButton: true,
                        confirmButtonColor: '#28a745',
                        cancelButtonColor: '#dc3545',
                        confirmButtonText: 'Sí, eliminarlo!',
                        cancelButtonText: 'Cancelar',
                        background: '#f8f9fa'
                    }).then((result) => {
                        if (result.isConfirmed) {
                            window.location.href = 'vuelo?accion=eliminar&id=' + id;
                        }
                    });
                }
            </script>
    </body>
</html>
