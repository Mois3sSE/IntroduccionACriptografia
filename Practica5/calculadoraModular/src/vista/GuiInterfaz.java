package vista;

public interface GuiInterfaz {
    int mostrarMenuPrincipal();
    int solicitarModulo(); 
    int seleccionarDimensionDeLaMatriz();
    
    int[][] solicitarDatosDeLaMatriz(int dimension, String nombreMatriz); 

    void mostrarResultado(int[][] resultado);
    void mostrarMensaje(String mensaje);

}
