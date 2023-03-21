package arraySort.advancedSorts.heapSort;

public class HeapSort {
    public static <T extends Comparable<T>> void sort(T[] arr){
        int lastIndex = arr.length - 1;
        int lastParent = (lastIndex - 1) / 2;

        while(lastParent >= 0){
            makeHeap(arr, lastParent, lastIndex);
            lastParent--;
        }

        int end = lastIndex;
        while (end > 0){
            T tmp = arr[0];
            arr[0] = arr[end];
            arr[end] = tmp;
            end--;

            makeHeap(arr, 0, end);
        }
    }

    private static <T extends Comparable<T>> void makeHeap(T[] arr, int start, int end) {
        int parentIndex = start;
        boolean heapRestored = false;
        while(!heapRestored){
            int maxSonIndex = getMaxSon(arr, parentIndex, end);

            if(maxSonIndex == - 1)
                heapRestored = true;
            else{
                if(arr[parentIndex].compareTo(arr[maxSonIndex]) < 0){
                    T tmp = arr[maxSonIndex];
                    arr[maxSonIndex] = arr[parentIndex];
                    arr[parentIndex] = tmp;
                    parentIndex = maxSonIndex;
                } else{
                    heapRestored = true;
                }
            }
        }
    }

    private static <T extends Comparable<T>> int getMaxSon(T[] arr, int parentIndex, int end) {
        int son1 = 2 * parentIndex + 1;
        int son2 = 2 * parentIndex + 2;
        int maxSonIndex = - 1;

        if(son1 < end)
            maxSonIndex = son1;

        if (son2 < end) {
            if(arr[son2].compareTo(arr[son1]) > 0)
                maxSonIndex = son2;
        }
        return maxSonIndex;
    };
}
