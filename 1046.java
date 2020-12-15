
/**
 * Last Stone Weight
 * 
 * We have a collection of stones, each stone has a positive integer weight.
 * 
 * Each turn, we choose the two heaviest stones and smash them together.
 * 
 * Suppose the stones have weights x and y with x <= y.  
 * The result of this smash is:
 *  If x == y, both stones are totally destroyed;
 *  If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.
 * 
 * At the end, there is at most 1 stone left.  Return the weight of this stone (or 0 if there are no stones left.)
 * 
 * 
 * 
 * Example 1:
 * Input: [2,7,4,1,8,1]
 * Output: 1
 * Explanation: 
 * We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
 * we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
 * we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
 * we combine 1 and 1 to get 0 so the array converts to [1] then that's the value of last stone.
 */
import java.util.*;


class Solution {
    private int smash(int stone1, int stone2){
        int smashResult = 0;

        if (stone1 > stone2){
            smashResult = stone1 - stone2;
        } else if (stone2 > stone1){
            smashResult = stone2 - stone1;
        }
        
        return smashResult;
    }

    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
        
        // turn input array to heap
        for (int i = 0; i < stones.length; i++){
            maxHeap.add(stones[i]);
        }
        
        // smash the stone until maxHeap size == 1
        while (maxHeap.peek() != null && maxHeap.size() > 1){
            // pop 2 largest stone in stones
            int stone1 = maxHeap.poll();
            int stone2 = maxHeap.poll();
            
            // smash 2 largest stones
            int smashResult = smash(stone1, stone2);
            
            // insert residue of smash
            if (smashResult != 0){
                maxHeap.add(smashResult);
            }
        }
        
        // return weight of last stone.
        int lastStoneWeight = 0;
        if (maxHeap.peek() != null){
            lastStoneWeight = maxHeap.poll();
        }
        return lastStoneWeight;
    }
}