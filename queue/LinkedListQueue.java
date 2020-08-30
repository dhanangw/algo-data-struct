class LinkedListQueue{
    private class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    private Node front = null;
    private Node rear = null;

    public boolean isEmpty() {
        return front == null && rear == null;
    }

    public void enqueue(int value){
        Node newNode = new Node(value);
        if (isEmpty()){
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        return;
    }

    public Node dequeue(){
        Node result = front;
        front = front.next;
        result.next = null;
        return result;
    }

    public Node top(){
        return front;
    }

    public void print(){
        Node currentNode = front;
        System.out.println("printing queue...");
        while(currentNode != null){
            System.out.println(currentNode.value);
            currentNode = currentNode.next;
        }
    }

    public static void main(String[] args){
        LinkedListQueue queue = new LinkedListQueue();
        queue.enqueue(1);
        System.out.println(queue.isEmpty());
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println("top: " + queue.top().value);
        queue.dequeue();
        queue.print();
    }

}