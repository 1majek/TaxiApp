/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevenmajek.CONTROLADOR;

import com.stevenmajek.DAO.GestionDao;
import com.stevenmajek.POJO.Nota;
import com.stevenmajek.POJO.Reserva;
import com.stevenmajek.POJO.Taxista;
import com.stevenmajek.POJO.Viaje;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author oficina taxi
 */
@WebServlet(name = "Controlador", urlPatterns = {"/Controlador"})
public class Controlador extends HttpServlet {

    GestionDao dao = new GestionDao();
    Reserva r = new Reserva();
    Viaje v = new Viaje();
    Taxista t = new Taxista();
    Nota n = new Nota();

    String formulario = "Vistas/formularioReserva.jsp";
    String reserva = "Vistas/Reserva.jsp";
    String listarReserva = "/index.jsp";
    String editarReserva = "Vistas/formularioEditar.jsp";
    String addTaxista = "Vistas/addTaxista.jsp";
    String showTaxistViaje = "Vistas/listaViajeTaxista.jsp";
    String showBusquedadOrigen = "Vistas/listaBusquedad.jsp";
    String showBusquedadFecha = "Vistas/listarBusquedadFecha.jsp";
    String error = "Vistas/ErrorScript.jsp";
    String showToday = "Vistas/showToday.jsp";
    String viajePorTaxista = "Vistas/viajePorTaxista.jsp";
    String showfechaNumTaxista = "Vistas/listaBusquedadFechaNum.jsp";
    String showfechaNumTaxistaOrigen = "Vistas/listaBusquedadFechaNumOrigen.jsp";
    String showRoig = "Vistas/busquedadRoig.jsp";
    String editarViaje = "Vistas/editarViaje.jsp";
    String errorPage = "Vistas/errorPage.jsp";
    String showReservaManana = "Vistas/reservaManana.jsp";
    String showReservaHoy = "Vistas/reservaHoy.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Controlador</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Controlador at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("accion");
        String acceso = "";

        if (action.equalsIgnoreCase("formulario")) {
            acceso = formulario;
        } else if (action.equalsIgnoreCase("daleNote")) {

            //request.setAttribute("nota", nota);
            try {
                String nota = request.getParameter("nota");
                n.setHora(nota);
                dao.addNota(n);
                acceso = formulario;
            } catch (Exception e) {

                String Error = e.toString();
                request.setAttribute("error", Error);
                acceso = errorPage;
            }
        } else if (action.equalsIgnoreCase("reservas")) {
            acceso = listarReserva;
        } else if (action.equalsIgnoreCase("Reservar")) {
            try {
                String hora = request.getParameter("hora");
                String fecha = request.getParameter("fecha");
                String origen = dao.capitalize(request.getParameter("origen"));
                String habitacion = request.getParameter("habitacion");
                String destino = dao.capitalize(request.getParameter("destino"));
                int pax = Integer.parseInt(request.getParameter("pax"));
                String observacion = request.getParameter("observacion");
                String claseReserva = request.getParameter("claseReserva");
                r.setHora(hora);
                r.setFecha(fecha);
                r.setOrigen(origen);
                r.setHabitacion(habitacion);
                r.setDestino(destino);
                r.setPax(pax);
                r.setObservacion(observacion);
                r.setClaseReserva(claseReserva);

                dao.addReserva(r);
                acceso = listarReserva;
            } catch (Exception e) {
                String Error = e.toString();
                request.setAttribute("error", Error);
                acceso = errorPage;
            }

        } else if (action.equalsIgnoreCase("eliminar")) {

            int id = Integer.parseInt(request.getParameter("idReserva"));
            r.setIdReserva(id);
            dao.deleteReseva(r);
            acceso = listarReserva;
        } else if (action.equalsIgnoreCase("reservasManana")) {

            acceso = showReservaManana;
        } else if (action.equalsIgnoreCase("reservasHoy")) {
            
            acceso = showReservaHoy;
        }else if (action.equalsIgnoreCase("eliminarViaje")) {

            int id = Integer.parseInt(request.getParameter("idReserva"));
            r.setIdReserva(id);
            dao.deleteReseva(r);
            acceso = showTaxistViaje;
        } else if (action.equalsIgnoreCase("editarReserva")) {

            String id = request.getParameter("idViaje");
            request.setAttribute("id", id);
            acceso = editarReserva;
        } else if (action.equalsIgnoreCase("Editar")) {
            try {
                int idViaje = Integer.parseInt(request.getParameter("idViaje"));
                String hora = request.getParameter("hora");
                String fecha = request.getParameter("fecha");
                String origen = dao.capitalize(request.getParameter("origen"));
                String habitacion = request.getParameter("habitacion");
                String destino = dao.capitalize(request.getParameter("destino"));
                int pax = Integer.parseInt(request.getParameter("pax"));
                String observacion = request.getParameter("observacion");
                String claseReserva = request.getParameter("claseReserva");
                r.setIdReserva(idViaje);
                r.setHora(hora);
                r.setFecha(fecha);
                r.setOrigen(origen);
                r.setHabitacion(habitacion);
                r.setDestino(destino);
                r.setPax(pax);
                r.setObservacion(observacion);
                r.setClaseReserva(claseReserva);
                dao.editarReserva(r);
                acceso = listarReserva;
            } catch (Exception e) {
                String Error = e.toString();
                request.setAttribute("error", Error);
                acceso = errorPage;
            }

        } else if (action.equalsIgnoreCase("Editar Viaje Taxista")) {

            try {

                int idViaje = Integer.parseInt(request.getParameter("idViaje"));
                String hora = request.getParameter("hora");
                String fecha = request.getParameter("fecha");
                String origen = dao.capitalize(request.getParameter("origen"));
                String habitacion = request.getParameter("habitacion");
                String destino = dao.capitalize(request.getParameter("destino"));
                int pax = Integer.parseInt(request.getParameter("pax"));
                String observacion = request.getParameter("observacion");
                String claseReserva = request.getParameter("claseReserva");
                int numeroTaxi = Integer.parseInt(request.getParameter("numeroTaxi"));
                int num = Integer.parseInt(request.getParameter("num"));
                if (dao.checkTaxista(numeroTaxi)) {
                    t.setIdReserva(idViaje);
                    t.setHora(hora);
                    t.setFecha(fecha);
                    t.setOrigen(origen);
                    t.setHabitacion(habitacion);
                    t.setDestino(destino);
                    t.setPax(pax);
                    t.setObservacion(observacion);
                    t.setClaseReserva(claseReserva);
                    t.setNum_licencia(numeroTaxi);
                    dao.editarTaxista(t, num);
                    acceso = showTaxistViaje;
                } else {
                    acceso = error;
                }
            } catch (Exception e) {
                String Error = e.toString();
                request.setAttribute("error", Error);
                acceso = errorPage;
            }

        } else if (action.equalsIgnoreCase("buscarOrigen")) {

            String input = request.getParameter("inputBuscar");
            request.setAttribute("input", input);
            acceso = showBusquedadOrigen;

        } else if (action.equalsIgnoreCase("buscarFecha")) {

            String input1 = request.getParameter("fecha1");
            request.setAttribute("desde", input1);
            String input2 = request.getParameter("fecha2");
            request.setAttribute("hasta", input2);
            String origen = request.getParameter("busquedadOrigen");
            request.setAttribute("origen", origen);
            String num = request.getParameter("busquedadNumeroTaxi");
            request.setAttribute("num", num);
            if ((!input1.isEmpty() && !input2.isEmpty()) && (origen.isEmpty() && num.isEmpty())) {
                acceso = showBusquedadFecha;
            } else if ((!input1.isEmpty() && !num.isEmpty()) && (input2.isEmpty() && origen.isEmpty())) {
                acceso = showfechaNumTaxista;
            } else if ((!input1.isEmpty() && !origen.isEmpty() && !num.isEmpty()) || (input2.isEmpty())) {
                acceso = showfechaNumTaxistaOrigen;
            }

        } else if (action.equalsIgnoreCase("buscarRoig")) {

            String roig = request.getParameter("busquedadRoig");
            request.setAttribute("roig", roig);
            acceso = showRoig;
        } else if (action.equalsIgnoreCase("buscarHoy")) {

            acceso = showToday;
//        } else if (action.equalsIgnoreCase("addTaxista")) {
//
//            String id = request.getParameter("idViaje");
//            request.setAttribute("id", id);
//            acceso = addTaxista;
        } else if (action.equalsIgnoreCase("editarReservaTaxista")) {

            String id = request.getParameter("idViaje");
            request.setAttribute("id", id);
            String num = request.getParameter("numLicencia");
            request.setAttribute("num", num);
            acceso = editarViaje;
        } else if (action.equalsIgnoreCase("Agregar Taxista")) {

//            String id = request.getParameter("idViaje");
//            request.setAttribute("id", id);
            int idReserva = Integer.parseInt(request.getParameter("idReserva"));
            String hora = request.getParameter("hora");
            String fecha = request.getParameter("fecha");
            String origen = request.getParameter("origen");
            String habitacion = request.getParameter("habitacion");
            String destino = request.getParameter("destino");
            int pax = Integer.parseInt(request.getParameter("pax"));
            int numeroTaxista = Integer.parseInt(request.getParameter("numeroTaxi"));
            if (dao.checkTaxista(numeroTaxista)) {
                v.setIdReserva(idReserva);
                v.setHora(hora);
                v.setFecha(fecha);
                v.setOrigen(origen);
                v.setHabitacion(habitacion);
                v.setDestino(destino);
                v.setPax(pax);
                v.setNum_licencia(numeroTaxista);
                dao.addTaxista(v);
                acceso = showTaxistViaje;

            } else {
                acceso = error;
            }

        } else if (action.equalsIgnoreCase("listaTaxis")) {

            acceso = showTaxistViaje;
        } else if (action.equalsIgnoreCase("detalle")) {

            String detalle = request.getParameter("listaDetalle");
            request.setAttribute("listaDetalle", detalle);
        } else if (action.equalsIgnoreCase("viajesTaxista")) {

            String numTaxista = request.getParameter("numTaxista");
            request.setAttribute("numTaxista", numTaxista);
            String nombre = request.getParameter("nombre");
            request.setAttribute("nombre", nombre);
            acceso = viajePorTaxista;
        }
        RequestDispatcher rd = request.getRequestDispatcher(acceso);
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("accion");
        String acceso = "";

        if (action.equalsIgnoreCase("formulario")) {
            acceso = formulario;
        } else if (action.equalsIgnoreCase("daleNote")) {

            //request.setAttribute("nota", nota);
            try {
                String nota = request.getParameter("nota");
                n.setHora(nota);
                dao.addNota(n);
                acceso = formulario;
            } catch (Exception e) {

                String Error = e.toString();
                request.setAttribute("error", Error);
                acceso = errorPage;
            }
        } else if (action.equalsIgnoreCase("reservas")) {
            acceso = listarReserva;
        } else if (action.equalsIgnoreCase("Reservar")) {
            try {
                String hora = request.getParameter("hora");
                String fecha = request.getParameter("fecha");
                String origen = dao.capitalize(request.getParameter("origen"));
                String habitacion = request.getParameter("habitacion");
                String destino = dao.capitalize(request.getParameter("destino"));
                int pax = Integer.parseInt(request.getParameter("pax"));
                String observacion = request.getParameter("observacion");
                String claseReserva = request.getParameter("claseReserva");
                r.setHora(hora);
                r.setFecha(fecha);
                r.setOrigen(origen);
                r.setHabitacion(habitacion);
                r.setDestino(destino);
                r.setPax(pax);
                r.setObservacion(observacion);
                r.setClaseReserva(claseReserva);

                dao.addReserva(r);
                acceso = listarReserva;
            } catch (Exception e) {
                String Error = e.toString();
                request.setAttribute("error", Error);
                acceso = errorPage;
            }

        } else if (action.equalsIgnoreCase("eliminar")) {

            int id = Integer.parseInt(request.getParameter("idReserva"));
            r.setIdReserva(id);
            dao.deleteReseva(r);
            acceso = listarReserva;
        } else if (action.equalsIgnoreCase("reservasManana")) {

            acceso = showReservaManana;
        } else if (action.equalsIgnoreCase("eliminarViaje")) {

            int id = Integer.parseInt(request.getParameter("idReserva"));
            r.setIdReserva(id);
            dao.deleteReseva(r);
            acceso = showTaxistViaje;
        } else if (action.equalsIgnoreCase("editarReserva")) {

            String id = request.getParameter("idViaje");
            request.setAttribute("id", id);
            acceso = editarReserva;
        } else if (action.equalsIgnoreCase("Editar")) {
            try {
                int idViaje = Integer.parseInt(request.getParameter("idViaje"));
                String hora = request.getParameter("hora");
                String fecha = request.getParameter("fecha");
                String origen = dao.capitalize(request.getParameter("origen"));
                String habitacion = request.getParameter("habitacion");
                String destino = dao.capitalize(request.getParameter("destino"));
                int pax = Integer.parseInt(request.getParameter("pax"));
                String observacion = request.getParameter("observacion");
                String claseReserva = request.getParameter("claseReserva");
                r.setIdReserva(idViaje);
                r.setHora(hora);
                r.setFecha(fecha);
                r.setOrigen(origen);
                r.setHabitacion(habitacion);
                r.setDestino(destino);
                r.setPax(pax);
                r.setObservacion(observacion);
                r.setClaseReserva(claseReserva);
                dao.editarReserva(r);
                acceso = listarReserva;
            } catch (Exception e) {
                String Error = e.toString();
                request.setAttribute("error", Error);
                acceso = errorPage;
            }

        } else if (action.equalsIgnoreCase("Editar Viaje Taxista")) {

            try {

                int idViaje = Integer.parseInt(request.getParameter("idViaje"));
                String hora = request.getParameter("hora");
                String fecha = request.getParameter("fecha");
                String origen = dao.capitalize(request.getParameter("origen"));
                String habitacion = request.getParameter("habitacion");
                String destino = dao.capitalize(request.getParameter("destino"));
                int pax = Integer.parseInt(request.getParameter("pax"));
                String observacion = request.getParameter("observacion");
                String claseReserva = request.getParameter("claseReserva");
                int numeroTaxi = Integer.parseInt(request.getParameter("numeroTaxi"));
                int num = Integer.parseInt(request.getParameter("num"));
                if (dao.checkTaxista(numeroTaxi)) {
                    t.setIdReserva(idViaje);
                    t.setHora(hora);
                    t.setFecha(fecha);
                    t.setOrigen(origen);
                    t.setHabitacion(habitacion);
                    t.setDestino(destino);
                    t.setPax(pax);
                    t.setObservacion(observacion);
                    t.setClaseReserva(claseReserva);
                    t.setNum_licencia(numeroTaxi);
                    dao.editarTaxista(t, num);
                    acceso = showTaxistViaje;
                } else {
                    acceso = error;
                }
            } catch (Exception e) {
                String Error = e.toString();
                request.setAttribute("error", Error);
                acceso = errorPage;
            }

        } else if (action.equalsIgnoreCase("buscarOrigen")) {

            String input = request.getParameter("inputBuscar");
            request.setAttribute("input", input);
            acceso = showBusquedadOrigen;

        } else if (action.equalsIgnoreCase("buscarFecha")) {

            String input1 = request.getParameter("fecha1");
            request.setAttribute("desde", input1);
            String input2 = request.getParameter("fecha2");
            request.setAttribute("hasta", input2);
            String origen = request.getParameter("busquedadOrigen");
            request.setAttribute("origen", origen);
            String num = request.getParameter("busquedadNumeroTaxi");
            request.setAttribute("num", num);
            if ((!input1.isEmpty() && !input2.isEmpty()) && (origen.isEmpty() && num.isEmpty())) {
                acceso = showBusquedadFecha;
            } else if ((!input1.isEmpty() && !num.isEmpty()) && (input2.isEmpty() && origen.isEmpty())) {
                acceso = showfechaNumTaxista;
            } else if ((!input1.isEmpty() && !origen.isEmpty() && !num.isEmpty()) || (input2.isEmpty())) {
                acceso = showfechaNumTaxistaOrigen;
            }

        } else if (action.equalsIgnoreCase("buscarRoig")) {

            String roig = request.getParameter("busquedadRoig");
            request.setAttribute("roig", roig);
            acceso = showRoig;
        } else if (action.equalsIgnoreCase("buscarHoy")) {

            acceso = showToday;
//        } else if (action.equalsIgnoreCase("addTaxista")) {
//
//            String id = request.getParameter("idViaje");
//            request.setAttribute("id", id);
//            acceso = addTaxista;
        } else if (action.equalsIgnoreCase("editarReservaTaxista")) {

            String id = request.getParameter("idViaje");
            request.setAttribute("id", id);
            String num = request.getParameter("numLicencia");
            request.setAttribute("num", num);
            acceso = editarViaje;
        } else if (action.equalsIgnoreCase("Agregar Taxista")) {

//            String id = request.getParameter("idViaje");
//            request.setAttribute("id", id);
            int idReserva = Integer.parseInt(request.getParameter("idReserva"));
            String hora = request.getParameter("hora");
            String fecha = request.getParameter("fecha");
            String origen = request.getParameter("origen");
            String habitacion = request.getParameter("habitacion");
            String destino = request.getParameter("destino");
            int pax = Integer.parseInt(request.getParameter("pax"));
            int numeroTaxista = Integer.parseInt(request.getParameter("numeroTaxi"));
            if (dao.checkTaxista(numeroTaxista)) {
                v.setIdReserva(idReserva);
                v.setHora(hora);
                v.setFecha(fecha);
                v.setOrigen(origen);
                v.setHabitacion(habitacion);
                v.setDestino(destino);
                v.setPax(pax);
                v.setNum_licencia(numeroTaxista);
                dao.addTaxista(v);
                acceso = showTaxistViaje;

            } else {
                acceso = error;
            }

        } else if (action.equalsIgnoreCase("listaTaxis")) {

            acceso = showTaxistViaje;
        } else if (action.equalsIgnoreCase("detalle")) {

            String detalle = request.getParameter("listaDetalle");
            request.setAttribute("listaDetalle", detalle);
        } else if (action.equalsIgnoreCase("viajesTaxista")) {

            String numTaxista = request.getParameter("numTaxista");
            request.setAttribute("numTaxista", numTaxista);
            String nombre = request.getParameter("nombre");
            request.setAttribute("nombre", nombre);
            acceso = viajePorTaxista;
        }
        RequestDispatcher rd = request.getRequestDispatcher(acceso);
        rd.forward(request, response);

        }

        @Override
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>

    }
