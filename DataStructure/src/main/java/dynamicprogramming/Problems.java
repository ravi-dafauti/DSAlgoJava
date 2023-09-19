package dynamicprogramming;

import stack.stackimpl.LargestAreaUnderHistogram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class Problems {


    // 1. LCS


    /**
     Given two sequences, find the length of longest subsequence present in both of them. A subsequence is a sequence that appears in the same relative order, but not necessarily contiguous. For example, “abc”, “abg”, “bdf”, “aeg”, ‘”acefg”, .. etc are subsequences of “abcdefg”.
     */

    public static void lcs( char[] X, char[] Y, int m, int n ) {

        StringBuilder res = new StringBuilder();
        int[][] result = new int[m + 1][n + 1];
        for(int i = 0; i < m + 1; i++) {
            for(int j = 0; j < n + 1; j++) {
                if(i == 0 || j == 0) {
                    result[i][j] = 0;
                } else if (X[i - 1] == Y[j - 1]) {
                        result[i][j] = result[i - 1][j - 1] + 1;
                } else {
                        result[i][j] = Math.max(result[i - 1][j], result[i][j - 1]);
                }
            }
        }

        int i = m;
        int j = n;
        while (i > 0  && j > 0) {
            if(X[i - 1] == Y[j - 1]) {
                res.append(X[i - 1]);
                i--;
                j--;
            } else if(result[i - 1][j] > result[i][j - 1]){
                i--;
            } else {
                j--;
            }
        }
        System.out.println("LCS is : "  + res);
    }


    // LCS top down

    public static int lcsTopDown(String x, String y) {

        int[][] dp = new int[x.length() + 1][y.length() + 1];
        for(int i = 0; i < x.length() + 1; i++ ) {
            for(int j = 0; j < y.length() + 1; j++) {
                dp[i][j] = -1;
            }
        }
        return lcsTopDownUtil(x,y,x.length(), y.length(), dp);
    }


    private static int lcsTopDownUtil(String x, String y, int m, int n, int[][] dp) {

        if(m == 0 || n == 0)
            return 0;
        if(dp[m][n] != -1)
            return dp[m][n];
        if(x.charAt(m - 1) == y.charAt(n - 1)) {
            dp[m][n] = 1 + lcsTopDownUtil(x, y, m -1, n-1, dp);
        } else {
            dp[m][n] = Math.max(lcsTopDownUtil(x, y, m -1, n , dp), lcsTopDownUtil(x, y, m, n-1, dp));
        }
        return dp[m][n];
    }


    //2. 0 -1 Knapsack

    /*
         0-1 Knapsack Problem
     */
    public static void knapSack(int W, int wt[],
                        int val[], int n) {


        int[][] dp = new int[n+ 1][W + 1];

        for(int i = 0; i <= n; i++) {
            for(int j = 0; j<=W; j++) {
                if(i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else {
                    if (wt[i - 1] > j) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], val[i - 1] + dp[i - 1][j - wt[i - 1]]);
                    }
                }
            }
        }

        int i = n;
        int j = W;
        List<Integer> res = new ArrayList<>();
        while (i > 0 && W > 0) {
            if(dp[i][j] == dp[i - 1][j]) {
                i--;
            } else {
                res.add(i - 1);
                j = j - wt[i - 1];
                i--;

            }
        }

        System.out.println("Values of items selected : ");
        for(Integer item  : res) {
            System.out.println(val[item]);
        }

        System.out.println("Max Value : " + dp[n][W]);
    }

    /*
         0-1 Knapsack Problem recursive
     */

    public static int knapSackRecur(int W, int wt[],
                                    int val[], int n) {


        int[][] dp = new int[n + 1][ W + 1];
        for(int i = 0; i <=n; i++) {
            for(int j = 0; j<= W; j++) {
                dp[i][j] = -1;
            }
        }

        return knapSackRecurUtil(wt, val, W, n, dp);

    }

    private static int knapSackRecurUtil(int wt[],
                                    int val[], int W,  int item, int[][] dp) {

        if(item == 0 || W == 0)
            return 0;
        if(dp[item][W] != -1)
            return dp[item][W];

        if(wt[item - 1] > W) {
            dp[item][W] = knapSackRecurUtil(wt,val,W,item - 1, dp);
        } else {
            dp[item][W] = Math.max(knapSackRecurUtil(wt,val,W,item - 1, dp), val[item - 1] + knapSackRecurUtil(wt,val,W - wt[item - 1],item - 1, dp));
        }
        return dp[item][W];
    }


    // 3. Subset sum problem
    /*
    Given a set of non-negative integers, and a value sum, determine if there is a subset of the given set with sum equal to given sum.
     */

    public static boolean subsetSum(int a[], int n, int sum) {

        boolean[][] dp = new boolean[n + 1][sum + 1];
        for(int i = 0; i <= n; i++)
            dp[i][0] = true;

        for(int j = 1; j <=sum; j++) {
               dp[0][j] = false;
        }

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <=sum; j++) {
                if(a[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - a[i - 1]];
                }
            }
        }

        return dp[n][sum];
    }

    // using top down

    public static int subsetSumRecur(int a[], int n, int sum) {

        int[][] dp = new int[n + 1][sum + 1];
        for(int i = 0; i <= n; i++) {
            for (int j = 0; j <= sum ; j++)
                dp[i][j] = -1;
        }
        return subsetSumRecurUtil(a, n, sum, dp);
    }

    public static int subsetSumRecurUtil(int a[], int n, int sum, int[][] dp) {

        if(sum == 0)
            return 1;
        if(n == 0){
            return 0;
        }

        if(dp[n][sum] != -1)
            return dp[n][sum];

        if(a[n - 1] > sum)
            dp[n][sum] = subsetSumRecurUtil(a, n - 1, sum, dp);
        else {
            if(subsetSumRecurUtil(a, n - 1, sum, dp) == 1)
                dp[n][sum] = 1;
            else
                dp[n][sum] = subsetSumRecurUtil(a, n - 1, sum - a[n - 1], dp);
        }
        return dp[n][sum];
    }



    // 4. Minimum Edit distance

    /**
     Given two strings str1 and str2 and below operations that can be performed on str1. Find minimum number of edits (operations) required to convert ‘str1’ into ‘str2’.

     Insert
     Remove
     Replace
     All of the above operations are of equal cost.

     */


    public static void EditDistDP(String str1, String str2) {

        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        for(int i = 0; i <= str1.length(); i++ )
            dp[i][0] = i;
        for(int i = 0; i<=str2.length(); i++)
            dp[0][i] = i;
        for(int i = 1; i<= str1.length(); i++) {
            for(int j = 1; j<= str2.length(); j++) {
                if(str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
            }
        }
        System.out.println("minimum edit distance is : " + dp[str1.length()][str2.length()]);
    }



    //5. Minimum jumps to reach end
    // Given an array of integers where each element represents the max number of steps that can be made forward from that element. Write a function to return the minimum number of jumps to reach the end of the array (starting from the first element). If an element is 0, they cannot move through that element. If the end isn’t reachable, return -1.


    public static int minJumps(int arr[], int n) {
        int[] dp = new int[n];
        for(int i = 0; i <  n; i++)
            dp[i] = Integer.MAX_VALUE;
        dp[0] = 0;
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < i; j++) {
                if(arr[j] >= i - j) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[n - 1];
    }

    //6. Longest Increasing Subsequence
    //The Longest Increasing Subsequence (LIS) problem is to find the length of the longest subsequence of a given sequence such that all elements of the subsequence are sorted in increasing order. For example, the length of LIS for {10, 22, 9, 33, 21, 50, 41, 60, 80} is 6


    public static int lis(int arr[], int n) {

        int[] dp = new int[n];
        for(int i = 0; i< n; i++)
            dp[i] = 1;
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < i; j++) {
                if(arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int res = Integer.MIN_VALUE;
        for(int i = 0; i< n; i++) {
            if(dp[i] > res)
                res = dp[i];
        }

        return res;
    }

    // better approach for nlogn use binary search

    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] lis = new int[n];
        int size = 0;
        lis[size++] = nums[0];
        for(int i = 1; i < n; i++) {
            if(nums[i] > lis[size - 1]) {
                lis[size++] = nums[i];
            } else {
                int index = getIndexBinarySearch(lis, 0, size - 1, nums[i]);
                lis[index] = nums[i];
            }
        }
        return size;
    }

    public int getIndexBinarySearch(int[] lis, int low , int high, int key) {
        if(low == high)
            return low;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if(key <= lis[mid])
                high = mid;
            else
                low = mid + 1;
        }
        return low;
    }

    // 7. Coin Change
    //Given a value V, if we want to make a change for V cents, and we have an infinite supply of each of C = { C1, C2, .., Cm} valued coins, what is the minimum number of coins to make the change? If it’s not possible to make a change, print -1.

    public static int coinChange(int[] a, int n, int sum) {
        int[] dp = new int[sum + 1];
        dp[0] = 0;
        for(int i = 1; i <= sum; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        for(int i = 0; i<n; i++){
            for(int j = 1; j<=sum; j++) {
                if(a[i] <= j) {
                    if(dp[j - a[i]] != Integer.MAX_VALUE && dp[j - a[i]] + 1 < dp[j]) {
                        dp[j] = dp[j - a[i]] + 1;
                    }

                }
            }
        }
        if(dp[sum] == Integer.MAX_VALUE)
            return  -1;
        return dp[sum];
    }


    // 8. Cutting a Rod

    /**

     Given a rod of length n inches and an array of prices that includes prices of all pieces of size smaller than n. Determine the maximum value obtainable by cutting up the rod and selling the pieces. For example, if the length of the rod is 8 and the values of different pieces are given as the following, then the maximum obtainable value is 22 (by cutting in two pieces of lengths 2 and 6)

     length   | 1   2   3   4   5   6   7   8
     --------------------------------------------
     price    | 1   5   8   9  10  17  17  20

     */

    public static int cutRod(int prices[], int n) {
        int[] dp = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(i <= j) {
                    dp[j] = Math.max(dp[j - i] + prices[i - 1], dp[j]);
                }
            }
        }
        return dp[n];
    }


    /**
     *
     9. Given a sequence, find the length of the longest palindromic subsequence in it.
     */


    public static int lps(String seq) {

        int[][] dp = new int[seq.length()][seq.length()];

        for(int l = 1; l <= seq.length(); l++) {
            for(int i = 0; i + l - 1 < seq.length(); i++) {
                if(l == 1) {
                    dp[i][i] = 1;
                } else {
                    if(seq.charAt(i) == seq.charAt(i + l -1)) {
                        if(l == 2) {
                            dp[i][i + l - 1] = 2;
                        } else {
                            dp[i][i + l - 1] = dp[i + 1][i + l - 2] + 2;
                        }
                    } else {
                        dp[i][i + l -1] = Math.max(dp[i + 1][i + l - 1], dp[i][i + l - 2]);
                    }
                }
            }
        }
        return dp[0][seq.length() - 1];
    }


    //10. Matrix Chain Multiplication

    /**
     *
     Given the dimension of a sequence of matrices in an array arr[], where the dimension of the ith matrix is (arr[i-1] * arr[i]), the task is to find the most efficient way to multiply these matrices together such that the total number of element multiplications is minimum.

     Input: arr[] = {40, 20, 30, 10, 30}
     Output: 26000
     Explanation:There are 4 matrices of dimensions 40×20, 20×30, 30×10, 10×30.
     Let the input 4 matrices be A, B, C and D.
     The minimum number of  multiplications are obtained by
     putting parenthesis in following way (A(BC))D.
     The minimum is 20*30*10 + 40*20*10 + 40*10*30

     */

    // Matrix Ai has dimension p[i-1] x p[i] for i = 1..n
    public static int MatrixChainOrder(int p[], int n) {

        int[][] dp = new int[n][n];

        for(int i = 1; i < n; i++)
            dp[i][i] = 0;

        for(int l = 2; l < n; l++) {
            for(int i = 1; i < n - l + 1; i++) {
                int j = i + l - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for(int k = i; k < j; k++) {
                    int res = dp[i][k] + dp[k + 1][j] + p[i - 1] * p[k] * p[j];
                    if(res < dp[i][j])
                        dp[i][j] = res;
                }

            }
        }

        return dp[1][n - 1];
    }


    // 11. Maximum size square sub-matrix with all 1s

    // *** DONE

    public int maximalSquare(char[][] matrix) {
        int[][] dp = new int[matrix.length][matrix[0].length];
        int max = 0;
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == '1') {
                    if(i == 0 || j == 0) {
                        dp[i][j] = 1;
                        if(dp[i][j] > max)
                            max = dp[i][j];
                    } else {
                        int c = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
                        dp[i][j] = c + 1;
                        if(dp[i][j] > max)
                            max = dp[i][j];
                    }
                }

            }
        }
        return max * max;
    }

    // 12. Maximum size rectangle binary sub-matrix with all 1s

    // DONE ******
    public static int maxRectangle(int R, int C, int A[][]) {
        int[] dp = new int[C];
        int maxArea = Integer.MIN_VALUE;
        for(int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(A[i][j] == 0) {
                    dp[j] = 0;
                } else {
                    dp[j] += A[i][j];
                }
            }
            int maxAreaForRow = LargestAreaUnderHistogram.getLargestAreaUnderHistogram(dp);
            if(maxAreaForRow > maxArea)
                maxArea = maxAreaForRow;
        }
        return maxArea;
    }




/*    public static int maximumSubSquare(int[][] arr) {
        int R = arr.length;
        int C = arr[0].length;
        class Pair {
            int x;
            int y;

            public Pair(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
        Pair[][] dp = new Pair[R][C];

        for(int i = 0; i< R; i++) {
            if(arr[i][0] == 'O') {
                dp[i][0] = new Pair(0, 0);
            } else {
                int y = (i == 0 ? 1 : dp[i - 1][0].y + 1);
                dp[i][0] = new Pair(1, y);
            }
        }

        for(int i = 1; i < C; i++) {
            if(arr[0][i] == 'O') {
                dp[0][i] = new Pair(0, 0);
            } else {
                dp[0][i] = new Pair(dp[0][i - 1].x + 1, 1);
            }
        }


        for(int i = 1; i < R; i++) {
            for(int j = 1; j < C; j++) {
                if(arr[i][j] == 'O') {
                    dp[i][j] = new Pair(0,0);
                } else {
                    dp[i][j] = new Pair(dp[i][j - 1].x + 1, dp[i - 1][j].y + 1);
                }
            }
        }

        int result = 0;
        for(int i = R - 1; i >= 0; i--) {
            for(int j = C - 1; j >= 0; j--) {
                int min = Math.min(dp[i][j].x, dp[i][j].y);
                while (min > result) {
                    if(dp[i - min + 1][j].x >= min && dp[i][j - min + 1].y >= min) {
                        result = min;
                        break;
                    } else {
                        min--;
                    }
                }
            }
        }
        return result;
    }*/


    // 13. Given a matrix of 1 and 0, find the largest subsquare surrounded by 1

    // **** DONE
    class Pair {
        int x;
        int y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int largest1BorderedSquare(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        Pair[][] dp = new Pair[m][n];


        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(i == 0) {
                    if(grid[i][j] == 1) {
                        if(j != 0)
                            dp[i][j] = new Pair(dp[i][j - 1].x + 1, 1);
                        else
                            dp[i][j] = new Pair(1, 1);
                    } else {
                        dp[i][j] = new Pair(0, 0);
                    }
                } else if(j == 0) {
                    if(grid[i][j] == 1) {
                        if(i != 0)
                            dp[i][j] = new Pair(1, dp[i - 1][j].y + 1);
                        else
                            dp[i][j] = new Pair(1, 1);
                    } else {
                        dp[i][j] = new Pair(0, 0);
                    }
                } else {
                    if(grid[i][j] == 1) {
                        dp[i][j] = new Pair(dp[i][j - 1].x + 1, dp[i - 1][j].y + 1);
                    } else {
                        dp[i][j] = new Pair(0, 0);
                    }
                }
            }
        }

        int res = 0;

        for(int i = m - 1; i >=0; i--) {
            for(int j = n - 1; j >=0; j--) {
                int count = Math.min(dp[i][j].x, dp[i][j].y);
                if(res < count) {
                    while (res < count) {
                        if(dp[i][j - count + 1].y >= count && dp[i - count + 1][j].x >= count){
                            res = count;
                            break;
                        } else {
                            count--;
                        }
                    }
                }
            }
        }

        return res * res;

    }

    //14. Program for nth Catalan Number

    /**
     *

     Catalan numbers are a sequence of natural numbers that occurs in many interesting counting problems like following.

     Count the number of expressions containing n pairs of parentheses which are correctly matched. For n = 3, possible expressions are ((())), ()(()), ()()(), (())(), (()()).
     Count the number of possible Binary Search Trees with n keys (See this)
     Count the number of full binary trees (A rooted binary tree is full if every vertex has either two children or no children) with n+1 leaves.
     Given a number n, return the number of ways you can draw n chords in a circle with 2 x n points such that no 2 chords intersect.
     Given preorder traversal count no of binary tree possible.

     for more  : https://www.geeksforgeeks.org/applications-of-catalan-numbers/

     */

     // **** DONE
    public static int numberOfBST(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n; i++) {
            for(int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }


    // 15. Optimal Binary Search Tree

    /**
     *
     Given a sorted array key [0.. n-1] of search keys and an array freq[0.. n-1] of frequency counts, where freq[i] is the number of searches for keys[i]. Construct a binary search tree of all keys such that the total cost of all the searches is as small as possible.
     Let us first define the cost of a BST. The cost of a BST node is the level of that node multiplied by its frequency. The level of the root is 1.
     */

    public static int optimalSearchTree(int keys[], int freq[], int n) {
        int[][] dp = new int[n][n];
        for(int i = 0; i < n; i++)
            dp[i][i] = freq[i];
        for(int L = 2; L <= n; L++) {
            for (int i = 0; i < n - L + 1; i++) {
                int j = i + L - 1;
                int cost = sum(freq, i, j);
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j; k++) {
                    int c = (k > i ? dp[i][k - 1] : 0) + (k < j ? dp[k + 1][j] : 0) + cost;
                    dp[i][j] = Math.min(dp[i][j], c);
                }
            }
        }
        return dp[0][n - 1];
    }

    static int sum(int freq[], int i, int j) {
        int s = 0;
        for (int k = i; k <= j; k++) {
            s += freq[k];
        }
        return s;
    }

    // 16. Longest Common Substring
    /*
       Given two strings ‘X’ and ‘Y’, find the length of the longest common substring.
     */

    // ****** DONE

    static int LCSubStr(String s,String t) {
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        int max = 0;
        for(int i = 1; i <= s.length(); i++) {
            for(int j = 1; j <= t.length(); j++) {
                if(s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max;
    }



    //17. Coin Change for all possible solutions
    /*
    Given a value sum, if we want to make change for sum cents, and we have an infinite supply of each of coins[] = { coins1, coins2, .. , coinsn} valued coins, how many ways can we make the change? The order of coins doesn’t matter.

        Examples:

        Input: sum = 4, coins[] = {1,2,3},
        Output: 4
        Explanation: there are four solutions: {1, 1, 1, 1}, {1, 1, 2}, {2, 2}, {1, 3}.

        Input: sum = 10, coins[] = {2, 5, 3, 6}
        Output: 5
        Explanation: There are five solutions:
        {2,2,2,2,2}, {2,2,3,3}, {2,2,6}, {2,3,5} and {5,5}.
     */

    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for(int i = 0; i < coins.length; i++) {
            for(int j = 1; j <= amount; j++) {
                if(coins[i] <= j) {
                    dp[j] += dp[j - coins[i]];
                }
            }
        }
        return dp[amount];
    }


    // 18. Min Cost Path
    /*
    Given a cost matrix cost[][] and a position (m, n) in cost[][], write a function that returns cost of minimum cost path to reach (m, n) from (0, 0). Each cell of the matrix represents a cost to traverse through that cell. The total cost of a path to reach (m, n) is the sum of all the costs on that path (including both source and destination). You can only traverse down, right and diagonally lower cells from a given cell, i.e., from a given cell (i, j), cells (i+1, j), (i, j+1), and (i+1, j+1) can be traversed. You may assume that all costs are positive integers.
     */

    public static int minCost(int cost[][], int m, int n) {
        int[][] dp = new int[m][n];
        dp[0][0] = cost[0][0];
        for(int i = 1; i < n; i++)
            dp[0][i] = dp[0][i - 1] + cost[0][i];
        for(int i = 1; i < m; i++)
            dp[i][0] = dp[i - 1][0] + cost[i][0];

        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                dp[i][j] = cost[i][j] + Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
            }
        }
        return dp[m - 1][n - 1];
    }

    //19. same types total ways to reach from 0,0 to m,n, see tushar roy video


    //20. Optimal Strategy for a Game
    /*

    Consider a row of n coins of values v1 . . . vn, where n is even. We play a game against an opponent by alternating turns. In each turn, a player selects either the first or last coin from the row, removes it from the row permanently, and receives the value of the coin. Determine the maximum possible amount of money we can definitely win if we move first.
Note: The opponent is as clever as the user.

Let us understand the problem with few examples:

5, 3, 7, 10 : The user collects maximum value as 15(10 + 5)
8, 15, 3, 7 : The user collects maximum value as 22(7 + 15)
     */

    public static int optimalStrategyOfGame(int arr[], int n) {
        class Pair {
            int x;
            int y;

            public Pair(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
        Pair[][] dp = new Pair[n][n];
        for(int i = 0; i < n; i++) {
            dp[i][i] = new Pair(arr[i], 0);
        }
        for (int L = 2; L <= n; L++) {
            for(int i = 0; i < n - L + 1; i++) {
                int j = i + L - 1;
                if(arr[i] + dp[i + 1][j].y > arr[j] + dp[i][j - 1].y) {
                    dp[i][j] = new Pair(arr[i] + dp[i + 1][j].y, dp[i + 1][j].x);
                } else {
                    dp[i][j] = new Pair(arr[j] + dp[i][j - 1].y, dp[i][j - 1].x);
                }
            }
        }
        return dp[0][n - 1].x;
    }

    // 21. Egg Dropping Puzzle

    public static int eggDrop(int n, int k) {


        int[][] dp = new int[n + 1][k + 1];

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= k; j++) {
                if(i == 1) {
                    dp[i][j] = j;
                } else if(i > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    int c = Integer.MAX_VALUE;
                    for(int f = 1; f <=j; f++) {
                        int t = Math.max(1 + dp[i - 1][f - 1], 1 + dp[i][j - f]);
                        if(t < c)
                            c = t;
                    }
                    dp[i][j] = c;
                }
            }
        }

        return dp[n][k];
    }


    // 22. stair case problem. typical problem with 1 and 2 steps is fibonaci no. we will do for 1, 2, ... m steps to reach n

    // Java program to count number of ways
    // to reach n't stair when a person
    // can climb 1, 2, ..m stairs at a time
    public static int countWays(int n, int m) {

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n; i++) {
            dp[i] = 0;
            for(int j = i - 1, k = 1; j >=0 && k<=m; j--, k++) {
                dp[i] += dp[j];
            }
        }
        return dp[n];
    }

    //23. Weighted Job Scheduling

    /*
    Given N jobs where every job is represented by following three elements of it.

        Start Time
        Finish Time
        Profit or Value Associated (>= 0)
        Find the maximum profit subset of jobs such that no two jobs in the subset overlap.
     */


    class Job {
        int start, finish, profit;
        Job(int start, int finish, int profit)
        {
            this.start = start;
            this.finish = finish;
            this.profit = profit;
        }
    }
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        Job[] jobs = new Job[startTime.length];
        for(int i = 0; i < startTime.length; i++) {
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        }
        int[] dp = new int[startTime.length];
        Arrays.sort(jobs, (a, b) -> a.start - b.start);
        dp[0] = jobs[0].profit;
        for(int i = 1; i < jobs.length; i++) {
            int inclusiveProfit = jobs[i].profit;
            for (int j = i - 1; j >=0 ; j--) {
                if(jobs[j].finish <= jobs[i].start) {
                    inclusiveProfit += dp[j];
                    break;
                }
            }

            dp[i] = Math.max(dp[i - 1], inclusiveProfit);
        }
        return dp[startTime.length - 1];
    }

    //better approach fast
    public int jobSchedulingFast(int[] startTime, int[] endTime, int[] profit) {
        Job[] jobs = new Job[startTime.length];
        for(int i = 0; i < startTime.length; i++) {
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        }
        int[] dp = new int[startTime.length];
        Arrays.sort(jobs, (a, b) -> a.finish - b.finish);
        dp[0] = jobs[0].profit;
        for(int i = 1; i < jobs.length; i++) {
            int inclusiveProfit = jobs[i].profit;
            for (int j = i - 1; j >=0 ; j--) {
                if(jobs[j].finish <= jobs[i].start) {
                    inclusiveProfit += dp[j];
                    break;
                }
            }

            dp[i] = Math.max(dp[i - 1], inclusiveProfit);
        }
        return dp[startTime.length - 1];
    }

    // 24. Word Break Problem

    /*

    Given an input string and a dictionary of words, find out if the input string can be segmented into a space-separated sequence of dictionary words. See following examples for more details.
    This is a famous Google interview question, also being asked by many other companies now a days.

    Consider the following dictionary
    { i, like, sam, sung, samsung, mobile, ice,
      cream, icecream, man, go, mango}

    Input:  ilike
    Output: Yes
    The string can be segmented as "i like".

    Input:  ilikesamsung
    Output: Yes
    The string can be segmented as "i like samsung"
    or "i like sam sung".
     */


    private static boolean dictionaryContains(String word)
    {
        String dictionary[] = {"mobile","samsung","sam","sung","man","mango",
                "icecream","and","go","i","like","ice","cream"};
        int size = dictionary.length;
        for (int i = 0; i < size; i++)
            if (dictionary[i].compareTo(word) == 0)
                return true;
        return false;
    }

    public static boolean wordBreak(String str) {

        if(str == null)
            return false;
        if(str.length() == 0) {
            return true;
        }
        boolean[][] dp = new boolean[str.length()][str.length()];

        for(int i = 0; i < str.length(); i++) {
            if(dictionaryContains(str.substring(i, i + 1))) {
                dp[i][i] = true;
            }
        }

        for(int L = 2; L <= str.length(); L++) {
            for(int i = 0; i < str.length() - L + 1; i++) {
                int j = i + L - 1;
                if(dictionaryContains(str.substring(i, i + L)))
                    dp[i][j] = true;
                else {
                    for(int k = i; k < j; k++) {
                        if(dp[i][k] && dp[k + 1][j]) {
                            dp[i][j] = true;
                            break;
                        }
                    }
                }
            }

        }
        return dp[0][str.length() - 1];
    }

    //25. Maximum Sum Increasing Subsequence
    /*
    Given an array of n positive integers. Write a program to find the sum of maximum sum subsequence of the given array such that the integers in the subsequence are sorted in increasing order. For example, if input is {1, 101, 2, 3, 100, 4, 5}, then output should be 106 (1 + 2 + 3 + 100), if the input array is {3, 4, 5, 10}, then output should be 22 (3 + 4 + 5 + 10) and if the input array is {10, 5, 4, 3}, then output should be 10
     */

    public static int maxSumIS(int arr[], int n) {
        int[] dp = new int[n];
        int[] res = new int[n];

        for(int i = 0; i < n; i++){
            dp[i] = arr[i];
            res[i] = i;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] <= arr[i]) {
                    if (dp[i] < dp[j] + arr[i]){
                        dp[i] = dp[j] + arr[i];
                        res[i] = j;
                    }
                }
            }
        }

        int max = Integer.MIN_VALUE;
        int maxIndex = 0;
        for (int i = 0; i < n; i++){
            if(dp[i] > max) {
                max = dp[i];
                maxIndex = i;
            }

        }

        // to print the sequence
        int j = maxIndex;
        List<Integer> result = new ArrayList<>();

        while (j != res[j]){
            result.add(arr[j]);
            j = res[j];
        }
        result.add(arr[j]);

        System.out.println(result);
        return max;
    }


    // 26. Given a positive integer N, count all possible distinct binary strings of length N such that there are no consecutive 1’s.
    // its fibonaci :)


    // 27. Largest Sum Contiguous Subarray (Kadane’s Algorithm)

    // Given an array arr[] of size N. The task is to find the sum of the contiguous subarray within a arr[] with the largest sum.

    public static int maxSubArraySum(int a[]) {

        int maxSum = Integer.MIN_VALUE;
        int currMaxSum = 0;
        int maxStart = 0;
        int maxEnd = 0;
        int curMaxStart = 0;

        for(int i = 0; i < a.length; i++) {
            currMaxSum = currMaxSum + a[i];
            if(currMaxSum  > maxSum) {
                maxSum = currMaxSum;
                maxStart = curMaxStart;
                maxEnd = i;
            }
            if(currMaxSum < 0) {
                currMaxSum = 0;
                curMaxStart = i + 1;
            }
        }

        //System.out.println("Max array is : " + maxStart  + ", " + maxEnd); uncomment to print
        return maxSum;
    }

    //28. Maximum sum rectangle in a 2D matrix
    /*
    Given a 2D array, find the maximum sum submatrix in it
     */

    public static int maxSumRectangle(int[][] mat) {

        int rows = mat.length;
        int cols = mat[0].length;
        int[] sum = new int[rows];

        int currentMax;
        int max = Integer.MIN_VALUE;
        for(int L = 0; L < cols; L++) {
            for(int i = 0; i < rows; i++) {
                sum[i] = 0;
            }
            for(int R = L; R < cols; R++) {
                for(int i = 0; i < rows; i++) {
                    sum[i] += mat[i][R];
                }
                currentMax = maxSubArraySum(sum);
                if(currentMax > max)
                    max = currentMax;
             }
        }

        return max;
    }


    //29. Submatrix Sum Queries
    /*
    Given a matrix of size M x N, there are large number of queries to find submatrix sums. Inputs to queries are left top and right bottom indexes of submatrix whose sum is to find out.
    How to preprocess the matrix so that submatrix sum queries can be performed in O(1) time.
     */

    public static int sumQuery(int mat[][], int tli,
                                 int tlj, int rbi, int rbj) {


        int rows = mat.length;
        int cols = mat[0].length;
        //create aux array to querry in O(1)
        int[][] aux = new int[rows + 1][cols + 1];
        for(int i = 1; i <= rows; i++) {
            for(int j = 1; j <= cols; j++) {
                aux[i][j] = mat[i - 1][j - 1] + aux[i - 1][j] + aux[i][j - 1] - aux[i - 1][j - 1];
            }
        }
        return aux[rbi + 1][rbj + 1]  + aux[tli][tlj] - aux[tli][rbj + 1] - aux[rbi + 1][tlj];
    }

    //30 . Longest Bitonic Subsequence
    /*
    Given an array arr[0 … n-1] containing n positive integers, a subsequence of arr[] is called Bitonic if it is first increasing, then decreasing. Write a function that takes an array as argument and returns the length of the longest bitonic subsequence.
     */

    public static int lbs( int arr[], int n ) {

        int[] lisLeft = new int[n];
        int[] lisRight = new int[n];

        for(int i = 0; i < n; i++) {
            lisLeft[i] = 1;
            lisRight[i] = 1;
        }

        for(int i = 1; i < n; i++) {
            for(int j = 0; j < i; j++) {
                lisLeft[i] = arr[i] > arr[j] ? Math.max(lisLeft[i], lisLeft[j] + 1) : lisLeft[i];
            }
        }

        for(int i = n - 2; i >= 0; i--) {
            for(int j = n - 1; j > i; j--) {
                lisRight[i] = arr[i] > arr[j] ? Math.max(lisRight[i], lisRight[j] + 1)  : lisRight[i];
            }
        }
        int maxbitonic = 0;

        for(int i = 0; i < n; i++) {
            if(lisLeft[i] + lisRight[i] - 1 > maxbitonic)
                maxbitonic = lisLeft[i] + lisRight[i] - 1;
        }

        return maxbitonic;
    }


    //31. Find if a string is interleaved of two other strings

    /*
    Given three strings A, B and C. Write a function that checks whether C is an interleaving of A and B. C is said to be interleaving A and B, if it contains all and only characters of A and B and order of all characters in individual strings is preserved.
        Input: strings: "XXXXZY", "XXY", "XXZ"
        Output: XXXXZY is interleaved of XXY and XXZ
        The string XXXXZY can be made by
        interleaving XXY and XXZ
        String:    XXXXZY
        String 1:    XX Y
        String 2:  XX  Z

        Input: strings: "XXY", "YX", "X"
        Output: XXY is not interleaved of YX and X
        XXY cannot be formed by interleaving YX and X.
        The strings that can be formed are YXX and XYX
     */


    // The main function that returns
// true if C is an interleaving of A
// and B, otherwise false.
    public static boolean isInterleaved(String A, String B,
                                 String C) {


        boolean[][] dp = new boolean[A.length() + 1][B.length() + 1];
        dp[0][0] = true;
        for(int i = 1; i <= B.length(); i++) {
            if(C.charAt(i - 1) == B.charAt(i - 1) && dp[0][i - 1] == true)
                dp[0][i] = true;
            else
                dp[0][i] = false;
        }

        for(int i = 1; i <= A.length(); i++) {
            if(C.charAt(i - 1) == A.charAt(i - 1) && dp[i - 1][0] == true)
                dp[i][0] = true;
            else
                dp[i][0] = false;
        }


        for(int i = 1; i <= A.length(); i++) {
            for(int j = 1; j<=B.length(); j++) {
                if(C.charAt(i + j - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i][j - 1];
                } else if (C.charAt(i + j - 1) == A.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = false;
                }
            }
        }

        return dp[A.length()][B.length()];
    }


    // 32. Maximum sum such that no two elements are adjacent
    /*
    Given an array arr[] of positive numbers, the task is to find the maximum sum of a subsequence with the constraint that no 2 numbers in the sequence should be adjacent in the array.
     */

    public static int findMaxSum(int arr[], int n) {

        int inclusive = arr[0];
        int exclusive = 0;

        for(int i = 1; i < n; i++) {

            int exclusiveSum = inclusive;
            int inclusiveSum = arr[i] + exclusive;
            exclusive = inclusive;
            inclusive = Math.max(exclusiveSum, inclusiveSum);

        }

        return inclusive;

    }

    //33. Palindrome Partitioning
    /*
    Given a string, a partitioning of the string is a palindrome partitioning if every substring of the partition is a palindrome. For example, “aba|b|bbabb|a|b|aba” is a palindrome partitioning of “ababbbabbababa”. Determine the fewest cuts needed for a palindrome partitioning of a given string. For example, minimum of 3 cuts are needed for “ababbbabbababa”. The three cuts are “a|babbbab|b|ababa”. If a string is a palindrome, then minimum 0 cuts are needed. If a string of length n containing all different characters, then minimum n-1 cuts are needed.
     */

    public static int minPalPartion(String str) {

        int[][] dp = new int[str.length()][str.length()];
        for(int i = 0; i < str.length(); i++) {
            dp[i][i] = 0;
        }
        for(int L = 2; L <= str.length(); L++) {
            for(int i = 0; i < str.length() - L + 1; i++) {
                int j = i + L -1;

                if(L == 2) {
                    if(str.charAt(i) == str.charAt(j)) {
                        dp[i][j] = 0;
                    } else {
                        dp[i][j] = 1;
                    }
                } else {
                    if(str.charAt(i) == str.charAt(j) && dp[i + 1][j - 1] == 0) {
                        dp[i][j] = 0;
                    } else {
                        dp[i][j] = Integer.MAX_VALUE;
                        for(int k = i; k < j; k++) {
                            dp[i][j] = (dp[i][k] + dp[k + 1][j] + 1 < dp[i][j]) ? dp[i][k] + dp[k + 1][j] + 1 : dp[i][j];
                        }

                    }
                }
            }
        }
        return dp[0][str.length() - 1];
    }


    //34.Wildcard Pattern Matching
    /*
    Given a text and a wildcard pattern, implement wildcard pattern matching algorithm that finds if wildcard pattern is matched with text. The matching should cover the entire text (not partial text). The wildcard pattern can include the characters ‘?’ and ‘*’

‘?’ – matches any single character
‘*’ – Matches any sequence of characters (including the empty sequence)
For example:

Text = "baaabab",
Pattern = “*****ba*****ab", output : true
Pattern = "baaa?ab", output : true
Pattern = "ba*a?", output : true
Pattern = "a*ab", output : false
     */

    public static boolean strmatch(String str, String pattern,
                            int n, int m) {

        pattern = removeAdajcentStar(pattern);
        boolean[][] dp = new boolean[str.length() + 1][pattern.length() + 1];
        dp[0][0] = true;
        for(int i = 1; i <= pattern.length(); i++) {
            if(pattern.charAt(i - 1) == '*' && i == 1)
                dp[0][i] = true;
            else
                dp[0][i] = false;
        }
        for(int i = 1; i <= str.length(); i++)
            dp[i][0] = false;

        for(int i = 1; i <= str.length(); i++) {
            for(int j = 1; j<= pattern.length(); j++) {
                if(pattern.charAt(j - 1) == '?' || pattern.charAt(j - 1) == str.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if(pattern.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                } else
                    dp[i][j] = false;
            }
        }
        return dp[str.length()][pattern.length()];
    }

    public static String removeAdajcentStar(String s) {

        if(s == null || s.length() == 0)
            return s;
        char[] arr = s.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        int prevChar = s.charAt(0);
        stringBuilder.append(s.charAt(0));
        for(int i = 1; i < arr.length; i++) {
            if(arr[i] == '*' && prevChar == '*')
                continue;
            prevChar = arr[i];
            stringBuilder.append(arr[i]);
        }

        return stringBuilder.toString();

    }

    //35. Box Stacking Problem
    /*
    You are given a set of n types of rectangular 3-D boxes, where the i^th box has height h(i), width w(i) and depth d(i) (all real numbers). You want to create a stack of boxes which is as tall as possible, but you can only stack a box on top of another box if the dimensions of the 2-D base of the lower box are each strictly larger than those of the 2-D base of the higher box. Of course, you can rotate a box so that any side functions as its base. It is also allowable to use multiple instances of the same type of box.
     */

    /* Representation of a box */
    static class Box implements Comparable<Box>{

        // h --> height, w --> width,
        // d --> depth
        int h, w, d, area;

        // for simplicity of solution,
        // always keep w <= d

        /*Constructor to initialise object*/
        public Box(int h, int w, int d) {
            this.h = h;
            this.w = w;
            this.d = d;
        }

        /*To sort the box array on the basis
        of area in decreasing order of area */
        @Override
        public int compareTo(Box o) {
            return o.area-this.area;
        }
    }

    static int maxStackHeight( Box arr[], int n){

        int[] dp = new int[n * 3];
        Box[] allBoxes = new Box[n * 3];
        for(int i = 0, j = 0; i < n; i++) {
            allBoxes[j] = new Box(arr[i].h, Math.min(arr[i].w, arr[i].d), Math.max(arr[i].w, arr[i].d));
            allBoxes[j].area = allBoxes[j].d * allBoxes[j].w;
            j++;
            allBoxes[j] = new Box(arr[i].w, Math.min(arr[i].h, arr[i].d), Math.max(arr[i].h, arr[i].d));
            allBoxes[j].area = allBoxes[j].d * allBoxes[j].w;
            j++;
            allBoxes[j] = new Box(arr[i].d, Math.min(arr[i].h, arr[i].w), Math.max(arr[i].h, arr[i].w));
            allBoxes[j].area = allBoxes[j].d * allBoxes[j].w;
            j++;
        }
        Arrays.sort(allBoxes);
        for(int i = 0; i < n * 3; i++) {
            dp[i] = allBoxes[i].h;
        }
        int max = Integer.MIN_VALUE;

        for(int i = 1; i < n * 3; i++) {
            for(int j = 0; j < i; j++) {
                if(allBoxes[i].w < allBoxes[j].w && allBoxes[i].d < allBoxes[j].d) {
                    dp[i] = Math.max(dp[i], dp[j] + allBoxes[i].h);
                    if(dp[i] > max)
                        max = dp[i];

                }
            }
        }
        return max;

    }

    // box stacking leetcode 1691. Maximum Height by Stacking Cuboids

    public int maxHeight(int[][] cuboids) {
        for(int i = 0; i < cuboids.length; i++)
            Arrays.sort(cuboids[i]);
        Arrays.sort(cuboids, (a, b) -> ((b[0] * b[1]) == (a[0] * a[1]) ? b[2] - a[2] : (b[0] * b[1]) - (a[0] * a[1])));
        int[] dp = new int[cuboids.length];
        for(int i = 0; i < cuboids.length; i++) {
            dp[i] = cuboids[i][2];
        }
        int max = cuboids[0][2];
        for(int i = 1; i < cuboids.length; i++) {
            for(int j = 0; j < i; j++) {
                if(cuboids[j][0] >= cuboids[i][0] && cuboids[j][1] >= cuboids[i][1] && cuboids[j][2] >= cuboids[i][2]) {
                    dp[i] = Math.max(dp[i], cuboids[i][2] + dp[j]);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    //russian doll very imp as it does LIS in O(nlogn)

    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (a, b) -> (a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]));
        int[] lis = new int[envelopes.length];
        int size = 0;
        lis[size++] = envelopes[0][1];
        for(int i = 1; i < envelopes.length; i++) {
            if(envelopes[i][1] > lis[size - 1]) {
                lis[size++] = envelopes[i][1];
            } else {
                int index = getIndex(lis, 0, size - 1, envelopes[i][1]);
                lis[index] = envelopes[i][1];
            }
        }
        return size;
    }

    public int getIndex(int[] lis, int low , int high, int key) {
        if(low == high)
            return low;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if(key <= lis[mid])
                high = mid;
            else
                low = mid + 1;
        }
        return low;
    }

    //36. Burst Balloon to maximize coins
    /*
    We have been given N balloons, each with a number of coins associated with it. On bursting a balloon i, the number of coins gained is equal to A[i-1]*A[i]*A[i+1]. Also, balloons i-1 and i+1 now become adjacent. Find the maximum possible profit earned after bursting all the balloons. Assume an extra 1 at each boundary.
    Examples:

    Input : 5, 10
    Output : 60
    Explanation - First Burst 5, Coins = 1*5*10
                  Then burst 10, Coins+= 1*10*1
                  Total = 60

    Input : 1, 2, 3, 4, 5
    Output : 110
     */

    public static int getMax(int[] A, int N) {
        int[][] dp = new int[N][N];
        for(int L = 1; L <= N ; L++) {
            for(int i = 0; i < N - L + 1; i++) {
                int j = i + L -1;
                int left = (i == 0) ? 1 : A[i - 1];
                int right = (j == N - 1) ? 1 : A[j + 1];
                if(L == 1) {
                    dp[i][j] = left * A[i] * right;
                } else {
                    int max = Integer.MIN_VALUE;
                    for(int k = i; k <= j; k++) {
                        int l = (k == i) ? 0 : dp[i][k - 1];
                        int r = (k == j) ? 0 : dp[k + 1][j];
                        int currMax = l + left * A[k] * right + r;
                        if(max < currMax)
                            max = currMax;
                    }
                    dp[i][j] = max;
                }
            }
        }
        return dp[0][N - 1];
    }




    //37. Longest Palindromic Substring, Given a string, find the longest substring which is a palindrome.

    public static int longestPalSubstr(String str) {

        boolean[][] dp = new boolean[str.length()][str.length()];
        for(int i = 0; i < str.length(); i++)
            dp[i][i] = true;
        int max = 1;
        for(int L = 2; L <= str.length(); L++) {
            for(int i = 0; i < str.length() - L + 1; i++) {
                int j = i + L - 1;
                if(str.charAt(i) == str.charAt(j)) {
                    if(L == 2) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                    if(dp[i][j] == true && max < L) {
                        max = L;
                    }
                } else {
                    dp[i][j] = false;
                }

            }
        }
        return max;
    }

    //38. Floyd Warshall Algorithm
    /*
    The Floyd Warshall Algorithm is for solving all pairs shortest path problems. The problem is to find the shortest distances between every pair of vertices in a given edge-weighted directed Graph.
     */

    public static void floydWarshall(int graph[][]) {

        int[][] dp = new int[graph.length][graph.length];
        for(int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                dp[i][j] = graph[i][j];
            }
        }
        for(int k = 0; k < graph.length; k++) {
            for(int i = 0; i < graph.length; i++) {
                for(int j = 0; j < graph.length; j++){
                    if(dp[i][k] != Integer.MAX_VALUE && dp[k][j] != Integer.MAX_VALUE && dp[i][k] + dp[k][j] < dp[i][j]) {
                        dp[i][j] = dp[i][k] + dp[k][j];
                    }
                }
            }
        }

        printSolution(dp);
    }

    private static void printSolution(int dist[][])
    {
        System.out.println(
                "The following matrix shows the shortest "
                        + "distances between every pair of vertices");
        for (int i = 0; i < dist.length; ++i) {
            for (int j = 0; j < dist.length; ++j) {
                if (dist[i][j] == Integer.MAX_VALUE)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i][j] + "   ");
            }
            System.out.println();
        }
    }

    //39. Boolean Parenthesization Problem
    /*
    Given a boolean expression with the following symbols.
    Symbols
        'T' ---> true
        'F' ---> false
    And following operators filled between symbols

    Operators
        &   ---> boolean AND
        |   ---> boolean OR
        ^   ---> boolean XOR
    Count the number of ways we can parenthesize the expression so that the value of expression evaluates to true.
    Let the input be in form of two arrays one contains the symbols (T and F) in order and the other contains operators (&, | and ^}
     */
    public static int countParenth(char symb[],
                                   char oper[]) {
        int n = symb.length;
        int[][] T = new int[n][n];
        int[][] F = new int[n][n];
        for(int i = 0; i < n; i++) {
            T[i][i] = symb[i] == 'T' ? 1 : 0;
            F[i][i] = symb[i] == 'F' ? 1 : 0;
        }

        for(int L = 2; L <= n; L++) {
            for(int i = 0; i < n - L + 1; i++) {
                int j = i + L - 1;
                for(int k = i; k < j; k++) {
                    int total = (T[i][k] + F[i][k]) * (T[k + 1][j] + F[k + 1][j]);
                    if(oper[k] == '&') {
                        T[i][j] += T[i][k] * T[k + 1][j];
                        F[i][j] += total - (T[i][k] * T[k + 1][j]);
                    }
                    if(oper[k] == '|') {
                        T[i][j] += total - (F[i][k] * F[k + 1][j]);
                        F[i][j] += F[i][k] * F[k + 1][j];
                    }
                    if(oper[k] == '^') {
                        T[i][j] += F[i][k] * T[k + 1][j] + T[i][k] * F[k + 1][j];
                        F[i][j] += F[i][k] * F[k + 1][j] + T[i][k] * T[k + 1][j];
                    }
                }
            }
        }
        return T[0][n -1];

    }


    //40. Builing bridges

    /*

    Consider a 2-D map with a horizontal river passing through its center. There are n cities on the southern bank with x-coordinates a(1) … a(n) and n cities on the northern bank with x-coordinates b(1) … b(n). You want to connect as many north-south pairs of cities as possible with bridges such that no two bridges cross. When connecting cities, you can only connect city a(i) on the northern bank to city b(i) on the southern bank. Maximum number of bridges that can be built to connect north-south pairs with the above mentioned constraints.

    Input : 6 4 2 1
        2 3 6 5
Output : Maximum number of bridges = 2
Explanation: Let the north-south x-coordinates
be written in increasing order.

1  2  3  4  5  6
  \  \
   \  \        For the north-south pairs
    \  \       (2, 6) and (1, 5)
     \  \      the bridges can be built.
      \  \     We can consider other pairs also,
       \  \    but then only one bridge can be built
        \  \   because more than one bridge built will
         \  \  then cross each other.
          \  \
1  2  3  4  5  6

Input : 8 1 4 3 5 2 6 7
        1 2 3 4 5 6 7 8
Output : Maximum number of bridges = 5

Here cities are always in sorted order and contraints are that A[i] should connect to B[i].

     */

    //41. Maximum number of uncrossed lines between two given arrays, This is not same as building bridges.

    /*
    Given two arrays A[] and B[], the task is to find the maximum number of uncrossed lines between the elements of the two given arrays.

A straight line can be drawn between two array elements A[i] and B[j] only if:

A[i] = B[j]
The line does not intersect any other line.
     */


    public static int uncrossedLines(int[] a, int[] b) {

        int[][] dp = new int[a.length + 1][b.length + 1];

        for(int i = 1; i <= a.length; i++) {
            for(int j = 1; j <= b.length; j++) {
                if(a[i - 1] == b[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[a.length][b.length];
    }


    //42. Unbounded Knapsack

    public static int unboundedKnapsack(int W, int wt[], int val[]) {

        int[][] dp = new int[wt.length + 1][W + 1];
        for(int i = 1; i <= wt.length; i++) {
            for(int j = 1; j <= W; j++) {
                if(wt[i - 1] > j)
                    dp[i][j] = dp[i - 1][j];
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], val[i - 1] + dp[i][j - wt[i - 1]]);
                }
            }
        }
        return dp[wt.length][W];
    }


    //43 . Buy sell stock with one transaction allowed. This is the maximum difference between two elements in array.

    //Given an array arr[] of integers, find out the maximum difference between any two elements such that larger element appears after the smaller number.

    public static int maxDiff(int arr[]) {

        int maxDiff = Integer.MIN_VALUE;
        int minElement = arr[0];

        for(int i = 1; i < arr.length; i++) {
            if(maxDiff < arr[i] - minElement)
                maxDiff = arr[i] - minElement;
            if(arr[i] < minElement)
                minElement = arr[i];
        }

        return maxDiff;
    }



    /// Unlimited transactions involved :

    // 1st way :


    //44. Stock Buy Sell to Maximize Profit unlimited transactions allowed.
    /*
    Input: arr[] = {100, 180, 260, 310, 40, 535, 695}
    Output: 865
    Explanation: Buy the stock on day 0 and sell it on day 3 => 310 – 100 = 210
                           Buy the stock on day 4 and sell it on day 6 => 695 – 40 = 655
                           Maximum Profit  = 210 + 655 = 865

    Input: arr[] = {4, 2, 2, 2, 4}
    Output: 2
    Explanation: Buy the stock on day 1 and sell it on day 4 => 4 – 2 = 2
                           Maximum Profit  = 2
     */

    public static void stockBuySell(int price[]) {

        class Interval{
            int buy;
            int sell;
        }

        int i = 0;
        int n = price.length;
        List<Interval> res = new ArrayList<>();
        while (i < n) {
            Interval interval = new Interval();
            while (i + 1 < n && price[i + 1] < price[i])
                i++;
            if(i == n - 1)
                break;
            else {
                interval.buy = i;
            }
            while (i + 1 < n && price[i + 1] > price[i])
                i++;
            interval.sell = i;
            res.add(interval);
        }

        for(Interval interval : res) {
            System.out.println("Buy on : "  + interval.buy + " " + "sell on : " + interval.sell);
        }

    }


    // 2nd way

    //122. Best Time to Buy and Sell Stock II
    // Unlimited Transactions Allowed
    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][2];
        for(int i = 0; i < prices.length; i++) {
            for(int j = 0; j < 2; j++) {
                dp[i][j] = -1;
            }
        }
        return maxProfitUtil(prices, 1, dp, 0);
    }

    public int maxProfitUtil(int[] prices, int buy, int[][] dp, int index) {
        if(index == prices.length)
            return 0;
        if(dp[index][buy] != -1)
            return dp[index][buy];
        if(buy == 1) {
            dp[index][buy] = Math.max(-prices[index] + maxProfitUtil(prices, 0, dp, index + 1), maxProfitUtil(prices, 1, dp, index + 1 ));
        } else {
            dp[index][buy] = Math.max(prices[index] + maxProfitUtil(prices, 1, dp, index + 1), maxProfitUtil(prices, 0, dp, index + 1));
        }
        return dp[index][buy];
    }



    // 3rd way tabulation :



    public int maxProfitTabulation(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n + 1][2];
        dp[n][0] = dp[n][1] = 0;
        for(int index = n -1 ; index >=0 ; index--) {
            for(int buy = 0; buy < 2; buy++) {
                if(buy == 1) {
                    dp[index][buy] = Math.max(-prices[index] + dp[index + 1][0], dp[index + 1][1]);
                } else {
                    dp[index][buy] = Math.max(prices[index] + dp[index + 1][1], dp[index + 1][0]);
                }
            }

        }
        return dp[0][1];
    }

    //4rth way is to improve space complexity for this tabulation method

    public int maxProfitTabulationSpaceOptimized(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n + 1][2];

        int[][] ahead = new int[1][2];
        int[][] current = new int[1][2];
        ahead[0][0] = ahead[0][1] = 0;

        for(int index = n -1 ; index >=0 ; index--) {
            for(int buy = 0; buy < 2; buy++) {
                if(buy == 1) {
                    current[0][buy] = Math.max(-prices[index] + ahead[0][0], ahead[0][1]);
                } else {
                    current[0][buy] = Math.max(prices[index] + ahead[0][1], ahead[0][0]);
                }
            }
            ahead = current;
        }
        return ahead[0][1];
    }




    //714. Best Time to Buy and Sell Stock with Transaction Fee, this is same as multiple , just need to include the fee

    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n + 1][2];
        dp[n][0] = dp[n][1] = 0;

        int[][] ahead = new int[1][2];
        int[][] current = new int[1][2];
        ahead[0][0] = ahead[0][1] = 0;

        for(int index = n -1 ; index >=0 ; index--) {
            for(int buy = 0; buy < 2; buy++) {
                if(buy == 1) {
                    current[0][buy] = Math.max(-prices[index] -fee + ahead[0][0], ahead[0][1]);
                } else {
                    current[0][buy] = Math.max(prices[index] + ahead[0][1], ahead[0][0]);
                }
            }
            ahead = current;
        }
        return ahead[0][1];
    }


    //309. Best Time to Buy and Sell Stock with Cooldown
    // same as unlimited with aditional constraint : After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).

    // dont try to space optimize as we need to take 2 aheads, can be done but no need
    public int maxProfitCoolDown(int[] prices) {

        int n = prices.length;
        int[][] dp = new int[n + 2][2];
        for(int index = n -1 ; index >=0 ; index--) {
            for(int buy = 0; buy < 2; buy++) {
                if(buy == 1) {
                    dp[index][buy] = Math.max(-prices[index] + dp[index + 1][0], dp[index + 1][1]);
                } else {
                    dp[index][buy] = Math.max(prices[index] + dp[index + 2][1], dp[index + 1][0]);
                }
            }

        }
        return dp[0][1];

    }


    //45. Maximum profit by buying and selling a share at most k times

    /*
    In share trading, a buyer buys shares and sells on a future date. Given the stock price of n days, the trader is allowed to make at most k transactions, where a new transaction can only start after the previous transaction is complete, find out the maximum profit that a share trader could have made.
     */

    public static int maxProfit(int[] price,
                                  int n,
                                  int k) {
        int[][] dp = new int[k + 1][n];
        for(int i = 1; i <= k; i++) {
            for(int j = 1; j < n; j++) {
                dp[i][j] = dp[i][j - 1];
                for(int b = 0; b < j; b++) {
                    dp[i][j] = Math.max(dp[i][j], price[j] - price[b] + dp[i - 1][b]);
                }
            }
        }
        return dp[k][n - 1];
    }

    // 45. Optimized

    public static int maxProfitOptimized(int[] price,
                                int n,
                                int k) {
        int[][] dp = new int[k + 1][n];
        for(int i = 1; i <= k; i++) {
            int prevDiff = Integer.MIN_VALUE;
            for(int j = 1; j < n; j++) {
                prevDiff = Math.max(prevDiff, dp[i - 1][j - 1] - price[j - 1]);
                dp[i][j] = Math.max(dp[i][j - 1], prevDiff + price[j]);
            }
        }
        return dp[k][n - 1];
    }

    //46. Find number of times a string occurs as a subsequence in given string

    /*
    Given two strings, find the number of times the second string occurs in the first string, whether continuous or discontinuous.

    Examples:

    Input:
    string a = "GeeksforGeeks"
    string b = "Gks"

    Output: 4

    Explanation:
    The four strings are - (Check characters marked in bold)
    GeeksforGeeks
    GeeksforGeeks
    GeeksforGeeks
    GeeksforGeeks
     */


    // A Dynamic Programming based
    // Java program to find the
    // number of times the second
    // string occurs in the first
    // string, whether continuous
    // or discontinuous

    static int countWays(String S1, String S2) {
        int[][] dp = new int[S2.length() + 1][S1.length() + 1];
        for(int i = 1; i <= S2.length(); i++) {
            for(int j = 1; j <= S1.length(); j++) {
                if(i == 1) {
                    if(S2.charAt(i - 1) == S1.charAt(j - 1)) {
                        dp[i][j] = dp[i][j - 1] + 1;
                    } else {
                        dp[i][j] = dp[i][j - 1];
                    }
                } else {
                    if(S2.charAt(i - 1) == S1.charAt(j - 1)) {
                        dp[i][j] = dp[i][j - 1] + dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = dp[i][j - 1];
                    }
                }
            }
        }
        return dp[S2.length()][S1.length()];
    }


    public static void main(String[] args)
    {

        String a = "GeeksforGeeks";
        String b = "Gks";

        System.out.println(countWays(a, b));
    }



    /*


    NOTES :

    variations of LIS :

    1. building bridges
    2. box stacking.
    3. russian doll


    russian dolls and building bridges are nearly same.

    use nlogn LIS solution
     */

}
