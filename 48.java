// You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
// You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
// DO NOT allocate another 2D matrix and do the rotation.


class Solution {
    public int[][] rotate(int[][] matrix) {
        matrix = transpose(matrix);
        return reflect(matrix);
        
    }
    
    private int[][] transpose(int[][] matrix){
        int length = matrix.length;
        for (int i = 0; i < length; ++i){
            for (int j = i; j < length; ++j){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        return matrix;
    }
    
    private int[][] reflect(int[][] matrix){
        int length = matrix.length;
        for (int i = 0; i < length; ++i){
            for(int j = 0; j < length / 2; ++j){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][length-1-j];
                matrix[i][length-1-j] = temp;
            }
        }
        return matrix;
    }
}