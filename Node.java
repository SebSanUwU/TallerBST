public class Node<E> {
    E key;
    Node<E> left, right;

    public Node(E item) {
        key = item;
        left = right = null;
    }
}
