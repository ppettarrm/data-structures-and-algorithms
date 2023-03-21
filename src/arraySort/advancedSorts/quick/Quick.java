package arraySort.advancedSorts.quick;

public class Quick {
    public static <T extends Comparable<T>> void sort(T[] arr){
        sort(arr, 0, arr.length - 1);
    }

    public static <T extends Comparable<T>> void sort(T[] arr, int l, int h){
        if(l < h){
            int j = partitionHoare(arr, l, h);
            sort(arr, l, j - 1);
            sort(arr, j + 1, h);
        }
    }

    private static <T extends Comparable<T>> int partitionHoare(T[] arr, int l, int h) {
        //Hoarova sema
        T pivot = arr[l];
        int i = l + 1;
        int j = h;
        while (i <= j){
            while(i <= h && arr[i].compareTo(pivot) < 0) i++;
            while(j >= l + 1 && arr[j].compareTo(pivot) > 0) j--;
            if(i <= j){
                T tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }
        }
        T tmp = arr[l];
        arr[l] = arr[j];
        arr[j] = tmp;

        return j;
    }

    public static <T extends Comparable<T>> int partitionLomuto(T[] arr, int l, int h){
        T pivot = arr[h];
        int ltePivot = l - 1;
        for(int i = l; i < h; i++){
            if(arr[i].compareTo(pivot) <= 0){
                ltePivot++;
                T tmp = arr[ltePivot];
                arr[ltePivot] = arr[i];
                arr[i] = tmp;
            }
        }
        int placeForPivot = ltePivot + 1;
        T tmp = arr[placeForPivot];
        arr[placeForPivot] = arr[h];
        arr[h] = tmp;

        return placeForPivot;
    }

    public static <T extends Comparable<T>> void sredina(T[] arr, int l, int h){
        T pivot = arr[(l + h) / 2];
        int i = l;
        int j = h;
        while(i <= j){
            while(arr[i].compareTo(pivot) < 0) i++;
            while (arr[j].compareTo(pivot) > 0) j--;
            if(i <= j){
                T tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++; j--;
            }
        }
        if(l < j) sredina(arr, l, j);
        if(i < h) sredina(arr, i, h);
    }
}
