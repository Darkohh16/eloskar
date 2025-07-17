import com.eloskar.restaurante.DTO.UsuarioDTO;
import com.eloskar.restaurante.services.UsuarioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UsuarioServiceTest {
    private UsuarioService usuarioService;

    @BeforeEach
    public void setUp() {
        usuarioService = new UsuarioService();
    }

    @Test
    public void testRegistroUsuarioExitoso() {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setDni("12345678");
        dto.setCel("987654321");
        dto.setNombre("Juan Perez");
        dto.setCorreo("juan@mail.com");
        dto.setPassword("password123");
        boolean resultado = usuarioService.insertarU(dto);
        Assertions.assertTrue(resultado, "El registro debe ser exitoso");
    }

    @Test
    public void testRegistroUsuarioDatosInvalidos() {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setDni(""); // Dato inválido
        dto.setCel("");
        dto.setNombre("");
        dto.setCorreo("");
        dto.setPassword("");
        boolean resultado = usuarioService.insertarU(dto);
        Assertions.assertFalse(resultado, "El registro debe fallar con datos inválidos");
    }

    @Test
    public void testObtenerRolAdmin() {
        int idUser = 1;
        String rol = usuarioService.obtenerRol(idUser);
        Assertions.assertEquals("admin", rol, "El usuario debe tener rol admin");
    }

    @Test
    public void testObtenerRolCliente() {
        int idUser = 2;
        String rol = usuarioService.obtenerRol(idUser);
        Assertions.assertEquals("cliente", rol, "El usuario debe tener rol cliente");
    }
} 