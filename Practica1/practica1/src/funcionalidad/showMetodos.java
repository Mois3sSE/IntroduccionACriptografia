package funcionalidad;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class showMetodos {
    ArrayList<Byte> mensajeCifrado = new ArrayList<>();
    boolean inicioMensaje = false;
    int byteArmado;
    int contadorBits;

    public BufferedImage leerBmp(String ruta) throws Exception {
        return ImageIO.read(new File(ruta));
    }

    private boolean procesarBit(int bit) {

        byteArmado = (byteArmado << 1) | bit;
        contadorBits++;

        if (contadorBits == 8) {
            if (!inicioMensaje) {
                if (byteArmado == 0x02) {
                    inicioMensaje = true;
                } else {
                    throw new RuntimeException("No se encontró algun mensaje oculto.");
                }
            } else {
                if (byteArmado == 0x04) {
                    return true;
                } else {
                    mensajeCifrado.add((byte) byteArmado);
                }
            }
            byteArmado = 0;
            contadorBits = 0;
        }
        return false;
    }

    private void crearArchivo(String mensaje, String ruta) {
        try {
            File archivo = new File(ruta);
            FileWriter escritor = new FileWriter(archivo);
            BufferedWriter bufferEscritor = new BufferedWriter(escritor);
            bufferEscritor.write(mensaje);
            bufferEscritor.close();
            escritor.close();

        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }
    }

    public void descifrarMensaje(BufferedImage imagen) {
        mensajeCifrado.clear();
        byteArmado = 0;
        contadorBits = 0;
        inicioMensaje = false;

        int tempR, tempG, tempB;

        for (int y = 0; y < imagen.getHeight(); y++) {
            for (int x = 0; x < imagen.getWidth(); x++) {
                int colorPixel = imagen.getRGB(x, y);
                int rojo = (colorPixel >> 16) & 0xFF;
                int verde = (colorPixel >> 8) & 0xFF;
                int azul = colorPixel & 0xFF;
                tempR = rojo & 1;
                tempG = verde & 1;
                tempB = azul & 1;

                if (procesarBit(tempR))
                    return;
                if (procesarBit(tempG))
                    return;
                if (procesarBit(tempB))
                    return;
            }
        }
    }

    public void imprimirMensaje(String ruta) {
        byte[] mensajeDescifrado = new byte[mensajeCifrado.size()];

        for (int i = 0; i < mensajeCifrado.size(); i++) {
            mensajeDescifrado[i] = mensajeCifrado.get(i);
        }
        String mensajeStr = new String(mensajeDescifrado);
        String rutaGuardado = ruta.replace("-h.bmp", "-s.txt");
        crearArchivo(mensajeStr, rutaGuardado);

    }

}
