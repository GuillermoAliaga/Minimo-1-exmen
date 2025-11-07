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
public void almacenarlibros(){
    logger.info("=== Test: Almacenar Libros ===");
    ManagerImpl manager = new ManagerImpl();
    assertTrue(manager.montoneslibros.isEmpty());
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

}
