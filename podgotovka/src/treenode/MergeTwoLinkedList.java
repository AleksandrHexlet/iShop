package treenode;

import java.util.List;

public class MergeTwoLinkedList {

    public static class ListNode{
        int value;
        ListNode next;

        public ListNode(int value) {
            this.value = value;
        }
    }


    public static ListNode MergeTwoList(ListNode firstHead, ListNode secondHead){
        if(firstHead == null && secondHead == null) return null;
        if(firstHead == null && secondHead != null) return secondHead;
        if(firstHead != null && secondHead == null) return firstHead;
        ListNode nodeStart = new ListNode(0);
        ListNode nodeTemp = nodeStart;

        while (firstHead != null && secondHead != null){
            if(firstHead.value < secondHead.value){
                nodeTemp.next = firstHead;

                firstHead = firstHead.next;
            } else {
                nodeTemp.next = secondHead;
                secondHead = secondHead.next;
            };
            nodeTemp = nodeTemp.next;
        }

        if(firstHead == null){
            nodeTemp.next = secondHead;
        } else {
            nodeTemp.next = firstHead;
        }

        return nodeStart.next;


    };
}
