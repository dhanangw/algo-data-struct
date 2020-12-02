
/**
 * Remove Outermost Parentheses
 * 
 * Example 1:
 * Input: "(()())(())"
 * Output: "()()()"
 * Explanation: 
 * The input string is "(()())(())", with primitive decomposition "(()())" + "(())".
 * After removing outer parentheses of each part, this is "()()" + "()" = "()()()".
 * 
 * 
 * Example 2:
 * Input: "(()())(())(()(()))"
 * Output: "()()()()(())"
 * Explanation: 
 * The input string is "(()())(())(()(()))", with primitive decomposition "(()())" + "(())" + "(()(()))".
 * After removing outer parentheses of each part, this is "()()" + "()" + "()(())" = "()()()()(())".
 * 
 * 
 * Example 3:
 * Input: "()()"
 * Output: ""
 * Explanation: 
 * The input string is "()()", with primitive decomposition "()" + "()".
 * After removing outer parentheses of each part, this is "" + "" = "".
 */
import java.util.*;

class Solution {
    public String removeOuterParentheses(String S) {
        Stack<Character> primitiveStack = new Stack<Character>();
        StringBuilder result = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        for (int i = 0; i < S.length(); i++) {
            char currentChar = S.charAt(i);
            temp.append(currentChar);
            if (currentChar == '(') {
                primitiveStack.push(currentChar);
            } else {
                primitiveStack.pop();
            }

            if (primitiveStack.empty()) {
                result.append(temp.deleteCharAt(0).deleteCharAt(temp.length() - 1));
                temp.setLength(0);
            }
        }
        return result.toString();
    }
}