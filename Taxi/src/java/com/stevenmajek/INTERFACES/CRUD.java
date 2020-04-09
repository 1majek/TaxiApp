/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevenmajek.INTERFACES;

import com.stevenmajek.POJO.Nota;
import com.stevenmajek.POJO.Reserva;
import com.stevenmajek.POJO.Taxista;
import com.stevenmajek.POJO.Viaje;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author oficina taxi
 */
public interface CRUD {
    
    public void addReserva(Reserva r) throws Exception;
    public List showReserva();
    public List showReservaManana();
    public void deleteReseva(Reserva r);
    public void editarReserva(Reserva r);
    public void editarTaxista(Taxista t, int old);
    public Reserva selectOneReserva(int id);
    public Taxista selectOneViaje(int id, int num);
    //public Reserva showByTaxiNumber(int id);
    public int countByTaxiNumber(int id);
    public int countOrigen(String userInput);
    public int countHoy();
    public int countReservaHoy();
    public int countFechaInicioFinal(String inicio, String fin);
    public int countViajeTaxista();
    public int countReserva();
    public int countManana();
    public void addTaxista(Viaje v);
    public List showViajeTaxista();
    public List search(String userInput);
    public List searchToday();
    public List showReservaHoy();
    public List searchDate(String firstDate, String secondDate);
    public boolean checkTaxista(int num);
    public boolean checkCountReserva();
    public List selectOneTaxista(int id);
    public int countTotalViaje(int num_licencia);
    public List viajePorTaxista(int num);
    public String capitalize(String str);
    public List searchDateNumeroTaxi(String date, String num);
    public List searchDateNumeroTaxiOrigen(String date, String num,String origen);
    public List searchRoig(String vale);
    public void addNota(Nota n) throws Exception;
    public Nota showNota();
   // public void deleteViaje(Taxista t);
}
