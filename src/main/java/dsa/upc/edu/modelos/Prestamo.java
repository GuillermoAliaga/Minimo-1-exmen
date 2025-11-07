package dsa.upc.edu.modelos;

import java.util.Date;

public class Prestamo {
    String idprestamo;
    String idlector;
    String idlibro;
    Date fechaprestamo;
    Date fechadevolucion;
    boolean estado;

    public Prestamo(String idprestamo,String idlector,String idlibro,Date fechaprestamo,Date fechadevolucion)
    {
        this.idprestamo = idprestamo;
        this.idlector = idlector;
        this.idlibro = idlibro;
        this.fechaprestamo = fechaprestamo;
        this.fechadevolucion = fechadevolucion;
        this.estado = false;
    }
    public Prestamo(String idprestamo, String idlector, String idlibro, Date fechaprestamo, Date fechadevolucion, boolean estado) {
        this(idprestamo, idlector, idlibro, fechaprestamo, fechadevolucion);
        this.estado = estado;
    }
    public Prestamo(){}

    public String getIdprestamo() {
        return idprestamo;
    }

    public void setIdprestamo(String idprestamo) {
        this.idprestamo = idprestamo;
    }

    public String getIdlector() {
        return idlector;
    }

    public void setIdlector(String idlector) {
        this.idlector = idlector;
    }

    public String getIdlibro() {
        return idlibro;
    }

    public void setIdlibro(String isbn) {
        this.idlibro = isbn;
    }

    public Date getFechaprestamo() {
        return fechaprestamo;
    }

    public void setFechaprestamo(Date fechaprestamo) {
        this.fechaprestamo = fechaprestamo;
    }

    public Date getFechadevolucion() {
        return fechadevolucion;
    }

    public void setFechadevolucion(Date fechadevolucion) {
        this.fechadevolucion = fechadevolucion;
    }
public boolean isDisponible(){return estado;}
    public void setDisponible(boolean estado){
        this.estado = estado;
    }

}
