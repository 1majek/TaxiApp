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
public class Taxista extends Viaje {
    String nombreTaxista;
    String dni;
    String matricula;
    int telefono;
    String marcaCoche;
    String poblacion;
    int idHorasTaxista;
    String modelo;
    boolean propietario;

    public Taxista() {
    }

    public Taxista(String nombreTaxista, String dni, String matricula, int telefono, String marcaCoche, String poblacion, int idHorasTaxista, String modelo, boolean propietario, int id_reserva, int num_licencia, String taxi, String nombre, String apellidos, int idReserva, String hora, String fecha, String diaRest, String origen, String habitacion, String destino, int pax, String observacion, String claseReserva, int numeroTaxi) {
        super(id_reserva, num_licencia, taxi, nombre, apellidos, idReserva, hora, fecha, diaRest, origen, habitacion, destino, pax, observacion, claseReserva, numeroTaxi);
        this.nombreTaxista = nombreTaxista;
        this.dni = dni;
        this.matricula = matricula;
        this.telefono = telefono;
        this.marcaCoche = marcaCoche;
        this.poblacion = poblacion;
        this.idHorasTaxista = idHorasTaxista;
        this.modelo = modelo;
        this.propietario = propietario;
    }

    public String getNombre() {
        return nombreTaxista;
    }

    public void setNombre(String nombre) {
        this.nombreTaxista = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getMarcaCoche() {
        return marcaCoche;
    }

    public void setMarcaCoche(String marcaCoche) {
        this.marcaCoche = marcaCoche;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public int getIdHorasTaxista() {
        return idHorasTaxista;
    }

    public void setIdHorasTaxista(int idHorasTaxista) {
        this.idHorasTaxista = idHorasTaxista;
    }

    public boolean isPropietario() {
        return propietario;
    }

    public void setPropietario(boolean propietario) {
        this.propietario = propietario;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    
    
    

    
    


    
    
    
}
