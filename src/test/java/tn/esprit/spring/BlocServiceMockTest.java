package tn.esprit.spring;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.DAO.Entities.Bloc;
import tn.esprit.spring.Services.Bloc.IBlocService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BlocServiceMockTest {

    @Mock
    private IBlocService blocService;

    private Bloc testBloc;

    @BeforeEach
    void setUp() {
        testBloc = Bloc.builder()
                .idBloc(1L)
                .nomBloc("Bloc A")
                .capaciteBloc(100)
                .build();
    }

    @Order(1)
    @Test
    void testAddOrUpdate() {
        when(blocService.addOrUpdate(any(Bloc.class))).thenReturn(testBloc);

        Bloc result = blocService.addOrUpdate(testBloc);

        assertNotNull(result);
        assertEquals("Bloc A", result.getNomBloc());
        verify(blocService, times(1)).addOrUpdate(testBloc);
    }

    @Order(2)
    @Test
    void testFindById() {
        when(blocService.findById(1L)).thenReturn(testBloc);

        Bloc result = blocService.findById(1L);

        assertEquals(1L, result.getIdBloc());
        verify(blocService).findById(1L);
    }

    @Order(3)
    @Test
    void testFindAll() {
        List<Bloc> blocList = Arrays.asList(testBloc);
        when(blocService.findAll()).thenReturn(blocList);

        List<Bloc> result = blocService.findAll();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(blocService).findAll();
    }

    @Order(4)
    @Test
    void testDeleteById() {
        doNothing().when(blocService).deleteById(1L);

        blocService.deleteById(1L);

        verify(blocService).deleteById(1L);
    }

    @Order(5)
    @Test
    void testAffecterChambresABloc() {
        List<Long> chambreIds = Arrays.asList(101L, 102L);
        when(blocService.affecterChambresABloc(chambreIds, "Bloc A")).thenReturn(testBloc);

        Bloc result = blocService.affecterChambresABloc(chambreIds, "Bloc A");

        assertNotNull(result);
        assertEquals("Bloc A", result.getNomBloc());
        verify(blocService).affecterChambresABloc(chambreIds, "Bloc A");
    }
}
