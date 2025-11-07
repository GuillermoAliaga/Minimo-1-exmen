package dsa.upc.edu.modelos;

public class Libro {
    String id;
    String ISBN;
    String titulo;
    String editorial;
    int publicacion;
    int numeroedicion;
    String autor;
    String tematica;
int cantidad;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Libro(String id, String ISBN, String titulo, String editorial, int publicacion, int numeroedicion, String autor, String tematica, int cantidad) {
        this.id = id;
        this.ISBN = titulo;
        this.titulo = titulo;
        this.editorial = editorial;
        this.publicacion = publicacion;
        this.numeroedicion = numeroedicion;
        this.autor = autor;
        this.tematica = tematica;
        this.cantidad = 1;
    }
    public Libro (){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(int publicacion) {
        this.publicacion = publicacion;
    }

    public int getNumeroedicion() {
        return numeroedicion;
    }

    public void setNumeroedicion(int numeroedicion) {
        this.numeroedicion = numeroedicion;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }
}
