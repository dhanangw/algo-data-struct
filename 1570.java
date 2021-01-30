// Given two sparse vectors, compute their dot product.

// Implement class SparseVector:

// SparseVector(nums) Initializes the object with the vector nums
// dotProduct(vec) Compute the dot product between the instance of SparseVector and vec
// A sparse vector is a vector that has mostly zero values, you should store the sparse vector efficiently and compute the dot product between two SparseVector.

// Follow up: What if only one of the vectors is sparse?

class SparseVector {
    public int[] array;

    SparseVector(int[] nums) {
        this.array = nums;
    }
    
	// Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        int result = 0;
        for (int i = 0; i < vec.array.length; ++i){
            if (this.array[i] != 0 || vec.array[i] != 0){
                result += this.array[i] * vec.array[i];
            }
        }
        return result;
    }
}

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);