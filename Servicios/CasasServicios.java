package Servicios;
import java.sql.Date;
import java.util.List;

import Entidades.Casas;
import Persistencia.*;

public class CasasServicios {
    private CasasDAO cd;

    public CasasServicios() {
        this.cd = new CasasDAO();
    }

    public void crearCasa(
            String calle,
            int numero,
            String codigoPostal,
            String ciudad,
            String pais,
            Date fechaDesde,
            Date fechaHasta,
            int tiempoMinimo,
            int tiempoMaximo,
            double precioHabitacion,
            String tipoVivienda) throws Exception {

        // TODO: falta validación
        cd.guardarCasa(new Casas(
                calle,
                numero,
                codigoPostal,
                ciudad,
                pais,
                fechaDesde,
                fechaHasta,
                tiempoMinimo,
                tiempoMaximo,
                precioHabitacion,
                tipoVivienda));
    }

    public void actualizarCasa(
            int idCasa,
            String calle,
            int numero,
            String codigoPostal,
            String ciudad,
            String pais,
            Date fechaDesde,
            Date fechaHasta,
            int tiempoMinimo,
            int tiempoMaximo,
            double precioHabitacion,
            String tipoVivienda) throws Exception {
        // TODO: falta validación
        cd.actualizarCasa(new Casas(
                idCasa,
                calle,
                numero,
                codigoPostal,
                ciudad,
                pais,
                fechaDesde,
                fechaHasta,
                tiempoMinimo,
                tiempoMaximo,
                precioHabitacion,
                tipoVivienda));
    }

    public List<Casas> listarCasas() throws Exception {
        return cd.listarTodasLasCasas();
    }

    public Casas buscarCasaPorId(int id) throws Exception {
        return cd.buscarCasaPorId(id);
    }

    public void eliminarCasa(int id) throws Exception {
        cd.eliminarCasa(id);
    }
}