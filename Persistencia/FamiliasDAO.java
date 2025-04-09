package Persistencia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Entidades.Familias;

public class FamiliasDAO extends DAO {

    public List<Familias> listarTodasLasFamilias() throws Exception {
        String sql = "SELECT * FROM familias;";
        consultarDataBase(sql);

        List<Familias> familias = new ArrayList<>();

        while (resultSet.next()) {
            Familias familia = new Familias(
                resultSet.getInt("idFamilia"),
                    resultSet.getString("nombre"),
                    resultSet.getInt("EdadMinima"),
                    resultSet.getInt("EdadMaxima"),
                    resultSet.getInt("numHijos"),
                    resultSet.getString("email"),
                    resultSet.getInt("idCasaFamilia"));
                   
            familias.add(familia);
            System.out.println(familias.toString());
        }

        return familias;
    }
    public void guardarFamilia(Familias familia) throws Exception {
        validarFamilia(familia);
        String sql = "INSERT INTO familias (nombre,edad_minima,edad_maxima,num_hijos,email,id_casa_familia) VALUES ('"
                    + familia.getNombre()
                    + "'," + familia.getEdadMinima() + ","
                    + familia.getEdadMaxima() + ","
                    + familia.getNumHijos() + ",'"
                    + familia.getEmail() + "',"                    
                    + familia.getIdCasaFamilia() + ")";
        insertarModificarEliminarDataBase(sql);
    }
    public Familias buscarFamiliaPorId(int id) throws Exception {
        Familias familia = new Familias();
        String sql = "SELECT * FROM familias WHERE id_familia = " + id;
        consultarDataBase(sql);
        while (resultSet.next()) {
            familia.setNombre(resultSet.getString("nombre"));
            familia.setEdadMinima(resultSet.getInt("edad_minima"));
            familia.setEdadMaxima(resultSet.getInt("edad_maxima"));
            familia.setNumHijos(resultSet.getInt("num_hijos"));
            familia.setEmail(resultSet.getString("email"));
            familia.setIdCasaFamilia(resultSet.getInt("id_casa_familia"));
       }
        return familia;
    }

    public void eliminarFamilia(int id) throws Exception {
        String sql = "DELETE FROM familias WHERE id_familia = " + id;
        insertarModificarEliminarDataBase(sql);
    }

    public void eliminarFamilia(Familias familia) throws Exception {
        String sql = "DELETE FROM familias WHERE id_familia = " + familia.getIdFamilia();
        insertarModificarEliminarDataBase(sql);
    }

    public void actualizarFamilia(Familias familia) throws Exception {
        validarFamilia(familia);
        String sql = "UPDATE familias SET nombre = '" + familia.getNombre()
                   + "', edad_minima = " + familia.getEdadMinima()
                   + ", edad_maxima = " + familia.getEdadMaxima()
                   + ", num_hijos = " + familia.getNumHijos()
                   + ", email = '" + familia.getEmail()
                   + "', id_casa_familia = " + familia.getIdCasaFamilia()
                   + " WHERE id_familia = " + familia.getIdFamilia();
        insertarModificarEliminarDataBase(sql);
    }
    
    // utils
    private static void validarFamilia(Familias f) throws Exception,SQLException{
        if (f == null) {
            throw new Exception("El campo 'familia' no puede ser nulo");
        }
        if (f.getEdadMinima() == 0 || f.getEdadMaxima() == 0 || f.getNombre() == null
            || f.getNumHijos() < 0 || f.getEmail() == null
            || f.getIdCasaFamilia() == 0) {
                throw new SQLException("Información errónea o incompleta");
        } 
    }

}
