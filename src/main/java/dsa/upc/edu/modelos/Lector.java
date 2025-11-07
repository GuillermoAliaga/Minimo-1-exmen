package dsa.upc.edu.modelos;

import java.util.Date;
import java.util.List;

public class Lector {
    String id;
    String nombre;
    String apellidos;
    Date fechanacimiento;
    String lugarnacimiento;
    String DNI;
    String direccion;


public Lector (String id, String nombre, String apellidos, Date fechanacimiento,String lugarnacimiento, String DNI, String direccion)
{
    this.id = id;
    this.nombre = nombre;
    this.apellidos = apellidos;
    this.fechanacimiento = fechanacimiento;
    this.lugarnacimiento = lugarnacimiento;
    this.DNI = DNI;
    this.direccion = direccion;
}
public Lector (){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public String getLugarnacimiento() {
        return lugarnacimiento;
    }

    public void setLugarnacimiento(String lugarnacimiento) {
        this.lugarnacimiento = lugarnacimiento;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public static Lector buscarLector(List<Lector> lectores, String lectorid){
        for(Lector lector : lectores){
            if(lector.getId().equals(lectorid)) {
                return lector;
            }
        }
        return null;
    }
}
