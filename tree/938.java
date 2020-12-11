/**
 * Range Sum of BST
 * 
 * Example 1:
 * Input: root = [10,5,15,3,7,null,18], low = 7, high = 15
 * Output: 32
 * 
 * Example 2:
 * Input: root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
 * Output: 23
 * 
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
class Solution {
    int sum;

    public void preorderTraversal(TreeNode root, int low, int high){
        if (root == null){
            return;
        }

        if (low <= root.val && root.val <= high){
            this.sum += root.val;
        } 
        preorderTraversal(root.left, low, high);
        preorderTraversal(root.right, low, high);
    }
    
    
    public int rangeSumBST(TreeNode root, int low, int high) {
        this.sum = 0;
        preorderTraversal(root, low, high);
        return this.sum;
    }
}