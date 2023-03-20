package elementarySorts.selectionSort;

import java.util.Comparator;
import java.util.Random;

public class SelectionSort {

    interface Selection{
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

        Selection[] sort = new Selection[]{
                SelectionSort::selectionSort_V1,
                SelectionSort::selectionSort_V2
        };

        for(Selection i : sort){
            i.sort(arr);
            print(arr);
        }
    }

    public static <T extends Comparable<T>> void selectionSort_V1(T[] arr){
        for(int i = arr.length - 1; i >= 1; i--){
            int maxIndex = 0;
            for(int j = 1; j <= i; j++){
                if(arr[j].compareTo(arr[maxIndex]) > 0)
                    maxIndex = j;
            }
            if(maxIndex != i){
                T tmp = arr[i];
                arr[i] = arr[maxIndex];
                arr[maxIndex] = tmp;
            }
        }
    }

    public static <T> void selectionSort_V1(T[] arr, Comparator<T> cmp){
        for(int i = arr.length - 1; i >= 1; i--){
            int maxIndex = 0;
            for(int j = 1; j <= i; j++){
                if(cmp.compare(arr[maxIndex], arr[j]) < 0)
                    maxIndex = j;
            }
            if(maxIndex != i){
                T tmp = arr[i];
                arr[i] = arr[maxIndex];
                arr[maxIndex] = tmp;
            }
        }
    }

    public static <T extends Comparable<T>> void selectionSort_V2(T[] arr){
        for(int i = 0; i < arr.length - 1; i++){
            int minIndex = i;
            for(int j = i + 1; j < arr.length; j++){
                if(arr[j].compareTo(arr[minIndex]) < 0)
                    minIndex = j;
            }
            if(minIndex != i){
                T tmp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = tmp;
            }
        }
    }

    public static <T> void selectionSort_V2(T[] arr, Comparator<T> cmp){
        for(int i = 0; i < arr.length - 1; i++){
            int minIndex = i;
            for(int j = i + 1; j < arr.length; j++){
                if(cmp.compare(arr[j], arr[minIndex]) < 0)
                    minIndex = j;
            }
            if(minIndex != i){
                T tmp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = tmp;
            }
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
}
