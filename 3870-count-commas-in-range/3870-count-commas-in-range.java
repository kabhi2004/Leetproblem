class Solution {
    public int countCommas(int n) {
        int t=1000;
        if(n<t)
        {
            return 0;
        }
        else{
        return n-t+1;
        }
    }
}