package listSort;

public class InsertionSort<T extends Comparable<T>> {

    private class Node{
        T info;
        Node next;
        public Node(T info){
            this.info = info;
        }
    }

    private Node root = null;

    public void add(T info){
        Node newElement = new Node(info);
        newElement.next = root;
        root = newElement;
    }

    public void insertionSort(){
        if(root == null || root.next == null)
            return;

        Node lastSorted = root;
        while(lastSorted.next != null){
            Node firstUnsorted = lastSorted.next;

            if(firstUnsorted.info.compareTo(lastSorted.info) >= 0)
                lastSorted = firstUnsorted;
            else if(firstUnsorted.info.compareTo(root.info) < 0){
                lastSorted.next = firstUnsorted.next;
                firstUnsorted.next = root;
                root = firstUnsorted;
            } else {
                Node curr = root, lastLeq = null;
                while (curr.info.compareTo(firstUnsorted.info) <= 0){
                    lastLeq = curr;
                    curr = curr.next;
                }
                lastSorted.next = firstUnsorted.next;
                firstUnsorted.next = lastLeq.next;
                lastLeq.next = firstUnsorted;
            }
        }
    }



}
