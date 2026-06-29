class Solution {
    public int numOfStrings(String[] patterns, String word) {
        int count=0;
        int worlen=word.length();
        for(String pattern:patterns)
        {
            // boolean flag=true;
            // for(int j=0;j+pattern.length()<=worlen;)
            // {
            //     for(int k=0;k<worlen;)
            //     {
            //         if(word.charAt(j+k)!=pattern.charAt(j))
            //         {
            //             flag=false;
            //             break;
            //         }
                
            //     }
            // }
            // if(flag)
            // {
            //     count++;
            // }
            if(word.indexOf(pattern)!=-1)
            {
                count++;
            }
           
        }
        return count;
        
    }
}