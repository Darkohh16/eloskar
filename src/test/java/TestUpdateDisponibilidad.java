import com.eloskar.restaurante.DAO.ProductoDAO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestUpdateDisponibilidad {
    @Test
    public void testUpdateCorrecto() {
        ProductoDAO dao = new ProductoDAO();
        int id = 1;
        boolean disponible = true;
        int resultado = dao.updateDispProd(id, disponible);
        assertEquals(1, resultado, "Deberia actualizar 1 fila");
    }

    @Test
    public void testUpdateIncorrecto() {
        ProductoDAO dao = new ProductoDAO();
        int id = -1;
        boolean disponible = true;
        assertThrows(RuntimeException.class, () -> {
            dao.updateDispProd(id, disponible);
        });
    }
}
