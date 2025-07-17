import com.eloskar.restaurante.DTO.CategoriaDTO;
import com.eloskar.restaurante.DTO.ProductoDTO;
import com.eloskar.restaurante.services.ProductoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductoServiceTest {
    private ProductoService productoService;

    @BeforeEach
    public void setUp() {
        productoService = new ProductoService();
    }

    @Test
    public void testAltaProductoExitoso() {
        ProductoDTO dto = new ProductoDTO();
        CategoriaDTO categoria = new CategoriaDTO();
        dto.setNombre("jkljklkj");
        dto.setDescripcion("kjljkljkl");
        dto.setPrecio(35);
        dto.setImagen("ceviche_mixtoklkjlj.jpg");
        categoria.setNombre("Ceviches");
        dto.setCategoria(categoria);
        boolean resultado = productoService.insertarProd(dto);
        Assertions.assertTrue(resultado, "El alta de producto debe ser exitosa");
    }

    @Test
    public void testAltaProductoDatosInvalidos() {
        ProductoDTO dto = new ProductoDTO();
        CategoriaDTO categoria = new CategoriaDTO();
        dto.setNombre(""); // Nombre vacío
        dto.setDescripcion("");
        dto.setPrecio(-100); // Precio inválido
        dto.setImagen("");
        dto.setDisponible(true);

        categoria.setNombre("Gaaaa"); //No existe cat
        dto.setCategoria(categoria);
        boolean resultado = productoService.insertarProd(dto);
        Assertions.assertFalse(resultado, "El alta de producto debe fallar con datos inválidos");
    }
} 