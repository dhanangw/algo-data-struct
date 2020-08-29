// Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

// An input string is valid if:

// Open brackets must be closed by the same type of brackets.
// Open brackets must be closed in the correct order.
// Note that an empty string is also considered valid.

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        char latestCharacterInStack;
        char character;
        for (int i = 0; i < s.length(); i++) {
            character = s.charAt(i);
            if (stack.isEmpty()) {
                latestCharacterInStack = '\u0000';
            } else {
                latestCharacterInStack = stack.peek();
            }

            if (character == '(' || character == '[' || character == '{') {
                stack.push(character);
            } else if (character == ')' && latestCharacterInStack == '(') {
                stack.pop();
            } else if (character == ']' && latestCharacterInStack == '[') {
                stack.pop();
            } else if (character == '}' && latestCharacterInStack == '{') {
                stack.pop();
            } else {
                return false;
            }
        }
        if (stack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
