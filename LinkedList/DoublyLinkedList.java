class DoublyLinkedList {
    private static class Node {
        int value;
        Node next;
        Node previous;

        public Node(int value) {
            this.value = value;
            this.next = null;
            this.previous = null;
        }
    }

    static Node head = null;

    public static void insertAtPosition(int value, int position) {
        Node newNode = new Node(value);
        if (position == 1) {
            if (head == null) {
                head = newNode;
                return;
            }
            head.previous = newNode;
            newNode.next = head;
            head = newNode;
        } else {
            Node previousNode = head;
            for (int i = 0; i < position - 2; i++) { // traverse to node before position
                if (previousNode.next == null) {
                    break;
                }
                // traverse to node position - 1
                previousNode = previousNode.next;
            }
            previousNode.next = newNode;
            newNode.previous = previousNode;
        }
        return;
    }

    public static void print() {
        Node currentNode = head;
        while (currentNode != null) {
            System.out.print(currentNode.value + " ");
            currentNode = currentNode.next;
        }
    }

    public static void reversePrint() {
        Node currentNode = head;
        // traverse to last node
        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }
        // traverse back to first node while printing value
        while (currentNode != null) {
            System.out.print(currentNode.value + " ");
            currentNode = currentNode.previous;
        }
    }

    public static void deleteNodeAtPosition(int position) {
        Node nextNode = head.next;
        if (position == 1) {
            nextNode.previous = null;
            head.next = null;
            head = nextNode;
        } else {
            Node currentNode = head;
            Node previousNode = null;
            for (int i = 0; i < position - 1; i++) { // traverse to delete position
                if (currentNode.next == null) {
                    currentNode.previous = null;
                    previousNode.next = null;
                    return;
                }
                previousNode = currentNode;
                currentNode = currentNode.next;
                nextNode = currentNode.next;
            }
            previousNode.next = nextNode;
            nextNode.previous = previousNode;
            currentNode.next = null;
            currentNode.previous = null;
        }
    }

    public static void main(String[] args) {
        insertAtPosition(1, 1);
        insertAtPosition(2, 2);
        insertAtPosition(3, 3);
        insertAtPosition(9, 9);
        // deleteNodeAtPosition(5);
        print();
        // reversePrint();

    }
}