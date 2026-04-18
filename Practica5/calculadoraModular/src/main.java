import controlador.ControladorCalculadora;
import vista.VistaConsola;

public class main {
   
    public static void main(String[] args) {
        VistaConsola vista = new VistaConsola();
        ControladorCalculadora controlador = new ControladorCalculadora(vista); 

        controlador.iniciar();
        
    }
}
