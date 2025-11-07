package dsa.upc.edu.servicios;

import dsa.upc.edu.Manager;
import dsa.upc.edu.ManagerImpl;
import dsa.upc.edu.modelos.Lector;
import dsa.upc.edu.modelos.Libro;
import dsa.upc.edu.modelos.Prestamo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

@Api(value = "/lector", description = "Endpoint to Lectores Service")
@Path("/lector")
public class Service {
    private Manager manager;
    public Service() {
        this.manager = ManagerImpl.getInstance();
    }
    @POST
    @ApiOperation(value = "Añade un lector", notes = "Añade un nuevo lector")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Lector añadido"),
            @ApiResponse(code = 500, message = "Error de validación")
    })
    @Path("/lectores/{lectorID}/{nombre}/{apellidos}/{fechadenacimiento}/{lugardenacimiento}/{DNI}/{direccion}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response anadirlector(@PathParam("lectorID") String lectorID, @PathParam("nombre") String nombre, @PathParam("apellidos") String apellidos, @PathParam("fechadenacimiento")String fechadenacimiento, @PathParam("lugardenacimiento")String lugardenacimiento, @PathParam("DNI")String DNI,@PathParam("direccion")String direccion){
        Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(fechadenacimiento, new ParsePosition(0));
        if (fecha == null) {
            return Response.status(400).entity("Formato de fecha inválido, usa yyyy-MM-dd").build();
        }

        Lector newLector = this.manager.anadirlectores(
                lectorID, nombre, apellidos, fecha, lugardenacimiento, DNI, direccion);

        return Response.status(201).entity(newLector).build();
    }
    @POST
    @Path("/almacenar/{id}/{ISBN}/{titulo}/{editorial}/{publicacion}/{numeroedicion}/{autor}/{tematica}/{cantidad}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response almacenarlibro(@PathParam("id") String id, @PathParam("ISBN") String ISBN, @PathParam("titulo") String titulo, @PathParam("editorial") String editorial, @PathParam("publicacion") int publicacion, @PathParam("numeroedicion") int numeroedicion, @PathParam("autor") String autor, @PathParam("tematica") String tematica, @PathParam("cantidad") int cantidad) {

        this.manager.almacenarlibro(id, ISBN, titulo, editorial, publicacion, numeroedicion, autor, tematica, cantidad);

        Libro libro = new Libro(id, ISBN, titulo, editorial, publicacion, numeroedicion, autor, tematica, cantidad);
        return Response.status(Response.Status.CREATED).entity(libro).build();
    }
    @POST
    @Path("/catalogar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response catalogarLibro() {
        try {
            manager.catalogarlibro();
            return Response.status(Response.Status.OK).entity(manager.getLibrosCatalogados()).build();
        } catch (Exception e) {
            e.printStackTrace(); // ver en consola
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al catalogar: " + e.getMessage()).build();
        }
    }
    @POST
    @Path("/prestar/{idprestamo}/{idlector}/{idlibro}/{fechaprestamo}/{fechadevolucion}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response prestarLibro(@PathParam("idprestamo") String idprestamo, @PathParam("idlector") String idlector, @PathParam("idlibro") String idlibro, @PathParam("fechaprestamo") String fechaprestamo, @PathParam("fechadevolucion") String fechadevolucion) {

        try {
            Date inicio = new SimpleDateFormat("yyyy-MM-dd").parse(fechaprestamo);
            Date fin = new SimpleDateFormat("yyyy-MM-dd").parse(fechadevolucion);
            Prestamo prestamo = manager.libroprestado(idprestamo, idlector, idlibro, inicio, fin);
            if (prestamo == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity("No se pudo realizar el préstamo").build();
            }
            return Response.status(Response.Status.CREATED).entity(prestamo).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al prestar libro: " + e.getMessage()).build();
        }
    }
    @GET
    @Path("/lector/{idlector}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarPrestamosLector(@PathParam("idlector") String idlector) {
        List<Prestamo> prestamos = manager.consultarlibroslector(idlector);
        return Response.ok(prestamos).build();
    }


}


