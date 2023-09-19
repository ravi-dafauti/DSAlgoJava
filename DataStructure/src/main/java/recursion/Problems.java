package recursion;

import java.util.*;

public class Problems {

    /** 1.
     * this will generate all possible strings of 0 and 1 of length n
     * @param n
     */

    private static int[] array;
    public static void printStringsOfNBits(int n) {
        array = new int[n];
        printNBitsStrings(array, 0);
    }

    public static void printNBitsStrings(int[] array, int index) {
        if(index == array.length) {
            System.out.println(Arrays.toString(array));
            return;
        }
        array[index] = 0;
        printNBitsStrings(array, index + 1);
        array[index] = 1;
        printNBitsStrings(array, index + 1);
    }

    /** 2.
     * this will generate all possible strings of int from 0 to k - 1 of length n
     * @param n
     * @param k
     */

    public static void printStringsNBitsOfK(int n, int k) {
        array = new int[n];
        printStringNWithK(array, 0, k);
    }

    public static void printStringNWithK(int[] a, int index , int k) {

        if(index >= a.length) {
            System.out.println(Arrays.toString(a));
            return;
        }
        for(int i = 0; i < k; i++) {
            a[index] = i;
            printStringNWithK(a, index + 1, k);
        }
    }

    /** 3.
     * Checks if the array is sorted in ascending order
     * @param a
     * @return
     */
    public static boolean isSorted(int[] a) {
        if(a.length <= 1)
            return true;
        return checkSorted(a, 0);
    }

    public static boolean checkSorted(int[] a, int index) {
        if(index == a.length - 1)
            return true;
        return a[index] > a[index + 1] ? false : checkSorted(a, index + 1);
    }

    public static void main(String[] args) {
        //printStringsOfNBits(0);
        //printStringsNBitsOfK(0, 3);

       /* int[] a = new int[] {0, 5, 9, 0};
        System.out.println(isSorted(a));*/

       Problems problems = new Problems();
        //problems.permute(new int[]{0, 1});
        problems.partition("a");


    }


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

    /**
     * Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].
     *
     * You may return the answer in any order.
     */


/*    public List<List<Integer>> combine(int n, int k) {

        int[] numbers = new int[n];
        for(int i = 0; i<n ;i++)
            numbers[i] = i + 1;
        arr = new Integer[k];
        cb(numbers, 0, 0);
        return res;

    }

    public void cb(int[] num, int k, int index) {

        if(index == k) {
            List<Integer> list = new ArrayList<>(Arrays.asList(arr));
            res.add(list);
            return;
        }

        for(int i = index; i < num.length; i++) {
            arr[index] = num[i];
            cb(num, k, index + 1);
        }

    }*/


    boolean[][] visited;
    public boolean exist(char[][] board, String word) {
        visited = new boolean[board.length][board[0].length];
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(check(board, word, 0, i, j) == true) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean check(char[][] board, String word, int index, int x, int y) {
        if(index == word.length()) {
            return true;
        }
        if(x < 0 || x >= board.length || y < 0 || y >= board[x].length)
            return false;

        if(visited[x][y] == false && board[x][y] == word.charAt(index)) {

            visited[x][y] = true;
            if(check(board, word, index + 1, x + 1, y) == true) {
                return true;
            }
            if(check(board, word, index + 1, x, y + 1) == true) {
                return true;
            }
            if( check(board, word, index + 1, x - 1, y) == true) {
                return true;
            }
            if(check(board, word, index + 1, x , y - 1) == true) {
                return true;
            }
            visited[x][y] = false;
        }
        return false;
    }




/*    int res = 0;
    public int totalNQueens(int n) {
        boolean[][] board = new boolean[n][n];
        countValidBoardPos(board, n, 0);
        return res;
    }


    public void countValidBoardPos(boolean[][] board, int n, int column) {

        if(column == n) {
            res++;
            return;
        }

        for(int i = 0; i < n; i++) {
            if(isValidPosition(i, column, board)) {
                board[i][column] = true;
                countValidBoardPos(board, n, column + 1);
                board[i][column] = false;
            }
        }
    }


    public boolean isValidPosition(int x, int y, boolean[][] board) {

        for(int i = y - 1; i >= 0; i--) {
            if(board[x][i] == true)
                return false;
        }

        for(int i = x - 1, j = y - 1; i >=0 && j >=0; i--, j--) {
            if(board[i][j] == true)
                return false;
        }

        for(int i = x + 1, j = y - 1; i < board.length && j >= 0; i++, j--) {
            if(board[i][j] == true)
                return false;
        }
        return true;
    }*/


    Map<Character, List<Character>> map = new HashMap<>();

    public List<String> letterCombinations(String digits) {

        if(digits == null || "".equals(digits))
            return new ArrayList<>();
        initMapping(map);
        List<String> res = new ArrayList<>();
        StringBuilder s = new StringBuilder();
        letterComRecur(digits, 0, s, res);
        return res;
    }

    public void letterComRecur(String digits, int index, StringBuilder s, List<String> res) {
        if(index == digits.length()) {
            res.add(s.toString());
            return;
        }
        char currChar = digits.charAt(index);
        List<Character> charsForCurChar = map.get(currChar);
        for(Character c : charsForCurChar) {
            s.append(c);
            letterComRecur(digits, index + 1, s, res);
            s.deleteCharAt(s.length() - 1);
        }

    }



    public void initMapping(Map<Character, List<Character>> map) {
        map.put('2', Arrays.asList('a', 'b', 'c'));
        map.put('3', Arrays.asList('d', 'e', 'f'));
        map.put('4', Arrays.asList('g', 'h', 'i'));
        map.put('5', Arrays.asList('j', 'k', 'l'));
        map.put('6', Arrays.asList('m', 'n', 'o'));
        map.put('7', Arrays.asList('p', 'q', 'r', 's'));
        map.put('8', Arrays.asList('t', 'u', 'v'));
        map.put('9', Arrays.asList('w', 'x', 'y', 'z'));
    }








    public List<List<String>> solveNQueens(int n) {
        boolean[][] board = new boolean[n][n];
        List<List<String>> result = new ArrayList<>();
        countValidBoardPos(board, n, 0, result);
        return result;
    }

    public void countValidBoardPos(boolean[][] board, int n, int column, List<List<String>> result) {
        if(column == n) {
            List<String> sol = new ArrayList<>();
            for(int i = 0; i < n; i++) {
                StringBuilder s = new StringBuilder();
                for(int j = 0; j < n; j++) {
                    if(board[i][j] = false) {
                        s.append(".");
                    } else {
                        s.append("Q");
                    }
                }
                sol.add(s.toString());
            }
            result.add(sol);
            return;
        }

        for(int i = 0; i < n; i++) {
            if(isValidPosition(i, column, board)) {
                board[i][column] = true;
                countValidBoardPos(board, n, column + 1, result);
                board[i][column] = false;
            }
        }
    }


    public boolean isValidPosition(int x, int y, boolean[][] board) {

        for(int i = y - 1; i >= 0; i--) {
            if(board[x][i] == true)
                return false;
        }

        for(int i = x - 1, j = y - 1; i >=0 && j >=0; i--, j--) {
            if(board[i][j] == true)
                return false;
        }

        for(int i = x + 1, j = y - 1; i < board.length && j >= 0; i++, j--) {
            if(board[i][j] == true)
                return false;
        }
        return true;
    }



     public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        List<String> currList = new ArrayList<>();
        getPartsRecur(s, currList, result, 0);
        return result;
    }

    public void getPartsRecur(String s, List<String> currList, List<List<String>> result, int start) {
        if(start == s.length()) {
            result.add(new ArrayList<>(currList));
            return;
        }
        for(int i = start; i < s.length(); i++) {
            String currPart = s.substring(start, i + 1);
            if(checkPalindrome(currPart)) {
                currList.add(currPart);
                getPartsRecur(s, currList, result, i + 1);
                currList.remove(currList.size() - 1);
            }
        }

    }

    public boolean checkPalindrome(String s) {
        if(s == null || s.isEmpty() || s.length() == 1)
            return true;
        return (s.charAt(0) == s.charAt(s.length() - 1)) ? checkPalindrome(s.substring(1, s.length() - 1)) : false;
    }


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


    //216. Combination Sum III
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        combinationSum3Util(k, n, 1, new ArrayList<>(), result, 0);
        return result;
    }

    public void combinationSum3Util(int k, int n, int index, List<Integer> list, List<List<Integer>> result, int sum) {
        if(list.size() == k) {
            if(sum == n) {
                result.add(new ArrayList<>(list));
            }
            return;
        }
        for(int i = index; i <= 9; i++) {
            list.add(i);
            combinationSum3Util(k, n, i + 1, list, result, sum + i);
            list.remove(list.size() - 1);
        }
    }


}
