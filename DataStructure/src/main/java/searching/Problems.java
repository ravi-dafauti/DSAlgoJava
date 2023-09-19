package searching;

import hashtable.schema.HashTable;

import java.util.*;

public class Problems {

    // find duplicates in an array.
    // given all numbers are > 0 and numbers are in the range of 0 n-1.


    public static boolean findIfDuplicates(int[] arr) {
        for(int i = 0; i < arr.length; i++){
            if(arr[Math.abs(arr[i])] < 0)
                return true;
            else
                arr[Math.abs(arr[i])] = -arr[Math.abs(arr[i])];
        }
        return false;
    }


    // find maximum repeating element in an array.
    // Given All numbers are > 0 and in range 0, n - 1

    public static int maxElement(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            arr[arr[i]%arr.length] += arr.length;
        }
        int maxElement = -1;
        int maxCount = Integer.MIN_VALUE;
        for(int i = 0 ;i < arr.length; i++) {
            if((arr[i]/arr.length) > maxCount) {
                maxCount = arr[i]/arr.length;
                maxElement = i;
            }
        }
        return maxElement;
    }

    //find 1st repeating Element in an array

    public static int firstRepeatingElement(int[] arr) {
        Map<Integer, Integer> hashMap= new HashMap<>();
        for(int i = 0; i < arr.length; i++) {
            if(hashMap.containsKey(arr[i])) {
                hashMap.put(arr[i], hashMap.get(arr[i]) + 1);
            } else {
                hashMap.put(arr[i], 1);
            }
        }

        for(int i = 0 ; i< arr.length; i++) {
            if(hashMap.get(arr[i]) > 1)
                return arr[i];
        }
        return -1;
    }
    // Given array of n-1 numbers in the range of 0, n-1, find one number missing
    public static int missingNumber(int[] arr) {

        int xorOfArray, xorOfAllNumbers;
        xorOfArray = xorOfAllNumbers = 0;

        for(int i = 0 ; i< arr.length + 1 ; i++) {
            xorOfAllNumbers = xorOfAllNumbers ^ i;
        }

        for(int i = 0; i< arr.length; i++) {
            xorOfArray^= arr[i];
        }
        return xorOfAllNumbers ^ xorOfArray;
    }

    // Find pair of number whose sum is closest to zero.


    public static void findClosetToZeroPair(int[] arr) {

        Arrays.sort(arr);
        int a, b;
        a = b = -1;
        int closeToZeroSum = Integer.MAX_VALUE;

        for(int i = 0, j = arr.length - 1; i < j;) {
            int sum = arr[i] + arr[j];
            if(sum > 0) {
                if(closeToZeroSum > sum) {
                    closeToZeroSum = sum;
                    a = arr[i];
                    b = arr[j];
                }
                j--;
            } else if(sum < 0) {
                if(Math.abs(sum) < closeToZeroSum) {
                    closeToZeroSum = Math.abs(sum);
                    a = arr[i];
                    b = arr[j];
                }
                i++;
            } else {
                a = arr[i];
                b = arr[j];
                break;
            }


        }
        System.out.println("Pair close to zero is : " + a + " " + b);
    }


    // Find 3 numbers whose sum is closest to zero.


    public static void findClosetToZeroTriplet(int[] arr) {

        Arrays.sort(arr);
        int a, b, c;
        a = b = c =  -1;
        int closeToZeroSum = Integer.MAX_VALUE;

        for(int k = 0; k < arr.length -3; k++) {
            for(int i = k + 1, j = arr.length - 1; i < j;) {
                int sum = arr[i] + arr[j] + arr[k];
                if(sum > 0) {
                    if(closeToZeroSum > sum) {
                        closeToZeroSum = sum;
                        a = arr[i];
                        b = arr[j];
                        c = arr[k];
                    }
                    j--;
                } else if(sum < 0) {
                    if(Math.abs(sum) < closeToZeroSum) {
                        closeToZeroSum = Math.abs(sum);
                        a = arr[i];
                        b = arr[j];
                        c = arr[k];
                    }
                    i++;
                } else {
                    a = arr[i];
                    b = arr[j];
                    c = arr[k];
                    break;
                }


            }
        }

        System.out.println("Triplet close to zero is : " + a + " " + b + " " + c);
    }


    /// We have 2 questions, search in rotated array and search in bitonic array :

    // 2 ways :

    // 1. Get the pivot and do binary search in 1st array if not found then do in next array, these 3 steps take log(n) so time complexity is log(n)
    // this approach can solve both problems.
    // 2. Do in one scan works only for rotated sorted array as below.

    //Search in Rotated Sorted Array 1st approach.

    public int search1(int[] nums, int target) {

        int pivot = findPivot(nums, 0, nums.length - 1);
        int findLeft = binarySearchAscending(nums, 0, pivot, target);
        if(findLeft != -1)
            return findLeft;
        return binarySearchAscending(nums, pivot + 1, nums.length - 1, target);
    }


    public int findPivot(int[] nums, int l , int h) {

        if(l > h)
            return -1;
        if(l == h)
            return l;
        if(l + 1 == h) {
            return nums[l] > nums[h] ? l : h;
        }
        int mid = l + (h - l)/2;
        if(nums[mid] >= nums[mid - 1] && nums[mid] > nums[mid + 1])
            return mid;
        if(nums[mid] >= nums[l]) {
            return findPivot(nums, mid + 1, h);
        } else {
            return findPivot(nums, l, mid - 1);
        }
    }

    //Search in Rotated Sorted Array 2nd approach.

    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while(low <= high) {
            int mid = low + (high - low)/2;
            if(nums[mid] == target)
                return mid;
            if(nums[low] <= nums[mid]) {
                if(nums[low] <= target && nums[mid] > target) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if(nums[mid] < target && nums[high] >= target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    // find maximum element in a bitonic array, return index of max element 1st approach

    public static int maxInBitonicArray(int[] arr) {

        int l = 0;
        int h = arr.length - 1;
        while(l <= h) {
            if(l == h)
                return l;
            else if(l == h - 1)
                return h;
            else {
                int mid = l + (h - l)/2;
                if(arr[mid] >= arr[mid - 1] && arr[mid] >= arr[mid + 1]) {
                    return mid;
                } else if(arr[mid] >= arr[mid -1] && arr[mid] <= arr[mid + 1]) {
                    l = mid + 1;
                } else if (arr[mid] <= arr[mid -1] && arr[mid] >= arr[mid + 1]) {
                    h = mid - 1;
                }
            }
        }
        return -1;
    }

    public static int binarySearchAscending(int[] arr, int l , int h, int k) {

        if(l > h)
            return -1;
        int mid = l + (h - l)/2;
        if(arr[mid] == k) {
            return mid;
        } else if(arr[mid] > k) {
            return binarySearchAscending(arr, l, mid - 1, k);
        } else {
            return binarySearchAscending(arr, mid + 1, h, k);
        }
    }


    public static int binarySearchDescending(int[] arr, int l , int h, int k) {

        if(l > h)
            return -1;
        int mid = l + (h - l)/2;
        if(arr[mid] == k) {
            return mid;
        } else if(arr[mid] < k) {
            return binarySearchAscending(arr, l, mid - 1, k);
        } else {
            return binarySearchAscending(arr, mid + 1, h, k);
        }
    }
    // find element in a bitonic array in Log(n), returns the index if found otherwise returns -1

    public static int findElementInBitonicArray(int[] arr, int k) {

        if(arr.length == 0)
            return -1;
        int pivot = maxInBitonicArray(arr);
        if(arr[pivot] < k)
            return -1;
        if(arr[pivot] == k)
            return pivot;
        int leftRes = binarySearchAscending(arr, 0, pivot -1, k);
        if(leftRes != -1)
            return leftRes;
        return binarySearchDescending(arr, pivot + 1, arr.length - 1, k);
    }


    // boyer moore voting algorithm, finds the element that appears more than n/2 times in an array

    public static int findElementOccuringMoreThenHalf(int[] arr) {
        int count = 0;
        int maxElement = -1;
        for(int i = 0; i < arr.length; i++) {
            if(count == 0) {
                count = 1;
                maxElement = arr[i];
            } else if(arr[i] == maxElement) {
                count++;
            } else {
                count--;
            }
        }
        count = 0;
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == maxElement)
                count++;
        }

        if(count > arr.length/2)
            return maxElement;
        return -1;
    }

    // seperate odd even number or 0, 1. Dutch nationl flag problem

    public static void seperateOddEven(int[] arr) {
        int i, j;
        i = 0;
        j = arr.length - 1;

        while(i < j) {
            while (i<j && (arr[i] % 2 == 0)) {
                i++;
            }
            while (i<j && (arr[j] % 2 != 0)) {
                j--;
            }

            if(i<j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
    }

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    // seperate 0, 1, 2
    public static void seperate012(int[] arr) {
        int low, high, mid;
        low = mid = 0;
        high = arr.length - 1;
        while(mid <= high) {
            if(arr[mid] == 0) {
                swap(arr, low, mid);
                low++;
                mid++;
            } else if(arr[mid] == 1) {
                mid++;
            } else {
                swap(arr, mid, high);
                high--;
            }
        }
    }

    // given an array of n * m with each row start with 1 and end with 0, return row with max 0

    public static int rowWithMax0(int[][] arr, int n, int m) {

        int i, j;
        i = 0;
        j = m - 1;
        int maxCount = 0;
        int maxRow = -1;
        while(i < n && j >= 0) {
            if(arr[i][j] == 0) {
                maxCount++;
                maxRow = i;
                j--;
            } else {
                i++;
            }
        }
        return maxRow;
    }

    // given an array of n * m in wich all rows and column are in ascending order, find a element in O(n)
    public static boolean searchElementIn2DArray(int[][] arr, int n, int m, int data) {
        int i, j;
        i = 0;
        j = m - 1;
        while(i < n && j >= 0) {
            if (arr[i][j] == data) {
                return true;
            } else if (arr[i][j] < data) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }


    // the above solution is O(row + low) this is O(log(n * n)) and is binary search
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int low = 0;
        int high = m * n - 1;
        while(low <= high) {
            int mid = low + (high - low) / 2;
            if(matrix[mid/n][mid%n] == target) {
                return true;
            }
            if(matrix[mid/n][mid%n] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return false;
    }

    //Given an array arr[], find the maximum j – i such that arr[j] > arr[i]

    public static int maxDifference(int[] arr) {

        int maxRight[] = new int[arr.length];

        maxRight[arr.length - 1] = arr[arr.length - 1];

        for(int j = arr.length - 2; j >= 0 ; j--)
            maxRight[j] = Math.max(arr[j], maxRight[j + 1]);
        int i = 0;
        int j = 0;
        int maxDiff = -1;
        while (i < arr.length && j < arr.length) {
            if(arr[i] < maxRight[j]) {
                maxDiff = Math.max(maxDiff, j - i);
                j++;
            } else {
                i++;
            }
        }

        return maxDiff;
    }


    public int hIndex(int[] citations) {
        sort(citations);
        int result = 0;
        for(int i = 0; i < citations.length; i++) {
            if(citations[i] > i) {
                result = i + 1;
            } else {
                break;
            }
        }
        return result;
    }


    public  void sort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    private  void mergeSort(int[] arr, int s, int l) {
        if(s >= l)
            return;
        int m = s + (l - s)/2;
        mergeSort(arr, s, m);
        mergeSort(arr, m + 1, l);
        merge(arr, s , l);
    }

    private  void merge(int[] arr, int s, int l) {

        int[] aux = new int[l - s + 1];
        int m = s + (l - s) / 2;
        int i = 0;
        int j = s;
        int k = m + 1;
        while (j <= m && k <= l) {
            aux[i++] = (arr[j] > arr[k]) ? arr[j++] : arr[k++];
        }
        while (j <= m)
            aux[i++] = arr[j++];
        while (k <= l)
            aux[i++] = arr[k++];

        i = 0;
        j = s;
        while (j <= l)
            arr[j++] = aux[i++];
    }



    public boolean isValidSudoku(char[][] board) {
        HashSet<String> set = new HashSet<>();
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(board[i][j] != '.') {
                    String element = Character.toString(board[i][j]);
                    if(!set.add("row+" + i + "+" + element) || !set.add("col+" + j + "+" + element) || !set.add("box+" + Integer.toString((i/3) * 3 + (j/3)) + "+" + element))
                        return false;
                }
            }
        }
        return true;
    }


    public int lengthOfLongestSubstring(String s) {

        if(s == null || s.length() == 0)
            return 0;

        HashSet<Character> set = new HashSet<>();

        int i = 0;
        int j = 1;
        int maxWindow = 1;
        set.add(s.charAt(0));

        while(j < s.length()) {
            if(set.contains(s.charAt(j))) {
                while (i < j && s.charAt(i) != s.charAt(j)) {
                    set.remove(s.charAt(i));
                    i++;
                }
                i++;
            } else {
                set.add(s.charAt(j));
            }
            maxWindow = Math.max(maxWindow, j - i + 1);
            j++;
        }
        return maxWindow;
    }


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
/////

    public List<Integer> findAnagrams(String s, String p) {

        List<Integer> result = new ArrayList<>();
        if(p.length() <= s.length()) {
            int[] freqP = new int[26];
            int[] freqQ = new int[26];
            calculateFreq(freqP, p);
            calculateFreq(freqQ, s.substring(0, p.length()));
            if(isEqual(freqP, freqQ)) {
                result.add(0);
            }
            for(int i = p.length(); i < s.length(); i++) {
                freqQ[s.charAt(i) - 'a']++;
                freqQ[s.charAt(i - p.length()) - 'a']--;
                if(isEqual(freqP, freqQ)) {
                    result.add(i - p.length() + 1);
                }
            }
        }
        return result;
    }

    public boolean isEqual(int[] a, int[] b) {
        for(int i = 0; i < a.length; i++) {
            if(a[i] != b[i])
                return false;
        }
        return true;
    }

    public void calculateFreq(int[] freq, String s) {
        for(int i = 0 ; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
        }
    }

/////

    public List<Integer> findSubstring(String s, String[] words) {

        List<Integer> result = new ArrayList<>();
        int m = words[0].length();
        int window = words.length * m;

        if(s.length() >= window) {

            Map<String, Integer> map = new HashMap<>();

            for(int i = 0; i < words.length; i++) {
                map.put(words[i], map.getOrDefault(words[i], 0) + 1);
            }
            for(int i = 0; i < s.length() - window + 1; i++) {
                Map<String , Integer> auxMap = new HashMap<>(map);
                int c = 0;
                int j = i;
                while (c < words.length) {
                    String sub = s.substring(j, j + m);
                    if(auxMap.containsKey(sub)) {
                        int count = auxMap.get(sub);
                        if(count > 1) {
                            auxMap.put(sub, count - 1);
                        } else {
                            auxMap.remove(sub);
                        }
                        j = j + m;
                        c++;
                    } else {
                        break;
                    }
                }
                if(c == words.length) {
                    result.add(i);
                }
            }
        }


        return result;
    }


    public void sortColors(int[] nums) {

        int start = 0;
        int end = nums.length - 1;
        int mid = 0;

        while (start < end) {
            if(nums[start] == 0) {
                swapNums(nums, start, mid);
                start++;
                mid++;
            } else if(nums[start] == 1) {
                start++;
            } else if(nums[start] == 2) {
                swapNums(nums, start, end);
                end--;
            }
        }
    }

    public void swapNums(int[] nums, int i , int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }


    //287. Find the Duplicate Number

    public int findDuplicate(int[] nums) {
        int fast = 0;
        int slow = 0;
        fast = nums[nums[fast]];
        slow = nums[slow];
        while (slow != fast) {
            fast = nums[nums[fast]];
            slow = nums[slow];
        }
        fast = 0;
        while (slow != fast) {
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;
    }


    //137. Single Number II

    public int singleNumber(int[] nums) {
        int res = 0;
        for(int i = 0; i < 32; i++) {
            int sum = 0;
            for(int num : nums) {
                sum += (num >> i) & 1;
                sum %= 3;
            }

            res |= sum << i;
        }
        return res;
    }

    //201. Bitwise AND of Numbers Range


    public int rangeBitwiseAnd(int left, int right) {
        int count = 0;
        while (left != right) {
            left >>= 1;
            right >>= 1;
            count++;
        }
        left <<= count;
        return left;
    }



    // 918. Maximum Sum Circular Subarray
    public int maxSubarraySumCircular(int[] nums) {

        int maxSum = nums[0];
        int curMax = 0;
        int minSum = nums[0];
        int curMin = 0;
        int totalSum = 0;

        for(int i = 0; i < nums.length; i++) {
            curMax = Math.max(curMax, 0) + nums[i];
            maxSum = Math.max(maxSum, curMax);
            curMin = Math.min(curMin, 0) + nums[i];
            minSum = Math.min(minSum, curMin);
            totalSum += nums[i];
        }
        if(totalSum == minSum)
            return maxSum;
        return Math.max(maxSum, totalSum - minSum);
    }


    //2300. Successful Pairs of Spells and Potions
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int[] result = new int[spells.length];
        Arrays.sort(potions);
        for(int i = 0; i < spells.length; i++) {
            int l = 0;
            int h = potions.length - 1;
            while (l <= h) {
                int mid = l + (h - l) / 2;
                long mul = (long) potions[mid] * spells[i];
                if(mul >= success) {
                    result[i] += h - mid + 1;
                    h = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
        }
        return result;
    }


    //875. Koko Eating Bananas
    public int minEatingSpeed(int[] piles, int h) {
        int high = piles[0];
        for(int i = 1; i < piles.length; i++) {
            high = Math.max(high, piles[i]);
        }
        int low = 1;
        int rate = high;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if(canEat(piles, h, mid)) {
                rate = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return rate;
    }

    public boolean canEat(int[] piles, int h, int k) {
        long hours = 0;
        for(int i = 0; i < piles.length; i++) {
            hours += (long) piles[i]/k;
            if(piles[i] % k != 0)
                hours++;
        }
        if(hours <= (long) h)
            return true;
        return false;
    }
    public static void main(String[] args) {

        Problems problems = new Problems();
        //problems.findSubstring("wordgoodgoodgoodbestword", new String[]{"word","good","best","word"});
        System.out.println(problems.rangeBitwiseAnd(20, 28));
        //System.out.println(20 & 28);
    }

}


