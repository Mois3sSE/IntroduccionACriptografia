package Funcionalidad;

import java.nio.charset.StandardCharsets;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class motorCriptografico {
    public static SecretKey hashDeLlave(String llave) throws Exception {
        MessageDigest hasheo = MessageDigest.getInstance("SHA-256");

        byte[] llaveByte = llave.getBytes(StandardCharsets.UTF_8);
        byte[] hash = hasheo.digest(llaveByte);
        return new SecretKeySpec(hash, "AES"); 
    }
    public static byte[] creacionVector(){
        SecureRandom random = new SecureRandom();
        byte[] vector = new byte[12]; 
        random.nextBytes(vector);
        return vector;
    }
    public static byte[] cifrarMensaje(SecretKey llave, GCMParameterSpec parametros,byte[] mensaje) throws Exception {
        Cipher cifrador = Cipher.getInstance("AES/GCM/NoPadding");
        cifrador.init(Cipher.ENCRYPT_MODE, llave,parametros);
        return cifrador.doFinal(mensaje);
    }
    public static byte[] descifrarMensaje(SecretKey llave, GCMParameterSpec parametros, byte[] mensajeCifrado) throws Exception {
        Cipher descifrador = Cipher.getInstance("AES/GCM/NoPadding");
        descifrador.init(Cipher.DECRYPT_MODE, llave, parametros);
        return descifrador.doFinal(mensajeCifrado);
    }   
    
}
