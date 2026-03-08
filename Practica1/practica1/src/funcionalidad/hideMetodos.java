package funcionalidad;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.*;
import javax.imageio.ImageIO;

public class hideMetodos {

    public byte[] leerTxt(String ruta) throws Exception {
        String mensaje = "\u0002" + new String(Files.readAllBytes(Paths.get(ruta))) + "\u0004";
        return mensaje.getBytes();
    }

    public void imprimirTxt(byte[] mensaje) {
        String mensajeStr = new String(mensaje);
        System.out.println("Mensaje oculto: " + mensajeStr);
    }

    public BufferedImage leerBmp(String ruta) throws Exception {
        return ImageIO.read(new File(ruta));
    }

    private int extraerBitMensaje(int bitActual, byte[] mensaje) {
        int indiceByte = bitActual / 8;
        int posicionBit = bitActual % 8;
        byte byteActual = mensaje[indiceByte];
        int bitIncrustado = (byteActual >> (7 - posicionBit)) & 1;
        return bitIncrustado;
    }

    public void cifrarMensaje(byte[] mensaje, BufferedImage imagen) throws Exception {
        int tamano_mensaje = mensaje.length * 8;
        int bitImg = (imagen.getWidth() * imagen.getHeight()) * 3;
        int bitActual = 0;

        if (tamano_mensaje > bitImg) {
            throw new Exception("El mensaje es demasiado grande para ser ocultado en la imagen.");
        }
        for (int y = 0; y < imagen.getHeight(); y++) {
            for (int x = 0; x < imagen.getWidth(); x++) {

                int colorPixel = imagen.getRGB(x, y);
                int transparencia = (colorPixel >> 24) & 0xFF;
                int rojo = (colorPixel >> 16) & 0xFF;
                int verde = (colorPixel >> 8) & 0xFF;
                int azul = colorPixel & 0xFF;

                if (bitActual < tamano_mensaje) {
                    int bitRojo = extraerBitMensaje(bitActual, mensaje);
                    rojo = (rojo & 0xFE) | bitRojo;
                    bitActual++;
                }
                if (bitActual < tamano_mensaje) {
                    int bitVerde = extraerBitMensaje(bitActual, mensaje);
                    verde = (verde & 0xFE) | bitVerde;
                    bitActual++;
                }
                if (bitActual < tamano_mensaje) {
                    int bitAzul = extraerBitMensaje(bitActual, mensaje);
                    azul = (azul & 0xFE) | bitAzul;
                    bitActual++;
                }
                int nuevoColorPixel = (transparencia << 24) | (rojo << 16) | (verde << 8) | azul;
                imagen.setRGB(x, y, nuevoColorPixel);
                if (bitActual >= tamano_mensaje) {
                    return;
                }
            }
        }
    }

    public void guardarBmp(BufferedImage imagen, String ruta) throws Exception {
        String nuevaRuta = ruta.replace(".bmp", "-h.bmp");
        File archivoSalida = new File(nuevaRuta);

        BufferedImage imagenNueva = new BufferedImage(
                imagen.getWidth(),
                imagen.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = imagenNueva.createGraphics();
        g.drawImage(imagen, 0, 0, null);
        g.dispose();

        ImageIO.write(imagenNueva, "bmp", archivoSalida);
    }

}
