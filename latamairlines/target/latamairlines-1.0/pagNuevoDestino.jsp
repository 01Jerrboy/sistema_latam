<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>Formulario Destino</title>
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
                                            <a href="destino?accion=listar" class="btn btn-link" title="Volver atrás" style="text-decoration: none; color: inherit; margin-left: 5px;">
                                                <i class="fa fa-arrow-left"></i> 
                                            </a>
                                            ${destino.idDestino == 0 ? "Nuevo" : "Editar"} Destino
                                        </h5>
                                    </div>
                                    <form method="post" action="destino">
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-sm-6">
                                                    <div class="mb-3">
                                                        <label>Pais: <span style="color: red;">(*)</span></label>
                                                        <select name="paisId" class="form-control" required="">
                                                            <option value="">::: Seleccione :::</option>
                                                            <c:forEach var="pais" items="${paises}">
                                                                <option value="${pais.idPais}" ${pais.idPais == destino.pais.idPais ? "selected" : ""}>
                                                                    ${pais.nombre}
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="col-sm-6">
                                                    <div class="mb-3">
                                                        <label>Ciudad: <span style="color: red;">(*)</span></label>
                                                        <input value="${destino.ciudad}" name="ciudad" type="text" maxlength="100" class="form-control" required="">
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-sm-6">
                                                    <div class="mb-3">
                                                        <label>Aeropuerto: <span style="color: red;">(*)</span></label>
                                                        <select name="aeropuertoId" class="form-control" required="">
                                                            <option value="">::: Seleccione :::</option>
                                                            <c:forEach var="aeropuerto" items="${aeropuertos}">
                                                                <option value="${aeropuerto.idAeropuerto}" ${aeropuerto.idAeropuerto == destino.aeropuerto.idAeropuerto ? "selected" : ""}>
                                                                    ${aeropuerto.nombre}
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-sm-12">
                                                    <div class="mb-3">
                                                        <input type="hidden" name="id" value="${destino.idDestino}">
                                                        <input type="hidden" name="accion" value="guardar">
                                                        <button class="btn btn-primary btn-sm">
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
