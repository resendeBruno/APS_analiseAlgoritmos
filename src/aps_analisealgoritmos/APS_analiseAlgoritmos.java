/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aps_analisealgoritmos;

/**
 *
 * @author bruno
 */
public class APS_analiseAlgoritmos {
    public static int chamadas;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] vetor, vetor2 = null;
        
        vetor = createArray(1000, 1, 100);
        vetor2 = new int[vetor.length];
        System.arraycopy(vetor, 0, vetor2, 0, vetor.length);
        chamadas = 0;
        quicksort2(vetor2);
        int chamadas1k = chamadas;
        long tempo1k = quicksort(vetor);
        
        vetor = createArray(2000, 1, 100);
        vetor2 = new int[vetor.length];
        System.arraycopy(vetor, 0, vetor2, 0, vetor.length);
        chamadas = 0;
        quicksort2(vetor2);
        int chamadas2k = chamadas;
        long tempo2k = quicksort(vetor);
        
        vetor = createArray(5000, 1, 100);
        vetor2 = new int[vetor.length];
        System.arraycopy(vetor, 0, vetor2, 0, vetor.length);
        chamadas = 0;
        quicksort2(vetor2);
        int chamadas5k = chamadas;
        long tempo5k = quicksort(vetor);
        
        vetor = createArray(10000, 1, 100);
        vetor2 = new int[vetor.length];
        System.arraycopy(vetor, 0, vetor2, 0, vetor.length);
        chamadas = 0;
        quicksort2(vetor2);
        int chamadas10k = chamadas;
        long tempo10k = quicksort(vetor);
        
        System.out.println("1000 - tempo " + tempo1k + " ns (" + (tempo1k * 0.000001) + " ms) e " + chamadas1k + " chamadas recursivas");
        System.out.println("2000 - tempo " + tempo2k + " ns (" + (tempo2k * 0.000001) + " ms) e " + chamadas2k + " chamadas recursivas");
        System.out.println("5000 - tempo " + tempo5k + " ns (" + (tempo5k * 0.000001) + " ms) e " + chamadas5k + " chamadas recursivas");
        System.out.println("10000 - tempo " + tempo10k + " ns (" + (tempo10k * 0.000001) + " ms) e " + chamadas10k + " chamadas recursivas");
    }
    
    /**
     * Ordena um vetor usando o algoritmo QuickSort
     *
     * Pior caso - O(n²) 
     * Melhor caso - O(n log n) 
     * Caso médio - O(n log n) 
     * Espaço necessário - O(1)
     *
     * @param vetor Vetor que será ordenado
     * 
     * @return long Tempo de execução
     */ 
    public static long quicksort(int[] vetor) {
        long startTime = System.nanoTime();
        quicksort(vetor, 0, vetor.length - 1);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    /**
     * Ordena um vetor usando o algoritmo QuickSort
     *
     * Pior caso - O(n²)
     * Melhor caso - O(n log n)
     * Caso médio - O(n log n) 
     * Espaço necessário - O(1)
     *
     * @param vetor Vetor que será ordenado
     * @param esq A partir de qual posição o vetor será ordenado
     * @param dir Até qual posição o vetor será ordenado
     */
    public static void quicksort(int[] vetor, int esq, int dir) {
        int meio = separar(vetor, esq, dir);
        if (esq < meio - 1) {
            quicksort(vetor, esq, meio - 1);
        }
        if (meio < dir) {
            quicksort(vetor, meio, dir);
        }
    }
    
    /**
     *
     * @param vetor Vetor que será separado
     * @param esq De qual posição o vetor será ordenado
     * @param dir Até qual posição o vetor será ordenado
     * 
     * @return int[] Posição em que o pivô ficou
     */
    public static int separar(int[] vetor, int esq, int dir) {
        int pivo = vetor[(esq + dir) / 2];
        while (esq < dir) {
            while (vetor[esq] < pivo) {
                esq++;
            }
            while (vetor[dir] > pivo) {
                dir--;
            }
            if (esq <= dir) {
                swap(vetor, esq, dir);
                esq++;
                dir--;
            }
        }

        return esq;
    }

    /**
     *
     * @param vetor Vetor que a troca será feita
     * @param a Posição que será trocada
     * @param b Posição que será trocada
     */
    public static void swap(int[] vetor, int a, int b) {
        int aux = vetor[a];
        vetor[a] = vetor[b];
        vetor[b] = aux;
    }
    
    /**
     * 
     * @param n Tamanho do vetor a ser criado
     * @param min Menor valor possível a ser criado no vetor
     * @param max Maior valor possível a ser criado no vetor
     * 
     * @return int[] Vetor de inteiros
     */
    static int[] createArray(int n, int min, int max) {
        int[] vetor = new int[n];
        
        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = (int) (Math.random() * (max - min + 1)) + min;
        }
        
        return vetor;
    }
    
    
    
    public static void quicksort2(int[] vetor) {
        quicksort2(vetor, 0, vetor.length - 1);
    }
    
    public static void quicksort2(int[] vetor, int esq, int dir) {
        int meio = separar2(vetor, esq, dir);
        if (esq < meio - 1) {
            quicksort2(vetor, esq, meio - 1);
        }
        if (meio < dir) {
            quicksort2(vetor, meio, dir);
        }
    }
    
    public static int separar2(int[] vetor, int esq, int dir) {
        int pivo = vetor[(esq + dir) / 2];
        while (esq < dir) {
                chamadas++;
            while (vetor[esq] < pivo) {
                esq++;
            }
            while (vetor[dir] > pivo) {
                dir--;
            }
            if (esq <= dir) {
                swap(vetor, esq, dir);
                esq++;
                dir--;
            }
        }

        return esq;
    }
}
