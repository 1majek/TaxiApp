/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevenmajek.POJO;


/**
 *
 * @author oficina taxi
 */
public class Reserva {
    private int idReserva;
    private String hora;
    private String fecha;
    private String diaRest;
    private String origen;
    private String habitacion;
    private String destino;
    private int pax;
    private String observacion;
    private String claseReserva;
    private int numeroTaxi;
 
    public Reserva() {
        
    }
    
    public Reserva(int idReserva, String hora, String fecha, String diaRest, String origen, String habitacion, String destino, int pax, String observacion, String claseReserva, int numeroTaxi) {
        this.idReserva = idReserva;
        this.hora = hora;
        this.fecha = fecha;
        this.diaRest = diaRest;
        this.origen = origen;
        this.habitacion = habitacion;
        this.destino = destino;
        this.pax = pax;
        this.observacion = observacion;
        this.claseReserva = claseReserva;
        this.numeroTaxi = numeroTaxi;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFecha() {
        return fecha;
    }
    
    public void setDiaRest(String diaRest) {
       this.diaRest = diaRest;
    }
    
    public String getDiaRest() {
        return diaRest;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(String habitacion) {
        this.habitacion = habitacion;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getPax() {
        return pax;
    }

    public void setPax(int pax) {
        this.pax = pax;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getClaseReserva() {
        return claseReserva;
    }

    public void setClaseReserva(String claseReserva) {
        this.claseReserva = claseReserva;
    }

    public int getNumeroTaxi() {
        return numeroTaxi;
    }

    public void setNumeroTaxi(int numeroTaxi) {
        this.numeroTaxi = numeroTaxi;
    }

    
    
    
}
