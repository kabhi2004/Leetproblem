/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode Temp=new ListNode(-1);
        ListNode head=Temp;

        while(list1!=null&&list2!=null)
        {
            if(list1.val<=list2.val)
            {
                Temp.next=list1;
                list1=list1.next;
                Temp=Temp.next;
            }
            else
            {
                Temp.next=list2;
                list2=list2.next;
                Temp=Temp.next;
            }
        }
        while(list1!=null)
        {
            Temp.next=list1;
            list1=list1.next;
            Temp=Temp.next;
        }
        while(list2!=null)
        {
            Temp.next=list2;
            list2=list2.next;
            Temp=Temp.next;
        }
        return head.next;
    }
}