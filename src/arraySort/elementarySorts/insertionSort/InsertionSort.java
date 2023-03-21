package arraySort.elementarySorts.insertionSort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class InsertionSort {

    interface Insertion{
        <T extends Comparable<T>> void sort(T[] arr);
    }

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

        Insertion[] sort = new Insertion[]{
                InsertionSort::insertionSort_V1,
                InsertionSort::insertionSort_V2,
                InsertionSort::insertionSort_V3
        };

        for(Insertion i : sort){
            i.sort(arr);
            print(arr);
        }
    }

    private static <T> void print(T[] arr){
        System.out.println("Sortirani niz: \n");
        for(int i = 1; i < arr.length + 1; i++){
            System.out.print(arr[i - 1] + " ");
            if(i % 10 == 0)
                System.out.println();
        }
        System.out.println();
    }

    public static <T extends Comparable<T>> void insertionSort_V1(T[] arr){
        for(int i = 1; i < arr.length; i++){
            if(arr[i].compareTo(arr[i - 1]) < 0){
                T curr = arr[i];
                int j = i - 1;
                while (j >= 0 && arr[j].compareTo(curr) > 0){
                    arr[j + 1] = arr[j]; j--;
                }
                arr[j + 1] = curr;
            }
        }
    }

    public static <T> void insertionSort_V1(T[] arr, Comparator<T> cmp){
        for(int i = 1; i < arr.length; i++){
            if(cmp.compare(arr[i], arr[i - 1]) < 0){
                T curr = arr[i];
                int j = i - 1;
                while(j >= 0 && cmp.compare(arr[j], curr) > 0){
                    arr[j + 1] = arr[j]; j--;
                }
                arr[j + 1] = curr;
            }
        }
    }

    public static <T extends Comparable<T>> void insertionSort_V2(T[] arr){
        for(int i = 1; i < arr.length; i++){
            T curr = arr[i];
            int j = i - 1;
            while(j >= 0 && arr[j].compareTo(curr) > 0){
                arr[j + 1] = arr[j]; j--;
            }
            arr[j + 1] = curr;
        }
    }

    public static <T> void insertionSort_V2(T[] arr, Comparator<T> cmp){
        for(int i = 1; i < arr.length; i++){
            T curr = arr[i];
            int j = i - 1;
            while(j >= 0 && cmp.compare(arr[j], curr) > 0){
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = curr;
        }
    }

    public static <T extends Comparable<T>> void insertionSort_V3(T[] arr){
        int minIndex = 0;
        for(int i = 1; i < arr.length; i++){
            if(arr[minIndex].compareTo(arr[i]) > 0)
                minIndex = i;
        }

        if(minIndex != 0){
            T tmp = arr[minIndex];
            arr[minIndex] = arr[0];
            arr[0] = tmp;
        }

        for(int i = 2; i < arr.length; i++){
            T curr = arr[i];
            int j = i - 1;
            while(arr[j].compareTo(curr) > 0){
                arr[j + 1] = arr[j]; j--;
            }
            arr[j + 1] = curr;
        }
    }

    public static <T> void insertionSort_V3(T[] arr, Comparator<T> cmp){
        int minIndex = 0;
        for(int i = 1; i < arr.length; i++){
            if(cmp.compare(arr[minIndex], arr[i]) > 0)
                minIndex = i;
        }

        if(minIndex != 0){
            T tmp = arr[minIndex];
            arr[minIndex] = arr[0];
            arr[0] = tmp;
        }

        for(int i = 2; i < arr.length; i++){
            T curr = arr[i];
            int j = i - 1;
            while(cmp.compare(arr[j], curr) > 0){
                arr[j + 1] = arr[j]; j--;
            }
            arr[j + 1] = curr;
        }
    }
}


