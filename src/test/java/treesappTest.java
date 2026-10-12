import com.zybooks.dsaj.treebook.Position;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class treesappTest {

    @Test
    void testBuildUnbalancedBST() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        BinarySearchTree<Integer> result = BSTBuilder.buildUnbalancedBST(bst);

        // Verify the method returns the passed tree instance
        assertSame(bst, result, "buildUnbalancedBST should return the provided BST instance");

        // Verify size
        assertEquals(16, bst.size(), "Unbalanced BST should contain 16 elements");

        // Expected sorted elements inserted in order
        int[] expectedValues = {2, 4, 5, 7, 8, 9, 12, 14, 17, 19, 22, 25, 27, 28, 33, 37};

        // Verify height: degenerate tree inserted in sorted order has height n - 1 = 15
        assertNotNull(bst.root(), "Root of the unbalanced tree should not be null");
        assertEquals(15, bst.height(bst.root()), "Height of unbalanced tree should be 15");

        // Verify structure: each node has only a right child (forming a right-leaning chain)
        Position<Integer> curr = bst.root();
        for (int i = 0; i < expectedValues.length; i++) {
            assertNotNull(curr, "Expected node at position " + i + " with element " + expectedValues[i]);
            assertEquals(expectedValues[i], curr.getElement(), "Element at node should match");
            assertNull(bst.left(curr), "Node " + expectedValues[i] + " should not have a left child");

            if (i < expectedValues.length - 1) {
                curr = bst.right(curr);
            } else {
                assertNull(bst.right(curr), "Last node " + expectedValues[i] + " should not have a right child");
            }
        }

        // Verify all elements are searchable via get()
        for (int val : expectedValues) {
            Position<Integer> found = bst.get(val);
            assertNotNull(found, "Element " + val + " should be found in the tree");
            assertEquals(val, found.getElement());
        }

        // Verify in-order traversal produces sorted order
        List<Integer> expectedList = List.of(2, 4, 5, 7, 8, 9, 12, 14, 17, 19, 22, 25, 27, 28, 33, 37);
        List<Integer> actualList = new ArrayList<>();
        for (int val : bst) {
            actualList.add(val);
        }
        assertEquals(expectedList, actualList, "In-order traversal should match expected elements");
    }

    @Test
    void testBuildBalancedBSTManual() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        BinarySearchTree<Integer> result = BSTBuilder.buildBalancedBSTManual(bst);

        // Verify the method returns the passed tree instance
        assertSame(bst, result, "buildBalancedBSTManual should return the provided BST instance");

        // Verify size
        assertEquals(16, bst.size(), "Balanced BST should contain 16 elements");

        // Verify root
        Position<Integer> root = bst.root();
        assertNotNull(root, "Root of balanced BST should not be null");
        assertEquals(14, root.getElement(), "Root element should be 14");

        // Verify height: height should be 4
        assertEquals(4, bst.height(root), "Height of manually balanced tree should be 4");

        // Level 1: Root's children
        Position<Integer> node7 = bst.left(root);
        Position<Integer> node25 = bst.right(root);
        assertNotNull(node7, "Node 14 should have a left child");
        assertEquals(7, node7.getElement(), "Left child of 14 should be 7");
        assertNotNull(node25, "Node 14 should have a right child");
        assertEquals(25, node25.getElement(), "Right child of 14 should be 25");

        // Level 2: Children of 7 and 25
        Position<Integer> node4 = bst.left(node7);
        Position<Integer> node9 = bst.right(node7);
        assertNotNull(node4, "Node 7 should have a left child");
        assertEquals(4, node4.getElement(), "Left child of 7 should be 4");
        assertNotNull(node9, "Node 7 should have a right child");
        assertEquals(9, node9.getElement(), "Right child of 7 should be 9");

        Position<Integer> node19 = bst.left(node25);
        Position<Integer> node28 = bst.right(node25);
        assertNotNull(node19, "Node 25 should have a left child");
        assertEquals(19, node19.getElement(), "Left child of 25 should be 19");
        assertNotNull(node28, "Node 25 should have a right child");
        assertEquals(28, node28.getElement(), "Right child of 25 should be 28");

        // Level 3: Children of 4, 9, 19, 28
        Position<Integer> node2 = bst.left(node4);
        Position<Integer> node5 = bst.right(node4);
        assertNotNull(node2, "Node 4 should have a left child");
        assertEquals(2, node2.getElement(), "Left child of 4 should be 2");
        assertNotNull(node5, "Node 4 should have a right child");
        assertEquals(5, node5.getElement(), "Right child of 4 should be 5");

        Position<Integer> node8 = bst.left(node9);
        Position<Integer> node12 = bst.right(node9);
        assertNotNull(node8, "Node 9 should have a left child");
        assertEquals(8, node8.getElement(), "Left child of 9 should be 8");
        assertNotNull(node12, "Node 9 should have a right child");
        assertEquals(12, node12.getElement(), "Right child of 9 should be 12");

        Position<Integer> node17 = bst.left(node19);
        Position<Integer> node22 = bst.right(node19);
        assertNotNull(node17, "Node 19 should have a left child");
        assertEquals(17, node17.getElement(), "Left child of 19 should be 17");
        assertNotNull(node22, "Node 19 should have a right child");
        assertEquals(22, node22.getElement(), "Right child of 19 should be 22");

        Position<Integer> node27 = bst.left(node28);
        Position<Integer> node33 = bst.right(node28);
        assertNotNull(node27, "Node 28 should have a left child");
        assertEquals(27, node27.getElement(), "Left child of 28 should be 27");
        assertNotNull(node33, "Node 28 should have a right child");
        assertEquals(33, node33.getElement(), "Right child of 28 should be 33");

        // Level 4: Child of 33
        assertNull(bst.left(node33), "Node 33 should not have a left child");
        Position<Integer> node37 = bst.right(node33);
        assertNotNull(node37, "Node 33 should have a right child");
        assertEquals(37, node37.getElement(), "Right child of 33 should be 37");

        // Leaves should have no children
        assertEquals(0, bst.numChildren(node2), "Node 2 should be a leaf");
        assertEquals(0, bst.numChildren(node5), "Node 5 should be a leaf");
        assertEquals(0, bst.numChildren(node8), "Node 8 should be a leaf");
        assertEquals(0, bst.numChildren(node12), "Node 12 should be a leaf");
        assertEquals(0, bst.numChildren(node17), "Node 17 should be a leaf");
        assertEquals(0, bst.numChildren(node22), "Node 22 should be a leaf");
        assertEquals(0, bst.numChildren(node27), "Node 27 should be a leaf");
        assertEquals(0, bst.numChildren(node37), "Node 37 should be a leaf");

        // Verify all elements are searchable via get()
        List<Integer> expectedList = List.of(2, 4, 5, 7, 8, 9, 12, 14, 17, 19, 22, 25, 27, 28, 33, 37);
        for (int val : expectedList) {
            Position<Integer> found = bst.get(val);
            assertNotNull(found, "Element " + val + " should be found in the tree");
            assertEquals(val, found.getElement());
        }

        // Verify in-order traversal produces sorted order
        List<Integer> actualList = new ArrayList<>();
        for (int val : bst) {
            actualList.add(val);
        }
        assertEquals(expectedList, actualList, "In-order traversal should match expected elements");
    }
}
