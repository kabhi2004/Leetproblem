class Solution {
    public int maxNumberOfBalloons(String text) {
    char []freq= new char[26];
    for(char ch:text.toCharArray())
    {
        freq[ch-'a']++;
    }
    int min=Integer.MAX_VALUE;
    min=Math.min(min,freq['b'-'a']);
    min=Math.min(min,freq['a'-'a']);
    min=Math.min(min,freq['l'-'a']/2);
    min=Math.min(min,freq['o'-'a']/2);
    min=Math.min(min,freq['n'-'a']);
    return min;
      
        
    }
    
}