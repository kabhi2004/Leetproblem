class Solution {
public:
    bool isPalindrome(int x) {
       int pal=x;
       long long sum=0;
        while(pal>0){
            sum=sum*10+(pal%10);
            pal=pal/10;
        }
        if(x==sum){
            return true;
        }
        else{
            return false;
        }
        
    }
};