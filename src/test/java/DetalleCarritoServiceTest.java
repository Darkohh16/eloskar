import com.eloskar.restaurante.DTO.CarritoDetalleDTO;
import com.eloskar.restaurante.services.DetalleCarritoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DetalleCarritoServiceTest {
    private DetalleCarritoService detalleCarritoService;

    @BeforeEach
    public void setUp() {
        detalleCarritoService = new DetalleCarritoService();
    }

    @Test
    public void testAgregarProductoAlCarritoExitoso() {
        CarritoDetalleDTO dto = new CarritoDetalleDTO();
        dto.setCarrito_id(1);
        dto.setProducto_id(1);
        dto.setCantidad(2);
        dto.setNotas("Sin cebolla");
        boolean resultado = detalleCarritoService.insertarDetalle(dto);
        Assertions.assertTrue(resultado, "El producto debe agregarse exitosamente al carrito");
    }

    @Test
    public void testAgregarProductoSinStock() {
        CarritoDetalleDTO dto = new CarritoDetalleDTO();
        dto.setCarrito_id(1);
        dto.setProducto_id(9999); // ID de producto inexistente
        dto.setCantidad(1);
        dto.setNotas("");
        boolean resultado = detalleCarritoService.insertarDetalle(dto);
        Assertions.assertFalse(resultado, "No debe agregarse un producto inexistente");
    }
} 