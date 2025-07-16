import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BlocServiceTest {  // Remove Spring annotations
    
    @BeforeAll
    static void beforeAll() {  // Must be static
        System.out.println("Before all tests");
    }

    @Test @Order(1)
    void test1() {
        assertTrue(true);
    }

    @Test @Order(2)
    void test2() {
        assertEquals(1, 1);
    }
}
