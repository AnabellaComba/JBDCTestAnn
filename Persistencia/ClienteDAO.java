package Persistencia;


import java.util.ArrayList;
import java.util.List;

import Entidades.Clientes;
import utilities.ServiceException;

public class ClienteDAO extends DAO {

    public void guardarCliente(Clientes cliente) throws Exception {
        if (cliente == null) {
            throw new Exception("El cliente no puede ser nulo");
        }
        String sql = "INSERT INTO cliente (id_cliente, nombre, numero, email ) VALUES ('"
                + cliente.getIdCliente() + "', '"
                + cliente.getNombre() + "', '"
                + cliente.getNumero() + "', '"
                + cliente.getEmail() + "');";

        insertarModificarEliminarDataBase(sql);
    }

    public List<Clientes> listarTodosLosClientes() throws Exception {
        String sql = "SELECT * FROM clientes;";
        consultarDataBase(sql);

        List<Clientes> clientes = new ArrayList<>();

        while (resultSet.next()) {
            Clientes cliente = new Clientes(
                    resultSet.getInt("id_cliente"),
                    resultSet.getString("nombre"),
                    resultSet.getString("calle"),
                    resultSet.getInt("numero"),
                    resultSet.getString("codigo_postal"),
                    resultSet.getString("pais"),
                    resultSet.getString("ciudad"),
                    resultSet.getString("email"));
            clientes.add(cliente);
            System.out.println(cliente.toString());
        }

        return clientes;
    }

    public void eliminarClientePorId(int id) throws Exception {
        String sql = "DELETE FROM cliente WHERE id_cliente = " + id + ";";
        insertarModificarEliminarDataBase(sql);
    }

    public Clientes buscarClientePorId(int codigo) throws Exception{
        
        String sql = "select*from clientes where id_cliente ="+ codigo + """
                """;
        String datosCliente = "";
        consultarDataBase(sql);
        Clientes cliente = new Clientes(codigo, datosCliente, datosCliente, codigo, datosCliente, datosCliente, datosCliente, datosCliente);
        while (resultSet.next()) {
            
            cliente.setIdCliente(resultSet.getInt("id_cliente"));
            cliente.setNombre(resultSet.getString("nombre"));
            cliente.setNumero(resultSet.getInt("numero"));
            cliente.setEmail(resultSet.getString("email"));
            datosCliente = cliente.toString();
         }
        desconectarDataBase();
        return cliente;
    }
    public void actualizarCliente(Clientes c) throws Exception {
        validarCliente(c);
        dao.actualizarCliente(c);
        System.out.println("\nACTUALIZAR CLIENTE\n");
        output(c);
    }
     private static void output(Clientes c) {
        System.out.println("Nommbre: " + c.getNombre() + " Calle: " +c.getCalle() 
        + " Número: " +  c.getNumero() + " Código postal: " + c.getCodigoPostal() 
        + " Ciudad: " + c.getCiudad() + " País: " + c.getPais() + " Email: " 
        + c.getEmail());
    }

    private static void validarCliente(Clientes c) throws ServiceException {
        if (c.getNumero() <= 0) {
            throw new ServiceException("El campo número es incorrecto");
        }        
        if (c.getNombre().length() > 50 || c.getCiudad().length() > 50
            || c.getCalle().length() > 50 || c.getCodigoPostal().length() > 10
            || c.getPais().length() > 50 || c.getEmail().length() > 50) {
            throw new ServiceException("Error en alguno de los datos ingresados");
        }
    }

  
}