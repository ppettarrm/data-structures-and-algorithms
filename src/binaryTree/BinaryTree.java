package binaryTree;

import java.util.LinkedList;
import java.util.Stack;

public class BinaryTree<T extends Comparable<T>>{

    private BTNode<T> root = null;

    public void setRoot(BTNode<T> root){
        this.root = root;
    }

    public boolean isEmpty(){
        return root == null;
    }

    public BTNode<T> getRoot(){ return root; }

    public int getSize(){
        return root == null ? 0 : getSize(root);
    }

    private int getSize(BTNode<T> curr){
        int ls = 0;
        BTNode<T> left = curr.getLeft();
        if(left != null)
            ls = getSize(left);

        int rs = 0;
        BTNode<T> right = curr.getRight();
        if(right != null)
            rs = getSize(right);

        return 1 + ls + rs;
    }

    public int getDepth() {
        return root == null ? -1 : getDepth(root);
    }
    private int getDepth(BTNode<T> current) {
        int leftDepth = -1;
        BTNode<T> left = current.getLeft();
        if (left != null)
            leftDepth = getDepth(left);
        int rightDepth = -1;
        BTNode<T> right = current.getRight();
        if (right != null)
            rightDepth = getDepth(right);
        if (leftDepth > rightDepth)
            return 1 + leftDepth;
        else
            return 1 + rightDepth;
    }

    public BTNode<T> dfs(T info) {
        return root != null ? dfs(root, info) : null;
    }
    private BTNode<T> dfs(BTNode<T> current, T info) {
        if (current.getInfo().equals(info)) {
            return current;
        }
        BTNode<T> left = current.getLeft();
        if (left != null) {
            BTNode<T> n = dfs(left, info);
            if (n != null) return n;
        }
        BTNode<T> right = current.getRight();
        if (right != null) {
            BTNode<T> n = dfs(right, info);
            if (n != null) return n;
        }
        return null;
    }

    public BTNode<T> bfs(T info) {
        if (root == null) return null;
        LinkedList<BTNode<T>> queue = new LinkedList<BTNode<T>>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            BTNode<T> f = queue.removeFirst();
            if (f.getInfo().equals(info))
                return f;
            BTNode<T> left = f.getLeft();
            if (left != null) {
                queue.addLast(left);
            }
            BTNode<T> right = f.getRight();
            if (right != null) {
                queue.addLast(right);
            }
        }
        return null;
    }

    public BTNode<T> dfsIter(T info) {
        if (root == null)
            return null;
        Stack<BTNode<T>> stack = new Stack<BTNode<T>>();
        stack.push(root);
        while (!stack.isEmpty()) {
            BTNode<T> f = stack.pop();
            if (f.getInfo().equals(info))
                return f;
            BTNode<T> right = f.getRight();
            if (right != null) {
                stack.push(right);
            }
            BTNode<T> left = f.getLeft();
            if (left != null) {
                stack.push(left);
            }
        }
        return null;
    }

    public void preOrder() {
        preOrder(root);
    }
    private void preOrder(BTNode<T> current) {
        if (current != null) {
            System.out.println("Radim nesto nad: " + current);
            preOrder(current.getLeft());
            preOrder(current.getRight());
        }
    }

    public void inOrder() {
        inOrder(root);
    }
    private void inOrder(BTNode<T> current) {
        if (current != null) {
            inOrder(current.getLeft());
            System.out.println("Radim nesto nad: " + current);
            inOrder(current.getRight());
        }
    }

    public void postOrder() {
        postOrder(root);
    }
    private void postOrder(BTNode<T> current) {
        if (current != null) {
            postOrder(current.getLeft());
            postOrder(current.getRight());
            System.out.println("Radim nesto nad: " + current);
        }
    }
}
