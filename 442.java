// Find all the elements that appear twice in this array.

// Could you do it without extra space and in O(n) runtime?

// Input:
// [4,3,2,7,8,2,3,1]

// Output:
// [2,3]


import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;


class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
        List<Integer> answer = new ArrayList<Integer>();
            
        for (int num: nums){
            if (hashMap.containsKey(num)){
                int previousCount = hashMap.get(num);
                hashMap.put(num, previousCount += 1);
            } else {
                hashMap.put(num, 1);
            }
        }
        
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()){
            System.out.println(entry.getKey() + " " +  entry.getValue());
            if (entry.getValue() > 1){
                answer.add(entry.getKey());
            }
        }
        
        return answer;
    }
}