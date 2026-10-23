public class BSTBuilder {

    private BSTBuilder() {
        // Private constructor to prevent instantiation of utility class
    }

    /**
     * Builds an unbalanced (degenerate) Binary Search Tree.
     *
     * In a standard BST, inserting keys in strictly ascending sorted order causes each
     * new key to be greater than all preceding keys. As a result, every node is added
     * as the right child of the previous node, degenerating the tree into a right-leaning
     * linked list of height n - 1 (height 15 for 16 elements).
     *
     * @param bst the BinarySearchTree instance to populate
     * @return the populated BinarySearchTree instance
     */
    public static BinarySearchTree<Integer>
    buildUnbalancedBST(BinarySearchTree<Integer> bst) {
        if (bst == null) {
            bst = new BinarySearchTree<>();
        }
        // Sorted sequence of 16 values:
        int[] sortedValues = {2, 4, 5, 7, 8, 9, 12, 14, 17, 19, 22, 25, 27, 28, 33, 37};
        for (int val : sortedValues) {
            bst.put(val);
        }
        return bst;
    }

    /**
     * Builds a balanced Binary Search Tree manually.
     *
     * To achieve a balanced BST without automatic tree rebalancing (such as AVL or Red-Black rotations),
     * elements must be inserted starting with the median of the sorted range (14), followed by the medians
     * of subsequent sub-ranges in level order:
     *   - Level 0 (root): 14 (median of elements 0..15)
     *   - Level 1: 7 (median of 0..6), 25 (median of 8..15)
     *   - Level 2: 4 (median of 0..2), 9 (median of 4..6), 19 (median of 8..10), 28 (median of 12..15)
     *   - Level 3: 2, 5, 8, 12, 17, 22, 27, 33
     *   - Level 4: 37 (right child of 33)
     *
     * This produces an optimal tree height of 4 for the 16 elements.
     *
     * @param bst the BinarySearchTree instance to populate
     * @return the populated BinarySearchTree instance
     */
    public static BinarySearchTree<Integer>
    buildBalancedBSTManual(BinarySearchTree<Integer> bst) {
        if (bst == null) {
            bst = new BinarySearchTree<>();
        }
        // Level-order insertion array to construct the balanced BST:
        int[] balancedOrderValues = {14, 7, 25, 4, 9, 19, 28, 2, 5, 8, 12, 17, 22, 27, 33, 37};
        for (int val : balancedOrderValues) {
            bst.put(val);
        }
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
