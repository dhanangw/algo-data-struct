
/**
 * Increasing Order Search Tree
 * Given the root of a binary search tree,
 * rearrange the tree in in-order so that the leftmost node in the tree is now the root of the tree,
 * and every node has no left child and only one right child.
 * 
 * 
 * Example 1:
 * Input: root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
 * Output: [1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
 * 
 * Example 2:
 * Input: root = [5,1,7]
 * Output: [1,null,5,null,7]
 */


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
import java.util.ArrayList;

class Solution {
    ArrayList<Integer> nodeValueList = new ArrayList<Integer>();

    public TreeNode increasingBST(TreeNode root) {
        // in order traversal, put all visited node value in a list.
        inorder(root);

        // loop through list and connect each node.
        TreeNode result = new TreeNode(-1);
        TreeNode currentNode = result;
        for (int nodeValue : this.nodeValueList) {
            currentNode.right = new TreeNode(nodeValue);
            currentNode = currentNode.right;
        }

        return result.right;
    }

    public void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        increasingBST(root.left);
        this.nodeValueList.add(root.val);
        increasingBST(root.right);
    }
}