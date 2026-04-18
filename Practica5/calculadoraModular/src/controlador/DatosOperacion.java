package controlador;

public class DatosOperacion {
    public int modulo;
    public int dimension;
    public int[][] matrizA;
    public int[][] matrizB;

    public DatosOperacion(int modulo, int dimension, int[][] matrizA, int[][] matrizB) {
        this.modulo = modulo;
        this.dimension = dimension;
        this.matrizA = matrizA;
        this.matrizB = matrizB;
    }
}
