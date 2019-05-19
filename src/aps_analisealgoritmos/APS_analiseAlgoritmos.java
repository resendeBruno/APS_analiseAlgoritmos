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
    public static int calls;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] vetor, vetor2 = null;
        
        printArray(mergeSort(createArray(60, 11, 99)));
        
        vetor = createArray(1000, 1, 100);
        vetor2 = new int[vetor.length];
        System.arraycopy(vetor, 0, vetor2, 0, vetor.length);
        int calls1k = mergeSort2(vetor2);
        long tempo1k = mergeSort3(vetor);
        
        vetor = createArray(2000, 1, 100);
        vetor2 = new int[vetor.length];
        System.arraycopy(vetor, 0, vetor2, 0, vetor.length);
        int calls2k = mergeSort2(vetor2);
        long tempo2k = mergeSort3(vetor);
        
        vetor = createArray(5000, 1, 100);
        vetor2 = new int[vetor.length];
        System.arraycopy(vetor, 0, vetor2, 0, vetor.length);
        int calls5k = mergeSort2(vetor2);
        long tempo5k = mergeSort3(vetor);
        
        vetor = createArray(10000, 1, 100);
        vetor2 = new int[vetor.length];
        System.arraycopy(vetor, 0, vetor2, 0, vetor.length);
        int calls10k = mergeSort2(vetor2);
        long tempo10k = mergeSort3(vetor);
        
        System.out.println("MergeSort");
        System.out.println("n = 1000 / " + calls1k + " chamadas recursivas e " + tempo1k + " ns (" + (tempo1k * 0.000001) + " ms)");
        System.out.println("n = 2000 / " + calls2k + " chamadas recursivas e " + tempo2k + " ns (" + (tempo2k * 0.000001) + " ms)");
        System.out.println("n = 5000 / " + calls5k + " chamadas recursivas e " + tempo5k + " ns (" + (tempo5k * 0.000001) + " ms)");
        System.out.println("n = 10000 / " + calls10k + " chamadas recursivas e " + tempo10k + " ns (" + (tempo10k * 0.000001) + " ms)");
    }
    
    /**
     * Ordena vetor de inteiros usando o algoritmo mergesort 
     * 
     * 
     * @param array Vetor para ser ordenado
     * 
     * @return int[] Vetor ordenado
     */
    static int[] mergeSort(int[] array) {
        return mergeSort(array, 0, (array.length - 1) );
    }
    
    /**
     * Ordena vetor de inteiros usando o algoritmo mergesort 
     * 
     * 
     * @param array Vetor para ser ordenado
     * @param start De qual posição começará a ser ordenado
     * @param end Até qual posição irá ser ordenado
     * 
     * @return int[] Vetor ordenado
     */
    static int[] mergeSort(int[] array, int start, int end) {
        if (start < end) {
            int half = (start + end) / 2;
            
            mergeSort(array, start, half);
            mergeSort(array, (half + 1), end);
            merge(array, start, half, end);
        }
        
        return array;
    }
    
    static void merge(int[] array, int start, int half, int end) {
        int lefty = half - start + 1;
        int righty = end - half;
        int[] arrayLeft = new int [lefty + 1];
        int[] arrayRight = new int [righty + 1];        
        
        for (int i = 0; i < lefty; i++) {
            arrayLeft[i] = array[start + i];
        }        
        for (int i = 0; i < righty; i++) {
            arrayRight[i] = array[half + i + 1];
        }
        
        arrayLeft[lefty] = Integer.MAX_VALUE;
        arrayRight[righty] = Integer.MAX_VALUE;        
        
        int m = 0;
        int n = 0;
        
        for (int i = start; i <= end; i++) {
            if (arrayLeft[m] < arrayRight[n]) {
                array[i] = arrayLeft[m];
                m++;
            } else {
                array[i] = arrayRight[n];
                n++;
            }
        }
    }
    
    
    
    /**
     * Ordena vetor de inteiros usando o algoritmo mergesort e calcula quantas
     * chamadas recursivas foram efetuadas
     * 
     * 
     * @param array Vetor para ser ordenado
     * 
     * @return int Número de chamadas recursivas efetuadas
     */
    static int mergeSort2(int[] array) {
        calls = 0;
        mergeSort2(array, 0, (array.length - 1) );
        return calls;
    }

    static int[] mergeSort2(int[] array, int start, int end) {
        if (start < end) {
            int half = (start + end) / 2;
            
            mergeSort2(array, start, half);
            calls++;
            mergeSort2(array, (half + 1), end);
            calls++;
            merge(array, start, half, end);
        }
        
        return array;
    }
    
    /**
     * Ordena vetor de inteiros usando o algoritmo mergesort e calcula o tempo
     * que foi necessário para ordenar
     * 
     * 
     * @param array Vetor para ser ordenado
     * 
     * @return long Tempo que levou para ordenar o vetor, em ns
     */
    static long mergeSort3(int[] array) {
        long startTime = System.nanoTime();
        mergeSort(array, 0, (array.length - 1) );
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    
    
    /**
     * Cria vetor de inteiros
     * 
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
}
