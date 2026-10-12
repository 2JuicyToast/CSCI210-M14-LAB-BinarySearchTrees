public class BSTBuilder {

    private BSTBuilder() {
        // Private constructor to prevent instantiation of utility class
    }

    public static BinarySearchTree<Integer>
    buildUnbalancedBST(BinarySearchTree<Integer> bst) {
        // //TODO: Implement this method following slide directions.
        return bst;
    }

    public static BinarySearchTree<Integer>
    buildBalancedBSTManual(BinarySearchTree<Integer> bst) {
        // //TODO: Implement this method following slide directions.
        return bst;
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst1 = new BinarySearchTree<>();
        buildUnbalancedBST(bst1);
        SwingBinaryTreeDisplay.showInWindow(bst1);

        BinarySearchTree<Integer> bst2 = new BinarySearchTree<>();
        buildBalancedBSTManual(bst2);
        SwingBinaryTreeDisplay.showInWindow(bst2);

    }
}
