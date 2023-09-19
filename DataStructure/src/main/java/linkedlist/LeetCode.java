/*

package linkedlist;

import linkedlist.schema.ListNode;

import java.util.HashMap;
import java.util.Map;

public class LeetCode {


    public boolean hasCycle(ListNode head) {

        if(head == null || head.next == null)
            return false;
        if(head.next == head)
            return true;

        ListNode s, f;
        s = f = head;

        while (f != null && f.getNext() != null) {
            s = s.next;
            f = f.next.next;
            if(s == f)
                return true;
        }
        return false;

    }


    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        if(list1 == null)
            return list2;
        if(list2 == null)
            return list1;
        ListNode result = null;
        if(list1.val < list2.val) {
            result = list1;
            result.next = mergeTwoLists(list1.next, list2);
        } else {
            result = list2;
            result.next = mergeTwoLists(list1, list2.next);
        }
        return result;
    }

    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        if(l1 == null)
            return l2;
        if(l2 == null)
            return l1;


        int carry = 0;

        ListNode result, tail;
        result = tail = null;

        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + carry;
            int val = sum % 10;
            if(sum > 9)
                carry = 1;
            else
                carry = 0;
            if(result == null) {
                result = tail = new ListNode(val, null);
            } else {
                tail.next = new ListNode(val, null);
            }

            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null) {
            int sum = l1.val + carry;
            int val = sum % 10;
            if(sum > 9)
                carry = 1;
            else
                carry = 0;
            if(result == null) {
                result = tail = new ListNode(val, null);
            } else {
                tail.next = new ListNode(val, null);
            }
            l1 = l1.next;
        }


        while (l2 != null) {
            int sum = l2.val + carry;
            int val = sum % 10;
            if(sum > 9)
                carry = 1;
            else
                carry = 0;
            if(result == null) {
                result = tail = new ListNode(val, null);
            } else {
                tail.next = new ListNode(val, null);
            }
            l2 = l2.next;
        }

        if (carry == 1) {
            if(result == null) {
                result = tail = new ListNode(1, null);
            } else {
                tail.next = new ListNode(1, null);
            }
        }

        return result;


    }


    public ListNode reverseBetween(ListNode head, int left, int right) {

        if(head == null || head.next == null || left == right)
            return head;

        ListNode t = head;
        ListNode ll;
        ListNode rr;
        ListNode pr = null;
        ListNode pl = null;
        ll = rr = head;
        int l, r;
        l = r = 1;


        while(t != null && (l <= left || r <= right)) {
            if(l == left) {
                ll = t;
                pl = pr;
            } else {
                l++;
            }
            if(r == right){
                rr = t;
            } else {
                r++;
            }
            pr = t;
            t = t.next;
        }

        ListNode temp = ll;
        ListNode rightRemain = rr.next;
        rr.next = null;
        if(pl != null)
            pl.next = null;
        ListNode reverse = reverseList(ll);
        temp.next = rightRemain;
        if(pl == null)
            return reverse;
        else {
            pl.next = reverse;
            return head;
        }
    }


    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode result, tail, end;
        end = head;
        tail = head.next;
        result = reverseList(head.next);
        end.next = null;
        tail.next = end;
        return result;
    }


    public ListNode rotateRight(ListNode head, int k) {

        if(head == null || head.next == null)
            return head;

        int length = length(head);
        k = k % length;
        int part = length - k;
        if(part == 0)
            return head;

        ListNode t = head;
        int c = 1;
        while(t != null && c < part) {
            t = t.next;
            c++;
        }

        ListNode res = t.next;
        t.next = null;
        ListNode tail = tail(res);
        tail.next = head;
        return res;
    }

    public int length(ListNode head) {
        if(head == null)
            return 0;
        int length = 0;
        while(head != null) {
            length++;
            head = head.next;
        }
        return length;
    }

    public ListNode tail(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode tail = null;
        while(head != null) {
            tail = head;
            head = head.next;
        }
        return tail;
    }



    public ListNode partition(ListNode head, int x) {


        if(head == null || head.next == null)
            return head;

        ListNode l, r, lt, rt;
        l = r = lt = rt = null;

        ListNode t = head;

        while(t != null) {
            if(t.val < x) {
                if(l == null) {
                    l = lt = t;
                    t = t.next;
                    lt.next = null;
                } else {
                    lt.next = t;
                    t = t.next;
                    lt.next = null;
                }
            } else {
                if(r == null) {
                    r = rt = t;
                    t = t.next;
                    rt.next = null;
                } else {
                    rt.next = t;
                    t = t.next;
                    rt.next = null;
                }
            }
        }
        if(lt == null)
            return r;
        else {
            lt.next = r;
            return l;
        }

    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {


        if(head == null)
            return head;

        Node t = head;
        while(t != null) {
            Node n = new Node(t.val);
            n.next = t.next;
            t.next = n;
            t = t.next.next;
        }

        t = head;
        while (t != null) {
            if(t.random == null){
                t.next.random = null;
            } else {
                t.next.random = t.random.next;
            }
            t = t.next.next;
        }


        Node res = new Node(0);
        Node res_t = res;
        t = head;

        while(t != null) {

            res_t.next = t.next;
            res_t = res_t.next;

            t.next = t.next.next;
            t = t.next;

        }

        return res.next;

    }


    public ListNode deleteDuplicates(ListNode head) {


        if(head == null || head.next == null)
            return head;
        ListNode result = new ListNode();
        ListNode result_tail = result;

        ListNode t = head;
        boolean flag = false;

        while(t != null) {
            if(t.next != null) {
                if(t.val == t.next.val) {
                    int curValToExclude = t.val;
                    while(t != null && t.val == curValToExclude) {
                        t = t.next;
                    }
                } else {
                    result_tail.next = t;
                    result_tail = result_tail.next;
                    t = t.next;
                }
            } else {
                result_tail.next = t;
                result_tail = result_tail.next;
                t = t.next;
            }
        }
        result_tail.next = null;
        return result.next;
    }


    public ListNode removeNthFromEnd(ListNode head, int n) {


        if(head == null || (head.next == null && n == 1))
            return null;

        ListNode p, q , r;
        p = q = head;
        r = null;
        int c = 1;
        while (c <= n) {
            p = p.next;
            c++;
        }
        while (p != null) {
            r = q;
            q = q.next;
            p = p.next;
        }

        r.next = q.next;
        q.next = null;

        return head;

    }


    public ListNode reverseKGroup(ListNode head, int k) {

        if(head == null || k == 1)
            return head;

        int c = 1;
        ListNode t = head;

        while(t != null && c < k) {
            t = t.next;
            c++;
        }

        if(t == null) {
            return  head;
        } else {
            ListNode first = head;
            ListNode second = t.next;
            t.next = null;
            ListNode reverseFirst = reverseList(first);
            head.next = reverseKGroup(second, k);
            return reverseFirst;
        }
    }


    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        int m = lengthOfList(headA);
        int n = lengthOfList(headB);
        ListNode temp;
        if(m < n) {
            temp = headA;
            headA = headB;
            headB = temp;
            int c = m;
            m = n;
            n = c;
        }

        for(int i = 0; i < m - n; i++){
            headA = headA.next;
        }
        while (headA != null && headB != null) {
            if(headA == headB)
                return headA;
            else {
                headA = headA.next;
                headB = headB.next;
            }
        }
        return null;
    }

    public int lengthOfList(ListNode head) {
        int c = 0;
        while (head != null) {
            c++;
            head = head.next;
        }
        return c;
    }


    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null)
            return true;
        ListNode s, f, p;
        s = f = head;
        p = null;
        while (f != null && f.next != null) {
            p = s;
            s = s.next;
            f = f.next.next;
        }

        ListNode first = head;
        ListNode second;
        if(f == null) {
            second = s;
        } else {
            second = s.next;
        }
        p.next = null;
        second = reverseList(second);

        while (first != null && second != null){
            if(first.val != second.val)
                return false;
            first = first.next;
            second = second.next;
        }

        return true;
    }


    public ListNode swapPairs(ListNode head) {
        return reverseKGroup(head, 2);
    }


    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null)
            return null;
        if(head.next == head)
            return head;

        ListNode s, f;
        s = f = head;

        boolean hasCycle = false;
        while (f != null && f.next != null) {
            s = s.next;
            f = f.next.next;
            if(s == f) {
                hasCycle = true;
                break;
            }
        }
        if(hasCycle) {
            s = head;
            while (s != f) {
                s = s.next;
                f = f.next;
            }
            return s;
        }
        return null;
    }


    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null)
            return head;

        ListNode middle = getMiddle(head);

        ListNode first = head;
        ListNode second = middle.next;
        middle.next = null;
        first = sortList(first);
        second = sortList(second);
        return mergeTwoLists(first, second);
    }

    public ListNode getMiddle(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode s, f, p;
        s = f = head;
        p = null;
        while (f != null && f.next != null) {
            p = s;
            s = s.next;
            f = f.next.next;
        }
        if(f == null)
            return p;
        return s;
    }


    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0)
            return null;

        ListNode result = null;
        int minimum = 0;
        int i;

        for(i = 0; i < lists.length; i++) {
            if(lists[i] != null) {
                minimum = i;
                break;
            }
        }
        if(i == lists.length)
            return null;

        for(; i < lists.length; i++) {
            if(lists[i] != null && lists[minimum] != null && lists[i].val < lists[minimum].val) {
                minimum = i;
            }
        }
        result = lists[minimum];
        lists[minimum] = lists[minimum].next;
        result.next = mergeKLists(lists);
        return result;
    }


    class LRUCache {

        class Node {
            int key;
            int val;
            Node prev;
            Node next;
            Node(int key, int val) {
                this.key = key;
                this.val = val;
                prev = null;
                next = null;
            }
        }

        HashMap<Integer, Node> map;
        Node head, tail;
        int capacity;
        public LRUCache(int capacity) {
            map = new HashMap<>();
            head = tail = null;
            this.capacity = capacity;
        }

        public int get(int key) {
            Node res = map.get(key);
            if(res == null)
                return -1;
            remove(res);
            add(res);
            return res.val;
        }

        public void put(int key, int value) {
            if(map.containsKey(key)) {
                Node res = map.get(key);
                res.val = value;
                remove(res);
                add(res);
            } else {
                if(map.size() >= capacity) {
                    map.remove(head.key);
                    remove(head);
                }
                Node newNode = new Node(key, value);
                add(newNode);
                map.put(key, newNode);
            }


        }

        public void remove(Node node) {
            if(node == head) {
                head = head.next;
                if(head != null) {
                    head.prev.next = null;
                    head.prev = null;
                } else {
                    tail = null;
                }
            } else {
                node.prev.next = node.next;
                if(node.next != null) {
                    node.next.prev = node.prev;
                } else {
                    tail = node.prev;
                }
                node.prev = null;
                node.next = null;
            }
        }


        public void add(Node node) {
            if(tail != null) {
                tail.next = node;
                node.prev = tail;
                tail = tail.next;
            } else {
                head = tail = node;
            }
        }
    }

}
*/