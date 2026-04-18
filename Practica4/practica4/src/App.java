import java.security.*;
import java.util.Base64;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048); 

            KeyPair par = keyGen.generateKeyPair();
            PrivateKey llavePrivada = par.getPrivate();
            PublicKey llavePublica = par.getPublic();

            System.out.println("--- Llave Pública ---");
            System.out.println(Base64.getEncoder().encodeToString(llavePublica.getEncoded()));
            System.out.println("\n--- Llave Privada ---");
            System.out.println(Base64.getEncoder().encodeToString(llavePrivada.getEncoded()));

            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
