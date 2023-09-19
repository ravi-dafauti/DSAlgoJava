package javastlnotes;

import linkedlist.schema.ListNode;
import recursion.Problems;

import java.util.*;

public class CodingNotes {


    public static void main(String[] args) {
        Integer[] arr = new Integer[]{1, 2 ,3};
        Character.isDigit('1'); // tells if char is a digit.
        Arrays.toString(arr); // this is to print array
        List<Integer> l = Arrays.asList(arr);// this will give a list using the array but it will be bridged to array and any changes to array will reflect in list
        List<Integer> list = new ArrayList<>(Arrays.asList(arr)); // to get new list without bridging
        printList(l); // 1 2 3
        arr[2] = 4;
        printList(l); // 1 2 4
        printList(list); // 1 2 3

        //Priority Queue :

        //Min heap :
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        //Max Heap :
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        //Incase of any object :

        class T {
            int a;
            int b;
        }

        PriorityQueue<T> minHeapcustom = new PriorityQueue<>((a, b) -> a.a - a.b);
        PriorityQueue<T> maxHeapcustom = new PriorityQueue<>((a, b) -> b.a - a.a);

        // Iterating a map :
        Map<Integer, Integer> map = new HashMap<>();
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

        // to get list of elements :
        List<Integer> listOfInts = List.of(1, 2, 3);


        // Dequque

        Deque<Integer> deque = new LinkedList<>();
        deque.getFirst();
        deque.getLast();
        deque.addLast(1);
        deque.addFirst(2);
        deque.isEmpty();
        // can use poll and offer also if return type boolean is expected.


        // imporant for hashmap update with some frequecy

        map.put(1, map.getOrDefault(1, 0) + 1); // this will update the map with freq + 1 if anything present else add freq as 1

        //
        List<Integer> mapValList = (List<Integer>) map.values(); //map.values will return a collection of values.

        // Initialize array :

        //Arrays.fill(array, Integer.MAX_VALUE);

        // 2D array : int[][] dp = new int[3][2];


        //Character.isLetterOrDigit(currLast) to check if letter or digit


        // BITWISE :

        // https://www.geeksforgeeks.org/bitwise-hacks-for-competitive-programming/
        // https://www.geeksforgeeks.org/bit-tricks-competitive-programming/

    }





    public static void printList(List<Integer> l) {
        for(Integer i : l) {
            System.out.print(i);
        }
        System.out.println("");
    }
}
