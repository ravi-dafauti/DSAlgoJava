package sorting.algo;


import java.util.ArrayList;
import java.util.List;

/*

This is used when we have an array with integers in range 1 , N or 0 , N-1.
Where we just need to place the elements in their correct index. complexity is : O(N).
Before sorting array:
3 2 4 5 1
Sorted array:
1 2 3 4 5


Can be used in many interview questions.
 */
public class CyclicSort {

    // numbers are in range 1 - n
    public static void cyclicSort(int[] arr, int n) {
        for(int i = 0; i < n;) {
            if(arr[i] == i + 1) {
                i++;
            } else {
                swap(arr, i, arr[i] - 1);
            }
        }
    }

    public static void swap(int[] arr , int i, int j){
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 4, 5, 1};
        cyclicSort(arr, 5);
        for(int i = 0; i < 5; i++)
            System.out.print(arr[i] + " ");
    }


    // For all questions with array where numbers are in range 0 to N or 1 to N-1 use cyclic sort

    //268. Missing Number

    // NOTE this can be done using XOR too but we are using cyclic sort

    public int missingNumber(int[] nums) {

        for(int i = 0; i < nums.length;) {
            if(nums[i] == i || nums[i] == nums.length) {
                i++;
            } else {
                swap(nums, i, nums[i]);
            }
        }

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != i)
                return i;
        }
        return nums.length;
    }

    //448. Find All Numbers Disappeared in an Array

    public List<Integer> findDisappearedNumbers(int[] nums) {

        for(int i = 0; i < nums.length;) {
            if(nums[i] == i + 1 || nums[nums[i] - 1] == nums[i]) {
                i++;
            } else {
                swap(nums, i, nums[i] - 1);
            }
        }
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != i + 1) {
                result.add(i + 1);
            }
        }
        return result;
    }

    //287. Find the Duplicate Number

    // NOTE this can be done using floyd algo also see leetcode

    public int findDuplicate(int[] nums) {
        for(int i = 0; i < nums.length;) {
            if(nums[i] == i || nums[nums[i]] == nums[i]) {
                i++;
            } else {
                swap(nums, i, nums[i]);
            }
        }
        return nums[0];
    }

    //442. Find All Duplicates in an Array

    public List<Integer> findDuplicates(int[] nums) {

        for(int i = 0; i < nums.length;) {
            if(nums[i] == i + 1 || nums[nums[i] - 1] == nums[i]) {
                i++;
            } else {
                swap(nums, i, nums[i] - 1);
            }
        }
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != i + 1) {
                result.add(nums[i]);
            }
        }
        return result;
    }

    //645. Set Mismatch

    public int[] findErrorNums(int[] nums) {
        for(int i = 0; i < nums.length;) {
            if(nums[i] == i + 1 || nums[nums[i] - 1] == nums[i]) {
                i++;
            } else {
                swap(nums, i, nums[i] - 1);
            }
        }
        int[] res = new int[2];
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != i + 1) {
                res[0] = nums[i];
                res[1] = i + 1;
                break;
            }
        }
        return res;
    }


    //41. First Missing Positive

    public int firstMissingPositive(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if(nums[i] == i + 1 || nums[i] <= 0 || nums[i] > nums.length || nums[nums[i] - 1] == nums[i])
                i++;
            else {
                swap(nums, nums[i] - 1 , i);
            }
        }
        for(i = 0; i < nums.length; i++) {
            if(nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

}
