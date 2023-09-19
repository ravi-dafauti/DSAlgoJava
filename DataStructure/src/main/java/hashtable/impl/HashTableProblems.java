package hashtable.impl;

import hashtable.schema.HashTable;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.Map;

public class HashTableProblems {

    public static char[] removeDuplicates(char[] arr) {

        LinkedHashSet<Character> hashSet = new LinkedHashSet<>();
        int i = 0;
        while (i < arr.length) {
            hashSet.add(arr[i++]);
        }
        char[] result = new char[hashSet.size()];
        i = 0;
        for(Character c : hashSet) {
            result[i++] = c;
        }
        return result;
    }

    public static boolean isSameSet(int[] arr1, int[] arr2) {

        Hashtable<Integer, Integer> hashtable = new Hashtable<>();
        boolean isSame = true;
        for(int a : arr1) {
            if(hashtable.containsKey(a)) {
                int count = hashtable.get(a);
                hashtable.replace(a, count, count + 1);
            } else {
                hashtable.put(a, 1);
            }
        }

        for(int a : arr2) {
            if(hashtable.containsKey(a)) {
                int count = hashtable.get(a);
                hashtable.replace(a, count, count - 1);
            } else {
                isSame = false;
                break;
            }
        }

        for(Map.Entry<Integer, Integer> entry : hashtable.entrySet()) {
            if(entry.getValue() != 0) {
                isSame = false;
                break;
            }
        }

        return isSame;

    }

    /*

    remove all chars that are present in string aux from string main
     */
    public static String removeSpecificChars(String main, String aux) {

        if(main == null || aux == null)
            return main;

        HashSet<Character> hashSet = new HashSet<>();

        for(int i = 0; i < aux.length(); i++) {
            hashSet.add(aux.charAt(i));
        }

        StringBuilder s = new StringBuilder("");

        for(int i = 0; i < main.length(); i++) {
            if(!hashSet.contains(main.charAt(i))) {
                s.append(main.charAt(i));
            }
        }

        return s.toString();
    }
}
