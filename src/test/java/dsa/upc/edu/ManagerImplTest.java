package dsa.upc.edu;
import dsa.upc.edu.modelos.Lector;
import dsa.upc.edu.modelos.Libro;
import dsa.upc.edu.modelos.Prestamo;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.AfterClass;

import java.util.*;

import static org.junit.Assert.*;
public class ManagerImplTest {
    private static final Logger logger = Logger.getLogger(ManagerImplTest.class);
    private Manager manager;

    @Before
    public void setUp() {
        manager = ManagerImpl.getInstance();
        logger.info("Inicializando Manager para un nuevo test");
    }

    @Test
    public void testAnadirlector() {
        logger.info("=== Test: Añadir Lector ===");
        Lector lector = manager.anadirlectores("Lector1","Ramon","Aliaga",new Date(), "Barcelona", "444444444", "Madrid" );
        assertNotNull(lector);
        assertEquals("Lector1", lector.getId());
    }
    @Test
public void testalmacenarlibros(){
    logger.info("=== Test: Almacenar Libros ===");
    ManagerImpl manager = new ManagerImpl();
    assertEquals(1, manager.montoneslibros.size());

    manager.almacenarlibro("Libro1","23", "titulo", "editorial", 12, 34, "autoor", "tematica", 1);
    Stack<Libro> primerMonton = ((LinkedList<Stack<Libro>>) manager.montoneslibros).getFirst();
    assertEquals(1, primerMonton.size());
    for (int i = 2; i <= 10; i++) {
        manager.almacenarlibro(String.valueOf(i), "ISBN" + i, "Libro" + i,
                "EditorialA", 2024, 1, "AutorA", "TemáticaA", 1);
    }
    assertEquals(10, primerMonton.size());

    manager.almacenarlibro("11", "ISBN11", "Libro11", "EditorialB", 2024, 1, "AutorB", "TemáticaB", 1);
    assertEquals(2, manager.montoneslibros.size());

    Stack<Libro> segundoMonton = ((LinkedList<Stack<Libro>>) manager.montoneslibros).getLast();
    assertEquals(1, segundoMonton.size());
}
    @Test
    public void testCatalogarLibro() {
        ManagerImpl manager = new ManagerImpl();
        manager.librosCatalogados = new ArrayList<>();
        manager.libros = new ArrayList<>();
        manager.montoneslibros.clear();

        Stack<Libro> monton = new Stack<>();
        Libro libro1 = new Libro("L1", "ISBN1", "titulo", "editorial", 1605, 1, "Cervantes", "Novela", 1);
        monton.push(libro1);
        manager.montoneslibros.add(monton);

        manager.catalogarlibro();
        assertEquals(1, manager.librosCatalogados.size());
        assertEquals("ISBN1", manager.librosCatalogados.get(0).getISBN());
        assertEquals(1, manager.librosCatalogados.get(0).getCantidad());

        Stack<Libro> monton2 = new Stack<>();
        Libro libro2 = new Libro("L2", "ISBN1", "titulo", "editorial", 1605, 1, "Cervantes", "Novela", 1);
        monton2.push(libro2);
        manager.montoneslibros.add(monton2);
        manager.libros.add(manager.librosCatalogados.get(0));

        manager.catalogarlibro();
        assertEquals(1, manager.librosCatalogados.size());
        assertEquals(2, manager.librosCatalogados.get(0).getCantidad());
    }
    @Test
    public void testLibroPrestado() {
        ManagerImpl manager = new ManagerImpl();
        manager.libros = new ArrayList<>();
        manager.lectores = new ArrayList<>();
        manager.Prestamos = new ArrayList<>();

        Lector lector = new Lector("L1", "Ramon", "Aliaga", new Date(), "Barcelona", "44444444X", "Madrid");
        manager.lectores.add(lector);

        Libro libro = new Libro("B1", "ISBN1", "titulo", "editorial", 1605, 1, "Cervantes", "Novela", 2);
        manager.libros.add(libro);

        Date fechaPrestamo = new Date();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 7);
        Date fechaDevolucion = cal.getTime();

        Prestamo prestamo = manager.libroprestado("P1", "L1", "B1", fechaPrestamo, fechaDevolucion);

        assertNotNull(prestamo);
        assertEquals("P1", prestamo.getIdprestamo());
        assertEquals("L1", prestamo.getIdlector());
        assertEquals("B1", prestamo.getIdlibro());
        assertTrue(prestamo.isDisponible());
        assertEquals(1, manager.Prestamos.size());
        assertEquals(1, libro.getCantidad());
    }
    @Test
    public void testConsultarLibrosLector() {
        ManagerImpl manager = new ManagerImpl();
        manager.Prestamos = new ArrayList<>();

        Prestamo p1 = new Prestamo("P1", "L1", "B1", new Date(), new Date(), true);
        Prestamo p2 = new Prestamo("P2", "L2", "B2", new Date(), new Date(), true);
        Prestamo p3 = new Prestamo("P3", "L1", "B3", new Date(), new Date(), true);

        manager.Prestamos.add(p1);
        manager.Prestamos.add(p2);
        manager.Prestamos.add(p3);

        List<Prestamo> prestamosL1 = manager.consultarlibroslector("L1");

        assertEquals(2, prestamosL1.size());
        assertTrue(prestamosL1.stream().allMatch(p -> p.getIdlector().equals("L1")));
    }

}
