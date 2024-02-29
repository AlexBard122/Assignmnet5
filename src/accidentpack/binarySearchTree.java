/**
 * 
 */
package accidentpack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Represents a binary search tree data structure.
 * 
 * @author Devin C
 * @version February 29, 2024
 */
public class binarySearchTree implements Iterable<report> {
    /**
     * Represents a node in the binary search tree.
     */
    static class Node {
        report data;
        Node left, right;
        int leftChildren;
        int rightChildren;

        /**
         * Constructs a new node with the specified report.
         * 
         * @param report the report associated with this node
         */
        public Node(report report) {
            data = report;
            left = right = null;
            leftChildren = 0;
            rightChildren = 0;
        }
    }
    Node root;

    /**
     * Constructs an empty binary search tree.
     */
    public binarySearchTree() {
        root = null;
    }
    
    /**
     * Adds a report to the binary search tree.
     * 
     * @param report the report to be added
     */
    public void add(report report) {
        root = insert(root, report);
    }

    /**
     * Inserts a report into the binary search tree.
     * 
     * @param root the root of the current subtree
     * @param report the report to be inserted
     * @return the root of the modified subtree
     */
    private Node insert(Node root, report report) {
        if (root == null) {
            root = new Node(report);
            return root;
        }

        if (report.compareTo(root.data) < 0) {
            root.left = insert(root.left, report);
            root.leftChildren++; // Increment leftChildren for current node
        } else if (report.compareTo(root.data) >= 0) {
            root.right = insert(root.right, report);
            root.rightChildren++; // Increment rightChildren for current node
        }
        
        // Update children count for all ancestor nodes
        updateChildren(root);

        return root;
    }
    
    /**
     * Update children count for ancestor nodes
     * 
     * @param node the node whose children count is to be updated
     * @pre children are not up to date
     * @post children count is updated
     */
    private void updateChildren(Node node) {
        if (node == null) {
            return;
        }

        // Update count for left subtree
        if (node.left != null) {
            node.leftChildren = node.left.leftChildren + node.left.rightChildren;
        }

        // Update count for right subtree
        if (node.right != null) {
            node.rightChildren = node.right.leftChildren + node.right.rightChildren;
        }

        // Recursively update count for ancestor nodes
        updateChildren(node.left);
        updateChildren(node.right);
    }
    
    /**
     * Gets the root node of the binary search tree.
     * 
     * @return the root node
     */
    public Node getRoot() {
        return root;
    }
    
    @Override
    public Iterator<report> iterator() {
        return new BSTIterator(root);
    }

    /**
     * Iterator for traversing the binary search tree in-order.
     */
    private class BSTIterator implements Iterator<report> {
        private Deque<Node> stack = new ArrayDeque<>();
        private Node lastRet;

        /**
         * Constructs a new BSTIterator.
         * 
         * @param root the root node of the binary search tree
         */
        private BSTIterator(Node root) {
            pushLeftChildren(root);
        }
        
        /**
         * Pushes the left children of the given node onto the stack.
         * 
         * @param node the node whose left children are to be pushed onto the stack
         */
        private void pushLeftChildren(Node node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        /**
         * Checks if there are more elements in the tree to iterate over.
         * 
         * @return true if there are more elements, otherwise false
         */
        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /**
         * Returns the next element in the iteration.
         * 
         * @return the next element
         * @throws NoSuchElementException if there are no more elements to iterate over
         */
        @Override
        public report next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the tree.");
            }
            lastRet = stack.pop();
            if (lastRet.right != null) {
                pushLeftChildren(lastRet.right); 
            }
            return lastRet.data;
        }
    }
}
