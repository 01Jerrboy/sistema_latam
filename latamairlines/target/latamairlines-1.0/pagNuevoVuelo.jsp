<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>Formulario Vuelo</title>
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
                                        <h5 class="card-title">
                                            <a href="vuelo?accion=listar" class="btn btn-link" title="Volver atrás" style="text-decoration: none; color: inherit; margin-left: 5px;">
                                                <i class="fa fa-arrow-left"></i> 
                                            </a>
                                            ${vuelo.idVuelo == 0 ? "Nuevo" : "Editar"} Vuelo
                                        </h5>
                                    </div>
                                    <form action="vuelo?accion=guardar" method="POST">
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-sm-6">
                                                    <div class="mb-3">
                                                        <label>Tipo de Vuelo: <span style="color: red;">(*)</span></label>
                                                        <select name="tipoVuelo" class="form-control" required="">
                                                            <option value="">::: Seleccione :::</option>
                                                            <c:forEach items="${tipoVuelos}" var="item">
                                                                <option value="${item}" ${vuelo.tipoVuelo == item ? 'selected' : ''}>
                                                                    ${item}
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="col-sm-3">
                                                    <div class="mb-3">
                                                        <label>Destino Origen: <span style="color: red;">(*)</span></label>
                                                        <select name="origenId" class="form-control" required="">
                                                            <option value="">::: Seleccione :::</option>
                                                            <c:forEach items="${destinos}" var="item">
                                                                <option value="${item.idDestino}" ${vuelo.destinoOrigen.idDestino == item.idDestino ? 'selected' : ''}>
                                                                    ${item.pais.nombre} - ${item.ciudad} 
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="col-sm-3">
                                                    <div class="mb-3">
                                                        <label>Destino Regreso: </label>
                                                        <select name="destinoId" class="form-control" required="">
                                                            <option value="">::: Seleccione :::</option>
                                                            <c:forEach items="${destinos}" var="item">
                                                                <option value="${item.idDestino}">
                                                                    ${item.pais.nombre} - ${item.ciudad} 
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-sm-6">
                                                    <div class="mb-3">
                                                        <label>Fecha Salida: <span style="color: red;">(*)</span></label>
                                                        <input value="${vuelo.fechaSalida}" name="fecha" type="date" 
                                                               class="form-control" required=""/>
                                                    </div>
                                                </div>
                                                <div class="col-sm-3">
                                                    <div class="mb-3">
                                                        <label>Hora Salida: <span style="color: red;">(*)</span></label>
                                                        <input value="${vuelo.horaSalida}" name="horaSalida" type="time" 
                                                               class="form-control" required=""/>
                                                    </div>
                                                </div>
                                                <div class="col-sm-3">
                                                    <div class="mb-3">
                                                        <label>Tiempo Vuelo: <span style="color: red;">(*)</span></label>
                                                        <input value="${vuelo.tiempoVuelo}" name="tiempoVuelo" type="time" 
                                                               class="form-control" required=""/>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-sm-3">
                                                    <div class="mb-3">
                                                        <label>Avión: <span style="color: red;">(*)</span></label>
                                                        <select name="avionId" class="form-control" required="">
                                                            <option value="">::: Seleccione :::</option>
                                                            <c:forEach items="${aviones}" var="item">
                                                                <option value="${item.idAvion}" ${vuelo.avion.idAvion == item.idAvion ? 'selected' : ''}>
                                                                    ${item.modelo} - ${item.fabricante}
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="mb-3">
                                                        <label>Clase Vuelo: <span style="color: red;">(*)</span></label>
                                                        <select name="claseVueloId" class="form-control" required="">
                                                            <option value="">::: Seleccione :::</option>
                                                            <c:forEach items="${claseVuelos}" var="item">
                                                                <option value="${item.idClaseVuelo}" ${vuelo.claseVuelo.idClaseVuelo == item.idClaseVuelo ? 'selected' : ''}>
                                                                    ${item.descripcion} 
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="mb-3">
                                                        <label>Costo Vuelo: <span style="color: red;">(*)</span></label>
                                                        <input type="number" name="costo" value="${vuelo.costoVuelo}" class="form-control">
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-sm-12">
                                                    <div class="mb-3">
                                                        <input type="hidden" name="id" value="${vuelo.idVuelo}">
                                                        <input type="hidden" name="accion" value="guardar">
                                                        <button class="btn btn-primary btn-sm" disabled="">
                                                            <i class="fa fa-save"></i> Guardar
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="includes/js.jsp" />
    </body>
</html>
