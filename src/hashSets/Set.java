package hashSets;

public interface Set<T> {
    boolean insert(T element);
    boolean remove(T element);
    boolean member(T element);
}
