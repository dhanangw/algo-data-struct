/**
 * Count Negative Numbers in a Sorted Matrix
 * 
 * Example 1:
 * Input: grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
 * Output: 8
 * Explanation: There are 8 negatives number in the matrix.
 * 
 * Example 2:
 * Input: grid = [[3,2],[1,0]]
 * Output: 0
 * 
 * Example 3:
 * Input: grid = [[1,-1],[-1,-1]] 
 * Output: 3
 * 
 * 
 * Example 4:
 * Input: grid = [[-1]] 
 * Output: 1
 * 
 */

class Solution {
    int count = 0;

    public void search(int[] array, int low, int high){
        if (array[high] >= 0 && low == high){
            return;
        }

        if (array[low] < 0){
            this.count += (high - low) + 1;
            return;
        } else {
            int mid = (high + low) / 2;
            if (array[mid] < 0){
                this.count += (high - mid) + 1;
                search(array, low, mid-1);
            } else if (array[mid] >= 0) {
                search(array, mid+1, high);
            }  
        }
    }

    public int countNegatives(int[][] grid) {
        for(int i = 0; i < grid.length; i++){
            // do binary search for negative number here.
            search(grid[i], 0, grid[i].length - 1);
        }
        return this.count;
    }
}