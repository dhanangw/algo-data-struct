/**
* 1213. Intersection of Three Sorted Arrays
* Given three integer arrays arr1, arr2 and arr3
* sorted in strictly increasing order,
* return a sorted array of only the integers that
* appeared in all three arrays.
*
*
* Example 1:
* Input: arr1 = [1,2,3,4,5], arr2 = [1,2,5,7,9], arr3 = [1,3,4,5,8]
* Output: [1,5]
* Explanation: Only 1 and 5 appeared in the three arrays.
*
*/

import java.util.*;

// 1st submission
class Solution {
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        int minLength = Math.min(arr1.length, Math.min(arr2.length, arr3.length));
        List<Integer> result = new ArrayList<Integer>();
        Hashtable<Integer, Integer> hashTable = new Hashtable<Integer, Integer>();

        // put each integer and it's number of occurences in a hashtable.
        for (int i = 0; i < minLength; i++){
            if (!hashTable.containsKey(arr1[i])){
                hashTable.put(arr1[i], 1);
            } else{
                int count = hashTable.get(arr1[i]) + 1;
                hashTable.put(arr1[i], count);
            }
            
            if (!hashTable.containsKey(arr2[i])){
                hashTable.put(arr2[i], 1);
            } else {
                int count = hashTable.get(arr2[i]) + 1;
                hashTable.put(arr2[i], count);
            }

            if (!hashTable.containsKey(arr3[i])){
                hashTable.put(arr3[i], 1);
            } else{
                int count = hashTable.get(arr3[i]) + 1;
                hashTable.put(arr3[i], count);
            }
        }

        // get all key where value == 3
        Set<Integer> keys = hashTable.keySet();
        for (int key : keys){
            if (hashTable.get(key) == 3){
                result.add(key);
            }
        }
        
        // sort
        Collections.sort(result);
        return result;
    }
}