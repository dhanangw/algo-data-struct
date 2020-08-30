public class ArrayQueue {
    int[] array;
    int front;
    int rear;

    public ArrayQueue(int arraySize){
        array = new int[arraySize];
        front = -1;
        rear = -1;
    }

    public boolean isFull(){
        return (rear + 1) % array.length == front;
    }

    public boolean isEmpty(){
        if (front == -1 && rear == -1){
            return true;
        } else {
            return false;
        }
    }

    public void enqueue(int value){
        if (isFull()){
            return;
        } else if (isEmpty()){
            front += 1;
            rear += 1;
        } else {
            rear = (rear + 1) % array.length;
        }
        array[rear] = value;
    }

    public int dequeue(){
        int result;
        if (isEmpty()){
            return -1;
        } else if (front == rear) {
            // there is only 1 value in queue.
            result = array[front];
            front = -1;
            rear = -1;
        } else {
            result = array[front];
            front = (front + 1) % array.length;
        }
        return result;
    }

    public void printQueue(){
        for (int i = front; i <= rear; i = (i + 1) % array.length) {
            System.out.println(array[i]);
        }
    }

    public static void main(String[] args){
        ArrayQueue queue = new ArrayQueue(10);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.dequeue();
        queue.printQueue();
    }
}