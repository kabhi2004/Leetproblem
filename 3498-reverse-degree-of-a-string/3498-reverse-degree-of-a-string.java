class Solution {
    public int reverseDegree(String s) {
      int n=s.length();
      int sum=0;
      for(int i=0;i<n;i++)
      {
        int rev=('z'-s.charAt(i))+1;
        int ind=i+1;
        sum=sum+(rev*ind);
      }
      return Math.abs(sum);
        
    }
}