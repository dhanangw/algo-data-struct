/**
 * 203. Remove Linked List Elements 
 * 
 * Input: 1->2->6->3->4->5->6, val = 6 
 * Output:
 * 1->2->3->4->5 
 * 
 * Definition for singly-linked list. 
 * public class ListNode { 
 *   int val; 
 *   ListNode next; 
 *   ListNode() {} 
 *   ListNode(int val) { 
 *     this.val = val; 
 *   }
 *   ListNode(int val, ListNode next) {
 *     this.val = val; 
 *     this.next = next; 
 *   } 
 * }
 */
class 203 {
    public ListNode removeElements1stSubmit(ListNode head, int val) {
        ListNode currentNode = head;
        ListNode previousNode = null;
        while(currentNode != null){
            if (currentNode.val == val) {
                if (currentNode == head){
                    head = currentNode.next;
                } else {
                    previousNode.next = currentNode.next;
                    currentNode = previousNode.next;
                    continue;
                }
            }
            previousNode = currentNode;
            currentNode = currentNode.next;    
        }
        return head;
    }
}