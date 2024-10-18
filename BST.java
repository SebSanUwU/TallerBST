
import java.util.LinkedList;
import java.util.Queue;



public class BST {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        BinaryTree treeDemoDelete = new BinaryTree();

        // 1. Insert some nodes
        tree.insert(35);
        tree.insert(25);
        tree.insert(67);
        tree.insert(11);
        tree.insert(28);
        tree.insert(66);
        tree.insert(99);

        treeDemoDelete.insert(35);
        treeDemoDelete.insert(25);
        treeDemoDelete.insert(67);
        treeDemoDelete.insert(11);
        treeDemoDelete.insert(28);
        treeDemoDelete.insert(66);
        treeDemoDelete.insert(99);
        
        // 2. Print Search
        System.out.println("\nSearch existing Node:");
        System.out.println(tree.search(66));

        System.out.println("Search not existing Node:");
        System.out.println(tree.search(1));

        
        // 4. Print inorder traversal of the tree
        System.out.println("Inorder traversal:");
        tree.inorderTraversal();

        // 4. Print preorder traversal of the tree
        System.out.println("\nPreorder traversal:");
        tree.preorderTraversal();

        // 4. Print postorder traversal of the tree
        System.out.println("\nPostorder traversal:");
        tree.postorderTraversal();

        // 5. Print Find Min
        System.out.println("\nFind Minimun:");
        System.out.println(tree.findMin().key);

        // 6. Print Find Max
        System.out.println("Find Maximum:");
        System.out.println(tree.findMax().key);

        // 7. Print Height
        System.out.println("Height:");
        System.out.println(tree.height());

        // 8. Print if the Tree is Balanced
        System.out.println("Check if the Tree is Balanced:");
        System.out.println(tree.isBalanced());

        // 9. Print Level-Order Traversal
        System.out.println("Level-Order Traversal:");
        tree.levelorderTraversal();

        // 11. Print count nodes
        System.out.println("\nCount nodes:");
        System.out.println(tree.countNodes());

        // 3. Deletion
        System.out.println("Delete Node");
        System.out.println("1. The node to be deleted is a leaf node (no children).");
        tree.delete(11);
        tree.levelorderTraversal();
        System.out.println("\n2. The node has only one child. ");
        tree.delete(25);
        tree.levelorderTraversal();
        System.out.println("\n3. The node has two children ");
        tree.delete(35);
        tree.levelorderTraversal();

        // 10. Clear
        treeDemoDelete.clear();
        
    }
}


// Node Class
class Node {
    int key;
    Node left, right;

    public Node(int item) {
        key = item;
        left = right = null;
    }
}

class BinaryTree{
    Node root;

    public BinaryTree(){
        root = null;
    }

    private Node insertRec(Node root, int key){
        if(root == null){
            root = new Node(key);
            return root;
        }

        if(key < root.key){
            root.left = insertRec(root.left, key);
        }else if (key > root.key){
            root.right = insertRec(root.right, key);
        }

        return root;
    }

    public void insert(int key){
        root = insertRec(root,key);
    }

    
    public void inorderTraversal() {
        inorderRec(root);
    }

    
    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.key + " ");
            inorderRec(root.right);
        }
    }

    public void preorderTraversal(){
        preorderRec(root);
    }

    private void preorderRec(Node root){
        if(root != null){
            System.out.print(root.key + " ");
            preorderRec(root.left);
            preorderRec(root.right);
        }
    }

    public void postorderTraversal(){
        postorderRec(root);
    }

    private void postorderRec(Node root) { 
        if (root != null) {
            postorderRec(root.left); 
            postorderRec(root.right); 
            System.out.print(root.key + " ");
        }
    }

    public Node search(int key){
        return searchRec(root,key);
    }

    private Node searchRec(Node root, int key) {
        if(root == null){
            return null;
        }
        if(root.key == key){
            return root;
        }

        if(key < root.key){
            return searchRec(root.left, key);
        }
        return searchRec(root.right, key);
    }

    
    public Node findMin() {
        return findMinRec(root);
    }

    
    private Node findMinRec(Node root) {
        if (root == null)
            return null;
        if (root.left == null)
            return root;
        return findMinRec(root.left);
    }

    public Node findMax(){
        return findMaxRec(root);
    }

    private Node findMaxRec(Node root) {
        if (root == null)
            return null;
        if (root.right == null)
            return root;
        return findMaxRec(root.right);
    }

    public int height(){
        return heightRec(root);
    }

    private int heightRec(Node root){
        if (root == null)
            return 0;
        int leftHeight = 0, rightHeight = 0;
        if(root.left != null){
            leftHeight = 1 + heightRec(root.left);
        }
        if(root.right != null){
            rightHeight = 1 + heightRec(root.right);
        }
        return Math.max(leftHeight, rightHeight);
    }

    public  boolean isBalanced(){
        int treeBalance = isBalancedRec(root);
        return treeBalance <= 1;
    }

    private int isBalancedRec(Node root) {
        if (root == null)
            return 0;
        int leftHeight = 0, rightHeight = 0;
        if(root.left != null){
            leftHeight = 1 + heightRec(root.left);
        }
        if(root.right != null){
            rightHeight = 1 + heightRec(root.right);
        }
        return rightHeight - leftHeight;
    }

    public void levelorderTraversal(){
        levelorderTraversalRec(root);
    }

    private void levelorderTraversalRec(Node root) { 
        if (root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.print(current.key + " ");
            
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
    }

    public int countNodes(){
        return countNodesRec(root);
    }

    private int countNodesRec(Node root) {
        if(root != null){
            return 1 + countNodesRec(root.left) + countNodesRec(root.right);
        }
        return 0;
    }

    // Función para eliminar un nodo con el valor especificado
    public void delete(int key) {
        if(search(key)==null){
            System.out.print("Node not found: "+key);
        }
        root = deleteRec(root, key);
    }

    // Función recursiva para eliminar un nodo
    private Node deleteRec(Node root, int key) {
        // Caso base: Si el árbol está vacío
        if (root == null) {
            return root;
        }

        // Buscar el nodo a eliminar
        if (key < root.key) {
            root.left = deleteRec(root.left, key); // Buscar en el subárbol izquierdo
        } else if (key > root.key) {
            root.right = deleteRec(root.right, key); // Buscar en el subárbol derecho
        } else {
            // Caso 1: El nodo es una hoja (sin hijos)
            if (root.left == null && root.right == null) {
                return null;
            }

            // Caso 2: El nodo tiene un solo hijo
            if (root.left == null) {
                return root.right; // Reemplazar el nodo por su hijo derecho
            } else if (root.right == null) {
                return root.left; // Reemplazar el nodo por su hijo izquierdo
            }

            // Caso 3: El nodo tiene dos hijos
            // Encontrar el sucesor in-order (el menor nodo en el subárbol derecho)
            root.key = minValue(root.right);

            // Eliminar el sucesor in-order
            root.right = deleteRec(root.right, root.key);
        }

        return root;
    }
    
    private int minValue(Node root) {
        int minValue = root.key;
        while (root.left != null) {
            minValue = root.left.key;
            root = root.left;
        }
        return minValue;
    }

    public void clear() {
        root = null; 
        System.out.println("\nEl árbol ha sido vaciado.");
    }

}