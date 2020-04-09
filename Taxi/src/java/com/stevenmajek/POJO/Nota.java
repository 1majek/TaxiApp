/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevenmajek.POJO;

/**
 *
 * @author Steven Majek
 */
public class Nota {

    String hora;
    String fecha;
    String origen;
    String hab;
    String destino;
    int pax;
    String observacion;

    public Nota() {
    }

    public Nota(String hora, String fecha, String origen, String hab, String destino, int pax, String observacion) {
        this.hora = hora;
        this.fecha = fecha;
        this.origen = origen;
        this.hab = hab;
        this.destino = destino;
        this.pax = pax;
        this.observacion = observacion;
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

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getHab() {
        return hab;
    }

    public void setHab(String hab) {
        this.hab = hab;
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
    
    
}
