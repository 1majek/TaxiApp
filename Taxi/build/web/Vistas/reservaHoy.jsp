<%-- 
    Document   : Reserva
    Created on : 12-jun-2019, 17:14:24
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
            <title>Reservas</title>
        </head>
        <body>
            <!--nav-->
            <nav class="navbar navbar-expand-lg navbar-dark fixed-top fondo">
                <a class="" title="Toma nota" data-toggle="modal" data-target="#modalRemove"><img style="width: 60px; height: 60px" src="./Vistas/img/badge.png" class="mr-5"></a>
                <div class="modal fade" id="modalRemove" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header fondo">
                                <h5 class="modal-title">Toma Nota</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <form action="Controlador">
                                <div class="modal-body">                               
                                    <textarea rows="4" cols="63" name="nota">
                                        
                                    </textarea>    
                                </div>

                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                                    <button value="daleNote" type="submit" class="btn btn-info" name="accion" value="buscarFecha">Hecho</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>        
                <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                    <li class="nav-item">
                        <a class="nav-link texto"  href="Controlador?accion=formulario">Añadir reserva</a>

                    </li>
                    <li class="nav-item">
                        <!--<a class="nav-link texto bg-warning" style="color: black;" href="Controlador?accion=reservas">Reservas</a>-->
                    <%
                    GestionDao dao = new GestionDao();
                    %>
                    <div class="btn-group texto">
                        <a class="nav-link bg-warning" style="color: black;" href="Controlador?accion=reservas">Reservas <span title="Total" class="badge badge-danger"><%=dao.countReservaHoy()%></span></a>
                        <!--<button type="button" class="btn btn-warning dropdown-toggle dropdown-toggle-split" data-toggle="dropdown">
                            <span class="sr-only">Toggle Dropdown</span>
                        </button>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="Controlador?accion=reservas">Reservas <span title="Total" class="badge badge-danger"><%=dao.countReserva()%></span></a>
                            <!--<a class="dropdown-item" href="#">Another action</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">Separated link</a>
                        </div>-->
                    </div>
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

        <div id="myDiv" class="container-fluid my-5">
            <div class="row">
                <div class="col">
                    <table class="text-center my-2 table table-responsive-xs border text-center table-hover">
                        <thead class="">
                            <tr>
                                <th scope="col">Id_reserva</th>
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
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                
                                dao.showReservaHoy();
                                int i = 0;
                                for (Reserva r : dao.listaReserva) {
                                    i++;


                            %>
                            <tr>
                                <td><%=r.getIdReserva()%></td>
                                <td><%=r.getHora()%></td>
                                <td><%=r.getFecha()%></td>
                                <td><%=r.getOrigen()%></td>
                                <td><%=r.getHabitacion()%></td>
                                <td><%=r.getDestino()%></td>                    
                                <td><%=r.getPax()%></td>
                                <td><%=r.getObservacion()%></td>
                                <td><%=r.getClaseReserva()%></td>
                                <td>
                                    <a class="btn" title="eliminar" data-toggle="modal" data-target="#modalRemove<%=i%>"><img style="width: 25px; height: 25px" src="./Vistas/img/delete.png"></a>
                                    <div class="modal fade" id="modalRemove<%=i%>" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                        <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header fondo">
                                                    <h5 class="modal-title">Eliminar Reserva <%=r.getIdReserva()%></h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    <p>¿Seguro que deseas eliminar esta reserva de <%=r.getOrigen()%> que van a <%=r.getDestino()%>?</p>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                                                    <a href="Controlador?accion=eliminar&idReserva=<%=r.getIdReserva()%>" class="btn btn-info removeBtn">Eliminar</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <a class="btn" title="editar" href="Controlador?accion=editarReserva&idViaje=<%=r.getIdReserva()%>"><img style="width: 25px; height: 25px" src="./Vistas/img/edit.png"></a>
                                </td>
                                <!--Add Taxista-->
                                <td>
                                    <a class="btn" data-toggle="modal" data-target="#modalAdd<%=i%>" title="añadir taxista" href=""><img style="width: 25px; height: 25px" src="./Vistas/img/add.png"></a>
                                    <div class="modal fade" id="modalAdd<%=i%>" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                        <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header fondo">
                                                    <h5 class="modal-title">Añadir Taxista a la reserva <%=r.getIdReserva()%></h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <form action="Controlador">
                                                    <div class="form-group">
                                                        <label class="d-none" for="idReserva">Id reserva</label>
                                                        <input readonly="readonly" type="number" value="<%=r.getIdReserva()%>" class="d-none form-control" name="idReserva">  
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="d-none" for="hora">Hora</label>
                                                        <input readonly="readonly" type="time" value="<%=r.getHora()%>" class="d-none form-control" name="hora">  
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="d-none" for="fecha">Fecha</label>
                                                        <input readonly="readonly" type="date" value="<%=r.getFecha()%>" class="d-none form-control" name="fecha">  
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="d-none" for="origen">Origen</label>
                                                        <input readonly="readonly" type="text" value="<%=r.getOrigen()%>" class="d-none form-control" name="origen">
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="d-none" for="habitacion">Habitacion</label>
                                                        <input readonly="readonly" type="text" value="<%=r.getHabitacion()%>" class="d-none form-control" name="habitacion">  
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="d-none" for="destino">Destino</label>
                                                        <input readonly="readonly" type="text" value="<%=r.getDestino()%>" class="d-none form-control" name="destino">  
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="d-none" for="pax">Pax</label>
                                                        <input readonly="readonly" type="number" value="<%=r.getPax()%>" class="d-none form-control" name="pax">
                                                    </div>
                                                    <div class="row modal-body justify-content-center">                               
                                                        <div class="form-group">
                                                            <label for="numeroTaxi">Numero de taxi</label>
                                                            <input required type="number" class="form-control" name="numeroTaxi" autofocus>
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
                            </tr>
                            <%}%>
                        </tbody>
                    </table>  
                </div>
            </div>
        </div>
        <script>
            function myFunction() {
                /* Get the text field */
                var copyText = document.getElementById("myInput");

                /* Select the text field */
                copyText.select();

                /* Copy the text inside the text field */
                document.execCommand("copy");

                /* Alert the copied text */
                alert("Copied the text: " + copyText.value);

                $('#mydiv').scrollTop(($('#mydiv').height() * 2));
            }


        </script>
    </body>
    <jsp:include page="footer.jsp"></jsp:include>
</html>
