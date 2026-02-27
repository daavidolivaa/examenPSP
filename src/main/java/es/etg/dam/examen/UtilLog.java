package es.etg.dam.examen;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class UtilLog {
    public static final String AUTENTICADOR = "Autenticador";
    public static final String AUTENTICAR_LOG = "autenticar.log";


    public static void escribirLog(String mensaje) {

        try {
            Logger logger = Logger.getLogger(AUTENTICADOR);
            FileHandler fh = new FileHandler(AUTENTICAR_LOG, false);
            SimpleFormatter formato = new SimpleFormatter();
            logger.addHandler(fh);
            fh.setFormatter(formato);
            logger.log(Level.INFO, mensaje);
            fh.close();

        } catch (IOException e) {
            throw new RuntimeException("");
        }

    }
}
