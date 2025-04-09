package Persistencia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entidades.Casas;

public class CasasDAO extends DAO{

        public List<Casas> listarTodasLasCasas() throws Exception {
        String sql = "SELECT * FROM casas;";
        consultarDataBase(sql);

        List<Casas> casas = new ArrayList<>();

        while (resultSet.next()) {
            Casas casa = new Casas(
                resultSet.getInt("IdCasa"),
                    resultSet.getString("calle"),
                    resultSet.getInt("numero"),
                    resultSet.getString("codigoPostal"),
                    resultSet.getString("ciudad"),
                    resultSet.getString("pais"),
                    resultSet.getDate("fechaDesde"),
                    resultSet.getDate("fechaHasta"),
                    resultSet.getInt("tiempoMinimo"),
                    resultSet.getInt("tiempoMaximo"),
                    resultSet.getDouble("precioHabitacion"),
                    resultSet.getString("tipoVivienda"));

            casas.add(casa);
            System.out.println(casas.toString());
        }

        return casas;
    }

    // Agregar

    public void guardarCasa(Casas casa) throws Exception {
        validarCasas(casa);
        String sql = "INSERT INTO casas (calle,numero,codigo_postal,ciudad,pais,fecha_desde,fecha_hasta,tiempo_minimo,tiempo_maximo,precio_habitacion,tipo_vivienda) VALUES ('"
                    + casa.getCalle()
                    + "'," + casa.getNumero()
                    + ",'" + casa.getCodigoPostal() + "','"
                    + casa.getCiudad() + "','"
                    + casa.getPais() + "','"
                    + casa.getFechaDesde() + "','"
                    + casa.getFechaHasta() + "',"
                    + casa.getTiempoMinimo() + ","
                    + casa.getTiempoMaximo() + ","
                    + casa.getPrecioHabitacion() + ",'"
                    + casa.getTipoVivienda() + "');";
        insertarModificarEliminarDataBase(sql);
    }

 private static void validarCasas(Casas c) throws Exception,SQLException{
        if (c == null) {
            throw new Exception("El campo 'casa' no puede ser nulo");
        }
        if (c.getNumero() == 0 || c.getCiudad() == null || c.getPais() == null
            || c.getFechaDesde() == null || c.getFechaHasta() == null
            || c.getTiempoMinimo() == 0 || c.getTiempoMaximo() == 0 
            || c.getPrecioHabitacion() < 0 || c.getTipoVivienda() == null) {
                throw new SQLException("Información errónea o incompleta");
        } 
    }
    public Casas buscarCasaPorId(int id) throws Exception {
        Casas casa = new Casas();
        String sql = "SELECT * FROM casas WHERE id_casa = " + id;
        consultarDataBase(sql);
        while (resultSet.next()) {
            casa.setIdCasa(resultSet.getInt("id_casa"));
            casa.setCalle(resultSet.getString("calle"));
            casa.setNumero(resultSet.getInt("numero"));
            casa.setCodigoPostal(resultSet.getString("codigo_postal"));
            casa.setCiudad(resultSet.getString("ciudad"));
            casa.setPais(resultSet.getString("pais"));
            casa.setFechaDesde(resultSet.getDate("fecha_desde"));
        }
        return casa;
    }

    public void eliminarCasa(int id) throws Exception {
        String sql = "DELETE FROM casas WHERE id_casa = " + id;
        insertarModificarEliminarDataBase(sql);
    }

    public void eliminarCasa(Casas casa) throws Exception {
        String sql = "DELETE FROM casas WHERE id_casa = " + casa.getIdCasa();
        insertarModificarEliminarDataBase(sql);
    }

    public void actualizarCasa(Casas casa) throws Exception {
        validarCasas(casa);
        String sql = "UPDATE casas SET calle = '" + casa.getCalle()
                   + "', numero = " + casa.getNumero()
                   + ", codigo_postal = '" + casa.getCodigoPostal()
                   + "', ciudad = '" + casa.getCiudad()
                   + "', pais = '" + casa.getPais()
                   + "', fecha_desde = '" + casa.getFechaDesde()
                   + "', fecha_hasta = '" + casa.getFechaHasta()
                   + "', tiempo_minimo = " + casa.getTiempoMinimo()
                   + ", tiempo_maximo = " + casa.getTiempoMaximo()
                   + ", precio_habitacion = " + casa.getPrecioHabitacion()
                   + ", tipo_vivienda = '" + casa.getTipoVivienda()
                   + "' WHERE id_casa = " + casa.getIdCasa();
        insertarModificarEliminarDataBase(sql);
    }
    
}
