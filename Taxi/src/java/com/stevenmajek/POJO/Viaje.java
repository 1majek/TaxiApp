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
public class Viaje extends Reserva{
    private int id_reserva;
    private int num_licencia;
    private String taxi;
    private String nombre;
    private String apellidos;

    public Viaje() {
    }

    public Viaje(int id_reserva, int num_licencia, String taxi, String nombre, String apellidos, int idReserva, String hora, String fecha, String diaRest, String origen, String habitacion, String destino, int pax, String observacion, String claseReserva, int numeroTaxi) {
        super(idReserva, hora, fecha, diaRest, origen, habitacion, destino, pax, observacion, claseReserva, numeroTaxi);
        this.id_reserva = id_reserva;
        this.num_licencia = num_licencia;
        this.taxi = taxi;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }
    


    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    public int getNum_licencia() {
        return num_licencia;
    }

    public void setNum_licencia(int num_licencia) {
        this.num_licencia = num_licencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTaxi() {
        return taxi;
    }

    public void setTaxi(String taxi) {
        this.taxi = taxi;
    }
    
    
    
    

   
    
    
    
    
    
}
