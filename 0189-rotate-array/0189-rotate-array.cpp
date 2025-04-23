// 
class Solution {
public:
    void rotate(vector<int>& nums, int k) {
        int size = nums.size();
        k = k % size;

        vector<int> temp(k);
        
        // Store the last k elements
        for (int i = 0; i < k; i++) {
            temp[i] = nums[size - k + i];
        }

        // Shift the first part of the array to the right
        for (int i = size - k - 1; i >= 0; i--) {
            nums[i + k] = nums[i];
        }

        // Copy temp to the beginning of the array
        for (int i = 0; i < k; i++) {
            nums[i] = temp[i];
        }
    }
};
