/**
 * 
 */
package accidentpack;

import java.time.LocalDate;

/**
 * Represents a binary search tree data structure.
 * 
 * @author Devin C
 * @version February 29, 2024
 */
public class binarySearchTree {
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
     * Updates the leftChildren and rightChildren counts for all nodes in the binary search tree.
     * This method should be called after inserting a new node.
     * 
     * @param root the root node of the binary search tree
     */
    public void updateChildren(Node root) {
        if (root != null) {
            updateChildren(root.left); // Update left subtree
            updateChildren(root.right); // Update right subtree
            root.leftChildren = countChildren(root.left); // Update leftChildren for current node
            root.rightChildren = countChildren(root.right); // Update rightChildren for current node
        }
    }

    /**
     * Counts the number of children for a given node.
     * 
     * @param node the node whose children count is to be calculated
     * @return the total number of children for the given node
     */
    private int countChildren(Node node) {
        if (node == null) {
            return 0;
        }
        // Total children count = left subtree children count + right subtree children count + 1 (for the node itself)
        return node.leftChildren + node.rightChildren + 1;
    }
    
    /**
     * Gets the root node of the binary search tree.
     * 
     * @return the root node
     */
    public Node getRoot() {
        return root;
    }
    
    /**
     * Calculates the number of records in the binary search tree
     * with a start time on or after the specified date.
     * 
     * @param date the start time to compare against
     * @return the number of records with start time on or after the specified date
     */
    public int numOnOrAfter(LocalDate date) {
        return numOnOrAfter(root, date);
    }

    /**
     * Recursive helper method to calculate the number of records in the subtree 
     * rooted at a given node with a start time on or after the specified date.
     * 
     * @param node the root of the subtree
     * @param date the start time to compare against
     * @return the number of records in the subtree with start time on or after the specified date
     */
    private int numOnOrAfter(Node node, LocalDate date) {
        if (node == null) {
            return 0;
        }
        // If the start time of the current node is on or after the specified date,
        // count the node itself and recursively count records in left and right subtrees
        if (node.data.getStartTime().isAfter(date) || node.data.getStartTime().isEqual(date)) {
            return 1 + numOnOrAfter(node.left, date) + numOnOrAfter(node.right, date);
        } else {
            // If the start time is before the specified date, only consider the right subtree
            return numOnOrAfter(node.right, date);
        }
    }
}
