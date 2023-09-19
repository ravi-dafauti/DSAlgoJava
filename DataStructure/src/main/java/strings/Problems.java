package strings;

import java.util.*;

public class Problems {


    //Pattern matching KMP algo.
    //check if pattern P is present in text T, if yes return the index of 1st occurence of P in T, else return -1

    public static int kmp(char[] T, char[] P) {
        if(P.length > T.length)
            return  -1;

        int[] preProcessedArray = getPreprocessedArray(P);
        int i = 0;
        int j = 0;
        while(i < T.length && j< P.length) {
            if(T[i] == P[j]) {
                i++;
                j++;
            } else {
                if(j == 0) {
                    i++;
                } else {
                    j = preProcessedArray[j - 1];
                }
            }
        }
        if(j == P.length)
            return i - P.length;
        return -1;
    }

    public static int[] getPreprocessedArray(char[] P) {
        int[] a = new int[P.length];
        int i, j;
        i = 1;
        j = 0;
        a[0] = 0;

        while(i<P.length) {
            if(a[i] == a[j]) {
                a[i] = j + 1;
                i++;
                j++;
            } else {
                if(j == 0) {
                    a[i] = 0;
                    i++;
                } else {
                    j = a[j - 1];
                }
            }
        }
        return a;
    }

    // KMP end
    //Pattern matching Rabin karp.
    //check if pattern P is present in text T, if yes return the index of 1st occurence of P in T, else return -1

    public static int rabinKarp(char[] T, char[] P) {

        if(P.length > T.length)
            return -1;
        int i = 0;
        int j, k;
        long patternHash = getPatternHash(P);
        long currHash = 0;
        while (i <= T.length - P.length) {
            currHash = getRollingHash(T, currHash, i, P.length);
            if(patternHash == currHash) {
                j = 0;
                k = i;
                while(j < P.length && T[k++] == P[j++]);
                if(j == P.length)
                    return i;
                else
                    i++;
            } else {
                i++;
            }
        }

        return -1;
    }

    public static long getRollingHash(char[] T, long oldHASH, int index, int patternLength) {
        int prime = 101;
        long hash = 0;
        if(index == 0) {
            for(int i = 0; i< patternLength; i++) {
                hash += Math.pow(prime, i) * T[index++];
            }
        } else {
            hash = oldHASH - T[index - 1];
            hash = hash / prime;
            hash += Math.pow(prime, patternLength - 1) * T[index + patternLength - 1];
        }
        return hash;
    }

    public static long getPatternHash(char[] P) {
        int prime = 101;
        long hash = 0;
        for(int i = 0; i < P.length; i++) {
            hash += Math.pow(prime, i) * P[i];
        }
        return hash;
    }


    // rabin karp end

    // print all anagrams or permutations of a string
    public static void printAnagramsOfString(String s) {
        if(s.length() == 0)
            return;
        printAnagramsOfStringUtil(s.toCharArray(), 0);
    }

    public static void printAnagramsOfStringUtil(char[] string, int index) {

        if(index == string.length) {
            for(char a : string) {
                System.out.print(a);
            }
            System.out.println("");
            return;
        }

        for(int i = index; i < string.length; i++) {
            swap(string, index, i);
            printAnagramsOfStringUtil(string, index + 1);
            swap(string, index, i);
        }

    }


    public static void swap(char[] string, int a , int b) {
        char temp = string[a];
        string[a] = string[b];
        string[b] = temp;
    }


    //reverse a string of words .
    // eg my name is ravinder --> ravinder is name my


    public static String reverseWords(String s) {

        if(s.length() == 0)
            return "";
        char[] string = s.toCharArray();
        reverseString(string,0, s.length() - 1);
        int i = 0;
        int j = 0;
        while (j < string.length) {
            if(string[j] == ' ') {
                i = j + 1;
                j = j + 1;
            } else {
                while (j < string.length && string[j] != ' ')
                    j++;
                reverseString(string, i, j - 1);
            }
        }
        return String.valueOf(string);
    }

    public static void reverseString(char[] string , int s, int e) {
        if(e >= string.length || s < 0)
            return;
        for(int i = s, j = e; i < j; i++, j--) {
            swap(string, i, j);
        }
    }


    // remove Adjacent duplicates in string :

    // ABBACADE -> CADE

    public static String removeAdjacentDuplicates(String s) {
        char[] string = s.toCharArray();
        int j = -1;
        int i = 0;
        while (i < string.length) {
            if(j == -1 || string[j] != string[i]) {
                string[++j] = string[i++];
            } else {
                while (i < string.length && string[j] == string[i]) {
                    i++;
                }
                j--;
            }
        }
        StringBuilder res = new StringBuilder("");
        for(i = 0; i <=j; i++) {
            res.append(string[i]);
        }

        return res.toString();
    }


    //Given two strings, string1 and string2, the task is to find the smallest substring in string1 containing all characters of string2 efficiently.

    // Function to find smallest
    // window containing
    // all characters of 'pat'
    public String minWindow(String s, String t) {

        int count = 0;
        Map<Character, Integer> map = new HashMap<>();
        int start, end;
        start = end = 0;
        int minWindowLength = Integer.MAX_VALUE;
        int startIndex = -1;

        for(int i = 0 ; i < t.length(); i++)
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1);

        for (; end < s.length(); end++) {
            if(map.containsKey(s.charAt(end))) {
                int c = map.get(s.charAt(end));
                if(c > 0) {
                    count++;
                }
                map.put(s.charAt(end), c - 1);
            }

            if(count == t.length()) {
                while (start < end && (!map.containsKey(s.charAt(start)) || map.get(s.charAt(start)) < 0)) {
                    if(map.containsKey(s.charAt(start))) {
                        map.put(s.charAt(start), map.get(s.charAt(start)) + 1);
                    }
                    start++;
                }
                if(minWindowLength > end - start + 1) {
                    minWindowLength = end - start + 1;
                    startIndex = start;
                }
            }
        }

        if(startIndex == -1)
            return "";
        return s.substring(startIndex, startIndex + minWindowLength);
    }


    /*

    You are given a 2D array of characters and a character pattern. WAP to find if pattern is present in 2D array. Pattern can be in any way (all 8 neighbors to be considered) but you can’t use same character twice while matching. Return true if match is found, false if not.

    eg :

        Matrix
        {'A','C','P','R','C'},
        {'X','S','O','P','C'},
        {'V','O','V','N','I'},
        {'W','G','F','M','N'},
        {'Q','A','T','I','T'}


        And pattern is microsoft.
     */

    public static boolean searchWord(char[][] grid, String word)
    {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(visited[i][j] == false && grid[i][j] == word.charAt(0)) {
                    if(doesWordExists(grid, visited, word, 0, i, j, m, n)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static int[] delta_row = { -1, -1, -1, 0, 0, 1, 1, 1 };
    private static int[] delta_col = { -1, 0, 1, -1, 1, -1, 0, 1 };


    public static boolean doesWordExists(char[][] grid, boolean[][] visited, String word, int index, int i, int j, int m, int n) {
        if(index == word.length())
            return true;
        if(i >= m || i < 0 || j >= n || j < 0)
            return false;
        if(visited[i][j] == false && grid[i][j] == word.charAt(index)) {
            visited[i][j] = true;
            for(int k = 0; k < 8; k++) {
                if(doesWordExists(grid, visited, word, index + 1, i + delta_row[k], j + delta_col[k], m, n)) {
                    return true;
                }
            }
            visited[i][j] = false;
        }
        return false;
    }




    /// LEETCODE DESIGN TRIE

    class Trie {

        class TrieNode {
            boolean efl;
            char c;
            TrieNode[] children;

            public TrieNode(char c) {
                this.efl = false;
                this.c = c;
                this.children = new TrieNode[26];
            }
        }

        TrieNode root;

        public Trie() {
            root = new TrieNode('#');
        }

        public void insert(String word) {
            int pos = 0;
            TrieNode itr = root;
            while (pos < word.length()) {
                if(itr.children[word.charAt(pos) - 'a'] == null) {
                    itr.children[word.charAt(pos) - 'a'] = new TrieNode(word.charAt(pos));
                }
                itr = itr.children[word.charAt(pos) - 'a'];
                pos++;
            }
            if(itr.efl == false)
                itr.efl = true;
        }

        public boolean search(String word) {
            int pos = 0;
            TrieNode itr = root;
            while (pos < word.length()) {
                if(itr.children[word.charAt(pos) - 'a'] == null) {
                    return false;
                }
                itr = itr.children[word.charAt(pos) - 'a'];
                pos++;
            }
            if(itr.efl == false)
                return false;
            return true;
        }

        public boolean startsWith(String prefix) {
            int pos = 0;
            TrieNode itr = root;
            while (pos < prefix.length()) {
                if(itr.children[prefix.charAt(pos) - 'a'] == null) {
                    return false;
                }
                itr = itr.children[prefix.charAt(pos) - 'a'];
                pos++;
            }
            return true;
        }
    }



    ///// LEETCODE TRIE

    class WordDictionary {

        class TrieNode {
            boolean efl;
            char c;
            int count;
            TrieNode[] children;

            public TrieNode(char c) {
                this.count = 0;
                this.efl = false;
                this.c = c;
                this.children = new TrieNode[26];
            }
        }

        TrieNode root;

        public WordDictionary() {
            root = new TrieNode('#');
        }

        public void addWord(String word) {
            int pos = 0;
            TrieNode itr = root;
            while (pos < word.length()) {
                if(itr.children[word.charAt(pos) - 'a'] == null) {
                    itr.children[word.charAt(pos) - 'a'] = new TrieNode(word.charAt(pos));
                    itr.count++;
                }
                itr = itr.children[word.charAt(pos) - 'a'];
                pos++;
            }
            if(itr.efl == false)
                itr.efl = true;
        }

        public boolean search(String word) {
            return searchHelper(word, root);
        }

        public boolean searchHelper(String word, TrieNode itr) {
            if(word.length() == 0)
                return itr.efl;
            int pos = 0;
            while (pos < word.length()) {
                if(word.charAt(pos) == '.') {
                    if(itr.count == 0) {
                        return false;
                    } else {
                        for(int i = 0; i < 26; i++) {
                            if(itr.children[i] != null && searchHelper(word.substring(pos + 1), itr.children[i])) {
                                return true;
                            }
                        }
                        return false;
                    }
                } else {
                    if(itr.children[word.charAt(pos) - 'a'] == null) {
                        return false;
                    }
                    itr = itr.children[word.charAt(pos) - 'a'];
                    pos++;
                }

            }
            if(itr.efl == false)
                return false;
            return true;
        }
    }

    /// Lets see how many questions on permutations can be asked :

    // 1. can be for getting all permutations of an array or strings


    // 1. a using visited array
    /**
     *
     * Input: nums = [1,2,3]
     * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     */

    Integer[] arr;
    int[] vis;
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    public  List<List<Integer>> permute(int[] nums) {
        arr = new Integer[nums.length];
        vis = new int[nums.length];
        pr(nums, 0);
        return res;
    }

    public  void pr(int[] nums, int index) {
        if(index == nums.length) {
            List<Integer> list = new ArrayList<>(Arrays.asList(arr));
            res.add(list);
            return;
        }
        for(int i = 0; i < nums.length; i++) {
            if(vis[i] == 0) {
                arr[index] = nums[i];
                vis[i] = 1;
                pr(nums, index + 1);
                vis[i] = 0;
            }
        }
    }

    //2 . without visted array :

    // print all anagrams or permutations of a string
    public  void permuteWithoutVis(int[] nums) {
        if(nums.length == 0)
            return;
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        permuteWithoutVisUtil(res, nums, 0);
    }

    public  void permuteWithoutVisUtil(List<List<Integer>> result, int[] nums, int index) {
        if(index == nums.length) {
            List<Integer> list = new ArrayList<>();
            for(int a : nums)
                list.add(a);
            result.add(new ArrayList<>(list));
            return;
        }
        for(int i = index; i < nums.length; i++) {
            swap(nums, index, i);
            permuteWithoutVisUtil(result, nums, index + 1);
            swap(nums, index, i);
        }
    }

    public void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }


    /// Lets see how many questions on combinations can be asked :

    // 1.   all possible combinations of lenght k of n, given 2 numbers n and k
    // now this can be asked for array also, you just need to iterate array instead of numbers.

    public static List<List<Integer>> printCombinations(int n , int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        printCombinationsUtil(result, list, 1, k, n);
        return result;
    }

    public static void printCombinationsUtil(List<List<Integer>> result, List<Integer> list, int index, int k , int n) {
        if(list.size() == k) {
            result.add(new ArrayList<>(list));
            return;
        }
        for(int i = index; i <= n; i++) {
            list.add(i);
            printCombinationsUtil(result, list, i + 1, k, n);
            list.remove(list.size() - 1);
        }
    }

    //2. Secondly it can be asked as get all possible combinations of an array or a string.
    // This is bascially getting all the subsets.
    // Complexity is 2 ^ n where n is the length of the array or string . why 2 ?? as we can take the decision to select or unselect the element.


    // 2.a for array :

    public List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> set = new ArrayList<>();
        getSubsets(nums, res, set, 0);
        return res;
    }

    public void getSubsets(int[] nums, List<List<Integer>> res, List<Integer> set, int index) {
        if(index == nums.length) {
            res.add(new ArrayList<>(set));
            return;
        }
        set.add(nums[index]);
        getSubsets(nums, res, set, index + 1);
        set.remove(set.size() - 1);
        getSubsets(nums, res, set, index + 1);
    }

    // 2.b for string
    public static void printAllCombinations(String s) {
        if(s.length() == 0)
            return;
        printAllCombinationsUtil(s, "", 0);
    }

    public static void printAllCombinationsUtil(String s, String combination, int index) {
        if(index == s.length()) {
            System.out.println(combination);
            return;
        }
        combination = combination + s.charAt(index);
        printAllCombinationsUtil(s, combination, index + 1);
        combination = combination.substring(0, combination.length() - 1);
        printAllCombinationsUtil(s, combination, index + 1);
    }



    /*
    Given a matrix where every element is either ‘O’ or ‘X’, replace ‘O’ with ‘X’ if surrounded by ‘X’. A ‘O’ (or a set of ‘O’) is considered to be by surrounded by ‘X’ if there are ‘X’ at locations just below, just above, just left and just right of it.

Examples:

Input: mat[M][N] =  {{'X', 'O', 'X', 'X', 'X', 'X'},
                     {'X', 'O', 'X', 'X', 'O', 'X'},
                     {'X', 'X', 'X', 'O', 'O', 'X'},
                     {'O', 'X', 'X', 'X', 'X', 'X'},
                     {'X', 'X', 'X', 'O', 'X', 'O'},
                     {'O', 'O', 'X', 'O', 'O', 'O'},
                    };
Output: mat[M][N] =  {{'X', 'O', 'X', 'X', 'X', 'X'},
                      {'X', 'O', 'X', 'X', 'X', 'X'},
                      {'X', 'X', 'X', 'X', 'X', 'X'},
                      {'O', 'X', 'X', 'X', 'X', 'X'},
                      {'X', 'X', 'X', 'O', 'X', 'O'},
                      {'O', 'O', 'X', 'O', 'O', 'O'},
                    };
     */


    static char[][] fill(int n, int m, char a[][]) {

        for(int i = 0 ; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if((i == 0 || i == n - 1 || j == 0 || j == m - 1) && a[i][j] == 'O') {
                    dfs(n, m, a, i, j);
                }
            }
        }

        for(int i = 0 ; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(a[i][j] == 'O')
                    a[i][j] = 'X';
                else if(a[i][j] == '&')
                    a[i][j] = 'O';
            }
        }
        return a;
    }

    public static int delta_rows[] = {1, -1, 0, 0};
    public static int delta_cols[] = {0, 0, 1, -1};
    static void dfs(int n, int m, char a[][], int i , int j) {
        if(i >= n || i < 0 || j < 0 || j >= m || a[i][j] != 'O')
            return;
        if(a[i][j] == 'O')
            a[i][j] = '&';
        for(int k = 0; k < 3; k++) {
            dfs(n, m, a, i + delta_rows[k], j + delta_cols[k]);
        }
    }


    /**
     * Given two binary strings a and b, return their sum as a binary string.
     * @param a
     * @param b
     * @return
     */
    public static String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() -1, carry = 0;
        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (j >= 0) sum += b.charAt(j--) - '0';
            if (i >= 0) sum += a.charAt(i--) - '0';
            sb.append(sum % 2);
            carry = sum / 2;
        }
        if (carry != 0) sb.append(carry);
        return sb.reverse().toString();
    }


    /*
    Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:

    '?' Matches any single character.
    '*' Matches any sequence of characters (including the empty sequence).
    The matching should cover the entire input string (not partial).
     */

    class Solution {
        // using tabulation

/*        public boolean isMatch(String s, String p) {
            p = removeAdjStar(p);
            boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];

            dp[0][0] = true;
            for(int i = 1; i < p.length() + 1; i++) {
                if(p.charAt(i - 1) == '*') {
                    dp[i][0] = dp[i - 1][0];
                }
            }

            for(int i = 1; i <= p.length(); i++) {
                for(int j = 1; j <= s.length(); j++) {
                    if(p.charAt(i - 1) == '?' || p.charAt(i - 1) == s.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else if(p.charAt(i - 1) == '*') {
                        dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                    }
                }
            }

            return dp[p.length()][s.length()];
        }*/

// using recusrsion
        public boolean isMatch(String s, String p) {
            p = removeAdjStar(p);
            boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];
            return isMatchUtil(s, p, p.length(), s.length(), dp);
        }

        public boolean isMatchUtil(String s, String p, int i, int j, boolean[][] dp) {

            if(i < 0 || j < 0 || i > p.length() || j > s.length())
                return false;
            if(dp[i][j])
                return true;
            if(i == 0 && j == 0) {
                dp[i][j] = true;
            } else if (i == 0) {
                dp[i][j] = false;
            } else if(j == 0) {
                if(p.charAt(i - 1) == '*') {
                    dp[i][j] = isMatchUtil(s, p, i - 1, j, dp);
                } else {
                    dp[i][j] = false;
                }
            } else {
                if(p.charAt(i - 1) == s.charAt(j - 1) || p.charAt(i - 1) == '?') {
                    dp[i][j] = isMatchUtil(s, p, i - 1, j - 1, dp);
                } else if(p.charAt(i - 1) == '*'){
                    dp[i][j] = isMatchUtil(s, p, i, j - 1, dp) || isMatchUtil(s, p, i - 1, j, dp);
                } else {
                    dp[i][j] = false;
                }
            }
            return dp[i][j];
        }

        public String removeAdjStar(String s) {

            if(s == null || s.length() == 0)
                return s;
            StringBuilder str = new StringBuilder();
            str.append(s.charAt(0));
            char prev = s.charAt(0);
            for(int i = 1; i < s.length(); i++) {
                if(prev == '*' && s.charAt(i) == prev)
                    continue;
                else {
                    str.append(s.charAt(i));
                    prev = s.charAt(i);
                }
            }
            return str.toString();
        }
    }


    //151. Reverse Words in a String leetcode

    public static String reverseWordsNew(String s) {

        s = s.trim();
        if(s.length() == 0)
            return "";
        s = reverse(s.toCharArray(), 0, s.length() - 1);

        StringBuilder res = new StringBuilder();

        int i = 0;
        int j = 0;
        while (i < s.length()) {
            while (j < s.length() && s.charAt(j) != ' ')
                j++;
            String sub = s.substring(i, j);
            res.append(reverse(sub.toCharArray(), 0, sub.length() - 1));
            res.append(" ");
            while (j < s.length() && s.charAt(j) == ' ')
                j++;
            i = j;
        }

        return res.toString().trim();
    }

    public static String reverse(char[] s, int i, int j) {
        while (i < j) {
            char t = s[i];
            s[i] = s[j];
            s[j] = t;
            i++;
            j--;
        }
        return String.valueOf(s);
    }


    //12. Integer to Roman

    public String intToRoman(int num) {

        String roman = "";

        int[] integer = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] rom = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        for(int i = 0; i < integer.length;) {
            if(num > integer[i]) {
                roman += rom[i];
                num -=integer[i];
            } else {
                i++;
            }
        }
        return roman;

    }

    //380. Insert Delete GetRandom O(1)

    class RandomizedSet {

        Map<Integer, Integer> map;
        List<Integer> list;
        Random random;

        public RandomizedSet() {
            map = new HashMap<>();
            list = new ArrayList<>();
            random = new Random();

        }

        public boolean insert(int val) {
            if(map.containsKey(val)) {
                return false;
            } else {
                list.add(val);
                map.put(val, list.size() - 1);
                return true;
            }
        }

        public boolean remove(int val) {
            if(!map.containsKey(val)) {
                return false;
            } else {
                int index = map.get(val);
                if(index != list.size() - 1) {
                    int temp = list.get(list.size() - 1);
                    list.set(index, temp);
                    map.put(temp, index);
                }
                list.remove(list.size() - 1);
                map.remove(val);
                return true;
            }

        }

        public int getRandom() {
            return list.get(random.nextInt(list.size()));
        }
    }


    //238. Product of Array Except Self

    public int[] productExceptSelf(int[] nums) {

        int[] result = new int[nums.length];
        int[] leftProduct = new int[nums.length];
        int[] rightProduct = new int[nums.length];

        for(int i = 0; i < nums.length; i++) {
            if(i == 0)
                leftProduct[i] = nums[i];
            else
                leftProduct[i] = leftProduct[i - 1] * nums[i];
        }

        for(int i = nums.length - 1; i >=0; i--) {
            if(i == nums.length - 1)
                rightProduct[i] = nums[i];
            else
                rightProduct[i] = rightProduct[i + 1] * nums[i];
        }

        for(int i = 0; i < nums.length; i++) {
            int l = i == 0? 1 : leftProduct[i - 1];
            int r = i == nums.length - 1 ? 1 : rightProduct[i + 1];
            result[i] = l * r;
        }
        return result;
    }

    public int candy(int[] ratings) {

        int[] result = new int[ratings.length];
        Arrays.fill(result, 1);
        int candies = 0;
        for(int i = 0; i < ratings.length - 1; i++) {
            if(ratings[i] < ratings[i + 1])
                result[i + 1] = result[i] + 1;
        }
        for(int i = ratings.length - 1; i > 0; i--) {
            if(ratings[i] < ratings[i - 1])
                result[i - 1] = result[i] + 1;
            candies += result[i];
        }
        candies += result[0];
        return candies;
    }


   // 6. Zigzag Conversion
   public static String convert(String s, int numRows) {
        if(numRows == 1)
            return s;
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < numRows; i++) {
            int j = i;
            if(i == 0 || i == numRows - 1) {
                while (j < s.length()) {
                    result.append(s.charAt(j));
                    j = j + ((2 * numRows) - 2);
                }
            } else {
                boolean down = true;
                while (j < s.length()) {
                    result.append(s.charAt(j));
                    if(down) {
                        j = j + (numRows - 1 - i) * 2;
                        down = false;
                    } else {
                        j = j + i * 2;
                        down = true;
                    }
                }
            }
        }
        return result.toString();
   }


   // 68. Text Justification
    public static List<String> fullJustify(String[] words, int maxWidth) {

        List<String> result = new ArrayList<>();

        int i = 0;
        int j = 0;
        int curWidth = 0;
        int count = 0;
        while(j < words.length) {
            curWidth += words[j].length();
            if(curWidth + count < maxWidth) {
                j++;
                count++;
            } else {
                result.add(middleJustify(words, i, j - 1, maxWidth, curWidth - words[j].length()));
                i = j;
                curWidth = 0;
                count = 0;
            }
        }
        result.add(leftJustify(words, i, j - 1, maxWidth));
        return result;
    }

    public static String leftJustify(String[] words, int start, int end, int maxWidth) {
        int currWidth = 0;
        StringBuilder s = new StringBuilder();
        for(int i = start; i <= end; i++) {
            s.append(words[i]);
            currWidth += words[i].length();
            if(i != end) {
                s.append(" ");
                currWidth++;
            }
        }
        while (currWidth < maxWidth) {
            s.append(" ");
            currWidth++;
        }
        return s.toString();
    }


    public static String middleJustify(String[] words, int start, int end, int maxWidth, int widthWithoutSpace) {
        StringBuilder s = new StringBuilder();
        int spaceWidth = maxWidth - widthWithoutSpace;
        int noOfSpace = end - start;
        if(noOfSpace == 0) {
            int curWidth = words[start].length();
            s.append(words[start]);
            while (curWidth < maxWidth) {
                s.append(" ");
                curWidth++;
            }
            return s.toString();
        }
        int minSpaceToAdd = spaceWidth / noOfSpace;
        int additionalSpace = spaceWidth % noOfSpace;

        int count = 1;
        for(int i = start; i <= end; i++) {
            s.append(words[i]);
            if(i != end) {
                int currSpace = 0;
                int spaceToAdd = count <= additionalSpace ? minSpaceToAdd + 1 : minSpaceToAdd;
                while (currSpace < spaceToAdd) {
                    s.append(" ");
                    currSpace++;
                }
                count++;
            }
        }
        return s.toString();
    }




    public static void main(String[] args) {

/*        char[][] arr = {{'A','C','P','R','C'},
                {'X','S','O','P','C'},
                {'V','O','V','N','I'},
                {'W','G','F','M','N'},
                {'Q','A','T','I','T'}};

        System.out.println(searchWord(arr,  "MICROSOFT"));*/
        //printAllCombinations("abc");
        //addBinary("1010", "1011");

        //reverseWordsNew("the sky is blue");
        //System.out.println(convert("A", 1));

        String[] word = {"This", "is", "an", "example", "of", "text", "justification."};
        fullJustify(word, 16);

    }
}
