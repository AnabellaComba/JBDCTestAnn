package Persistencia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Entidades.Comentarios;

public class ComentariosDAO extends DAO{

        public List<Comentarios> listarTodosLosComentarios() throws Exception {
        String sql = "SELECT * FROM comentarios;";
        consultarDataBase(sql);

        List<Comentarios> comentarios = new ArrayList<>();

        while (resultSet.next()) {
            Comentarios comentario = new Comentarios(
                    
                    resultSet.getInt("idComentario"),
                    resultSet.getInt("idCasa"),
                    resultSet.getString("comentario"));
                    
            comentarios.add(comentario);
            System.out.println(comentarios.toString());
        }

        return comentarios;
    }
    public void guardarComentario(Comentarios com) throws Exception {
        validarComentarios(com);
        String sql = "INSERT INTO comentarios (id_casa,comentario) VALUES ("
                + com.getIdCasa()
                + ",'" + com.getComentario()
                + "')";
        insertarModificarEliminarDataBase(sql);
    }
    public List<Comentarios> listaComentariosPorIdCasa(int id) throws Exception {
        String sql = "SELECT * FROM comentarios WHERE id_casa = " + id;
        consultarDataBase(sql);
        List<Comentarios> comentarios = new ArrayList<>();
        while (resultSet.next()) {
            Comentarios coment = new Comentarios();
            coment.setIdComentario(resultSet.getInt("id_comentario"));
            coment.setIdCasa(resultSet.getInt("id_casa"));
            coment.setComentario(resultSet.getString("comentario"));
            comentarios.add(coment);
        }
        return comentarios;
    }

    public Comentarios buscarComentarioPorId(int id) throws Exception {
        Comentarios coment = new Comentarios();
        String sql = "SELECT * FROM comentarios WHERE id_comentario = " + id;
        consultarDataBase(sql);
        while (resultSet.next()) {
            coment.setIdComentario(resultSet.getInt("id_comentario"));
            coment.setIdCasa(resultSet.getInt("id_casa"));
            coment.setComentario(resultSet.getString("comentario"));
        }
        return coment;
    }

    public void eliminarComentarioPorId(int id) throws Exception {
        String sql = "DELETE FROM comentarios WHERE id_comentario = " + id;
        insertarModificarEliminarDataBase(sql);
    }

    public void eliminarComentarioPorId(Comentarios coment) throws Exception {
        String sql = "DELETE FROM comentarios WHERE id_comentario = " + coment.getIdComentario();
        insertarModificarEliminarDataBase(sql);
    }

    public void actualizarComentario(Comentarios coment) throws Exception {
        validarComentarios(coment);
        String sql = "UPDATE comentarios SET comentario = '" + coment.getComentario()
                   + "' WHERE id_comentario = " + coment.getIdComentario();
        insertarModificarEliminarDataBase(sql);
    }

    // utils
    private static void validarComentarios(Comentarios c) throws Exception,SQLException{
        if (c == null) {
            throw new Exception("El campo 'comentario' no puede ser nulo");
        }
        if (c.getIdCasa() == 0 || c.getComentario() == null || 
            c.getComentario().length() > 255) {
                throw new SQLException("Información errónea o incompleta");
        } 
    }
    
}
