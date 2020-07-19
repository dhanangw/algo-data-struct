public class LinkedListStack {
    private static class Node {
        int value;
        Node next;

        public Node(int value){
            this.value = value;
            this.next = null;
        }
    }
    
    static Node top = null;

    public static void push(int value) {
        // push makes new Node at the start of LinkedList
        Node newNode = new Node(value);
        newNode.next = top;
        top = newNode;
    }

    public static Node pop(){
        Node result = top;
        top = top.next;
        return result;
    }

    public static boolean isEmpty(){
        return top == null;
    }

    public static int top(){
        return top.value;
    }

    public static void print(){
        Node currentNode = top;
        while (currentNode != null){
            System.out.println(currentNode.value + " ");
            currentNode = currentNode.next;
        }
    }

    public static void main (String[] args){
        push(1);
        push(2);
        push(3);
        pop();
        print();
        System.out.println("top: " + top());
        System.out.println("is stack empty: " + isEmpty());
    }
}