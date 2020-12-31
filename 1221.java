/*
*  Split a String in Balanced Strings
*
* Example 1:
* Input: s = "RLRRLLRLRL"
* Output: 4
* Explanation: s can be split into "RL", "RRLL", "RL", "RL", each substring contains same number of 'L' and 'R'.
* 
* Input: s = "RLLLLRRRLR"
* Output: 3
* Explanation: s can be split into "RL", "LLLRRR", "LR", each substring contains same number of 'L' and 'R'.
*
* Example 3:
* Input: s = "LLLLRRRR"
* Output: 1
* Explanation: s can be split into "LLLLRRRR".
*
* Example 4:
* Input: s = "RLRRRLLRLL"
* Output: 2
* Explanation: s can be split into "RL", "RRRLLRLL", since each substring contains an equal number of 'L' and 'R'
*
*/


class Solution {
    public int balancedStringSplit(String s) {
        int l = 0;
        int r = 0;
        int countResult = 0;
        
        for (char character: s.toCharArray()){
            if (character == 'R'){
                r += 1;
            } else if (character == 'L'){
                l += 1;
            }
            
            if (r > 0 && r == l){
                countResult += 1;
            }
        }
        
        return countResult;
    }
}