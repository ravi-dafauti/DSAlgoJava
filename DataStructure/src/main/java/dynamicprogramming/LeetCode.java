package dynamicprogramming;

import java.util.*;

public class LeetCode {


    public int rob(int[] nums) {
        int a = nums[0];
        if(nums.length == 1)
            return a;
        int b = Math.max(a, nums[1]);
        int c = b;
        for(int i = 2; i < nums.length; i++) {
            c = Math.max(nums[i] + a, b);
            a = b;
            b = c;
        }
        return c;
    }


    public boolean wordBreak(String s, List<String> wordDict) {

        HashSet dict = new HashSet(wordDict);

        boolean[][] dp = new boolean[s.length()][];
        for(int i = 0; i < s.length(); i++) {
            dp[i] = new boolean[s.length()];
        }
        for(int l = 1; l <= s.length(); l++) {
            for(int i = 0; i + l - 1 < s.length(); i++) {
                if(l == 1) {
                    if(dict.contains(s.substring(i, i + 1)))
                        dp[i][i] = true;
                } else {
                    if(dict.contains(s.substring(i, i + l))) {
                        dp[i][i + l - 1] = true;
                    } else {
                        for(int j = i; j < i + l - 1; j++) {
                            if(dp[i][j] == true && dp[j + 1][i + l - 1] == true) {
                                dp[i][i + l - 1] = true;
                                break;
                            }
                        }
                    }
                }
            }
        }

        return dp[0][s.length() - 1];
    }


    public int coinChange(int[] coins, int amount) {

        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for(int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        for(int i = 0; i<coins.length; i++){
            for(int j = 1; j<=amount; j++) {
                if(coins[i] <= j) {
                    if(dp[j - coins[i]] != Integer.MAX_VALUE && dp[j - coins[i]] + 1 < dp[j]) {
                        dp[j] = dp[j - coins[i]] + 1;
                    }

                }
            }
        }
        if(dp[amount] == Integer.MAX_VALUE)
            return  -1;
        return dp[amount];
    }

    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int lis = 1;
        dp[0] = 1;
        for(int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for(int j = 0; j < i; j++) {
                if(nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            lis = Math.max(lis, dp[i]);
        }
        return lis;
    }


    public String longestPalindrome(String s) {


        int[][] dp = new int[s.length()][];
        for(int i = 0; i < s.length(); i++) {
            dp[i] = new int[s.length()];
        }
        for(int i = 0; i < s.length(); i++)
            dp[i][i] = 1;

        int longestPalindromLen = 1;
        String palindrome = String.valueOf(s.charAt(0));

        for(int l = 2; l <= s.length(); l++) {
            for(int i = 0; i + l - 1 < s.length(); i++) {
                if(s.charAt(i) == s.charAt(i + l - 1)) {
                    if(l == 2) {
                        longestPalindromLen = 2;
                        palindrome = s.substring(i, i + l);
                        dp[i][i + l - 1] = 2;
                    } else {
                        if(dp[i + 1][i + l - 2] != 0) {
                            dp[i][i + l - 1] = 2 + dp[i + 1][i + l - 2];
                            if(longestPalindromLen < dp[i][i + l - 1]) {
                                longestPalindromLen = dp[i][i + l - 1];
                                palindrome = s.substring(i, i + l);
                            }
                        }
                    }
                }
            }
        }
        return palindrome;
    }



    public int maximalSquare(char[][] matrix) {

        int[][] dp = new int[matrix.length][];
        for(int i = 0; i < matrix.length; i++) {
            dp[i] = new int[matrix[0].length];
        }
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


    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        for(int i = 1 ; i < n ; i++)
            grid[0][i] = grid[0][i - 1] + grid[0][i];
        for (int i = 1; i < m; i++)
            grid[i][0] = grid[i - 1][0] + grid[i][0];

        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
            }
        }
        return grid[m - 1][n - 1];
    }

    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m + 1][];
        for(int i = 0; i <= m; i++) {
            dp[i] = new int[n + 1];
        }

        for(int i = 0; i <= n; i++)
            dp[0][i] = i;
        for(int i = 0; i<=m ;i++)
            dp[i][0] = i;

        for(int i = 1; i <= m ; i++) {
            for(int j = 1; j <=n ;j++) {
                if(word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i - 1][j - 1], dp[i][j - 1])) + 1;
                }
            }
        }
        return dp[m][n];
    }

    public boolean isInterleave(String s1, String s2, String s3) {

        int m = s1.length();
        int n = s2.length();

        if(m + n != s3.length())
            return false;

        boolean[][] dp = new boolean[m + 1][];
        for(int i = 0; i <= m; i++)
            dp[i] = new boolean[n + 1];
        if(s1.isEmpty() && s2.isEmpty() && s3.isEmpty())
            return true;
        dp[0][0] = true;

        for(int i = 1; i <= n; i++) {
            if(s2.charAt(i - 1) == s3.charAt(i - 1) && dp[0][i - 1] == true)
                dp[0][i] = true;
        }

        for(int i = 1; i <=m ;i++) {
            if(s1.charAt(i - 1) == s3.charAt(i - 1) && dp[i - 1][0] == true)
                dp[i][0] = true;
        }

        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if((s1.charAt(i - 1) == s3.charAt(i + j - 1) && dp[i - 1][j] == true) || (s2.charAt(j - 1) == s3.charAt(i + j - 1) && dp[i][j - 1] == true)) {
                    dp[i][j] = true;
                }
            }
        }

        return dp[m][n];
    }


    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[][] dp = new int[m][];
        for(int i = 0; i < m ; i++)
            dp[i] = new int[n];

        if(obstacleGrid[0][0] == 0)
            dp[0][0] = 1;
        else
            dp[0][0] = 0;

        for(int i = 1; i < n ; i++) {
            if(obstacleGrid[0][i] == 0 && dp[0][i - 1] != 1)
                dp[0][i] = 1;
        }
        for(int i = 1; i < m ; i++) {
            if(obstacleGrid[i][0] == 0 && dp[i - 1][0] != 1)
                dp[i][0] = 1;
        }

        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                if(obstacleGrid[i - 1][j - 1] == 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }


        return dp[m - 1][n - 1];
    }


    public int maxProfit(int[] prices) {

        int n = prices.length;
        int[][] dp = new int[3][n];
        for(int i = 1; i <= 2; i++) {
            int prevDiff = Integer.MIN_VALUE;
            for(int j = 1; j < n; j++) {
                prevDiff = Math.max(prevDiff, dp[i - 1][j - 1] - prices[j - 1]);
                dp[i][j] = Math.max(dp[i][j - 1], prevDiff + prices[j]);
            }
        }
        return dp[2][n - 1];
    }


    public int minimumTotal(List<List<Integer>> triangle) {

        if(triangle.size() == 1)
            return triangle.get(0).get(0);

        int[][] dp = new int[triangle.size()][];
        for(int i = 0; i < triangle.size(); i++) {
            dp[i] = new int[i + 1];
            for(int j = 0; j < i + 1; j++)
                dp[i][j] = Integer.MAX_VALUE;
        }


        dp[0][0] = triangle.get(0).get(0);
        int min = Integer.MAX_VALUE;

        for(int i = 0; i < triangle.size() - 1; i++) {
            List<Integer> list = triangle.get(i);
            List<Integer> nextList = triangle.get(i + 1);
            for(int j = 0; j < list.size(); j++) {
                dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + nextList.get(j));
                dp[i + 1][j + 1] = Math.min(dp[i + 1][j + 1], dp[i][j] + nextList.get(j + 1));

                if(i == triangle.size() - 2) {
                    min = Math.min(min, Math.min(dp[i + 1][j], dp[i + 1][j + 1]));
                }
            }
        }
        return min;
    }


    public int longestCommonSubsequence(String text1, String text2) {

        int m = text1.length();
        int n = text2.length();

        int[][] dp = new int[m + 1][];
        for(int i = 0 ; i <= m; i++)
            dp[i] = new int[n + 1];

        for(int i = 1; i <=m; i++) {
            for(int j = 1; j<= n; j++) {
                if(text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j - 1], Math.max(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }

        return dp[m][n];

    }


    public int maxProduct(int[] nums) {

        int[][] dp = new int[nums.length][];
        for(int i = 0; i < nums.length; i++)
            dp[i] = new int[nums.length];
        int maxProduct = Integer.MIN_VALUE;

        for(int l = 1; l <=nums.length; l++) {
            for(int i = 0; i + l - 1 < nums.length; i++) {
                if(l == 1) {
                    dp[i][i + l - 1] = nums[i];
                } else if( l == 2) {
                    dp[i][i + l - 1] = nums[i] * nums[i + l - 1];
                } else {
                    dp[i][i + l - 1] = nums[i] * nums[i + l - 1] * dp[i + 1][i + l - 2];
                }
                if(dp[i][i + l - 1] > maxProduct)
                    maxProduct = dp[i][i + l - 1];
            }
        }
        return maxProduct;
    }

    public boolean canPartition(int[] nums) {

        int sum = 0;
        for(int i = 0; i < nums.length; i++)
            sum += nums[i];

        if(sum % 2 != 0)
            return false;

        sum = sum/2;

        boolean[][] dp = new boolean[nums.length + 1][sum + 1];
        for(int i = 0; i <= nums.length; i++)
            dp[i][0] = true;
        for(int i = 1; i <= nums.length; i++) {
            for(int j = 1; j <= sum; j++) {
                if(nums[i - 1] <= j) {
                    if(dp[i - 1][j - nums[i - 1]] == true)
                        dp[i][j] = true;
                }
            }
        }
        return dp[nums.length][sum];
    }



    public int longestValidParentheses(String s) {
        int max = Integer.MIN_VALUE;
        int[] dp = new int[s.length()];
        for(int i = 1; i < s.length(); i++) {
            if(s.charAt(i) == ')') {
                if(s.charAt(i - 1) == '(') {
                    dp[i] = i - 2 >= 0 ? dp[i - 2] + 2 : 2;
                    max = Math.max(dp[i], max);
                } else {
                    int j = i - dp[i - 1] - 1;
                    if(j >= 0 && s.charAt(j) == '(') {
                        dp[i] = j - 1 >= 0 ? (dp[j - 1] + i - j + 1) : (i - j + 1);
                        max = Math.max(dp[i], max);
                    }
                }
            }
        }
        return max;
    }


    public int numSquares(int n) {

        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for(int i = 1; i * i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(i * i <= j && dp[j - (i * i)] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j - (i * i)] + 1, dp[j]);
                }
            }
        }

        return dp[n];
    }


    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> prevList = null;
        for(int i = 0; i < numRows; i++) {
            List<Integer> curList = new ArrayList<>();
            for(int j = 0; j<= i; j++) {
                if(j == 0 || j == i) {
                    curList.add(1);
                } else {
                    curList.add(prevList.get(j) + prevList.get(j + 1));
                }
            }
            prevList = curList;
            result.add(curList);
        }
        return result;
    }


    public int maximalRectangle(char[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[] dp = new int[col];
        int max = 0;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(matrix[i][j] == '1') {
                    dp[j] += 1;
                } else {
                    dp[j] = 0;
                }
            }
            max = Math.max(max, largestAreaUnderHistogram(dp));
        }
        return max;
    }

    public int largestAreaUnderHistogram(int[] histogram) {
        Stack<Integer> stack = new Stack<>();

        int maxArea = 0;
        int i = 0;
        while (i < histogram.length) {
            if(stack.isEmpty() || histogram[stack.peek()] <= histogram[i]) {
                stack.push(i++);
            } else {
                while (!stack.isEmpty() && histogram[stack.peek()] > histogram[i]) {
                    int cur = stack.pop();
                    int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                    int length = histogram[cur];
                    maxArea = Math.max(maxArea, width * length);
                }
                stack.push(i++);
            }
        }
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            int width = stack.isEmpty() ? i : i - stack.peek() - 1;
            int length = histogram[cur];
            maxArea = Math.max(maxArea, width * length);
        }
        return maxArea;
    }

    class Score {
        int p1;
        int p2;
        Score(int p1, int p2) {
            this.p1 = p1;
            this.p2 = p2;
        }
    }

    public boolean predictTheWinner(int[] nums) {
        Score[][] dp = new Score[nums.length][nums.length];
        for(int l = 1; l <= nums.length; l++) {
            for(int i = 0; i + l - 1 < nums.length; i++) {
                if(l == 1) {
                    dp[i][i + l - 1] = new Score(nums[i], 0);
                } else {
                    if(nums[i] + dp[i + 1][i + l - 1].p2 > nums[i + l - 1] + dp[i][i + l - 2].p2) {
                        dp[i][i + l - 1] = new Score(nums[i] + dp[i + 1][i + l - 1].p2, dp[i + 1][i + l - 1].p1);
                    } else {
                        dp[i][i + l - 1] = new Score(nums[i + l - 1] + dp[i][i + l - 2].p2, dp[i][i + l - 2].p1);
                    }
                }
            }
        }
        Score result = dp[0][nums.length - 1];
        if(result.p1 >= result.p2)
            return true;
        return false;
    }

    public static void main(String[] args) {
        LeetCode l = new LeetCode();
        l.predictTheWinner(new int[]{1, 5, 2});
    }


    public int superEggDrop(int k, int n) {

        int[][] dp = new int[k + 1][n + 1];

        for(int i = 1; i <= k; i++) {
            for(int j = 1; j <= n; j++) {
                if(i == 1) {
                    dp[i][j] = j;
                } else {
                    dp[i][j] = Integer.MAX_VALUE;
                    for(int x = 1; x <=j; x++) {
                        dp[i][j] = Math.min(dp[i][j], Math.max(dp[i - 1][x - 1] + 1, dp[i][j - x] + 1));
                    }
                }
            }
        }

        return dp[k][n];
    }


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

    class NumMatrix {

        int[][] dp;

        public NumMatrix(int[][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length;
            dp = new int[m + 1][n + 1];
            for(int i = 1; i <= m; i++) {
                for(int j = 1; j <= n; j++) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + matrix[i - 1][j - 1];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return dp[row2 - 1][col2 - 1] - dp[row1 - 2][col2 - 1] - dp[row2 - 1][col1 - 2] + dp[row1 - 2][col1 - 2];
        }
    }


    public int maxProfit1(int[] prices) {
        int maxProfit = 0;
        int localMin = -1;
        int localMax = -1;
        for(int i = 0; i < prices.length; i++) {
            while(i + 1 < prices.length && prices[i] > prices[i + 1])
                i++;
            if(i < prices.length) {
                localMin = i;
            }
            if(i < prices.length - 1) {
                while (i + 1 < prices.length && prices[i] < prices[i + 1])
                    i++;
                if(i < prices.length) {
                    localMax = i;
                }

                maxProfit += prices[localMax] - prices[localMin];
            }
        }
        return maxProfit;
    }

    //790. Domino and Tromino Tiling

    public int numTilings(int n) {
        if(n == 1)
            return 1;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
            int trimino = n / 3;
            int domino = n % 3 == 0 ? 1 : n % 3;
            dp[i] += 2 * trimino * domino;
        }
        return dp[n];
    }
}
