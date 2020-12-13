import java.util.Arrays;

public class MinHeap {
    int[] heap;
    private int capacity;
    private int size = 0;

    public MinHeap(int capacity){
        this.heap = new int[capacity];
        this.capacity = capacity;
    }

    // BEGIN helper methods
    public int getParentIndex(int index){
        /**
         * returns index of parent of a node in array.
         */
        return (index - 2) / 2;
    }

    public int getLeftChildIndex(int index) {
        /**
         * returns index of left child of a node in array.
         */
        return (index * 2) + 1;
    }

    public int getRightChildIndex(int index) {
        /**
         * returns index of right child of a node in array.
         */
        return (index * 2) + 2;
    }

    public boolean hasParent(int index){
        /**
         * returns boolean indicating whether a node has a parent node or not.
         */
        return getParentIndex(index) >= 0;
    }

    public boolean hasLeftChild(int index){
        /**
         * returns boolean indicating whether a node has a left child node or not.
         */
        return getLeftChildIndex(index) < this.size;
    }

    public boolean hasRightChild(int index) {
        /**
         * returns boolean indicating whether a node has a right child node or not.
         */
        return getRightChildIndex(index) < this.size;
    }

    public int getParentValue(int index){
        /**
         * returns value of parent node.
         */
        return this.heap[getParentIndex(index)];
    }

    public int getLeftChildValue(int index) {
        /**
         * returns value of left child node.
         */
        return this.heap[getLeftChildIndex(index)];
    }

    public int getRightChildValue(int index) {
        /**
         * returns value of right child node.
         */
        return this.heap[getRightChildIndex(index)];
    }

    public void swap(int index1, int index2){
        /**
         * swaps value node of index1 with value of node of index2.
         */
        int temp = this.heap[index1];
        this.heap[index1] = this.heap[index2];
        this.heap[index2] = temp;
    }

    public void ensureExtraCapacity(){
        /**
         * increase size of array, if full.
         */
        if (this.size == this.capacity){
            this.heap = Arrays.copyOf(this.heap, this.capacity * 2);
            this.capacity *= 2;
        }
    }
    // END helper methods

    // BEGIN Heap operations.
    public int peek(){
        /**
         * return min element in heap without deleting it.
         */
        if (size == 0){
            throw new IllegalStateException();
        }

        return this.heap[0];
    }

    public int poll(){
        /**
         * return min element in heap and deletes it.
         */
        if (size == 0) {
            throw new IllegalStateException();
        }
        int item = this.heap[0];
        this.heap[0] = this.heap[this.size - 1];
        this.size -= 1;
        heapifyDown();
        return item;
    }

    public void insert(int value){
        /**
         * inserts a new node to heap.
         */
        ensureExtraCapacity();
        this.heap[this.size] = value;
        this.size += 1;
        heapifyUp();
    }

    public void heapifyUp(){
        /**
         * rearranges the nodes of heap downward.
         */
        int index = this.size - 1;
        while (hasParent(index) && getParentValue(index) > this.heap[index]){
            int parentIndex = getParentIndex(index);
            swap(parentIndex, index);
            index = parentIndex;
        }
    }

    public void heapifyDown(){
        /**
         * rearranges the nodes of heap downward.
         */
        int index = 0;
        while (hasLeftChild(index)){
            // only checks has left child,
            // because if no left child
            // then certainly there is no right child.

            // get index of smallest value between left child and right child.
            int smallerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && getRightChildValue(index) < this.heap[smallerChildIndex]){
                smallerChildIndex = getRightChildIndex(index);
            }

            if (this.heap[index] < this.heap[smallerChildIndex]){
                break;
            }
            swap(index, smallerChildIndex);
            index = smallerChildIndex;
        }
        
    }
    // END Heap operations.

    public static void main(String[] args){
        MinHeap heap = new MinHeap(10);
        heap.insert(10);
        heap.insert(15);
        heap.insert(20);
        heap.insert(17);
        System.out.println(Arrays.toString(heap.heap) + " " + heap.size);
        heap.insert(8);
        System.out.println(Arrays.toString(heap.heap) + " " + heap.size);
        int minElement = heap.poll();
        System.out.println(minElement);
        System.out.println(Arrays.toString(heap.heap) + " " + heap.size);
        heap.insert(25);
        System.out.println(Arrays.toString(heap.heap) + " " + heap.size);
    }
}
