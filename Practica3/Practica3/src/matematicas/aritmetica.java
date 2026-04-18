package matematicas; 

public class aritmetica {
    // Algoritmo de Euclides 
    public static int calcularMCD(int a, int b) {
        if (b == 0) {
            return a;
        }
        return calcularMCD(b, a % b);
    }

    // Algoritmo Extendido de Euclides
    public static int[] euclidesExtendido(int a, int b) {
        if (b == 0) {
            return new int[] { a, 1, 0 };
        }
        
        int[] resultadosPrevios = euclidesExtendido(b, a % b);
        int d = resultadosPrevios[0];
        int x1 = resultadosPrevios[1];
        int y1 = resultadosPrevios[2];
        
        int x = y1;
        int y = x1 - (a / b) * y1;
        
        return new int[] { d, x, y };
    }

    // Calculacion del inverso multiplicativo de alfa 
    public static int inversoMultiplicativo(int alfa, int n) throws IllegalArgumentException {
        int[] resultados = euclidesExtendido(alfa, n);
        int mcd = resultados[0];
        
        if (mcd != 1) {
            throw new IllegalArgumentException("El MCD de alfa y n no es 1. No existe inverso multiplicativo.");
        }
        
        int x = resultados[1];
        return ((x % n) + n) % n;
    }

}
