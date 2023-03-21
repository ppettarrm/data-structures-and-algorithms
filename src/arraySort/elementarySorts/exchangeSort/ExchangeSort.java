package arraySort.elementarySorts.exchangeSort;

import java.util.Comparator;

public class ExchangeSort {

    public static <T extends Comparable<T>> void exchangeSort_V1(T[] arr){
        //Isplivavanje maksimuma. arr[0,..., i] je nesortirani deo niza, stoga i varira od arr.len - 1 do 1
        for(int i = arr.length - 1; i >= 1; i--){
            for(int j = 0; j < i; j++){
                if(arr[j].compareTo(arr[j + 1]) > 0){
                    T tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }

    public static <T> void exchangeSort_V1(T[] arr, Comparator<T> cmp){
        for(int i = arr.length - 1; i >= 1; i--){
            for(int j = 0; j < i; j++){
                if(cmp.compare(arr[j], arr[j + 1]) > 0){
                    T tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }

    public static <T extends Comparable<T>> void exchangeSort_V2(T[] arr){
        //Isplivavanje minimuma, arr[i, ..., arr.len - 1] je nesortirani deo niza, stoga i varira od 0 do arr.len - 2
        for(int i = 0; i < arr.length - 1; i++){
            for(int j = arr.length - 1; j > i; j--){
                if(arr[j].compareTo(arr[j - 1]) < 0){
                    T tmp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = tmp;
                }
            }
        }
    }

    public static <T> void exchangeSort_V2(T[] arr, Comparator<T> cmp){
        for(int i = 0; i < arr.length - 1; i++){
            for(int j = arr.length - 1; j > i; j--){
                if(cmp.compare(arr[j], arr[j - 1]) < 0){
                    T tmp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = tmp;
                }
            }
        }
    }

}
