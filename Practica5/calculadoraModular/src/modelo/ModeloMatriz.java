package modelo;

public class ModeloMatriz {

    private int moduloMatematico(int numero, int modulo) {
        return ((numero % modulo + modulo) % modulo);
    }

    public int[][] sumaDeMatrices(int[][] matrizA, int[][] matrizB, int modulo) {
        int[][] resultado = new int[matrizA.length][matrizA.length];

        for (int i = 0; i < matrizA.length; i++) {
            for (int j = 0; j < matrizA.length; j++) {
                resultado[i][j] = moduloMatematico(matrizA[i][j] + matrizB[i][j], modulo);
            }
        }
        return resultado;
    }

    public int[][] restaDeMatrices(int[][] matrizA, int[][] matrizB, int modulo) {
        int[][] resultado = new int[matrizA.length][matrizA.length];

        for (int i = 0; i < matrizA.length; i++) {
            for (int j = 0; j < matrizA.length; j++) {
                resultado[i][j] = moduloMatematico(matrizA[i][j] - matrizB[i][j], modulo);
            }
        }
        return resultado;
    }

    public int[][] multiplicacionDeMatrices(int[][] matrizA, int[][] matrizB, int modulo) {
        int[][] resultado = new int[matrizA.length][matrizA.length];

        for (int i = 0; i < matrizA.length; i++) {
            for (int j = 0; j < matrizA.length; j++) {
                int sumaParcial = 0;
                for (int k = 0; k < matrizA.length; k++) {
                    sumaParcial += matrizA[i][k] * matrizB[k][j];
                }
                resultado[i][j] = moduloMatematico(sumaParcial, modulo);
            }
        }
        return resultado;
    }

    public int[][] inversaMatriz(int[][] matriz, int modulo) {
        int n = matriz.length;
        int det = 0;
        int[][] matrizAdjunta;

        switch (n) {
            case 2:
                det = determinante2x2(matriz, modulo);
                matrizAdjunta = adjunta2x2(matriz, modulo);
                break;
            case 3:
                det = determinante3x3(matriz, modulo);
                matrizAdjunta = adjunta3x3(matriz, modulo);
                break;
            default:
                return null;

        }
        int invDet = inversoMultiplicativo(det, modulo);

        if (invDet == -1 || det == 0) {
            return null;
        }
        int[][] inversa = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inversa[i][j] = moduloMatematico(matrizAdjunta[i][j] * invDet, modulo);
            }
        }

        return inversa;

    }

    public int determinante2x2(int[][] matriz, int modulo) {
        int det = matriz[0][0] * matriz[1][1] - matriz[0][1] * matriz[1][0];
        return moduloMatematico(det, modulo);
    }

    public int determinante3x3(int[][] matriz, int modulo) {
        // Tapamos 0 y 0
        int[][] det1 = {
                { matriz[1][1], matriz[1][2] },
                { matriz[2][1], matriz[2][2] }
        };
        // Tapamos 0 y 1
        int[][] det2 = {
                { matriz[1][0], matriz[1][2] },
                { matriz[2][0], matriz[2][2] }
        };
        // Tapamos 0 y 2
        int[][] det3 = {
                { matriz[1][0], matriz[1][1] },
                { matriz[2][0], matriz[2][1] }
        };
        int parte1 = matriz[0][0] * determinante2x2(det1, modulo);
        int parte2 = matriz[0][1] * determinante2x2(det2, modulo);
        int parte3 = matriz[0][2] * determinante2x2(det3, modulo);

        int det = parte1 - parte2 + parte3;

        return moduloMatematico(det, modulo);
    }

    public int[][] adjunta2x2(int[][] matriz, int modulo) {
        int[][] adjunta = new int[2][2];

        adjunta[0][0] = moduloMatematico(matriz[1][1], modulo);
        adjunta[1][1] = moduloMatematico(matriz[0][0], modulo);

        adjunta[0][1] = moduloMatematico(-matriz[0][1], modulo);
        adjunta[1][0] = moduloMatematico(-matriz[1][0], modulo);

        return adjunta;
    }

    public int[][] adjunta3x3(int[][] matriz, int modulo) {
        int[][] adjunta = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int[][] subMatriz = obtenerSubmatriz3x3(matriz, i, j);
                int det = determinante2x2(subMatriz, modulo);

                if ((i + j) % 2 != 0) {
                    det = -det;
                }
                adjunta[j][i] = moduloMatematico(det, modulo);
            }
        }
        return adjunta;

    }

    public int inversoMultiplicativo(int alfa, int n) {
        int[] resultados = algortimoDeEuclides(alfa, n);
        int mcd = resultados[0];
        if (mcd != 1) {
            return -1;
        }
        int x = resultados[1];
        return moduloMatematico(x, n);

    }

    private int[] algortimoDeEuclides(int a, int b) {
        if (b == 0)
            return new int[] { a, 1, 0 };

        int[] resultadosPrevios = algortimoDeEuclides(b, a % b);
        int d = resultadosPrevios[0];
        int x1 = resultadosPrevios[1];
        int y1 = resultadosPrevios[2];

        int x = y1;
        int y = x1 - (a / b) * y1;

        return new int[] { d, x, y };
    }

    private int[][] obtenerSubmatriz3x3(int[][] matriz, int filaExcluida, int columnaExcluida) {
        int[][] subMatriz = new int[2][2];
        int f = 0;
        for (int i = 0; i < 3; i++) {
            if (i == filaExcluida)
                continue;
            int c = 0;
            for (int j = 0; j < 3; j++) {
                if (j == columnaExcluida)
                    continue;
                subMatriz[f][c] = matriz[i][j];
                c++;
            }
            f++;
        }
        return subMatriz;
    }

}
