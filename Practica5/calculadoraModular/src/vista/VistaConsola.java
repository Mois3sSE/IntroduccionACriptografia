package vista;

import java.util.Scanner;

public class VistaConsola implements GuiInterfaz {
    Scanner scan = new Scanner(System.in);

    @Override
    public int mostrarMenuPrincipal() {
        System.out.println("Bienvenido a la Calculadora Modular");
        System.out.println("Seleccione la operación que desea realizar:");
        System.out.println("1. Suma de matrices");
        System.out.println("2. Resta de matrices");
        System.out.println("3. Multiplicación de matrices");
        System.out.println("4. Inversa de una matriz");
        System.out.println("5. Salir");
        return scan.nextInt();
    }

    @Override
    public int solicitarModulo() {
        System.out.print("Ingrese el módulo (n) a usar en las operaciones: ");
        return scan.nextInt();
    }

    @Override
    public int seleccionarDimensionDeLaMatriz() {
        System.out.print("Ingrese la dimensión de las matrices (n x n): ");
        return scan.nextInt();
    }

    @Override
    public int[][] solicitarDatosDeLaMatriz(int dimension, String nombreMatriz) {
        int[][] matriz = new int[dimension][dimension];
        System.out.println("Ingrese los elementos de la matriz " + nombreMatriz + ":");
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                System.out.print("Elemento [" + (i + 1) + "][" + (j + 1) + "]: ");
                matriz[i][j] = scan.nextInt();
            }
        }
        return matriz;
    }

    @Override
    public void mostrarResultado(int[][] resultado) {
        System.out.println("El resultado es:");
        for (int[] fila : resultado) {
            for (int elemento : fila) {
                System.out.print(elemento + " ");
            }
            System.out.println();
        }
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

}
