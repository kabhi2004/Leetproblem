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
    public ListNode removeNthFromEnd(ListNode head, int n) {
     if(head.next==null){
        return null;
     }
     int size=0;
     ListNode curr=head;
     while(curr!=null)
     {
        curr=curr.next;
        size++;
     }
     int len=size-n;
     ListNode pre=head;
     int i=1;
     if(n==size)
     {
        return head.next;
     }
     while(i<len)
     {
        pre=pre.next;
        i++;
     }
     pre.next=pre.next.next;
     return head;

    }
}
