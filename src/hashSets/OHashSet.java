package hashSets;

public class OHashSet<T extends Comparable<T>> implements Set<T>{

    public static void main(String[] args) {
        OHashSet<Integer> set = new OHashSet<Integer>(5);
        for (int i = 0; i < 24; i++) {
            set.insert(i);
        }
        set.print();
        if (set.insert(10))
            System.out.println("10 ubacen u skup");
        else
            System.out.println("10 je vec u skupu");
        System.out.println(
                "Member(10) - " + set.member(10) +
                        ", Member(34) - " + set.member(34)
        );
        System.out.println("Remove(22) - " + set.remove(22));
        System.out.println("Member(22) - " + set.member(22));
        set.print();
    }

    private static final int DEFAULT_TABLE_SIZE = 101;

    private static class Node{
        Object value;
        Node next;
        public Node(Object value){
            this.value = value;
        }
    }

    private Node[] table;

    public OHashSet(int initialSize) {
        if(initialSize < 1)
            throw new IllegalArgumentException("Initial size < 1!!");
        table = new Node[initialSize];
    }
    public OHashSet(){
        this(DEFAULT_TABLE_SIZE);
    }

    private int hash(T o){
        if(o == null)
            throw new IllegalArgumentException("Null!");
        return Math.abs(o.hashCode() % table.length);
    }

    private Node[] searchColissionChain(T element, int hashValue){
        Node curr = table[hashValue];
        Node prev = null;
        while(curr != null){
            if(curr.value.equals(element)){
                Node[] ret = new Node[2];
                ret[0] = curr;
                ret[1] = prev;
                return ret;
            }
            prev = curr;
            curr = curr.next;
        }
        return null;
    }

    @Override
    public boolean insert(T element) {
        int hashValue = hash(element);
        Node[] n = searchColissionChain(element, hashValue);
        if(n != null)
            return false;

        Node newElement = new Node(element);
        newElement.next = table[hashValue];
        table[hashValue] = newElement;
        return true;
    }

    @Override
    public boolean remove(T element) {
        int hashValue = hash(element);
        Node[] n = searchColissionChain(element, hashValue);
        if(n == null)
            return false;
        if(n[0] == table[hashValue])
            table[hashValue] = table[hashValue].next;
        else
            n[1].next = n[0].next;
        return true;
    }

    public void print(){
        for(int i = 0; i < table.length; i++){
            System.out.println("Hashcode = " + i + ": ");
            Node head = table[i];
            if(head == null){
                System.out.println("Empty!");
            } else{
                while(head != null){
                    System.out.println("[" + head.value + "]");
                    head = head.next;
                }
                System.out.println();
            }
        }
    }

    @Override
    public boolean member(T element) {
        return searchColissionChain(element, hash(element)) != null;
    }
}
