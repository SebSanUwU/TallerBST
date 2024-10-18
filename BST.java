public class BST {
    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>();
        BinaryTree<Integer> treeDemoDelete = new BinaryTree<>();

        // 1. Insert some nodes
        tree.add(35);
        tree.add(25);
        tree.add(67);
        tree.add(11);
        tree.add(28);
        tree.add(66);
        tree.add(99);

        treeDemoDelete.add(35);
        treeDemoDelete.add(25);
        treeDemoDelete.add(67);
        treeDemoDelete.add(11);
        treeDemoDelete.add(28);
        treeDemoDelete.add(66);
        treeDemoDelete.add(99);

        // 2. Print Search
        System.out.println("\nSearch existing Node:");
        System.out.println(tree.contains(66)); // true

        System.out.println("Search not existing Node:");
        System.out.println(tree.contains(1)); // false

        // 4. Print inorder traversal of the tree
        System.out.println("Inorder traversal:");
        tree.inorderTraversal(); // Debe imprimir: 11 25 28 35 66 67 99
        System.out.println();

        // 4. Print preorder traversal of the tree
        System.out.println("\nPreorder traversal:");
        tree.preorderTraversal(); // Debe imprimir: 35 25 11 28 67 66 99
        System.out.println();

        // 4. Print postorder traversal of the tree
        System.out.println("\nPostorder traversal:");
        tree.postorderTraversal(); // Debe imprimir: 11 28 25 66 99 67 35
        System.out.println();

        // 5. Print Find Min
        System.out.println("\nFind Minimum:");
        System.out.println(tree.findMin().key); // 11

        // 6. Print Find Max
        System.out.println("Find Maximum:");
        System.out.println(tree.findMax().key); // 99

        // 7. Print Height
        System.out.println("Height:");
        System.out.println(tree.height()); // 4

        // 8. Print if the Tree is Balanced
        System.out.println("Check if the Tree is Balanced:");
        System.out.println(tree.isBalanced()); // true (o false dependiendo de los valores)

        // 9. Print Level-Order Traversal
        System.out.println("Level-Order Traversal:");
        tree.levelorderTraversal(); // Debe imprimir: 35 25 67 11 28 66 99
        System.out.println();

        // 11. Print count nodes
        System.out.println("\nCount nodes:");
        System.out.println(tree.size()); // 7

        // 3. Deletion
        System.out.println("Delete Node");
        System.out.println("1. The node to be deleted is a leaf node (no children). -> 11");
        tree.remove(11);
        tree.levelorderTraversal(); // Debe imprimir: 35 25 67 28 66 99
        System.out.println();

        System.out.println("\n2. The node has only one child. -> 25 ");
        tree.remove(25);
        tree.levelorderTraversal(); // Debe imprimir: 35 67 28 66 99
        System.out.println();

        System.out.println("\n3. The node has two children -> 35");
        tree.remove(35);
        tree.levelorderTraversal(); // Debe imprimir: 66 28 67 99
        System.out.println();

        // 10. Clear
        treeDemoDelete.clear();
        System.out.println("Tree Demo Deleted: Size after clear = " + treeDemoDelete.size()); // 0
    }
}



