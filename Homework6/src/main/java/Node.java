import java.util.Objects;

public class Node<E extends Comparable<? super E>> {

    private final E value;

    private Node<E> leftChild;
    private Node<E> rightChild;
    private int level;

    public Node(E value) {
        this.value = value;
    }

    public int getLevel(){
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public E getValue() {
        return value;
    }

    public Node<E> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node<E> leftChild) {
        this.leftChild = leftChild;
    }

    public Node<E> getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node<E> rightChild) {
        this.rightChild = rightChild;
    }

    public boolean shouldLeftChild(E value) {
        if ( value.compareTo(getValue()) < 0 ) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node<?> node = (Node<?>) o;
        return Objects.equals(value, node.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}