/**
 * Diet Plan Performance
 * 
 * Example 1: 
 * Input: calories = [1,2,3,4,5], k = 1, lower = 3, upper = 3 
 * Output: 0 
 * Explanation: Since k = 1, we consider each element of the array separately
 * and compare it to lower and upper. calories[0] and calories[1] are less than
 * lower so 2 points are lost. calories[3] and calories[4] are greater than
 * upper so 2 points are gained.
 * 
 * Example 2:
 * Input: calories = [3,2], k = 2, lower = 0, upper = 1 
 * Output: 1
 * Explanation: Since k = 2, we consider subarrays of length 2. calories[0] +
 * calories[1] > upper so 1 point is gained.
 * 
 * Example 3: 
 * Input: calories = [6,5,0,0], k = 2, lower = 1, upper = 5
 * Output: 0
 * Explanation: calories[0] + calories[1] > upper so 1 point is gained. lower <=
 * calories[1] + calories[2] <= upper so no change in points. calories[2] +
 * calories[3] < lower so 1 point is lost.
 */


class Solution {
    public int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
        int point = 0;

        for (int i = 0; i < calories.length; i++) {
            if ((i + k) > calories.length) {
                break;
            }
            int total = 0;
            int j = i;

            while (j < (i + k)) {
                total += calories[j];
                j += 1;
            }

            if (total > upper) {
                point += 1;
            } else if (total < lower) {
                point -= 1;
            }
        }

        return point;
    }
}