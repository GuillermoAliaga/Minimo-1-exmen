package dsa.upc.edu;
import java.util.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import dsa.upc.edu.modelos.Lector;
import dsa.upc.edu.modelos.Libro;
import dsa.upc.edu.modelos.Prestamo;
import org.apache.log4j.Logger;

public class ManagerImpl implements Manager {
    private static Manager instance;
    protected List<Lector> lectores;
    protected List<Libro> libros;
    protected List<Prestamo> Prestamos;
    protected Queue<Stack<Libro>> montoneslibros;
    protected List<Libro> librosCatalogados;
    final static Logger logger = Logger.getLogger(ManagerImpl.class);

    public ManagerImpl(){
        this.lectores = new ArrayList<>();
        this.libros = new ArrayList<>();
        this.Prestamos = new ArrayList<>();
        this.montoneslibros = new LinkedList<>();
        this.montoneslibros.add(new Stack<>());
    }
    public static Manager getInstance() {
        if (instance==null) instance = new ManagerImpl();
        return instance;
    }
    public Lector anadirlectores(String id, String nombre, String apellidos, Date fechanacimiento,String lugarnacimiento, String DNI, String direccion) {
        Lector lector = new Lector(id, nombre, apellidos, fechanacimiento,lugarnacimiento,DNI,direccion);
        this.lectores.add(lector);
        logger.info("Lector añadido");
        return lector;
    }
@Override
    public void almacenarlibro(String id, String ISBN, String titulo, String editorial, int publicacion, int numeroedicion, String autor, String tematica, int cantidad) {
        Libro libro = new Libro(id, ISBN, titulo, editorial, publicacion, numeroedicion, autor, tematica,cantidad);
        if(montoneslibros.isEmpty()){
        Stack<Libro> nuevomonton = new Stack<>();
        nuevomonton.push(libro);
        montoneslibros.add(nuevomonton);
    }
        else {
            Stack<Libro> ultimomonton = ((LinkedList<Stack<Libro>>)montoneslibros).getLast();
            if(ultimomonton.size() < 10)
            {
                ultimomonton.push(libro);
            }
            else {
                Stack<Libro> nuevomonton = new Stack<>();
                nuevomonton.push(libro);
                montoneslibros.add(nuevomonton);
            }
        }
        logger.info ("Libro añadido correctamente:" + libro.getTitulo());

}

    @Override
    public void catalogarlibro() {
        if(montoneslibros.isEmpty()){
            logger.info ("No hay libros que catalogar");
        }
        Stack<Libro> montonactual = montoneslibros.peek();
        Libro libro = montonactual.pop();
        logger.info ("Libro"+ libro.getTitulo() + "con ISBN" + libro.getISBN());
        if (montonactual.isEmpty()){
            montoneslibros.poll();
            logger.info ("El monton actual esta vacio");
        }
        boolean mismoisbn = false;
        for(Libro libro1 : this.libros)
        {
            if(libro1.getISBN().equals(libro.getISBN())){
                libro1.setCantidad(libro1.getCantidad() + 1);
                mismoisbn = true;
                logger.info ("Hay más ejemplares de:" + libro.getTitulo());
                break;
            }
        }
        if(!mismoisbn)
        {
            libro.setCantidad(1);
            librosCatalogados.add(libro);
            logger.info("Libro" + libro.getTitulo() + "ha sido catalogado");
        }
    }

    @Override
    public Prestamo libroprestado(String idprestamo, String idlector, String idlibro, Date fechaprestamo, Date fechadevolucion) {
        Libro libro =null;
        Lector lector = null;
        for(Libro libro1 : this.libros)
        {
            if (libro1.getId().equals(idlibro))
            {
                libro = libro1;
                break;
            }
        }
        for(Lector lector1 : this.lectores)
        {
            if (lector1.getId().equals(idlector))
            {
                lector = lector1;
                break;
            }
        }
        if (lector == null || libro == null)
        {
            logger.info("No encontrado");
        }
        if(libro.getCantidad() <= 0)
        {
            logger.info ("No hay suficientes libros titutlados:" +libro.getTitulo() + "en este momento");
        }
        Prestamo prestamo = new Prestamo(idprestamo, lector.getId(), libro.getId(),fechaprestamo, fechadevolucion);
        prestamo.setDisponible(true);
        libro.setCantidad(libro.getCantidad() - 1);
        Prestamos.add(prestamo);
        logger.info ("Libro prestado correctamente");
        return prestamo;
    }
    public List<Prestamo> consultarlibroslector(String idlector)
    {
        List<Prestamo> prestamo1 = new ArrayList<>();
        for (Prestamo prestamo: this.Prestamos) {
            if (prestamo.getIdlector().equals(idlector))
                prestamo1.add(prestamo);
        }
        return prestamo1;
    }
    }

