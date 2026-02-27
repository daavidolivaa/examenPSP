package es.etg.dam.examen;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

@Getter
public class ServicioAutenticacion {

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
            UtilLog.escribirLog("Partida no existe" + nombre);
            return false;
        }

        String salt = generarSalt(nombre);
        String hash = getHashConSalt(bloque, salt);
        boolean ok = datos.getHash().contentEquals(hash);
        UtilLog.escribirLog("autenticar" + nombre + ":" + (ok ? "EXITO" : "FALLO"));
        return ok;

    }
}
