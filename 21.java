/**
 * Merge two sorted linked lists and return it as a new sorted list. The new
 * list should be made by splicing together the nodes of the first two lists.
 *
 * Example 1: 
 * Input: l1 = [1,2,4], l2 = [1,3,4] 
 * Output: [1,1,2,3,4,4]
 * 
 * Example 2:
 * Input: l1 = [], l2 = [] 
 * Output: []
 * 
 * Example 3:
 * Input: l1 = [], l2 = [0]
 * Output: [0]
 * 
 * 
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode() {} ListNode(int val) { this.val = val; } ListNode(int val,
 * ListNode next) { this.val = val; this.next = next; } }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null){
            return l2;
        } else if (l2 == null){
            return l1;
        }
        
        ListNode dummyNode = new ListNode(-1);
        ListNode prev = dummyNode;
        while(l1 != null && l2 != null){
            if (l1.val <= l2.val){
                prev.next = l1;
                l1 = l1.next;
            } else if (l2.val <= l1.val){
                prev.next = l2;   
                l2 = l2.next;
            }      
            prev = prev.next;
        }
        if (l1 != null){
            prev.next = l1;
        } else if (l2 != null){
            prev.next = l2;
        }
        return dummyNode.next;
    }
}