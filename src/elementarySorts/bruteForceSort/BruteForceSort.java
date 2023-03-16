package elementarySorts.bruteForceSort;

import elementarySorts.insertionSort.InsertionSort;

import java.util.Comparator;
import java.util.Random;

public class BruteForceSort {

    public static void main(String[] args) {
        Integer[] arr = new Integer[100];
        Random rnd = new Random();
        System.out.println("Nesortirani niz: \n");
        for(int i = 1; i < arr.length + 1; i++){
            arr[i - 1] = rnd.nextInt() / 10000000;
            System.out.print(arr[i - 1] + " ");
            if(i % 10 == 0)
                System.out.println();
        }

        sort(arr);
        print(arr);
    }

    public static <T extends Comparable<T>> void sort(T[] arr){
        for(int j = 1; j < arr.length; j++){
            for(int i = 0; i < j; i++){
                if(arr[i].compareTo(arr[j]) > 0){
                    T tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
    }

    public static <T> void sort(T[] arr, Comparator<T> cmp){
        for(int j = 1; j < arr.length; j++){
            for(int i = 0; i < j; i++){
                if(cmp.compare(arr[i], arr[j]) > 0){
                    T tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
    }

    private static <T extends Comparable<T>> void print(T[] arr){
        System.out.println("Sortirani niz: \n");
        for(int i = 1; i < arr.length + 1; i++){
            System.out.print(arr[i - 1] + " ");
            if(i % 10 == 0)
                System.out.println();
        }
        System.out.println();
    }
}
