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
public class APS_analiseAlgoritmos2 {
    public static int chamadas;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] vetor, vetor2 = null;
        
        // printArray(quicksort(createArray(60, 11, 99)));
        
        vetor = createArray(1000, 1, 100);
        vetor2 = new int[vetor.length];
        System.arraycopy(vetor, 0, vetor2, 0, vetor.length);
        int calls1k = quicksort2(vetor2);
        long tempo1k = quicksort3(vetor);
        
        vetor = createArray(2000, 1, 100);
        vetor2 = new int[vetor.length];
        System.arraycopy(vetor, 0, vetor2, 0, vetor.length);
        int calls2k = quicksort2(vetor2);
        long tempo2k = quicksort3(vetor);
        
        vetor = createArray(5000, 1, 100);
        vetor2 = new int[vetor.length];
        System.arraycopy(vetor, 0, vetor2, 0, vetor.length);
        int calls5k = quicksort2(vetor2);
        long tempo5k = quicksort3(vetor);
        
        vetor = createArray(10000, 1, 100);
        vetor2 = new int[vetor.length];
        System.arraycopy(vetor, 0, vetor2, 0, vetor.length);
        int calls10k = quicksort2(vetor2);
        long tempo10k = quicksort3(vetor);
        
        System.out.println("QuickSort");
        System.out.println("n = 1000 / " + calls1k + " chamadas recursivas e " + tempo1k + " ns (" + (tempo1k * 0.000001) + " ms)");
        System.out.println("n = 2000 / " + calls2k + " chamadas recursivas e " + tempo2k + " ns (" + (tempo2k * 0.000001) + " ms)");
        System.out.println("n = 5000 / " + calls5k + " chamadas recursivas e " + tempo5k + " ns (" + (tempo5k * 0.000001) + " ms)");
        System.out.println("n = 10000 / " + calls10k + " chamadas recursivas e " + tempo10k + " ns (" + (tempo10k * 0.000001) + " ms)");
    }
    
    /**
     * Ordena um vetor usando o algoritmo QuickSort
     *
     * @param vetor Vetor que será ordenado
     */ 
    public static int[] quicksort(int[] vetor) {
        quicksort(vetor, 0, vetor.length - 1);
        
        return vetor;
    }

    /**
     * Ordena um vetor usando o algoritmo QuickSort
     *
     * @param vetor Vetor que será ordenado
     * @param esq A partir de qual posição o vetor será ordenado
     * @param dir Até qual posição o vetor será ordenado
     */
    public static int[] quicksort(int[] vetor, int esq, int dir) {
        int meio = separar(vetor, esq, dir);
        if (esq < meio - 1) {
            quicksort(vetor, esq, meio - 1);
        }
        if (meio < dir) {
            quicksort(vetor, meio, dir);
        }
        
        return vetor;
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
    
    /**
     * Imprime vetor de inteiros na tela
     * 
     * 
     * @param array Vetor de inteiros a ser impresso na tela
     */
    static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (i % 20 == 0) {
                System.out.println("");
            }
            System.out.print(array[i] + " / ");
        }
        System.out.println("");
        System.out.println("");
    }
    
    
    
    public static int quicksort2(int[] vetor) {
        chamadas = 0;
        quicksort2(vetor, 0, vetor.length - 1);
        return chamadas;
    }
    
    public static void quicksort2(int[] vetor, int esq, int dir) {
        int meio = separar(vetor, esq, dir);
        if (esq < meio - 1) {
            quicksort2(vetor, esq, meio - 1);
            chamadas++;
        }
        if (meio < dir) {
            quicksort2(vetor, meio, dir);
            chamadas++;
        }
    }
    
    public static long quicksort3(int[] vetor) {
        long startTime = System.nanoTime();
        quicksort(vetor, 0, vetor.length - 1);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
}
