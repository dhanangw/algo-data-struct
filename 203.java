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

    public ListNode removeElements2ndSubmit(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode currentNode = dummy;
        while(currentNode.next != null){
            if (currentNode.next.val == val) {
                currentNode.next = currentNode.next.next;
            } else {
                currentNode = currentNode.next;        
            }
        }
        return dummy.next;
    }
}