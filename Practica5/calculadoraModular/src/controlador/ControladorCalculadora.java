package controlador;

import vista.GuiInterfaz;
import modelo.ModeloMatriz;

public class ControladorCalculadora {
    private GuiInterfaz vista;
    private ModeloMatriz modelo = new ModeloMatriz();

    public ControladorCalculadora(GuiInterfaz vista) {
        this.vista = vista;
    }

    public void iniciar() {
        int opcion;
        do {
            opcion = vista.mostrarMenuPrincipal();
            procesarOpcion(opcion);
        } while (opcion != 5);
    }

    private void procesarOpcion(int opcion) {
        DatosOperacion datosOperacion;

        switch (opcion) {
            case 1: // Suma de matrices
                datosOperacion = ingresoDeDatos();
                int[][] resultadoSuma = modelo.sumaDeMatrices(datosOperacion.matrizA, datosOperacion.matrizB,
                        datosOperacion.modulo);
                vista.mostrarResultado(resultadoSuma);
                break;
            case 2: // Resta de matrices
                datosOperacion = ingresoDeDatos();
                int[][] resultadoResta = modelo.restaDeMatrices(datosOperacion.matrizA, datosOperacion.matrizB,
                        datosOperacion.modulo);
                vista.mostrarResultado(resultadoResta);
                break;
            case 3: // Multiplicación de matrices
                datosOperacion = ingresoDeDatos();
                int[][] resultadoMultiplicacion = modelo.multiplicacionDeMatrices(datosOperacion.matrizA,
                        datosOperacion.matrizB, datosOperacion.modulo);
                vista.mostrarResultado(resultadoMultiplicacion);
                break;
            case 4: // Inversa de una matriz
                datosOperacion = ingresoDeDatosInversa();
                int[][] resultadoInversa = modelo.inversaMatriz(datosOperacion.matrizA, datosOperacion.modulo);
                if (resultadoInversa == null) {
                    vista.mostrarMensaje("La matriz no tiene inversa");
                } else {
                    vista.mostrarResultado(resultadoInversa);
                }
                break;
            case 5: // Salir
                    vista.mostrarMensaje("Saliendo ..... ");
                System.exit(0);
                break;

            default:
                vista.mostrarMensaje("Opcion no valida.\nSelecciona una opcion valida");
                break;
        }
    }

    private DatosOperacion ingresoDeDatos() {
        int modulo = vista.solicitarModulo();
        int dimension = vista.seleccionarDimensionDeLaMatriz();
        int[][] matrizA = vista.solicitarDatosDeLaMatriz(dimension, "A");
        int[][] matrizB = vista.solicitarDatosDeLaMatriz(dimension, "B");
        return new DatosOperacion(modulo, dimension, matrizA, matrizB);
    }

    private DatosOperacion ingresoDeDatosInversa() {
        int modulo = vista.solicitarModulo();
        int dimension = vista.seleccionarDimensionDeLaMatriz();
        int[][] matrizA = vista.solicitarDatosDeLaMatriz(dimension, "A");
        return new DatosOperacion(modulo, dimension, matrizA, null);
    }

}
