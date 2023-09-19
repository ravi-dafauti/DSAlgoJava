package sorting.problems;

import linkedlist.listImpl.DetectLoop;
import linkedlist.schema.DllNode;
import linkedlist.schema.ListNode;

import java.util.*;

public class Problems {


    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

    }
    public static void sortArrayOf012(int arr[]) {
        int zero = 0;
        int two = arr.length - 1;
        int i = 0;
        while(i <= two) {
            if(arr[i] == 0) {
                swap(arr, zero, i);
                zero++;
                i++;
            } else if (arr[i] == 2){
                swap(arr, two, i);
                two--;
            } else {
                i++;
            }
        }
    }

    public static boolean checkForRepeatedElements(int[] arr) {

        Arrays.sort(arr);
        for(int i = 0; i< arr.length - 1; i++) {
            if(arr[i] == arr[i + 1])
                return true;
        }

        return false;
    }

    public static int maximumOccurringElement(int[] arr) {

        if(arr == null || arr.length == 0)
            return -1;

        Arrays.sort(arr);
        int currentElement = arr[0];
        int currentCount = 1;
        int maxCount = 1;
        int maxElement = arr[0];

        for(int i = 1; i < arr.length ; i++) {
            if(arr[i] == currentElement) {
                currentCount++;
            } else {
                if(currentCount > maxCount) {
                    maxCount = currentCount;
                    maxElement = currentElement;
                }
                currentCount = 1;
                currentElement = arr[i];
            }
        }

        return maxElement;
    }

    /**
     *
     * @param a
     * @param b
     * @return
     *
     * check if there exists a pair A, B one from each array whose sum is K
     */
    public static boolean checkIfPairsExist(int a[], int b[], int K) {

        Arrays.sort(a);
        for(int i = 0; i< b.length; i++) {
            if(Arrays.binarySearch(a, K - b[i]) >= 0)
                return true;
        }

        return false;
    }

    /**
     * arr = [-4,-1,0,2] => [0, 1, 4,16]
     * @param arr
     */

    public static int[] sortSquaresOfArray(int arr[]) {

        int mid = -1;
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] >= 0) {
                mid = i;
                break;
            }
        }

        int res[] = new int[arr.length];


        int i = mid - 1;
        int j = mid;

        int k = 0;


        while (i >= 0 && j < arr.length) {
            if(arr[i] * arr[i] < arr[j] * arr[j]) {
                res[k++] = arr[i] * arr[i];
                i--;
            } else {
                res[k++] = arr[j] * arr[j];
                j++;
            }
        }

        while (i >= 0) {
            res[k++] = arr[i] * arr[i];
            i--;
        }

        while (j < arr.length) {
            res[k++] = arr[j] * arr[j];
            j++;
        }

        return res;
    }


    public static ListNode mergeSortLinkedList(ListNode head) {

        if(head == null || head.getNext() == null)
            return head;

        ListNode mid = DetectLoop.getMedian(head); // no need for this just create 2 list by taking alternate elemenet is two list.
        ListNode list1 = head;
        ListNode list2 = mid.getNext();
        mid.setNext(null);
        return mergeList(mergeSortLinkedList(list1), mergeSortLinkedList(list2));
    }

    private static ListNode mergeList(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        ListNode tail = res;
        while (l1 != null && l2 != null) {
            if(l1.getData() <= l2.getData()) {
                tail.setNext(l1);
                l1 = l1.getNext();
            } else {
                tail.setNext(l2);
                l2 = l2.getNext();
            }
            tail = tail.getNext();
        }

        if(l1 != null) {
            tail.setNext(l1);
        } else {
            tail.setNext(l2);
        }
        return res.getNext();
    }

    private static class ListInfo {
        private ListNode head;
        private ListNode tail;

        public ListInfo() {

        }

        public ListInfo(ListNode head, ListNode tail) {
            this.head = head;
            this.tail = tail;
        }

        public ListNode getHead() {
            return head;
        }

        public void setHead(ListNode head) {
            this.head = head;
        }

        public ListNode getTail() {
            return tail;
        }

        public void setTail(ListNode tail) {
            this.tail = tail;
        }
    }

/*    public static ListNode quickSortLinkedList(ListNode head) {
        if(head == null || head.getNext() == null)
            return head;
        ListInfo listInfo = quickSortList(head);
        return  listInfo.getHead();
    }

    public static ListInfo quickSortList(ListNode head) {
        if(head == null)
            return new ListInfo(null, null);
        if(head.getNext() == null)
            return new ListInfo(head, head);

        ListNode mid = head;
        head = head.getNext();
        mid.setNext(null);

        ListNode leftHead, rightHead, leftTail, rightTail;
        leftHead = rightHead = leftTail = rightTail = null;

        while (head != null) {
            if(head.getData() <= mid.getData()) {
                if(leftHead == null) {
                    leftHead = leftTail = head;
                } else {
                    leftTail.setNext(head);
                    leftTail = leftTail.getNext();
                }
                head = head.getNext();
                leftTail.setNext(null);
            } else {
                if(rightHead == null) {
                    rightHead = rightTail = head;
                } else {
                    rightTail.setNext(head);
                    rightTail = rightTail.getNext();
                }
                head = head.getNext();
                rightTail.setNext(null);
            }
        }

        ListInfo leftInfo = quickSortList(leftHead);
        ListInfo rightInfo = quickSortList(rightHead);
        ListInfo result = new ListInfo();

        if(leftInfo.getHead() != null) {
            leftInfo.getTail().setNext(mid);
            result.setHead(leftInfo.getHead());
        } else {
            result.setHead(mid);
        }

        mid.setNext(rightInfo.getHead());
        if(rightInfo.getHead() == null) {
            result.setTail(mid);
        } else {
            result.setTail(rightInfo.getTail());
        }

        return result;

    }*/


// QUICK SORT FOR LINKED LIST VV IMP

    public static ListNode quickSort(ListNode node)
    {
        quickSortUtil(node, null);
        return node;
    }

    public static void quickSortUtil(ListNode start, ListNode end) {
        if(start != end) {
            ListNode piv = part(start, end);
            quickSortUtil(start, piv);
            quickSortUtil(piv.next, end);
        }
    }

    public static ListNode part(ListNode start, ListNode end) {

        ListNode itr = start.next;
        ListNode partition = start;
        while( itr != end) {
            if(itr.getData() <= start.getData()) {
                partition = partition.next;
                swap(partition, itr);
            }
            itr = itr.next;
        }
        swap(partition, start);
        return partition;
    }

    public static void swap(ListNode a, ListNode b) {
        int t = a.getData();
        a.setData(b.getData());
        b.setData(t);
    }

    public static DllNode insertSortLinkedList(DllNode head) {

        if(head == null || head.getNext() == null)
            return head;

        DllNode temp = head.getNext();
        DllNode prev;

        while (temp != null) {
            prev = temp;
            int data = temp.getData();
            while (prev.getPrev() != null && prev.getPrev().getData() > data) {
                prev.setData(prev.getPrev().getData());
                prev = prev.getPrev();
            }
            prev.setData(data);
            temp=temp.getNext();
        }

        return head;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        Set<List<Integer>> s = new HashSet<>();
        for(int i = 0; i + 2 < nums.length; i++) {
            for(int j = i + 1, k = nums.length - 1; j < k;) {
                if(nums[i] + nums[j] + nums[k] == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    s.add(list);
                } else if(nums[i] + nums[j] + nums[k] < 0 ) {
                    j++;
                } else if(nums[i] + nums[j] + nums[k] > 0 ) {
                    k--;
                }
            }
        }
        result.addAll(s);
        return result;
    }
}
