<%-- 
    Document   : formularioReserva
    Created on : 12-jun-2019, 18:16:08
    Author     : oficina taxi
--%>

<%@page import="com.stevenmajek.POJO.Reserva"%>
<%@page import="com.stevenmajek.DAO.GestionDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="bootStrap.html"></jsp:include>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Add taxista</title>
        </head>
        <body class="fondo">
            <!--nav-->
            <nav class="navbar navbar-expand-lg navbar-dark fixed-top fondo">
                <img style="width: 60px; height: 60px" src="./Vistas/img/badge.png" class="mr-5">        
                <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                    <li class="nav-item">
                        <a class="nav-link bg-warning" style="color: black;" href="Controlador?accion=formulario">Añadir Taxista</a>                           
                    </li>
                    <li class="nav-item">
                        <a class="nav-link texto" href="Controlador?accion=formulario">Añadir reserva</a>                           
                    </li>
                    <li class="nav-item">
                        <a class="nav-link texto" href="Controlador?accion=reservas">Reservas</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link texto" href="Controlador?accion=listaTaxis">Reservas Hechas</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link texto" href="#" data-toggle="modal" data-target="#mimodal">Busquedad</a>
                        <div class="modal fade" id="mimodal">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header fondo">
                                        <form action="Controlador">
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
                                    <form class="" action="Controlador">
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
                <form class="form-inline" action="Controlador">
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
                    <div class="col align-middle">
                        <img src="./Vistas/img/cyber.jpg" alt="Imagen Taxi" class="rounded-circle" style="height: 600px; width: 600px">
                    </div>
                    <div class="col-4">
                    <%
                        int id = Integer.parseInt((String) request.getAttribute("id"));
                        GestionDao dao = new GestionDao();
                        Reserva r = dao.selectOneReserva(id);
                    %>
                    <form action="Controlador">
                        <h1 class="text-center">Añadir Taxista</h1>
                        <div class="form-group">
                            <label for="idReserva">Id reserva</label>
                            <input readonly="readonly" type="number" value="<%=r.getIdReserva()%>" class="form-control" name="idReserva">  
                        </div>
                        <div class="form-group">
                            <label for="hora">Hora</label>
                            <input required readonly="readonly" type="time" value="<%=r.getHora()%>" class="form-control" name="hora">  
                        </div>
                        <div class="form-group">
                            <label for="fecha">Fecha</label>
                            <input required readonly="readonly" type="date" value="<%=r.getFecha()%>" class="form-control" name="fecha">  
                        </div>
                        <div class="form-group">
                            <label for="origen">Origen</label>
                            <input required readonly="readonly" type="text" value="<%=r.getOrigen()%>" class="form-control" name="origen">
                        </div>
                        <div class="form-group">
                            <label for="habitacion">Habitacion</label>
                            <input readonly="readonly" type="text" value="<%=r.getHabitacion()%>" class="form-control" name="habitacion">  
                        </div>
                        <div class="form-group">
                            <label for="destino">Destino</label>
                            <input required readonly="readonly" type="text" value="<%=r.getDestino()%>" class="form-control" name="destino">  
                        </div>
                        <div class="form-group">
                            <label for="pax">Pax</label>
                            <input required readonly="readonly" type="number" value="<%=r.getPax()%>" class="form-control" name="pax">
                        </div>
                        <div class="form-group">
                            <label for="numeroTaxi">Numero Taxi</label>
                            <input required type="number" class="form-control" name="numeroTaxi">
                        </div>
                        <input type="submit" class="btn btn-info btn-block" value="Agregar Taxista" name="accion">
                    </form>
                </div>
            </div>
        </div>
    </body>
    
    <jsp:include page="footer.jsp"></jsp:include>
</html>
