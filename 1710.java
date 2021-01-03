/**
 * Maximum Units on a Truck
 * 
 * Example 1:
 * Input: boxTypes = [[1,3],[2,2],[3,1]], truckSize = 4
 * Output: 8
 * Explanation: There are: 
 * - 1 box of the first type that contains 3 units.
 * - 2 boxes of the second type that contain 2 units each.
 * - 3 boxes of the third type that contain 1 unit each.
 * You can take all the boxes of the first and second types, and one box of the third type.
 * The total number of units will be = (1* 3) + (2 * 2) + (1 * 1) = 8.
 * 
 * Example 2:
 * Input: boxTypes = [[5,10],[2,5],[4,7],[3,9]], truckSize = 10
 * Output: 9
 * 
 */
class Solution {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        int remainingCapacity = truckSize;
        int maxUnits = 0;
        
        // loop through boxTypes
        for(int i = 0; i < boxTypes.length; ++i){
            if (remainingCapacity > 0){
                // get boxType with the maximum units. enter it to truck.
                int largestBoxIndex = getLargestBox(boxTypes);
                if (largestBoxIndex == -1){
                    break;
                }
                
                // get number of boxes that can be inserted to truck.
                int numOfBox = Math.min(boxTypes[largestBoxIndex][0], remainingCapacity);
                maxUnits += boxTypes[largestBoxIndex][1] * numOfBox;

                // mark taken boxType from boxTypes to skip it in getLargestBox().
                boxTypes[largestBoxIndex][1] = -1;

                // get remaining truck capacity.
                remainingCapacity -= numOfBox;
            }
        }
        return maxUnits;
    }
    
    private int getLargestBox(int[][] boxTypes){
        int maxUnit = -1;
        int largestBoxIndex = -1;
        for (int i = 0; i < boxTypes.length; ++i){
            if (boxTypes[i][1] > maxUnit){
                maxUnit = boxTypes[i][1];
                largestBoxIndex = i;
            }
        }
        return largestBoxIndex;
    }
}