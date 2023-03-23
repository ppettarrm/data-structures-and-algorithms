package listSort;

import arraySort.advancedSorts.quick.Quick;

public class QuickSort<T extends Comparable<T>> {

    public static void main(String[] args) {
        QuickSort<Integer> ms = new QuickSort<>();

        ms.add(1);
        ms.add(31);
        ms.add(11);
        ms.add(16);
        ms.add(111);
        ms.add(-5);

        ms.quickSort();
        ms.print();
    }

    private class Node{
        T info;
        Node next;

        public Node(T info) {
            this.info = info;
        }
    }

    Node root = null;

    public void add(T info){
        Node tmp = new Node(info);
        tmp.next = root;
        root = tmp;
    }

    public void print(){
        Node curr = root;
        while(curr != null){
            System.out.println(curr.info);
            curr = curr.next;
        }
    }

    public void quickSort(){
        if(root != null)
            root = quickSort(root);
    }

    private Node quickSort(Node root) {
        if(root.next == null)
            return root;

        Node pivot = root;
        Node smaller = null, greater = null;

        Node curr = pivot.next;
        while(curr != null){
            Node afterCurr = curr.next;
            if(curr.info.compareTo(pivot.info) < 0){
                curr.next = smaller;
                smaller = curr;
            } else{
                curr.next = greater;
                greater = curr;
            }
            curr = afterCurr;
        }

        if(smaller != null) smaller = quickSort(smaller);
        if(greater != null) greater = quickSort(greater);

        pivot.next = greater;
        if(smaller == null) return pivot;
        else{
            Node tmp = smaller;
            while(tmp.next != null) tmp = tmp.next;
            tmp.next = pivot;
            return smaller;
        }
    }


}
