package dsa.upc.edu;
import dsa.upc.edu.modelos.Lector;
import dsa.upc.edu.modelos.Libro;
import dsa.upc.edu.modelos.Prestamo;

import java.util.Date;
import java.util.List;

public interface Manager {
    Lector anadirlectores (String id, String nombre, String apellidos, Date fechanacimiento, String lugarnacimiento, String DNI, String direccion);
    void almacenarlibro (String id, String ISBN, String titulo, String editorial, int publicacion, int numeroedicion, String autor, String tematica, int cantidad);
    void catalogarlibro ();
    Prestamo libroprestado(String idprestamo,String idlector,String isbn,Date fechaprestamo,Date fechadevolucion);
    List<Prestamo> consultarlibroslector(String idlector);
}
