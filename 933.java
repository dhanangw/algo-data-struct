/**
 * Number of Recent Calls.
 * 
 * Write a class RecentCounter to count recent requests.
 * 
 * It has only one method: ping(int t), where t represents some time in
 * milliseconds.
 * 
 * Every time you receive a call on your ping function, you need to return how
 * many pings occurred within the last 3000 milliseconds
 * 
 * It is guaranteed that every call to ping uses a strictly larger value of t
 * than before.
 */

class RecentCounter {
    Deque<Integer> queue;

    public RecentCounter() {
        queue = new LinkedList<Integer>();
    }
    
    public int ping(int t) {
        while(!queue.isEmpty() && t - queue.peekFirst() > 3000){
            queue.pollFirst();
        }
        queue.addLast(t);
        return queue.size();
    }
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */