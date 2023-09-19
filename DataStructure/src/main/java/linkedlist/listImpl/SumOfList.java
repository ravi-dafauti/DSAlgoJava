package linkedlist.listImpl;

import linkedlist.schema.ListNode;

public class SumOfList {


    public static ListNode sum(ListNode num1, ListNode num2) {
        ListNode res = null;
        ListNode tail = null;

        ListNode num1Rev = ReverseList.reverseLinkedListRecursive(num1);
        ListNode num2Rev = ReverseList.reverseLinkedListRecursive(num2);

        int carry = 0;
        while (num1Rev != null && num2Rev !=null) {
            int sum = num1Rev.getData() + num2Rev.getData() + carry;
            ListNode listNode = new ListNode(sum % 10);
            carry = sum > 9 ? 1 : 0;
            if(res == null) {
                res = listNode;
                tail = listNode;
            } else {
                tail.setNext(listNode);
                tail = tail.getNext();
            }
            num1Rev = num1Rev.getNext();
            num2Rev = num2Rev.getNext();
        }

        while (num1Rev != null) {
            int sum = num1Rev.getData() + carry;
            ListNode listNode = new ListNode(sum % 10);
            carry = sum > 9 ? 1 : 0;
            if(res == null) {
                res = listNode;
                tail = listNode;
            } else {
                tail.setNext(listNode);
                tail = tail.getNext();
            }
            num1Rev = num1Rev.getNext();
        }

        while (num2Rev != null) {
            int sum = num2Rev.getData() + carry;
            ListNode listNode = new ListNode(sum % 10);
            carry = sum > 9 ? 1 : 0;
            if(res == null) {
                res = listNode;
                tail = listNode;
            } else {
                tail.setNext(listNode);
                tail = tail.getNext();
            }
            num2Rev = num2Rev.getNext();
        }

        if(carry != 0) {
            tail.setNext(new ListNode(carry));
        }

        return ReverseList.reverseLinkedListRecursive(res);
    }


    public static ListNode sumOfListBetterSol(ListNode num1, ListNode num2) {

        if(num1 == null)
            return num2;
        if(num2 == null)
            return num1;
        ListNode resultHead, resultTail;
        resultHead = resultTail = null;
        num1 = ReverseList.reverseLinkedListRecursive(num1);
        num2 = ReverseList.reverseLinkedListRecursive(num2);
        int carry = 0;
        while (num1 != null && num2 != null) {
            int sum = num1.getData() + num2.getData() + carry;
            carry = sum / 10;
            if(resultHead == null) {
                resultHead = resultTail = new ListNode(sum % 10);
            } else {
                resultTail.setNext(new ListNode(sum %10));
                resultTail = resultTail.getNext();
            }
            num1 = num1.getNext();
            num2 = num2.getNext();
        }

        while (num1 != null) {
            int sum = num1.getData() + carry;
            resultTail.setNext(new ListNode(sum % 10));
            carry = sum / 10;
            num1 = num1.getNext();
        }

        while (num2 != null) {
            int sum = num2.getData() + carry;
            resultTail.setNext(new ListNode(sum % 10));
            carry = sum / 10;
            num2 = num2.getNext();
        }

        if(carry != 0) {
            resultTail.setNext(new ListNode(carry));
        }

        return ReverseList.reverseLinkedListRecursive(resultHead);
    }
}
