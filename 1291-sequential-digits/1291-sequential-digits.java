class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
      String st="123456789";
      ArrayList<Integer> arr=new ArrayList<>();
      for(int i=1;i<=9;i++)
      {
        for(int start=0,end=i;end<st.length();start++,end++)
        {
            int num=Integer.parseInt(st.substring(start,end+1));
            if(num>=low&&num<=high)
            {
                arr.add(num);
            }
        }
      }
      return arr;
    }
}