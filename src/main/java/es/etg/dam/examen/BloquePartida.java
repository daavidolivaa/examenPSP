package es.etg.dam.examen;

public class BloquePartida {

    private String nombre;
    private String hash;

    public BloquePartida(String nombre, String hash) {
        this.nombre = nombre;
        this.hash = hash;
    }

    public String getNombre() {
        return nombre;
    }

    public String getHash() {
        return hash;
    }

}
