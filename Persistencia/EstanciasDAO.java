package Persistencia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entidades.Estancias;

public class EstanciasDAO extends DAO {
    public List<Estancias> listarTodasLasEstancias() throws Exception {
        String sql = "SELECT * FROM estancias;";
        consultarDataBase(sql);

        List<Estancias> estancias = new ArrayList<>();

        while (resultSet.next()) {
            Estancias estancia = new Estancias(

                    resultSet.getDate("fechaDesde"),
                    resultSet.getDate("fechaHasta"),
                    resultSet.getInt("idEstancias"),
                    resultSet.getInt("idCliente"),
                    resultSet.getInt("idCasa"),
                    resultSet.getString("nombreHuesped"));
           
            estancias.add(estancia);
            System.out.println(estancias.toString());
        }

        return estancias;
    }
    public void guardarEstancia(Estancias estancia) throws Exception {
        validarEstancia(estancia);
        String sql = "INSERT INTO estancias (id_cliente,id_casa,nombre_huesped,fecha_desde,fecha_hasta) VALUES ("
                    + estancia.getIdCliente()
                    + "," + estancia.getIdCasa() + ",'"
                    + estancia.getNombreHuesped() + "','"
                    + estancia.getFechaDesde() + "','"
                    + estancia.getFechaHasta()+ "')";
        insertarModificarEliminarDataBase(sql);
    }
public List<Estancias> listaEstanciasCliente(int id_cliente) throws Exception {
        String sql = "SELECT * FROM estancias WHERE id_cliente = " + id_cliente;
        consultarDataBase(sql);        
        List<Estancias> estancias = new ArrayList<>();
        while (resultSet.next()) {
            Estancias estancia = new Estancias();
            estancia.setIdCliente(resultSet.getInt("id_cliente"));
            estancia.setIdCasa(resultSet.getInt("id_casa"));
            estancia.setNombreHuesped(resultSet.getString("nombre_huesped"));
            estancia.setFechaDesde(resultSet.getDate("fecha_desde"));
            estancia.setFechaHasta(resultSet.getDate("fecha_hasta"));
            estancias.add(estancia);
        } 
        return estancias;
    }
    
    public List<Estancias> listaEstanciasCasa(int id_casa) throws Exception {
        String sql = "SELECT * FROM estancias WHERE id_casa = " + id_casa;
        consultarDataBase(sql);        
        List<Estancias> estancias = new ArrayList<>();
        while (resultSet.next()) {
            Estancias estancia = new Estancias();
            estancia.setIdCliente(resultSet.getInt("id_cliente"));
            estancia.setIdCasa(resultSet.getInt("id_casa"));
            estancia.setNombreHuesped(resultSet.getString("nombre_huesped"));
            estancia.setFechaDesde(resultSet.getDate("fecha_desde"));
            estancia.setFechaHasta(resultSet.getDate("fecha_hasta"));
            estancias.add(estancia);
        } 
        return estancias;
    }

    public Estancias buscarEstanciaPorId(int id) throws Exception {
        Estancias estancia = new Estancias();
        String sql = "SELECT * FROM estancias WHERE id_estancia = " + id;
        consultarDataBase(sql);
        while (resultSet.next()) {
            estancia.setIdCliente(resultSet.getInt("id_cliente"));
            estancia.setIdCasa(resultSet.getInt("id_casa"));
            estancia.setNombreHuesped(resultSet.getString("nombre_huesped"));
            estancia.setFechaDesde(resultSet.getDate("fecha_desde"));
            estancia.setFechaHasta(resultSet.getDate("fecha_hasta"));
        }
        return estancia;
    }

    public void eliminarEstanciaPorId(int id) throws Exception {
        String sql = "DELETE FROM estancias WHERE id_estancia = " + id;
        insertarModificarEliminarDataBase(sql);
    }

    public void eliminarEstanciaPorId(Estancias estancia) throws Exception {
        String sql = "DELETE FROM estancias WHERE id_estancia = " + estancia.getIdEstancias();
        insertarModificarEliminarDataBase(sql);
    }

    public void actualizarEstancia(Estancias estancia) throws Exception {
        validarEstancia(estancia);
        String sql = "UPDATE estancias SET id_cliente = " + estancia.getIdCliente()
                   + ", id_casa = " + estancia.getIdCasa()
                   + ", nombre_huesped = '" + estancia.getNombreHuesped()
                   + "', fecha_desde = '" + estancia.getFechaDesde()
                   + "', fecha_hasta = '" + estancia.getFechaHasta()
                   + "' WHERE id_estancia = " + estancia.getIdEstancias();
        insertarModificarEliminarDataBase(sql);
    }
    
    // utils
    private static void validarEstancia(Estancias e) throws Exception,SQLException{
        if (e == null) {
            throw new Exception("El campo 'estancia' no puede ser nulo");
        }
        if (e.getIdCliente() == 0 || e.getIdCasa() == 0 || e.getNombreHuesped() == null
            || e.getFechaDesde() == null || e.getFechaHasta() == null) {
                throw new SQLException("Información errónea o incompleta");
        } 
    }
}
