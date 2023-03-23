package priorityQueue;


import java.util.ArrayList;
import java.util.NoSuchElementException;

interface PriorityQueue<T extends Comparable<T>>{
    void insert(T info);
    T max();
    T delMax();
    boolean isEmpty();
    int size();
}


public class PQ<T extends Comparable<T>> implements PriorityQueue<T> {

    private static final int DEFAULT_INITIAL_CAPACITY = 100;
    private ArrayList<T> queue;

    public PQ(int initialCapacity){
        if(initialCapacity < 1)
            throw new IllegalArgumentException("Initial capacity <= 0!");
        queue = new ArrayList<>(initialCapacity);
    }

    public PQ(){
        this(DEFAULT_INITIAL_CAPACITY);
    }

    @Override
    public void insert(T info) {
        queue.add(info);
        restoreHeapProperty(queue.size() - 1);
    }

    private void restoreHeapProperty(int sonIndex) {
        boolean heapRestored = false;
        int parentIndex = (sonIndex - 1) / 2;
        while (!heapRestored && sonIndex > 0) {
            T parent = queue.get(parentIndex);
            T son = queue.get(sonIndex);

            if (parent.compareTo(son) > 0)
                heapRestored = true;
            else{
                swap(sonIndex, parentIndex);
                sonIndex = parentIndex;
                parentIndex = (sonIndex - 1) / 2;
            }
        }
    }

    private void swap(int son, int parent){
        T a = queue.get(son);
        queue.set(son, queue.get(parent));
        queue.set(parent, a);
    }


    @Override
    public T max() {
        if(isEmpty())
            throw new NoSuchElementException("Empty queue!");
        return queue.get(0);
    }

    @Override
    public T delMax() {
        if(isEmpty())
            throw new NoSuchElementException("Empty queue!");
        T res = queue.get(0);
        swap(0, size() - 1);
        queue.remove(size() - 1);

        restoreHeapProperty();
        return res;
    }

    private void restoreHeapProperty() {
        boolean heapRestored = false;
        int parentIndex = 0;
        while(!heapRestored){
            int maxSonIndex = getMaxSon(parentIndex);
            if(maxSonIndex == - 1)
                heapRestored = true;
            else{
                T parent = queue.get(parentIndex);
                T maxSon = queue.get(maxSonIndex);
                if(parent.compareTo(maxSon) > 0)
                    heapRestored = true;
                else{
                    swap(parentIndex, maxSonIndex);
                    parentIndex = maxSonIndex;
                }
            }
        }


    }

    private int getMaxSon(int parentIndex) {
        int son1Index = 2 * parentIndex + 1;
        int son2Index = 2 * parentIndex + 2;
        int maxSonIndex = -1;

        if(son1Index < queue.size())
            maxSonIndex = son1Index;

        if(son2Index < queue.size()){
            T s1 = queue.get(son1Index);
            T s2 = queue.get(son2Index);
            if(s2.compareTo(s1) > 0)
                maxSonIndex = son2Index;
        }

        return maxSonIndex;
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public int size() {
        return queue.size();
    }
}
