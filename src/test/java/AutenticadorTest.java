import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import es.etg.dam.examen.BloquePartida;
import es.etg.dam.examen.ServicioAutenticacion;

public class AutenticadorTest {

    @DisplayName ("Tests Autenticacion salt")
    class ServicioAutenticacionTest {
        private ServicioAutenticacion servicio;

        @BeforeEach
        void setUp() {
            servicio = new ServicioAutenticacion();
        }

        private BloquePartida crearPartida(String nombre, String bloque) throws Exception {
            String salt = servicio.generarSalt(nombre);
            String hash = servicio.getHashConSalt(bloque, salt);
            return new BloquePartida(nombre, hash);
        }
    @Test
    void comprobarAutenticacion(@TempDir Path tempDir) throws Exception {
        Path origen = tempDir.resolve("origen.txt");
        Path destino = tempDir.resolve("partida.txt");
        Files.writeString(origen, "A");
        String log = Files.readString(Path.of("partida.log"));
        assertTrue(log.contains("Son iguales"));
        assertTrue(Files.exists(destino));

    }
    }
    
}
