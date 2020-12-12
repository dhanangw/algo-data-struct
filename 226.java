/**
 * Invert Binary Tree
 * 
 * Example:
 * Input:
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 
 * Output:
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 */

 /**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode() {} TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left
 * = left; this.right = right; } }
 */
class Solution {
    public TreeNode reverse(TreeNode node) {
        if (node != null && node.left != null && node.right != null) {
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
        } else if (node != null && node.left == null) {
            node.left = node.right;
            node.right = null;
        } else if (node != null && node.right == null) {
            node.right = node.left;
            node.left = null;
        }
        return node;
    }

    public TreeNode invertTree(TreeNode root) {
        if (root != null) {
            root.left = invertTree(root.left);
            root.right = invertTree(root.right);
        }
        return reverse(root);
    }
}