package binaryTree;

import org.jetbrains.annotations.NotNull;

public class BTNode<T extends Comparable<T>> implements Comparable<BTNode<T>>{
    T info;
    BTNode<T> left, right;

    public BTNode(T info) {
        this.info = info;
    }

    public BTNode(T info, BTNode<T> left, BTNode<T> right) {
        this.info = info;
        this.left = left;
        this.right  = right;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public BTNode<T> getLeft() {
        return left;
    }

    public void setLeft(BTNode<T> left) {
        this.left = left;
    }

    public BTNode<T> getRight() {
        return right;
    }

    public void setRight(BTNode<T> rightl) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "BTNode{" +
                "info=" + info +
                ", left=" + left +
                ", rightl=" + right +
                '}';
    }

    @Override
    public int compareTo(@NotNull BTNode<T> o) {
        return info.compareTo(o.info);
    }
}
