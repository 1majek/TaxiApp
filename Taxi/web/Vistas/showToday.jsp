<%-- 
    Document   : Reserva
    Created on : 12-jun-2019, 17:14:24
    Author     : oficina taxi
--%>

<%@page import="com.stevenmajek.POJO.Taxista"%>
<%@page import="com.stevenmajek.POJO.Reserva"%>
<%@page import="com.stevenmajek.DAO.GestionDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="bootStrap.html"></jsp:include>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Reservas</title>
        </head>
        <body>
            <!--nav-->
            <nav class="navbar navbar-expand-lg navbar-dark fixed-top fondo">
                <img style="width: 60px; height: 60px" src="./Vistas/img/badge.png" class="mr-5">        
                <ul class="navbar-nav mr-auto mt-2 mt-lg-0">

                    <li class="nav-item">
                        <a class="nav-link texto"  href="Controlador?accion=formulario">Añadir reserva</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link texto" href="Controlador?accion=reservas">Reservas</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link texto " href="Controlador?accion=listaTaxis">Reservas Hechas</a>
                    <%
                     GestionDao dao = new GestionDao();
                    %>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link texto bg-warning" style="color: black;" href="Controlador?accion=formulario">Reservas Hoy <span title="Total" class="badge badge-danger"><%=dao.countHoy()%></span></a>
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
            <span>.</span>
        </div>

        <div class="container-fluid my-5">
            <div class="row">
                <div class="col">
                    <table class="text-center my-2 table table-responsive-xs border text-center table-hover">
                        <thead class="">
                            <tr>
                                <th scope="col">Id reserva</th>
                                <th scope="col">Taxi</th>
                                <th scope="col">Numero Taxi</th>
                                <th scope="col">Hora</th>
                                <th scope="col">Fecha</th>
                                <th scope="col">Origen</th>
                                <th scope="col">Habitacion</th>
                                <th scope="col">Destino</th>
                                <th scope="col">Pax</th>
                                <th scope="col">Observacion</th>
                                <th scope="col">Clase Reserva</th> 
                                <th scope="col">Eliminar</th>   
                                <th scope="col">Modificar</th> 
                                <th scope="col">Añadir Taxista</th>
                                <th scope="col">Copiar</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%

                                dao.searchToday();

                                int i = 0;

                                for (Taxista t : dao.listaFecha) {
                                    i++;

                            %>
                            <tr>
                                <td><%=t.getIdReserva()%></td>
                                <td><%=t.getTaxi()%></td>
                                <td>
                                    <div id="acordion2">
                                        <a class="enlace" title="detalles" href="#demo4<%=i%>" data-toggle="collapse" ><%=t.getNum_licencia()%></a>

                                        <div id="demo4<%=i%>" class="collapse">
                                            <span>
                                                <p class="badge badge-pill badge-info my-2"><%=t.getNombre()%> <%=t.getNum_licencia()%></p><br>
                                                Telefono: <%=t.getTelefono()%> <br>
                                                Marca de coche: <%=t.getMarcaCoche()%> <br>
                                                Modelo: <%=t.getModelo()%> <br>
                                                Matricula: <%=t.getMatricula()%> <br>
                                                Poblacion: <%=t.getPoblacion()%> <br>
                                            </span>
                                            <a href="Controlador?accion=viajesTaxista&numTaxista=<%=t.getNum_licencia()%>&nombre=<%=t.getNombre()%>"<span class="badge badge-warning">Viajes realizadas: <%=dao.countTotalViaje(t.getNum_licencia())%></span></a>


                                        </div>
                                    </div>
                                </td>
                                <td><%=t.getHora()%></td>
                                <td><%=t.getFecha()%></td>
                                <td><%=t.getOrigen()%></td>
                                <td><%=t.getHabitacion()%></td>
                                <td><%=t.getDestino()%></td>                    
                                <td><%=t.getPax()%></td>
                                <td><%=t.getObservacion()%></td>
                                <td><%=t.getClaseReserva()%></td>
                                <td>
                                    <a class="btn" title="eliminar" data-toggle="modal" data-target="#modalRemove<%=i%>"><img style="width: 25px; height: 25px" src="./Vistas/img/delete.png"></a>
                                    <div class="modal fade" id="modalRemove<%=i%>" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                        <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header fondo">
                                                    <h5 class="modal-title">Eliminar Reserva <%=t.getIdReserva()%></h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    <p>¿Seguro que deseas eliminar esta reserva de <%=t.getOrigen()%> que van a <%=t.getDestino()%> que realizara el taxi numero <%=t.getNum_licencia()%>?</p>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                                                    <a href="Controlador?accion=eliminarViaje&idReserva=<%=t.getIdReserva()%>" class="btn btn-info removeBtn">Eliminar</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <a class="btn" title="editar" href="Controlador?accion=editarReservaTaxista&idViaje=<%=t.getIdReserva()%>&numLicencia=<%=t.getNum_licencia()%>"><img style="width: 25px; height: 25px" src="./Vistas/img/edit.png"></a>
                                </td>
                                <!--Add Taxista-->
                                <td>
                                    <a class="btn" data-toggle="modal" data-target="#modalAdd<%=i%>" title="añadir taxista" href=""><img style="width: 25px; height: 25px" src="./Vistas/img/add.png"></a>
                                    <div class="modal fade" id="modalAdd<%=i%>" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                        <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header fondo">
                                                    <h5 class="modal-title">Añadir Taxista a la reserva <%=t.getIdReserva()%></h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <form action="Controlador">
                                                    <div class="form-group">
                                                        <label class="d-none" for="idReserva">Id reserva</label>
                                                        <input readonly="readonly" type="number" value="<%=t.getIdReserva()%>" class="d-none form-control" name="idReserva">  
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="d-none" for="hora">Hora</label>
                                                        <input readonly="readonly" type="time" value="<%=t.getHora()%>" class="d-none form-control" name="hora">  
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="d-none" for="fecha">Fecha</label>
                                                        <input readonly="readonly" type="date" value="<%=t.getFecha()%>" class="d-none form-control" name="fecha">  
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="d-none" for="origen">Origen</label>
                                                        <input readonly="readonly" type="text" value="<%=t.getOrigen()%>" class="d-none form-control" name="origen">
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="d-none" for="habitacion">Habitacion</label>
                                                        <input readonly="readonly" type="text" value="<%=t.getHabitacion()%>" class="d-none form-control" name="habitacion">  
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="d-none" for="destino">Destino</label>
                                                        <input readonly="readonly" type="text" value="<%=t.getDestino()%>" class="d-none form-control" name="destino">  
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="d-none" for="pax">Pax</label>
                                                        <input readonly="readonly" type="number" value="<%=t.getPax()%>" class="d-none form-control" name="pax">
                                                    </div>
                                                    <div class="row modal-body justify-content-center">                               
                                                        <div class="form-group">
                                                            <label for="numeroTaxi">Numero de taxi</label>
                                                            <input type="number" class="form-control" name="numeroTaxi">
                                                        </div>
                                                    </div>
                                                    <div class="modal-footer justify-content-center">
                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                                                        <input type="submit" class="btn btn-info" value="Agregar Taxista" name="accion">
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                                <!--End add Taxista-->
                                <td>
                                    <span class="fw-code-copy">
                                        <a title="copiar" class="btn fw-code-copy-button"><img style="width: 25px; height: 25px" src="./Vistas/img/copy.png"></a>
                                        <code class="d-none"><%=t.getIdReserva()%>   <%=t.getTaxi()%>   <%=t.getNum_licencia()%>   <%=t.getHora()%>   <%=t.getFecha()%>   <%=t.getOrigen()%>   <%=t.getHabitacion()%>   <%=t.getDestino()%>   <%=t.getPax()%> Pax  <%=t.getObservacion()%>   <%=t.getClaseReserva()%></code>
                                    </span>

                                </td>
                            </tr>

                        <script>
                            new Clipboard(".fw-code-copy-button", {
                                text: function (trigger) {
                                    return $(trigger).parent().find('code').first().text().trim();
                                }
                            });

                            //window.scrollTo(0, document.body.scrollHeight || document.documentElement.scrollHeight);
                        </script>
                        <%}%>
                        </tbody>
                    </table>
                    <table class="text-center my-2 table table-responsive-xs border text-center table-hover">

                        <tr>
                            <th scope="col">
                                Total
                                <%=dao.countHoy()%>
                            </th>
                        </tr>

                    </table>
                </div>
            </div>
        </div>
    </body>
    <jsp:include page="footer.jsp"></jsp:include>
</html>
