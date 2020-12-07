
/**
 * TwoSum
 * 
 * Example 1:
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 * 
 * Example 2:
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 * 
 * Example 3:
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 * 
 */

import java.util.*;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length; i++) {
            // put to hash tabel
            // array value is hash key,
            // array index is hash value,
            map.put(nums[i], i);
        }
        // focus on an array element, and find the pair of that element.
        for (int i = 0; i < nums.length; i++) {
            int pair = target - nums[i];
            result[0] = i;
            if (map.containsKey(pair) && map.get(pair) != i) {
                result[1] = map.get(pair);
                break;
            }
        }
        return result;
    }
}