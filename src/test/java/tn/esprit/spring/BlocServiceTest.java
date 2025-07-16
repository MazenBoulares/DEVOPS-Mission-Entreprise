package tn.esprit.spring;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BlocServiceTest {

    @BeforeAll
    void before() {
        System.out.println("Before all tests");
    }

    @AfterAll
    void after() {
        System.out.println("After all tests");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("Before each test");
    }

    @AfterEach
    void afterEach() {
        System.out.println("After each test");
    }

    @Order(1)
    @RepeatedTest(4)
    void test() {
        Assertions.assertTrue(true);
    }

    @Order(4)
    @Test
    void test2() {
        Assertions.assertEquals(1, 1);
    }

    @Order(2)
    @Test
    void test3() {
        Assertions.assertNotNull("Hello");
    }

    @Order(3)
    @Test
    void test4() {
        Assertions.assertThrows(ArithmeticException.class, () -> {
            int x = 1 / 0;
        });
    }
}
