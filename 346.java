/***
 * Moving Average from Data Stream 
 * 
 * Given a stream of integers and a window size,
 * calculate the moving average of all integers
 * in the sliding window.
 * 
 * Example:
 *  MovingAverage m = new MovingAverage(3);
 *  m.next(1) = 1
 *  m.next(10) = (1 + 10) / 2
 *  m.next(3) = (1 + 10 + 3) / 3
 *  m.next(5) = (10 + 3 + 5) / 3
*/ 

class MovingAverage {
    int[] queue;
    int front;
    int back;
    double sum = 0.0;
    int numOfElements = 0;

    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        this.queue = new int[size];
        this.front = -1;
        this.back = -1;
    }
    
    public void enqueue(int val){
        if (this.back == -1){
            this.front = (this.front + 1) % this.queue.length;    
        }
        this.back = (this.back + 1) % this.queue.length;
        this.queue[this.back] = val;
        this.sum += val;
        if (this.numOfElements < this.queue.length) {
            this.numOfElements += 1;    
        }   
    }
    
    public void dequeue(){
        this.sum -= this.queue[this.front];
        this.front = (this.front + 1 ) % this.queue.length;
        if (this.numOfElements == 1){
            this.front = -1;
            this.back = -1;
        }
        this.numOfElements -= 1;
    }
    
    public double next(int val) {
        if (this.numOfElements == this.queue.length) {
            dequeue();
        } 
        enqueue(val);
        double movingAverage = this.sum / this.numOfElements;
        return movingAverage;
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */