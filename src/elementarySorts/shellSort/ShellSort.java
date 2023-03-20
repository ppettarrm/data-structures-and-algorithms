package elementarySorts.shellSort;

public class ShellSort {

    private static int[] gaps = {701, 301, 132, 57, 23, 10, 4, 1};
    public static <T extends Comparable<T>> void sort(T[] arr){
        for(int k : gaps){
            if(k > arr.length)
                continue;
            for(int i  = 0; i < k; i++){
                for(int j = i + k; j < arr.length; j += k){
                    T curr = arr[j];
                    int prev = j - k;
                    while (prev >= i && arr[prev].compareTo(curr) > 0){
                        arr[prev + k] = arr[prev];
                        prev -= k;
                    }
                    arr[prev + k] = curr;
                }
            }
        }
    }


}
