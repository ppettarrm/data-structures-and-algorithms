package binaryTree;

import hashSets.Set;


public class ADTSet<T extends Comparable<T>> implements Set<T> {

    private BTNode<T> root = null;

    private SearchResult search(T info) {
        BTNode<T> curr = root, parent = null;
        boolean found = false;

        while (curr != null && !found) {
            int cmp = info.compareTo(curr.info);
            if (cmp == 0)
                found = true;
            else {
                parent = curr;
                if (cmp < 0) curr = curr.getLeft();
                else curr = curr.getRight();
            }
        }

        return new SearchResult(curr, parent);
    }

    public boolean member(T element) {
        SearchResult sr = search(element);
        return sr.node != null;
    }

    @Override
    public boolean insert(T element) {
        if (root == null) {
            root = new BTNode<>(element);
            return true;
        }
        SearchResult sr = search(element);
        if (sr.node != null)
            return false;

        BTNode<T> newNode = new BTNode<>(element);
        BTNode<T> parent = sr.parent;
        if (element.compareTo(parent.getInfo()) < 0)
            parent.setLeft(newNode);
        else
            parent.setRight(newNode);
        return true;
    }

    @Override
    public boolean remove(T element) {
        SearchResult sr = search(element);
        if (sr.node == null)
            return false;
        BTNode<T> toRemove = sr.node;
        BTNode<T> parent = sr.parent;
// prvi slucaj (cvor je list)
        if (toRemove.getLeft() == null &&
                toRemove.getRight() == null) {
            removeLeaf(toRemove, parent);
        }
// drugi slucaj (cvor nema jednog sina)
        else if (toRemove.getLeft() == null ||
                toRemove.getRight() == null) {
            removeInternalWithOneChild(toRemove, parent);
        } else {
            removeInternal(toRemove, parent);
        }
        return true;
    }

    private void removeLeaf(BTNode<T> toRemove, BTNode<T> parent) {
        if (parent == null) {
            // uklanjamo korenski element
            root = null;
        } else {
            // da li je toRemove sa leve ili desne strane parent
            boolean left = parent.getLeft() == toRemove;
            if (left)
                parent.setLeft(null);
            else
                parent.setRight(null);
        }
    }

    private void removeInternalWithOneChild(
            BTNode<T> toRemove, BTNode<T> parent) {
        // child -- jedino dete toRemove cvora
        BTNode<T> child = toRemove.getLeft();
        if (child == null) {
            child = toRemove.getRight();
        }
        if (parent == null) {
            root = child;
        } else {
            boolean left = parent.getLeft() == toRemove;
            if (left) {
                parent.setLeft(child);
            } else {
                parent.setRight(child);
            }
        }
    }

    private void removeInternal(BTNode<T> toRemove, BTNode<T> parent) {
        // nadji minimum u desnom podstablu i njegovog oca
        BTNode<T> min = toRemove.getRight();
        BTNode<T> minParent = toRemove;
        while (min.getLeft() != null) {
            minParent = min;
            min = min.getLeft();
        }
        // informacija u minimumu
        T minInfo = min.getInfo();
        // izbaci min iz stabla
        if (minParent == toRemove) {
        // nije napravljen nijedan korak u levo
            minParent.setRight(min.getRight());
        } else {
        // napravljen je bar jedan korak u levo
            minParent.setLeft(min.getRight());
        }
        toRemove.setInfo(minInfo);
    }

    private class SearchResult {
        BTNode node, parent;

        public SearchResult(BTNode<T> node, BTNode<T> parent) {
            this.node = node;
            this.parent = parent;
        }
    }
}
