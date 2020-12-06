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

// 1st submission uses Hash Table.
class Solution1 {
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

import java.util.*;


// Solution 2 uses three pointers
class Solution2 {
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        int pointer1 = 0;
        int pointer2 = 0;
        int pointer3 = 0;
        List<Integer> result = new ArrayList<Integer>();

        while (pointer1 < arr1.length && pointer2 < arr2.length && pointer3 < arr3.length) {
            if (arr1[pointer1] == arr2[pointer2] && arr2[pointer2] == arr3[pointer3]) {
                result.add(arr1[pointer1]);
                pointer1 += 1;
                pointer2 += 1;
                pointer3 += 1;
            } else if (arr1[pointer1] <= arr2[pointer2] && arr1[pointer1] <= arr3[pointer3]) {
                // if min is in arr1, increment pointer1
                pointer1 += 1;
            } else if (arr2[pointer2] <= arr3[pointer3] && arr2[pointer2] <= arr1[pointer1]) {
                pointer2 += 1;
                // if min is in arr2, increment pointer2
            } else {
                // increment pointer3
                pointer3 += 1;
            }
        }
        return result;
    }
}