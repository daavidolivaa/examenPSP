package es.etg.dam.examen;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UtilHash {

    public static final String SHA256 = "SHA-256";
    public static final String MD5 = "MD5";
    public static final String ERROR_LOG = "Algoritmo no encontrado";
    public static final Integer MAX_LENGTH = 1;


    
    public static String generarHash(byte[] contenido, String algotitmo) {
        MessageDigest mg;

        try {
            mg = MessageDigest.getInstance(algotitmo);
            return bytesToHex(mg.digest(contenido));
        } catch (NoSuchAlgorithmException e) {
            UtilLog.escribirLog(ERROR_LOG);
            throw new RuntimeException(ERROR_LOG);
        }
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == MAX_LENGTH) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
