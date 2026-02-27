package es.etg.dam.examen;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

@Getter
public class ServicioAutenticacion {
    public static final String ERROR_LOG_PARTIDA = "Partida no existe";
    private static final String AUTENTICAR = "autenticar";
    public static final String EXITO = "EXITO";
    public static final String FALLO = "FALLO";
    public static final String DOS_PUNTOS = ":";

    private final Map<String, BloquePartida> partida = new HashMap<>();

    public String generarSalt(String nombre) {
        return UtilHash.generarHash(nombre.getBytes(), UtilHash.MD5);
    }

    public String getHashConSalt(String bloque, String salt) {
        return UtilHash.generarHash((bloque + salt).getBytes(), UtilHash.SHA256);
    }

    public void registrar(BloquePartida partida) {
        this.partida.put(partida.getNombre(), partida);

    }

    public boolean autenticar(String nombre, String bloque) throws Exception {

        BloquePartida datos = partida.get(nombre);
        if (datos == null) {
            UtilLog.escribirLog(ERROR_LOG_PARTIDA + nombre);
            return false;
        }

        String salt = generarSalt(nombre);
        String hash = getHashConSalt(bloque, salt);
        boolean ok = datos.getHash().contentEquals(hash);
        UtilLog.escribirLog(AUTENTICAR + nombre + DOS_PUNTOS + (ok ? EXITO : FALLO));
        return ok;

    }
}
