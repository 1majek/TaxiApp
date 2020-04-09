/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevenmajek.DAO;

import com.stevenmajek.CONFIG.Conexion;
import com.stevenmajek.INTERFACES.CRUD;
import com.stevenmajek.POJO.Nota;
import com.stevenmajek.POJO.Reserva;
import com.stevenmajek.POJO.Taxista;
import com.stevenmajek.POJO.Viaje;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author oficina taxi
 */
public class GestionDao implements CRUD {

    Connection con = Conexion.getConexion();
    PreparedStatement ps = null;
    ResultSet rs = null;
    public ArrayList<Taxista> listaFecha = new ArrayList<>();
    public ArrayList<Reserva> listaReserva = new ArrayList();
    //public ArrayList<Reserva> listaNumeroTaxi = new ArrayList();
    public ArrayList<Taxista> listaViajeTaxista = new ArrayList<>();
    //public ArrayList<Taxista> listaSearch = new ArrayList<>();
    //public ArrayList<Taxista> listaFechaNum = new ArrayList<>();
    public ArrayList<Taxista> listaTaxista = new ArrayList<>();
    // public ArrayList<Taxista> listaFechaHoy = new ArrayList<>();
    //public ArrayList<Taxista> listaviajePorTaxista = new ArrayList<>();
    public ArrayList<Nota> listaNota = new ArrayList<>();

    Reserva r = new Reserva();
    Viaje v = new Viaje();
    Taxista t = new Taxista();
    Nota n = new Nota();

    @Override
    public void addReserva(Reserva r) throws Exception {
        
            String query = "INSERT INTO `reserva` (`hora`, `fecha`, `origen`, `habitacion`, `destino`, `pax`, `observacion`, `clase_reserva`) VALUES (TIME_FORMAT('" + r.getHora() + "','%H:%i'), DATE_FORMAT('" + r.getFecha() + "','%Y/%m/%d'), '" + r.getOrigen() + "', '" + r.getHabitacion() + "', '" + r.getDestino() + "', '" + r.getPax() + "', '" + r.getObservacion() + "', '" + r.getClaseReserva() + "')";
            ps = con.prepareStatement(query);
            ps.executeUpdate();


    }

    @Override
    public List showReserva() {

        try {
            String query = "select * from vista_reserva where id_reserva not in (SELECT id_reserva from viaje) order by id_reserva desc";
            String query2 = "select id_reserva,time_format(hora,'%H:%i %p') as hora,date_format(fecha,'%d/%m/%Y') as fecha,TIMESTAMPDIFF(DAY,CURRENT_DATE(),fecha) as dia_restante,origen,habitacion,destino,pax,observacion,clase_reserva from reserva where id_reserva not in (SELECT id_reserva FROM viaje) order by dia_restante, id_reserva DESC";
            String query3 = "select `reserva`.`id_reserva`,`taxi`.`taxi`,`taxi`.`num_licencia`,taxi.nombre,taxi.dni,taxi.telefono,taxi.marca_coche,taxi.modelo,taxi.matricula,poblacion.poblacion,time_format(`reserva`.`hora`,'%H:%i %p') AS `hora`,date_format(`reserva`.`fecha`,'%d/%m/%Y') AS `fecha`,`reserva`.`origen`,`reserva`.`habitacion`,`reserva`.`destino`,`reserva`.`pax`,`reserva`.`observacion`,`reserva`.`clase_reserva` from `reserva` inner join `viaje` on `reserva`.`id_reserva` = `viaje`.`id_reserva` inner join `taxi` on `viaje`.`num_licencia` = `taxi`.`num_licencia` inner join poblacion on taxi.id_poblacion = poblacion.id_poblacion";
            
            ps = con.prepareStatement(query2);
            rs = ps.executeQuery();

            while (rs.next()) {
                Reserva r = new Reserva();
                r.setIdReserva(rs.getInt("id_reserva"));
                r.setHora(rs.getString("hora"));
                r.setFecha(rs.getString("fecha"));
                r.setDiaRest(rs.getString("dia_restante"));
                r.setOrigen(rs.getString("origen"));
                r.setHabitacion(rs.getString("habitacion"));
                r.setDestino(rs.getString("destino"));
                r.setPax(rs.getInt("pax"));
                r.setObservacion(rs.getString("observacion"));
                r.setClaseReserva(rs.getString("clase_reserva"));

                listaReserva.add(r);
            }
        } catch (SQLException e) {
            System.out.println(e.fillInStackTrace());
        }
        return listaReserva;
    }
    
    public List showReservaManana() {
        try {
            String query = "select * from vista_reserva where id_reserva not in (SELECT id_reserva from viaje) and fecha = date_format(adddate(CURRENT_DATE, interval 1 day),'%d/%m/%Y') order by id_reserva";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Reserva r = new Reserva();
                r.setIdReserva(rs.getInt("id_reserva"));
                r.setHora(rs.getString("hora"));
                r.setFecha(rs.getString("fecha"));
                r.setOrigen(rs.getString("origen"));
                r.setHabitacion(rs.getString("habitacion"));
                r.setDestino(rs.getString("destino"));
                r.setPax(rs.getInt("pax"));
                r.setObservacion(rs.getString("observacion"));
                r.setClaseReserva(rs.getString("clase_reserva"));

                listaReserva.add(r);
            }
        } catch (SQLException e) {
            System.out.println(e.fillInStackTrace());
        }
        return listaReserva;
    }

    @Override
    public void deleteReseva(Reserva r) {
        try {
            String query = "delete from reserva where id_reserva='" + r.getIdReserva() + "'";
            ps = con.prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.fillInStackTrace());
        }
    }

//    @Override
//    public void deleteViaje(Taxista t) {
//        try {
//            String query = "delete from viaje where num_licencia='" + t.getNum_licencia()+ "'";
//            ps = con.prepareStatement(query);
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println(e.fillInStackTrace());
//        }
//    }

    @Override
    public void editarReserva(Reserva r) {
        try {
            String query = "UPDATE `reserva` SET `hora` = TIME('" + r.getHora() + "'), `fecha` = DATE('" + r.getFecha() + "'), `origen` = '" + r.getOrigen() + "', `habitacion` = '" + r.getHabitacion() + "', `destino` = '" + r.getDestino() + "', `pax` = '" + r.getPax() + "', `observacion` = '" + r.getObservacion() + "', `clase_reserva` = '" + r.getClaseReserva() + "' WHERE id_reserva = '" + r.getIdReserva() + "' ";
            ps = con.prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.fillInStackTrace());
        }
    }

    @Override
    public void editarTaxista(Taxista t, int old) {
        try {
            //String query = "UPDATE VIAJE set num_licencia = '" + nuevo + "' where num_licencia = '" + t.getNum_licencia() + "'";
            String query2 = "UPDATE viaje inner join reserva on viaje.id_reserva = reserva.id_reserva set `viaje`.`num_licencia` = '" + t.getNum_licencia()+ "', `reserva`.`hora`= time('" + t.getHora() + "'),`reserva`.`fecha`=date('" + t.getFecha() + "'),`reserva`.`origen`='" + t.getOrigen() + "',`reserva`.`habitacion`='" + t.getHabitacion() + "',`reserva`.`destino`='" + t.getDestino() + "',`reserva`.`pax`='" + t.getPax() + "',`reserva`.`observacion` ='" + t.getObservacion() + "',`reserva`.`clase_reserva`='" + t.getClaseReserva() + "' where viaje.id_reserva ='"+t.getIdReserva()+"' and num_licencia='"+old+"'";
            ps = con.prepareStatement(query2);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.fillInStackTrace());
        }
    }

    @Override
    public Reserva selectOneReserva(int id) {
        try {
            String query = "select * from reserva where id_reserva='" + id + "'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                r.setIdReserva(rs.getInt("id_reserva"));
                r.setHora(rs.getString("hora"));
                r.setFecha(rs.getString("fecha"));
                r.setOrigen(rs.getString("origen"));
                r.setHabitacion(rs.getString("habitacion"));
                r.setDestino(rs.getString("destino"));
                r.setPax(rs.getInt("pax"));
                r.setObservacion(rs.getString("observacion"));
                r.setClaseReserva(rs.getString("clase_reserva"));
            }
        } catch (SQLException e) {
            System.out.println(e.fillInStackTrace());
        }
        return r;

    }

    @Override
    public Taxista selectOneViaje(int id, int num) {
        try {
            String query = "select * from vista_taxista where id_reserva='" + id + "'";
            String query2 ="SELECT viaje.num_licencia,reserva.id_reserva,reserva.hora,reserva.fecha,reserva.origen,reserva.habitacion,reserva.destino,reserva.pax,reserva.observacion,reserva.clase_reserva from viaje inner JOIN reserva on viaje.id_reserva = reserva.id_reserva where reserva.id_reserva='"+id+"' and viaje.num_licencia='"+num+"'";
            ps = con.prepareStatement(query2);
            rs = ps.executeQuery();

            while (rs.next()) {
                t.setNum_licencia(rs.getInt("num_licencia"));
                t.setIdReserva(rs.getInt("id_reserva"));
                t.setHora(rs.getString("hora"));
                t.setFecha(rs.getString("fecha"));
                t.setOrigen(rs.getString("origen"));
                t.setHabitacion(rs.getString("habitacion"));
                t.setDestino(rs.getString("destino"));
                t.setPax(Integer.parseInt(rs.getString("pax")));
                t.setObservacion(rs.getString("observacion"));
                t.setClaseReserva(rs.getString("clase_reserva"));
//                t.setTaxi(rs.getString("taxi"));           
//                t.setNombre(rs.getString("nombre"));
//                t.setTelefono(rs.getInt("telefono"));
//                t.setMarcaCoche(rs.getString("marca_coche"));
//                t.setModelo(rs.getString("modelo"));
//                t.setMatricula(rs.getString("matricula"));
//                t.setPoblacion(rs.getString("poblacion"));
            }
        } catch (SQLException e) {
            System.out.println(e.fillInStackTrace());
        }
        return t;

    }

//    @Override
//    public Reserva showByTaxiNumber(int id) {
//        try {
//            String query = "select * from taxista where num_licencia='" + id + "'";
//            ps = con.prepareStatement(query);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                Reserva r = new Reserva();
//                v.setIdReserva(rs.getInt("id_reserva"));
//                v.setHora(rs.getString("horaf"));
//                v.setFecha(rs.getString("fechaf"));
//                v.setOrigen(rs.getString("origen"));
//                v.setHabitacion(rs.getString("habitacion"));
//                v.setDestino(rs.getString("destino"));
//                v.setPax(rs.getInt("pax"));
//                v.setObservacion(rs.getString("observacion"));
//                v.setClaseReserva(rs.getString("clase_reserva"));
//                v.setTaxi(rs.getString("taxi"));
//                v.setNumeroTaxi(rs.getInt("numero_taxi"));
//                listaNumeroTaxi.add(v);
//            }
//        } catch (SQLException e) {
//            System.out.println(e.fillInStackTrace());
//        }
//        return v;
//    }

    @Override
    public int countByTaxiNumber(int id) {
        int total = 0;
        try {
            String query = "select count(*) as total from vista_reserva where numero_taxi='" + id + "'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (SQLException e) {
            System.out.println(e.fillInStackTrace());
        }
        return total;
    }

    @Override
    public void addTaxista(Viaje v) {
        try {
            String query = "insert into viaje(id_reserva,num_licencia)values((select id_reserva from reserva where id_reserva='" + v.getIdReserva() + "'),(SELECT num_licencia from taxi where num_licencia='" + v.getNum_licencia() + "'))";

            ps = con.prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.fillInStackTrace());
        }
    }

    @Override
    public List showViajeTaxista() {
        try {
            String query = "select * from vista_taxista order by id_reserva desc limit 100";
            //String query2 = "create or replace view vista_taxista as select `reserva`.`id_reserva`,`taxi`.`taxi`,`taxi`.`num_licencia`,taxi.nombre,taxi.dni,taxi.telefono,taxi.marca_coche,taxi.modelo,taxi.matricula,poblacion.poblacion,time_format(`reserva`.`hora`,'%H:%i %p') AS `hora`,date_format(`reserva`.`fecha`,'%d/%m/%Y') AS `fecha`,`reserva`.`origen`,`reserva`.`habitacion`,`reserva`.`destino`,`reserva`.`pax`,`reserva`.`observacion`,`reserva`.`clase_reserva` from `viaje` inner join `taxi` on `viaje`.`num_licencia` = `taxi`.`num_licencia` inner join `reserva` on `viaje`.`id_reserva` = `reserva`.`id_reserva` inner join poblacion on taxi.id_poblacion = poblacion.id_poblacion";
            String query2 = "select `reserva`.`id_reserva`,`taxi`.`taxi`,`taxi`.`num_licencia`,taxi.nombre,taxi.dni,taxi.telefono,taxi.marca_coche,taxi.modelo,taxi.matricula,poblacion.poblacion,time_format(`reserva`.`hora`,'%H:%i %p') AS `hora`,date_format(`reserva`.`fecha`,'%d/%m/%Y') AS `fecha`,`reserva`.`origen`,`reserva`.`habitacion`,`reserva`.`destino`,`reserva`.`pax`,`reserva`.`observacion`,`reserva`.`clase_reserva` from `viaje` inner join `taxi` on `viaje`.`num_licencia` = `taxi`.`num_licencia` inner join `reserva` on `viaje`.`id_reserva` = `reserva`.`id_reserva` inner join poblacion on taxi.id_poblacion = poblacion.id_poblacion order by reserva.id_reserva DESC";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Taxista t = new Taxista();
                t.setIdReserva(rs.getInt("id_reserva"));
                t.setHora(rs.getString("hora"));
                t.setFecha(rs.getString("fecha"));
                t.setOrigen(rs.getString("origen"));
                t.setHabitacion(rs.getString("habitacion"));
                t.setDestino(rs.getString("destino"));
                t.setPax(Integer.parseInt(rs.getString("pax")));
                t.setObservacion(rs.getString("observacion"));
                t.setClaseReserva(rs.getString("clase_reserva"));
                t.setTaxi(rs.getString("taxi"));
                t.setNum_licencia(rs.getInt("num_licencia"));
                t.setNombre(rs.getString("nombre"));
                t.setTelefono(rs.getInt("telefono"));
                t.setMarcaCoche(rs.getString("marca_coche"));
                t.setModelo(rs.getString("modelo"));
                t.setMatricula(rs.getString("matricula"));
                t.setPoblacion(rs.getString("poblacion"));

                listaViajeTaxista.add(t);
            }
        } catch (SQLException e) {
            System.out.println(e.fillInStackTrace());
        }
        return listaViajeTaxista;
    }

    @Override
    public int countOrigen(String userInput) {
        int total = 0;
        try {
            String query = "select count(*) as total from vista_taxista where origen like '%" + userInput + "%' or num_licencia = '"+userInput+"' or id_reserva='"+userInput+"' or clase_reserva like '%"+userInput+"%' or habitacion='"+userInput+"'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (SQLException e) {
            System.out.println(e.fillInStackTrace());
        }
        return total;
    }
    
    @Override
    public int countFechaInicioFinal(String inicio, String fin) {
        int total = 0;
        try {
            String query = "select count(*) as total from vista_taxista where fecha BETWEEN date_format('" + inicio + "','%d/%m/%Y') and date_format('" + fin + "','%d/%m/%Y')";
            String query2= "select COUNT(*) as total from `viaje` inner join `taxi` on `viaje`.`num_licencia` = `taxi`.`num_licencia` inner join `reserva` on `viaje`.`id_reserva` = `reserva`.`id_reserva` inner join poblacion on taxi.id_poblacion = poblacion.id_poblacion where fecha BETWEEN '" + inicio + "' and '" + fin + "'";
            //select count(*) as total from vista_taxista where fecha BETWEEN '02/06/2019' and '11/07/2019'
            ps = con.prepareStatement(query2);
            rs = ps.executeQuery();
            while (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (SQLException e) {
            System.out.println(e.fillInStackTrace());
        }
        return total;
    }
    
    @Override
    public int countViajeTaxista() {
        int total = 0;
        try {
            String query = "select count(*) as total from vista_taxista";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (SQLException e) {
            System.out.println(e.fillInStackTrace());
        }
        return total;
    }
    
    @Override
    public int countReserva() {
        int total = 0;
        try {
            String query = "select count(*) as total from vista_reserva where id_reserva not in (SELECT id_reserva from viaje)";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (SQLException e) {
            System.out.println(e.fillInStackTrace());
        }
        return total;
    }
    
    @Override
    public int countManana() {
        int total = 0;
        try {
            String query = "select COUNT(*) as total from vista_reserva where id_reserva not in (SELECT id_reserva from viaje) and fecha = date_format(adddate(CURRENT_DATE, interval 1 day),'%d/%m/%Y')";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (SQLException e) {
            System.out.println(e.fillInStackTrace());
        }
        return total;
    }

    @Override
    public List search(String userInput) {
        try {
            String query = "select* from vista_taxista where origen like'%" + userInput + "%' or num_licencia = '"+userInput+"' or id_reserva='"+userInput+"' or clase_reserva like '%"+userInput+"%' order by id_reserva desc";
            String query2= "select* from vista_taxista where origen like'%" + userInput + "%' or num_licencia = '"+userInput+"' or id_reserva='"+userInput+"' or clase_reserva like '%"+userInput+"%' or habitacion='"+userInput+"' order by id_reserva DESC";
            ps = con.prepareStatement(query2);
            rs = ps.executeQuery();

            while (rs.next()) {
                Taxista t = new Taxista();
                t.setIdReserva(rs.getInt("id_reserva"));
                t.setHora(rs.getString("hora"));
                t.setFecha(rs.getString("fecha"));
                t.setOrigen(rs.getString("origen"));
                t.setHabitacion(rs.getString("habitacion"));
                t.setDestino(rs.getString("destino"));
                t.setPax(Integer.parseInt(rs.getString("pax")));
                t.setObservacion(rs.getString("observacion"));
                t.setClaseReserva(rs.getString("clase_reserva"));
                t.setTaxi(rs.getString("taxi"));
                t.setNum_licencia(rs.getInt("num_licencia"));
                t.setNombre(rs.getString("nombre"));
                t.setTelefono(rs.getInt("telefono"));
                t.setMarcaCoche(rs.getString("marca_coche"));
                t.setModelo(rs.getString("modelo"));
                t.setMatricula(rs.getString("matricula"));
                t.setPoblacion(rs.getString("poblacion"));

                listaFecha.add(t);
            }
        } catch (SQLException e) {
            System.out.println(e.fillInStackTrace());
        }
        return listaFecha;
    }

    @Override
    public List searchDate(String firstDate, String secondDate) {
        try {
            String query = "select `reserva`.`id_reserva`,`taxi`.`taxi`,`taxi`.`num_licencia`,taxi.nombre,taxi.dni,taxi.telefono,taxi.marca_coche,taxi.modelo,taxi.matricula,poblacion.poblacion,time_format(`reserva`.`hora`,'%H:%i %p') AS `hora`,date_format(`reserva`.`fecha`,'%d/%m/%Y') AS `fecha`,`reserva`.`origen`,`reserva`.`habitacion`,`reserva`.`destino`,`reserva`.`pax`,`reserva`.`observacion`,`reserva`.`clase_reserva` from `viaje` inner join `taxi` on `viaje`.`num_licencia` = `taxi`.`num_licencia` inner join `reserva` on `viaje`.`id_reserva` = `reserva`.`id_reserva` inner join poblacion on taxi.id_poblacion = poblacion.id_poblacion where fecha BETWEEN '" + firstDate + "' and '" + secondDate + "' order by id_reserva desc";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery(query);

            while (rs.next()) {
                Taxista t = new Taxista();

                t.setIdReserva(rs.getInt("id_reserva"));
                t.setHora(rs.getString("hora"));
                t.setFecha(rs.getString("fecha"));
                t.setOrigen(rs.getString("origen"));
                t.setHabitacion(rs.getString("habitacion"));
                t.setDestino(rs.getString("destino"));
                t.setPax(Integer.parseInt(rs.getString("pax")));
                t.setObservacion(rs.getString("observacion"));
                t.setClaseReserva(rs.getString("clase_reserva"));
                t.setTaxi(rs.getString("taxi"));
                t.setNum_licencia(rs.getInt("num_licencia"));
                t.setNombre(rs.getString("nombre"));
                t.setTelefono(rs.getInt("telefono"));
                t.setMarcaCoche(rs.getString("marca_coche"));
                t.setModelo(rs.getString("modelo"));
                t.setMatricula(rs.getString("matricula"));
                t.setPoblacion(rs.getString("poblacion"));

                listaFecha.add(t);
            }
        } catch (SQLException e) {
            System.out.println(e.fillInStackTrace());
        }
        return listaFecha;

    }

    @Override
    public boolean checkTaxista(int num) {
        String query = "select num_licencia from taxi where num_licencia = '" + num + "'";
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.fillInStackTrace());
        }
        return false;
    }
    
    @Override
    public boolean checkCountReserva() {
        String query = "select count(*) as total from vista_reserva where id_reserva not in (select id_reserva from viaje) and fecha = date_format(CURRENT_DATE,'%d/%m/%Y')";
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.fillInStackTrace());
        }
        return false;
    }

    @Override
    public List selectOneTaxista(int id) {
        try {
            String query = "select * from vista_taxista where num_licencia='" + id + "'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Taxista t = new Taxista();
                t.setIdReserva(rs.getInt("id_reserva"));
                t.setHabitacion(rs.getString("habitacion"));
                t.setNombre(rs.getString("nombre"));
                t.setDni(rs.getString("dni"));
                t.setTelefono(rs.getInt("telefono"));
                t.setMarcaCoche(rs.getString("marca_coche"));
                t.setModelo(rs.getString("modelo"));
                t.setMatricula(rs.getString("matricula"));
                listaTaxista.add(t);
            }
        } catch (SQLException e) {
            System.out.println(e.fillInStackTrace());
        }
        return listaTaxista;
    }

    @Override
    public List searchToday() {
        try {
            String query = "select * from vista_taxista where fecha = date_format(current_date,'%d/%m/%Y') order by id_reserva desc";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery(query);

            while (rs.next()) {
                Taxista t = new Taxista();

                t.setIdReserva(rs.getInt("id_reserva"));
                t.setHora(rs.getString("hora"));
                t.setFecha(rs.getString("fecha"));
                t.setOrigen(rs.getString("origen"));
                t.setHabitacion(rs.getString("habitacion"));
                t.setDestino(rs.getString("destino"));
                t.setPax(Integer.parseInt(rs.getString("pax")));
                t.setObservacion(rs.getString("observacion"));
                t.setClaseReserva(rs.getString("clase_reserva"));
                t.setTaxi(rs.getString("taxi"));
                t.setNum_licencia(rs.getInt("num_licencia"));
                t.setNombre(rs.getString("nombre"));
                t.setTelefono(rs.getInt("telefono"));
                t.setMarcaCoche(rs.getString("marca_coche"));
                t.setModelo(rs.getString("modelo"));
                t.setMatricula(rs.getString("matricula"));
                t.setPoblacion(rs.getString("poblacion"));

                listaFecha.add(t);
            }
        } catch (SQLException e) {
            System.out.println(e.fillInStackTrace());
        }
        return listaFecha;
    }
    
    @Override
    public List showReservaHoy() {

        try {
            String query = "select * from vista_reserva where id_reserva not in (SELECT id_reserva from viaje) and fecha = date_format(current_date,'%d/%m/%Y') order by id_reserva DESC";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Reserva r = new Reserva();
                r.setIdReserva(rs.getInt("id_reserva"));
                r.setHora(rs.getString("hora"));
                r.setFecha(rs.getString("fecha"));
                r.setOrigen(rs.getString("origen"));
                r.setHabitacion(rs.getString("habitacion"));
                r.setDestino(rs.getString("destino"));
                r.setPax(rs.getInt("pax"));
                r.setObservacion(rs.getString("observacion"));
                r.setClaseReserva(rs.getString("clase_reserva"));

                listaReserva.add(r);
            }
        } catch (SQLException e) {
            System.out.println(e.fillInStackTrace());
        }
        return listaReserva;
    }

    @Override
    public int countHoy() {
        int total = 0;
        try {
            String query = "select count(*) as total from vista_taxista where fecha = date_format(current_date,'%d/%m/%Y')";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (SQLException e) {
            System.out.println(e.fillInStackTrace());
        }
        return total;
    }
    
    @Override
    public int countReservaHoy() {
        int total = 0;
        try {
            String query = "select count(*) as total from vista_reserva where id_reserva not in (select id_reserva from viaje) and fecha = date_format(CURRENT_DATE,'%d/%m/%Y')";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (SQLException e) {
            System.out.println(e.fillInStackTrace());
        }
        return total;
    }

    @Override
    public int countTotalViaje(int num_licencia) {
        int total = 0;
        try {
            String query = "SELECT num_licencia,nombre, COUNT(*) as total_viaje FROM `vista_taxista` where num_licencia = '" + num_licencia + "' group by nombre";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                total = total = rs.getInt("total_viaje");
            }
        } catch (SQLException e) {
            System.out.println(e.fillInStackTrace());
        }
        return total;
    }

    @Override
    public List viajePorTaxista(int num) {
        try {
            String query = "SELECT * from vista_taxista where num_licencia = '" + num + "' order by id_reserva desc";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Taxista t = new Taxista();
                t.setIdReserva(rs.getInt("id_reserva"));
                t.setHora(rs.getString("hora"));
                t.setFecha(rs.getString("fecha"));
                t.setOrigen(rs.getString("origen"));
                t.setHabitacion(rs.getString("habitacion"));
                t.setDestino(rs.getString("destino"));
                t.setPax(rs.getInt("pax"));
                t.setObservacion(rs.getString("observacion"));
                t.setClaseReserva(rs.getString("clase_reserva"));
                t.setTaxi(rs.getString("taxi"));
                t.setNum_licencia(rs.getInt("num_licencia"));
                t.setNombre(rs.getString("nombre"));
                t.setTelefono(rs.getInt("telefono"));
                t.setMarcaCoche(rs.getString("marca_coche"));
                t.setModelo(rs.getString("modelo"));
                t.setMatricula(rs.getString("matricula"));
                t.setPoblacion(rs.getString("poblacion"));

                listaFecha.add(t);
            }
        } catch (SQLException e) {
            System.out.println(e.fillInStackTrace());
        }
        return listaFecha;
    }

    @Override
    public String capitalize(String str) {
        
        String words[] = str.split("\\s");
        String capitalizeWord = "";
        for (String w : words) {
            String first = w.substring(0, 1);
            String afterfirst = w.substring(1);
            capitalizeWord += first.toUpperCase() + afterfirst + " ";
        }
        return capitalizeWord.trim();
    }

    @Override
    public List searchDateNumeroTaxi(String fecha, String num) {
        try {
            String query = "SELECT * FROM `vista_taxista` where fecha= date_format('" + fecha + "','%d/%m/%Y') and num_licencia ='" + num + "' order by id_reserva desc";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery(query);

            while (rs.next()) {
                Taxista t = new Taxista();

                t.setIdReserva(rs.getInt("id_reserva"));
                t.setHora(rs.getString("hora"));
                t.setFecha(rs.getString("fecha"));
                t.setOrigen(rs.getString("origen"));
                t.setHabitacion(rs.getString("habitacion"));
                t.setDestino(rs.getString("destino"));
                t.setPax(Integer.parseInt(rs.getString("pax")));
                t.setObservacion(rs.getString("observacion"));
                t.setClaseReserva(rs.getString("clase_reserva"));
                t.setTaxi(rs.getString("taxi"));
                t.setNum_licencia(rs.getInt("num_licencia"));
                t.setNombre(rs.getString("nombre"));
                t.setTelefono(rs.getInt("telefono"));
                t.setMarcaCoche(rs.getString("marca_coche"));
                t.setModelo(rs.getString("modelo"));
                t.setMatricula(rs.getString("matricula"));
                t.setPoblacion(rs.getString("poblacion"));

                listaFecha.add(t);
            }
        } catch (SQLException e) {
            System.out.println(e.fillInStackTrace());
        }
        return listaFecha;
    }

    @Override
    public List searchDateNumeroTaxiOrigen(String date, String num, String origen) {
        try {
            String query = "SELECT * FROM `vista_taxista` where fecha= date_format('" + date + "','%d/%m/%Y') and num_licencia ='" + num + "' and origen like '%" + origen + "%' order by id_reserva desc";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery(query);

            while (rs.next()) {
                Taxista t = new Taxista();

                t.setIdReserva(rs.getInt("id_reserva"));
                t.setHora(rs.getString("hora"));
                t.setFecha(rs.getString("fecha"));
                t.setOrigen(rs.getString("origen"));
                t.setHabitacion(rs.getString("habitacion"));
                t.setDestino(rs.getString("destino"));
                t.setPax(Integer.parseInt(rs.getString("pax")));
                t.setObservacion(rs.getString("observacion"));
                t.setClaseReserva(rs.getString("clase_reserva"));
                t.setTaxi(rs.getString("taxi"));
                t.setNum_licencia(rs.getInt("num_licencia"));
                t.setNombre(rs.getString("nombre"));
                t.setTelefono(rs.getInt("telefono"));
                t.setMarcaCoche(rs.getString("marca_coche"));
                t.setModelo(rs.getString("modelo"));
                t.setMatricula(rs.getString("matricula"));
                t.setPoblacion(rs.getString("poblacion"));

                listaFecha.add(t);
            }
        } catch (SQLException e) {
            System.out.println(e.fillInStackTrace());
        }
        return listaFecha;
    }

    @Override
    public List searchRoig(String vale) {
        try {
            String query = "SELECT * FROM `vista_taxista` where observacion like '%"+vale+"'";
            String query2 = "SELECT * FROM `vista_taxista` where observacion REGEXP '^[v]' or clase_reserva like '%aplazado%'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery(query);

            while (rs.next()) {
                Taxista t = new Taxista();

                t.setIdReserva(rs.getInt("id_reserva"));
                t.setHora(rs.getString("hora"));
                t.setFecha(rs.getString("fecha"));
                t.setOrigen(rs.getString("origen"));
                t.setHabitacion(rs.getString("habitacion"));
                t.setDestino(rs.getString("destino"));
                t.setPax(Integer.parseInt(rs.getString("pax")));
                t.setObservacion(rs.getString("observacion"));
                t.setClaseReserva(rs.getString("clase_reserva"));
                t.setTaxi(rs.getString("taxi"));
                t.setNum_licencia(rs.getInt("num_licencia"));
                t.setNombre(rs.getString("nombre"));
                t.setTelefono(rs.getInt("telefono"));
                t.setMarcaCoche(rs.getString("marca_coche"));
                t.setModelo(rs.getString("modelo"));
                t.setMatricula(rs.getString("matricula"));
                t.setPoblacion(rs.getString("poblacion"));

                listaFecha.add(t);
            }
        } catch (SQLException e) {
            System.out.println(e.fillInStackTrace());
        }
        return listaFecha;
    }

    @Override
    public void addNota(Nota n) throws Exception {
        
            String query = "insert into nota(note) values('"+n.getHora()+"')";
            String query2 = "update nota set note = '"+n.getHora()+"' ";
            ps = con.prepareStatement(query2);
            ps.executeUpdate();
    }
    
    @Override
    public Nota showNota() {
        try{
            String query = "select note from nota";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()) {
                //Nota n = new Nota();
                n.setHora(rs.getString("note"));
                //listaNota.add(n);
            }
        }catch (SQLException e) {
            System.out.println(e.fillInStackTrace());
        }
        return n;
    }
    

}
