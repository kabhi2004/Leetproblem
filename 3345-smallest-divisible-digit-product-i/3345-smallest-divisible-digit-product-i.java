class Solution {
    public int smallestNumber(int n, int t) {
        while (true) {
            if (digitProduct(n) % t == 0) {
                return n;
            }
            n++;
        }
    }

    private int digitProduct(int num) {
        int product = 1;

        while (num > 0) {
            product *= (num % 10);
            num /= 10;
        }

        return product;
    }
}

// Since the constraints are very small:

// 1 <= n <= 100
// 1 <= t <= 10

// A brute force solution is the simplest and most efficient.

// Idea
// Start from n.
// Compute the product of its digits.
// If product % t == 0, return the number.
// Otherwise, increment the number and repeat.

// Because t <= 10 and numbers are small, you'll find the answer very quickly.

// Java Solution
// class Solution {
//     public int smallestNumber(int n, int t) {
//         while (true) {
//             if (digitProduct(n) % t == 0) {
//                 return n;
//             }
//             n++;
//         }
//     }

//     private int digitProduct(int num) {
//         int product = 1;

//         while (num > 0) {
//             product *= (num % 10);
//             num /= 10;
//         }

//         return product;
//     }
// }
// Dry Run
// Example 1
// n = 10, t = 2

// digit product = 1 × 0 = 0
// 0 % 2 == 0

// Answer = 10
// Example 2
// n = 15, t = 3

// 15 -> 1×5 = 5 (not divisible)
// 16 -> 1×6 = 6 (divisible)

// Answer = 16
// Complexity

// Let k be the number of numbers checked (very small here).

// Time: O(k × d) where d is the number of digits (at most 3 for these constraints).
// Space: O(1).