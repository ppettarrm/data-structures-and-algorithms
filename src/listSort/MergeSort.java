package listSort;

public class MergeSort<T extends Comparable<T>>{

    public static void main(String[] args) {
        MergeSort<Integer> ms = new MergeSort<>();

        ms.add(1);
        ms.add(31);
        ms.add(11);
        ms.add(16);
        ms.add(111);
        ms.add(-5);

        ms.mergeSort();
        ms.print();
    }

    private class Node{
        T info;
        Node next;

        public Node(T info) {
            this.info = info;
        }
    }

    private Node root = null;

    public void add(T info){
        Node tmp = new Node(info);
        tmp.next = root;
        root = tmp;
    }

    public void print(){
        Node curr = root;
        while (curr != null){
            System.out.println(curr.info);
            curr = curr.next;
        }
    }

    public Node merge(Node l1, Node l2){
        Node root = null;
        if(l1.info.compareTo(l2.info) < 0){
            root = l1;
            l1 = l1.next;
        } else {
            root = l2;
            l2 = l2.next;
        }

        Node last = root;
        while(l1 != null && l2 != null){
            if(l1.info.compareTo(l2.info) < 0){
                last.next = l1;
                last = l1;
                l1 = l1.next;
            } else {
                last.next = l2;
                last = l2;
                l2 = l2.next;
            }
        }

        last.next = l1 == null ? l2 : l1;
        return root;
    }

    public void mergeSort(){
        if(root != null)
            root = mergeSort(root);
    }

    private Node mergeSort(Node start){
        if(start.next == null){
            return start;
        }

        Node l1 = start, l1End = l1;
        Node l2 = start.next, l2End = l2;

        Node curr = start.next.next;
        while(curr != null){
            l1End.next = curr;
            l1End = curr;

            curr = curr.next;
            if(curr != null){
                l2End.next = curr;
                l2End = curr;
                curr = curr.next;
            }
        }
        l1End.next = null;
        l2End.next = null;

        l1 = mergeSort(l1);
        l2 = mergeSort(l2);

        return merge(l1, l2);
    }


}
