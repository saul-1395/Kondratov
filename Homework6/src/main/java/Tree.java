public interface Tree<E extends Comparable<? super E>> {

    enum TraverseMode {
        IN_ORDER,
        PRE_ORDER,
        POST_ORDER
    }

    void add(E value);

    boolean remove(E value);

    boolean find(E value);

    void display();

    boolean isEmpty();

    void traverse(TraverseMode traverseMode);
}