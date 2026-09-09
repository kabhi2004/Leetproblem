class Solution {
    public long countCommas(long n) {
        long res=0;
        long s=1000;
        while(s<=n)
        {
            res+=n-s+1;
            s*=1000;
        }
        return res;
        
    }
}