class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int ones=0;
        for(int c:s.toCharArray())
        {
            if(c=='1')
            ones++;
        }
        String t="1"+s+"1";
        ArrayList<Character> ch=new ArrayList<>();
        ArrayList<Integer> len=new ArrayList<>();
        int i=0;
        while(i<t.length())
        {
            char c=t.charAt(i);
            int j=i;
            while(j<t.length()&& t.charAt(j)==c)
            {
                j++;
            }
            ch.add(c);
            len.add(j-i);
            i=j;
        }
        int maxgain=0;
        for(int k=1;k<ch.size()-1;k++)
        {
            if(ch.get(k)=='1')
            {
                maxgain=Math.max(maxgain,len.get(k-1)+len.get(k+1));
            }
        }
        return maxgain+ones;

    }
}