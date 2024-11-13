<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>Formulario Aeropuerto</title>
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
                                            <a href="aeropuerto?accion=listar" class="btn btn-link" title="Volver atrás" style="text-decoration: none; color: inherit; margin-left: 5px;">
                                                <i class="fa fa-arrow-left"></i> 
                                            </a>
                                            ${aeropuerto.idAeropuerto == 0 ? "Nuevo" : "Editar"} Aeropuerto
                                        </h5>
                                    </div>
                                    <form>
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-sm-6">
                                                    <div class="mb-3">
                                                        <label>Nombre: <span style="color: red;">(*)</span></label>
                                                        <input value="${aeropuerto.nombre}" name="nombre" type="text" maxlength="100" 
                                                               class="form-control" required="">
                                                    </div>
                                                </div>
                                                <div class="col-sm-6">
                                                    <div class="mb-3">
                                                        <label>Código IATA: <span style="color: red;">(*)</span></label>
                                                        <input value="${aeropuerto.codigo}" name="codigo" type="text" maxlength="10" 
                                                               class="form-control" required="">
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-sm-12">
                                                    <div class="mb-3">
                                                        <input type="hidden" name="id" value="${aeropuerto.idAeropuerto}">
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
