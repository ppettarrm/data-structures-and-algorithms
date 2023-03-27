package hashSets;

public class CHashSet<T> implements Set<T> {

    private static final int DEFAULT_TABLE_SIZE = 101;
    private Object[] table;

    private Status[] status;
    private int numElements;

    public CHashSet() {
        this(DEFAULT_TABLE_SIZE);
    }

    public CHashSet(int size) {
        if (size < 1)
            throw new IllegalArgumentException("initial size < 1");
        reset(size);
    }

    private void reset(int size) {
        table = new Object[size];
        status = new Status[size];
        for (int i = 0; i < status.length; i++) {
            status[i] = Status.EMPTY;
        }
        numElements = 0;
    }

    private int hash(T o) {
        if (o == null)
            throw new IllegalArgumentException("Null!");
        return Math.abs(o.hashCode() % table.length);
    }

    private int searchCollisionChain(T element, int hashValue) {
        int curr = hashValue;
        int i = 0;
        int maxChainLength = (table.length - 1) / 2;

        while (i <= maxChainLength && status[curr] != Status.EMPTY) {
            if (status[curr] == Status.OCCUPIED && table[curr].equals(element)) {
                return curr;
            }
            i++;
            curr = (hashValue + i * i) % table.length;
        }
        return -1;
    }

    @Override
    public boolean insert(T element) {
        int hashValue = hash(element);
        int maxChainLength = (table.length - 1) / 2;
        int i = 0;
        boolean endOfChain = false;
        int firstAvailablePosition = -1;

        while (!endOfChain && i <= maxChainLength) {
            int curr = (hashValue + i * i) % table.length;
            if (status[curr] == Status.OCCUPIED) {
                if (table[curr].equals(element))
                    return false;
            } else {
                if (firstAvailablePosition == -1) {
                    firstAvailablePosition = curr;
                }

                if (status[curr] == Status.EMPTY)
                    endOfChain = true;
            }
            i++;
        }
        if (firstAvailablePosition == -1 || loadFactor() > 0.7) {
            expand();
            hashValue = hash(element);
            add(element, hashValue);
        } else {
            status[firstAvailablePosition] = Status.OCCUPIED;
            table[firstAvailablePosition] = element;
            numElements++;
        }
        return true;
    }

    private void add(T element, int hashValue) {
        boolean foundPos = false;
        int i = 0;
        while(!foundPos){
            int curr = (hashValue + i * i) % table.length;
            if(status[curr] != Status.OCCUPIED){
                status[curr] = Status.OCCUPIED;
                table[curr] = element;
                numElements++;
                foundPos = true;
            } else{
                i++;
            }
        }
    }

    private void expand() {
        int oldSize = table.length;
        int size = 2 * oldSize;
        while(!isPrime(size)){
            size++;
        }

        System.out.println("Expanding to size - " + size);

        Object[] oldTable = new Object[oldSize];
        Status[] oldStatus = new Status[oldSize];

        for(int i = 0; i < oldSize; i++){
            oldTable[i] = table[i];
            oldStatus[i] = status[i];
        }

        reset(size);

        for(int i = 0; i < oldSize; i++){
            if(oldStatus[i] == Status.OCCUPIED){
                T elem = (T) oldTable[i];
                add(elem, hash(elem));
            }
        }

        oldTable = oldStatus = null;
    }

    private boolean isPrime(int size) {
        for(int i = 2; i * i <= size; i++){
            if(size % i == 0)
                return false;
        }
        return true;
    }

    private double loadFactor() {
        return (double) numElements / (double) table.length;
    }


    @Override
    public boolean remove(T element) {
        int pos = searchCollisionChain(element, hash(element));
        if (pos < 0)
            return false;
        status[pos] = Status.DELETED;
        numElements--;

        return true;
    }

    @Override
    public boolean member(T element) {
        return searchCollisionChain(element, hash(element)) >= 0;
    }

    private enum Status {
        EMPTY,
        OCCUPIED,
        DELETED
    }

    public void print(){
        for(int i = 0; i < table.length; i++){
            if(status[i] == Status.EMPTY)
                System.out.println("[E]");
            else if(status[i] == Status.DELETED)
                System.out.println("[D]");
            else
                System.out.println("[O - " + table[i] + "]");
        }
        System.out.println();
    }

}