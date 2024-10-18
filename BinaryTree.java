import java.util.*;

public class BinaryTree<E extends Comparable<E>> implements Collection<E> {
    Node<E> root;

    public BinaryTree() {
        root = null;
    }

    // Métodos de la interfaz Collection

    @Override
    public boolean add(E key) {
        root = insertRec(root, key);
        return true; // Siempre devuelve true porque el árbol no tiene elementos duplicados
    }

    private Node<E> insertRec(Node<E> root, E key) {
        if (root == null) {
            root = new Node<>(key);
            return root;
        }

        if (key.compareTo(root.key) < 0) {
            root.left = insertRec(root.left, key);
        } else if (key.compareTo(root.key) > 0) {
            root.right = insertRec(root.right, key);
        }

        return root;
    }

    @Override
    public boolean remove(Object o) {
        if (!contains(o)) {
            return false;
        }
        root = deleteRec(root, (E) o);
        return true;
    }

    private Node<E> deleteRec(Node<E> root, E key) {
        if (root == null) {
            return root;
        }

        if (key.compareTo(root.key) < 0) {
            root.left = deleteRec(root.left, key);
        } else if (key.compareTo(root.key) > 0) {
            root.right = deleteRec(root.right, key);
        } else {
            // Caso 1: El nodo es una hoja (sin hijos)
            if (root.left == null && root.right == null) {
                return null;
            }

            // Caso 2: El nodo tiene un solo hijo
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Caso 3: El nodo tiene dos hijos
            root.key = minValue(root.right);
            root.right = deleteRec(root.right, root.key);
        }

        return root;
    }

    private E minValue(Node<E> root) {
        E minValue = root.key;
        while (root.left != null) {
            minValue = root.left.key;
            root = root.left;
        }
        return minValue;
    }

    @Override
    public boolean contains(Object o) {
        return search((E) o) != null;
    }

    public Node<E> search(E key) {
        return searchRec(root, key);
    }

    private Node<E> searchRec(Node<E> root, E key) {
        if (root == null) {
            return null;
        }
        if (root.key.equals(key)) {
            return root;
        }

        if (key.compareTo(root.key) < 0) {
            return searchRec(root.left, key);
        }
        return searchRec(root.right, key);
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public int size() {
        return countNodesRec(root);
    }

    private int countNodesRec(Node<E> root) {
        if (root != null) {
            return 1 + countNodesRec(root.left) + countNodesRec(root.right);
        }
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public Iterator<E> iterator() {
        return new InOrderIterator();
    }

    private class InOrderIterator implements Iterator<E> {
        private Stack<Node<E>> stack = new Stack<>();

        public InOrderIterator() {
            pushLeft(root);
        }

        private void pushLeft(Node<E> node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<E> node = stack.pop();
            pushLeft(node.right);
            return node.key;
        }
    }

    @Override
    public Object[] toArray() {
        List<E> list = new ArrayList<>();
        inorderTraversalRec(root, list);
        return list.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        List<E> list = new ArrayList<>();
        inorderTraversalRec(root, list);
        return list.toArray(a);
    }

    private void inorderTraversalRec(Node<E> root, List<E> list) {
        if (root != null) {
            inorderTraversalRec(root.left, list);
            list.add(root.key);
            inorderTraversalRec(root.right, list);
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean modified = false;
        for (E e : c) {
            if (add(e)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object e : c) {
            if (remove(e)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Set<E> set = new HashSet<>((Collection<? extends E>) c);
        boolean modified = false;
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            E element = it.next();
            if (!set.contains(element)) {
                remove(element);
                modified = true;
            }
        }
        return modified;
    }

    public void inorderTraversal() {
        inorderRec(root);
    }

    private void inorderRec(Node<E> root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.key + " ");
            inorderRec(root.right);
        }
    }

    public void preorderTraversal() {
        preorderRec(root);
    }

    private void preorderRec(Node<E> root) {
        if (root != null) {
            System.out.print(root.key + " ");
            preorderRec(root.left);
            preorderRec(root.right);
        }
    }

    public void postorderTraversal() {
        postorderRec(root);
    }

    private void postorderRec(Node<E> root) {
        if (root != null) {
            postorderRec(root.left);
            postorderRec(root.right);
            System.out.print(root.key + " ");
        }
    }

    public Node<E> findMin() {
        return findMinRec(root);
    }

    private Node<E> findMinRec(Node<E> root) {
        if (root == null)
            return null;
        if (root.left == null)
            return root;
        return findMinRec(root.left);
    }

    public Node<E> findMax() {
        return findMaxRec(root);
    }

    private Node<E> findMaxRec(Node<E> root) {
        if (root == null)
            return null;
        if (root.right == null)
            return root;
        return findMaxRec(root.right);
    }

    public int height() {
        return heightRec(root);
    }

    private int heightRec(Node<E> root) {
        if (root == null)
            return 0;
        return 1 + Math.max(heightRec(root.left), heightRec(root.right));
    }

    public boolean isBalanced() {
        return isBalancedRec(root) != -1;
    }

    private int isBalancedRec(Node<E> root) {
        if (root == null)
            return 0;
        int leftHeight = isBalancedRec(root.left);
        int rightHeight = isBalancedRec(root.right);
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        return 1 + Math.max(leftHeight, rightHeight);
    }

    public void levelorderTraversal() {
        levelorderTraversalRec(root);
    }

    private void levelorderTraversalRec(Node<E> root) {
        if (root == null) {
            return;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node<E> current = queue.poll();
            System.out.print(current.key + " ");
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
    }
}

