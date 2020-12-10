
/**
 * Shuffle String
 * 
 * Example 1:
 * Input: s = "codeleet", indices = [4,5,6,7,0,2,1,3]
 * Output: "leetcode"
 * Explanation: As shown, "codeleet" becomes "leetcode" after shuffling.
 * 
 * Example 2:
 * Input: s = "abc", indices = [0,1,2]
 * Output: "abc"
 * Explanation: After shuffling, each character remains in its position.
 *
 * Example 3:
 * Input: s = "aiohn", indices = [3,1,4,2,0]
 * Output: "nihao"
 * 
 * Example 4:
 * Input: s = "aaiougrt", indices = [4,0,2,6,7,3,1,5]
 * Output: "arigatou"
 * 
 * Example 5:
 * Input: s = "art", indices = [1,0,2]
 * Output: "rat"
*/


import java.util.Hashtable;
import java.util.Arrays;


class Solution1 {
    /**
     * time = O(n)
     * space = O(n)
     */
    public String restoreString(String s, int[] indices) {
        Hashtable<Integer, Character> hashTable = new Hashtable<Integer, Character>();
        StringBuilder output = new StringBuilder();

        // put each char in string to a hash table, along with it's index.
        for (int i = 0; i < indices.length; i++){
            hashTable.put(indices[i], s.charAt(i));
        }

        // sort indices
        Arrays.sort(indices);
        
        // traverse through sorted array, put chars of indices to output string.
        for (int i = 0; i < indices.length; i++){
            output.append(hashTable.get(i));
        }
        
        return output.toString();
    }
}

class Solution2 {
    /**
     * time = O(n) 
     * space = O(n)
     * but faster as we only loop once.
     */
    public String restoreString(String s, int[] indices) {
        char[] output = new char[indices.length];
        for (int i = 0; i < indices.length; i++){
            output[indices[i]] = s.charAt(i);
        }
        return new String(output);
    }
}