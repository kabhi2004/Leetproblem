class Solution {
    public double myPow(double x, int n) {
        
        long N = n;   // convert int to long (important for overflow case)
        
        // Handle negative power
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        
        double result = 1;
        
        while (N > 0) {
            
            // If current power is odd
            if ((N & 1) == 1) {
                result = result * x;
            }
            
            // Square the base
            x = x * x;
            
            // Divide power by 2
            N = N >> 1;
        }
        
        return result;
    }
}
