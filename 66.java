// Plus One

// Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
// The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.
// You may assume the integer does not contain any leading zero, except the number 0 itself.

// Example 1:
// Input: [1,2,3]
// Output: [1,2,4]
// Explanation: The array represents the integer 123.

// Example 2:
// Input: [4,3,2,1]
// Output: [4,3,2,2]
// Explanation: The array represents the integer 4321.
import java.util.Arrays;

class Solution {
    public static void main(String[] args) {
        int[] arrayInt = { 4, 3, 2, 1 };
        int[] output = plusOne(arrayInt);
        System.out.println(Arrays.toString(output));
    }

    public static int[] plusOne(int[] digits) {
        int maxIndex = digits.length - 1;

        for (int i = maxIndex; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i] = digits[i] + 1;
                return digits;
            }

            digits[i] = 0;
        }

        int[] newNumber = new int[digits.length + 1];
        newNumber[0] = 1;
        return newNumber;
    }
}