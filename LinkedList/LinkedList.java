
// source: mycodeschool data structure playlist on Youtube.
import java.lang.*;

class LinkedList {
    private static class Node {
        int value;
        Node next;
    }

    private static Node head;

    public static void insertToLinkedListAtTail(Node newNode) {
        if (head == null) {
            head = newNode;
        } else if (head != null) {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public static void insertToLinkedListAtPosition(int value, int position) {
        Node newNode = new Node();
        newNode.value = value;
        newNode.next = null;

        if (position == 1) {
            newNode.next = head;
            head = newNode;
            return;
        } else {
            Node current = head;
            try {
                for (int i = 0; i < position - 2; i++) {
                    // traverse to node position - 1
                    current = current.next;
                }
                newNode.next = current.next;
                current.next = newNode;
            } catch (Exception e) {
                // if position is beyond LinkedList's length + 1
                insertToLinkedListAtTail(newNode);
                return;
            }
        }
    }

    public static void deleteNodeInPosition(int position) {
        Node current = head;
        Node previous = head;
        for (int i = 0; i < position - 1; i++) {
            // traverse to position to delete
            previous = current;
            current = current.next;
        }
        if (current == head) {
            head = head.next;
            previous = null;
            return;
        }
        previous.next = current.next;
        current = null;
        return;
    }

    public static void reverseLinkedListIteration() {
        Node currentNode = head;
        Node previousNode = null;
        Node nextNode = null;
        while (currentNode != null) {
            nextNode = currentNode.next;
            currentNode.next = previousNode;
            previousNode = currentNode;
            currentNode = nextNode;
        }
        head = previousNode;
    }

    public static void reverseLinkedListRecursion(Node currentNode) {
        if (currentNode.next == null) {
            head = currentNode;
            return;
        }
        reverseLinkedListRecursion(currentNode.next);
        Node nextNode = currentNode.next;
        nextNode.next = currentNode;
        currentNode.next = null;
    }

    public static void printLinkedList() {
        Node current = head;
        StringBuilder linkedListStr = new StringBuilder("");
        while (current != null) {
            linkedListStr.append(Integer.toString(current.value) + " ");
            current = current.next;
        }
        System.out.println(linkedListStr);
    }

    public static void main(String args[]) {
        insertToLinkedListAtPosition(4, 1);
        insertToLinkedListAtPosition(2, 3);
        insertToLinkedListAtPosition(3, 2);
        insertToLinkedListAtPosition(5, 1);
        deleteNodeInPosition(4);
        deleteNodeInPosition(1);
        reverseLinkedListRecursion(head);
        printLinkedList();
    }
}