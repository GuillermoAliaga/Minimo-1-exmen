package dsa.upc.edu.servicios;

import dsa.upc.edu.Manager;
import dsa.upc.edu.ManagerImpl;
import dsa.upc.edu.modelos.Lector;
import dsa.upc.edu.modelos.Libro;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Date;

@Api(value = "/lector", description = "Endpoint to Lectores Service")
@Path("/lector")
public class Service {
    private Manager manager;
    @POST
    @ApiOperation(value = "Añade un lector", notes = "Añade un nuevo lector")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Lector añadido"),
            @ApiResponse(code = 500, message = "Error de validación")
    })
    @Path("/lectores/{lectorID}/{nombre}/{apellidos}/{fechadenacimiento}/{lugardenacimiento}/{DNI}/{direccion}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response anadirlector(@PathParam("lectorid") String lectorid, @PathParam("nombre") String nombre, @PathParam("apellidos") String apellidos, @PathParam("fechadenacimiento")Date fechadenacimiento, @PathParam("lugardenacimiento")String lugardenacimiento, @PathParam("DNI")String DNI,@PathParam("direccion")String direccion){
        Lector newlector = this.manager.anadirlectores(lectorid, nombre, apellidos, fechadenacimiento,lugardenacimiento, DNI, direccion );
        return Response.status(201).entity(newlector).build();
    }

}
