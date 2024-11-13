<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="main-header" data-background-color="purple" >
    <div class="logo-header">
        <a href="#" class="logo" style="font-weight: bold; color: white;">
            LATAM airlenes
        </a>
        <button class="navbar-toggler sidenav-toggler ml-auto" type="button" data-toggle="collapse" data-target="collapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon">
                <i class="fa fa-bars"></i>
            </span>
        </button>
        <button class="topbar-toggler more"><i class="fa fa-ellipsis-v"></i></button>
        <div class="navbar-minimize">
            <button class="btn btn-minimize btn-rounded">
                <i class="fa fa-bars"></i>
            </button>
        </div>
    </div>

    <nav class="navbar navbar-header navbar-expand-lg">

        <div class="container-fluid">

            <ul class="navbar-nav topbar-nav ml-md-auto align-items-center">
                <li class="nav-item toggle-nav-search hidden-caret">
                    <a class="nav-link" data-toggle="collapse" href="#search-nav" role="button" aria-expanded="false" aria-controls="search-nav">
                        <i class="fa fa-search"></i>
                    </a>
                </li>

                <li class="nav-item nav-profile dropdown">
                    <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" id="profileDropdown">
                        <img src="assets/img/airline.png" alt="profile" style="width: 60px;height: 40px;"/>
                        <span class="nav-profile-name">Admin</span>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right navbar-dropdown" aria-labelledby="profileDropdown">
                        <a class="dropdown-item">
                            <i class="mdi mdi-settings text-primary"></i>
                            Perfil
                        </a>
                        <a class="dropdown-item">
                            <i class="mdi mdi-logout text-primary"></i>
                            Cerrar Sesión
                        </a>
                    </div>
                </li>
            </ul>
        </div>
    </nav>
</div>

<div class="sidebar">
    <div class="sidebar-wrapper scrollbar-inner">
        <div class="sidebar-content">
            <div class="user">
                <div class="avatar-sm float-left mr-2">
                    <img src="assets/img/latam2.webp" alt="..." class="avatar-img rounded-circle">
                </div>
                <div class="info">
                    <a data-toggle="collapse" href="#collapseExample" aria-expanded="true">
                        <span>
                            ${sessionScope.usuario.username}
                            <span class="user-level">${sessionScope.usuario.nombreRol}</span>
                        </span>
                    </a>
                    <div class="clearfix"></div>

                </div>
            </div>
            <ul class="nav">
                <li class="nav-item">
                    <a href="#">
                        <i class="fas fa-home"></i>
                        <p>Inicio</p>
                    </a>
                </li>
                <li class="nav-section">
                    <span class="sidebar-mini-icon">
                        <i class="fa fa-ellipsis-h"></i>
                    </span>
                    <h4 class="text-section">Módulos</h4>
                </li>


                <li class="nav-item">
                    <a data-toggle="collapse" href="#forms">
                        <i class="fas fa-tasks"></i>
                        <p>Gestión</p>
                        <span class="caret"></span>
                    </a>
                    <div class="collapse" id="forms">
                        <ul class="nav nav-collapse">
                            <li>
                                <a href="aeropuerto?accion=listar">
                                    <span class="sub-item">Aeropuerto</span>
                                </a>
                            </li>
                            <li>
                                <a href="avion?accion=listar">
                                    <span class="sub-item">Avión</span>
                                </a>
                            </li>
                            <li>
                                <a href="destino?accion=listar">
                                    <span class="sub-item">Destino</span>
                                </a>
                            </li>
                            <li>
                                <a href="vuelo?accion=listar">
                                    <span class="sub-item">Vuelo</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </li>

            </ul>
        </div>
    </div>
</div>