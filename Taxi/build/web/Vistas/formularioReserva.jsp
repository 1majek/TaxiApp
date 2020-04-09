<%-- 
    Document   : formularioReserva
    Created on : 12-jun-2019, 18:16:08
    Author     : oficina taxi
--%>


<%@page import="com.stevenmajek.POJO.Nota"%>
<%@page import="com.stevenmajek.DAO.GestionDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="errorPage.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="bootStrap.html"></jsp:include>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Formulario</title>
        </head>
        <body class="fondo">
            <!--nav-->
        <%  
            //String nota = (String) request.getAttribute("nota");
            GestionDao dao = new GestionDao();
            Nota n = dao.showNota();
        %>
        <nav class="navbar navbar-expand-lg navbar-dark fixed-top fondo">

            <a title="Desplegar notas" href="#demo2" data-toggle="collapse" ><img class="mr-5 d-none d-lg-block" style="width: 60px; height: 60px" src="./Vistas/img/badge.png"></a>
            <div id="demo2" class="collapse row mr-3">
                <div class="col">
                    <%=n.getHora()%>
                </div>             
            </div>
                <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                    <li class="nav-item">
                        <a class="nav-link bg-warning texto" style="color: black;" href="Controlador?accion=formulario">Añadir reserva</a>                           
                    </li>
                    <li class="nav-item">
                        <a class="nav-link texto" href="Controlador?accion=reservas">Reservas</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link texto" href="Controlador?accion=listaTaxis">Reservas Hechas</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link texto" href="#" data-toggle="modal" data-target="#mimodal">Búsqueda</a>
                        <div class="modal fade" id="mimodal">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header fondo">
                                        <form method="POST" action="Controlador">
                                            <h4 class="modal-title"><div id="acordion1" class=" my-2 ml-2">
                                                    <a class="mr-3 text-center enlace texto" style="color: #fefefe" title="Busquedad Roig" href="#demo3" data-toggle="collapse" >Roig</a>
                                                    <div id="demo3" class="collapse row">
                                                        <div class="col">
                                                            <input type="search" class="form-control mt-2" placeholder="V124356" name="busquedadRoig"> 
                                                        </div>
                                                        <div class="col">
                                                            <button type="submit" class="mt-2 btn btn-info btn-sm" name="accion" value="buscarRoig">Buscar</button>
                                                        </div>                                                                  
                                                    </div>
                                                </div>
                                            </h4>
                                        </form>
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    </div>
                                    <form method="POST" action="Controlador">
                                        <div class="modal-body">
                                            <div class=" row form-group justify-content-around" style="color: black;">

                                                <div class="my-2">
                                                    <label class="mr-3 text-center" for="fecha1">Desde</label>
                                                    <input type="date" class="form-control" name="fecha1">
                                                </div>

                                                <div class="my-2">
                                                    <label class="mr-3 text-center " for="fecha">Hasta</label>
                                                    <input type="date" class="form-control" name="fecha2">
                                                </div>

                                                <div class="my-2">
                                                    <label class="mr-3 text-center" for="busquedadOrigen">Origen</label>
                                                    <input type="search" class="form-control" placeholder="Blau" name="busquedadOrigen">
                                                </div>

                                                <div class="my-2">
                                                    <label class="mr-3 text-center" for="busquedadNumeroTaxi">Numero Taxi</label>
                                                    <input type="search" class="form-control" placeholder="5" name="busquedadNumeroTaxi">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <div class="col-4">
                                                <button type="submit" class="btn btn-outline-info" value="buscarHoy" name="accion">Hoy</button>
                                            </div>
                                            <div class="col-5">                                                                
                                                <button type="submit" class="btn btn-info" name="accion" value="buscarFecha">Buscar</button>
                                            </div>
                                        </div>                                     
                                    </form>
                                </div>
                            </div>
                        </div>
                    </li>
                </ul>
                <form class="form-inline" method="POST" action="Controlador">
                    <input class="form-control mr-sm-2" type="search" placeholder="Search" name="inputBuscar">
                    <button class="btn bg-warning" title="buscar" value="buscarOrigen" type="search" name="accion"><img style="width: 25px; height: 25px" src="./Vistas/img/search.png"></button>
                </form>

        </nav>
        <!--end nav-->
        <div class="mb-1 invisible">
            <span>lol</span>
        </div>

        <div class="my-5 container">
            <div class="row align-items-center">
                <div class="col align-middle d-none d-lg-block d-xl-none">
                    <img src="./Vistas/img/taxi.jpg" alt="Imagen Taxi" class="rounded-circle" style="height: 450px; width: 450px">
                </div>
                <div class="col align-middle d-none d-none d-xl-block">
                    <img src="./Vistas/img/taxi.jpg" alt="Imagen Taxi" class="rounded-circle" style="height: 600px; width: 600px">
                </div>
                <div class="col-4">
                    <form method="POST" action="Controlador">
                        <h1 class=" text-center">Añadir Reserva</h1>
                        <div class="form-group">
                            <label for="hora">Hora</label>
                            <input required type="time" class="form-control" name="hora" autofocus>  
                        </div>
                        <div class="form-group">
                            <label for="fecha">Fecha</label>
                            <input required type="date" class="form-control" name="fecha" placeholder="Enter email">  
                        </div>
                        <div class="form-group">
                            <label for="origen">Origen</label>
                            <input required type="text" class="form-control" name="origen" placeholder="Hotel Blau">
                        </div>
                        <div class="form-group">
                            <label for="habitacion">Habitación</label>
                            <input type="text" class="form-control" name="habitacion" placeholder="B1254">  
                        </div>
                        <div class="form-group">
                            <label for="destino">Destino</label>
                            <input required type="text" class="form-control" name="destino" placeholder="Salida aeropuerto">  
                        </div>
                        <div class="form-group">
                            <label for="pax">Pax</label>
                            <input required type="number" class="form-control" name="pax" placeholder="">
                        </div>
                        <div class="form-group">
                            <label for="observacion">Observación</label>
                            <input type="text" class="form-control" name="observacion" placeholder="1 silla">  
                        </div>
                        <div class="form-group">
                            <label for="claseReserva">Clase reserva</label>
                            <select name="claseReserva" class="custom-select">
                                <option value="Pago directo">Pago Directo</option>
                                <option value="Agencia de pago aplazado">Agencia De Pago Aplazado</option>
                                <option value="Al Momento">Al Momento</option>
                                <option value="Taxistas">Taxistas</option>
                            </select>
                        </div>
                        <input type="submit" class="btn btn-info btn-block" title="Realizar reserva"value="Reservar" name="accion">
                    </form>
                </div>
            </div>
        </div>
    </body>
    <jsp:include page="footer.jsp"></jsp:include>
</html>
