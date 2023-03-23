package hashMap;

import hashSets.OHashSet;

public class OHashMap<K, V> implements Map<K, V>{

    private static final int DEFAULT_TABLE_SIZE = 101;

    private static class Node{
        Object key;
        Object value;
        Node next;

        public Node(Object key, Object value){
            this.key = key;
            this.value = value;
        }
    }

    private Node[] table;

    public OHashMap(){
        this(DEFAULT_TABLE_SIZE);
    }

    public OHashMap(int size){
        if(size < 1)
            throw new IllegalArgumentException("Initial capacity < 1!");
        table = new Node[size];
    }

    private int hash(K o){
        if(o == null)
            throw new IllegalArgumentException("Null!");
        return Math.abs(o.hashCode() % table.length);
    }

    private Node[] searchCollisionChain(K key, int hashValue){
        Node curr = table[hashValue];
        Node prev = null;
        while(curr != null){
            if(curr.key.equals(key)){
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
    public boolean insert(K key, V value) {
        int hashValue = hash(key);
        if(searchCollisionChain(key, hashValue) != null){
            return false;
        }
        Node newEl = new Node(key, value);
        newEl.next = table[hashValue];
        table[hashValue] = newEl;
        return true;
    }

    @Override
    public boolean delete(K key) {
        int hashValue = hash(key);
        Node[] n = searchCollisionChain(key, hashValue);
        if(n == null)
            return false;
        if(n[0] == table[hashValue])
            table[hashValue] = table[hashValue].next;
        else
            n[1].next = n[0].next;
        return true;
    }

    @Override
    public V get(K key) {
        int hashValue = hash(key);
        Node[] n = searchCollisionChain(key, hashValue);
        if(n == null)
            return null;
        if(n[0] != table[hashValue]) {
            n[1].next = n[0].next;
            n[0].next = table[hashValue];
            table[hashValue] = n[0];
        }
        return (V) n[0].value;
    }

    @Override
    public boolean modify(K key, V value) {
        int hashValue = hash(key);
        Node[] n = searchCollisionChain(key, hashValue);
        if(n[0] != null){
            n[0].value = value;
            return true;
        }
        return false;
    }

    public void print(){
        for(int i = 0; i < table.length; i++){
            System.out.println("Hashcode = " + i + ": ");
            Node head = table[i];
            while(head != null){
                System.out.println("[" + head.key + ": " + head.value + "]");
                head = head.next;
            }
            System.out.println();
        }
    }
}
