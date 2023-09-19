package charu;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Test {


    // java mae arrary ek object hota h.

    public static void main(String[] args) {

        freq(new int[]{10, 4, 10, 2, 2, 4, 5, 6, 10});

    }


    //O(N^2)
    public static void printPairsThatSumToK(int[] arr, int K) {
        for(int i = 0; i < arr.length - 1; i++) {
            for(int j = i + 1; j < arr.length; j++) {
                if(arr[i] + arr[j] == K) {
                    System.out.println("numbers are : " + arr[i] + " : " + arr[j]);
                }
            }
        }
    }


    //O(NLogN)
    public static void printPairsThatSumToKBetter(int[] arr, int K) {
        Arrays.sort(arr);
        int i = 0;
        int j = arr.length - 1;
        while (i < j) {
            if(arr[i] + arr[j] == K) {
                System.out.println("numbers are : " + arr[i] + " : " + arr[j]);
                i++;
                j--;
            } else if(arr[i] + arr[j] < K) {
                i++;
            } else {
                j--;
            }
        }
    }

    // 1 2 3 5 3 2 1 ->> yes
    // 1 2 2 1 -> yes  1 2 2 1


    // O(N)
    public static boolean isPalindrome(int[] arr) {
        int i = 0;
        int j = arr.length - 1;
        while (i < j) {
            if(arr[i] != arr[j]) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static boolean isPalindrome(String s) {

        char[] arr = s.toCharArray();
        int i = 0;
        int j = arr.length - 1;
        while (i < j) {
            if(arr[i] != arr[j]) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }


    // arr = 10, 4, 10, 2, 2, 4, 5, 6, 10

    // 10 3
    // 2 2
    // 4 2
    //5 1
    // 6 1

    // brute force



    /// map - > empty
    // 10 - > 3
    // 4 - > 2
    // 2 - > 2
    // 5 - > 1
    // 6 - > 1




    //                                 i
    // arr = 10, 4, 10, 2, 2, 4, 5, 6, 10

    public static void freq(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < arr.length; i++) {
            if(!map.containsKey(arr[i])) {
                map.put(arr[i], 1);
            } else {
                map.put(arr[i], map.get(arr[i]) + 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

}

class Charu {
    int x;
    Charu(int x) {
        this.x = x;
    }
}
