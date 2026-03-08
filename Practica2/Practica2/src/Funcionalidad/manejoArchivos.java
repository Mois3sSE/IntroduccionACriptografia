package Funcionalidad;

import java.nio.file.*;
import java.util.Arrays;
import java.io.*; 

public class manejoArchivos {
    public static byte[] leerTxt(String ruta) throws Exception {
        return Files.readAllBytes(Paths.get(ruta));
    }
    public static void crearTxt(String ruta, byte[] mensajeCifrado, byte[] vector)  {
        String rutaCifrado = ruta.replace(".txt", "_e.txt"); 
       try(FileOutputStream fos = new FileOutputStream(rutaCifrado)) {
            fos.write(vector);
            fos.write(mensajeCifrado);
            fos.close();
            
        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }
    }
    public static void crearTxt(String ruta, byte[] mensajeDescifrado)  {
        String rutaDescifrado = ruta.replace(".txt", "_d.txt"); 
       try(FileOutputStream fos = new FileOutputStream(rutaDescifrado)) {
            fos.write(mensajeDescifrado);
            fos.close();
            
        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }
    }
    public static byte[] leerCifradoV(String ruta) throws Exception{
        byte[] archivo = leerTxt(ruta); 
        byte[] vector = Arrays.copyOfRange(archivo, 0, 12);
        return vector; 
    }
    public static byte[] leerCifradoM(String ruta) throws Exception{
        byte[] archivo = leerTxt(ruta); 
        byte[] mensajeCifrado = Arrays.copyOfRange(archivo, 12, archivo.length);
        return mensajeCifrado; 
    }


}
