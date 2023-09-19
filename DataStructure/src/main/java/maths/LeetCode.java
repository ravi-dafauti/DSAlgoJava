package maths;

public class LeetCode {

    ////
    public double myPow(double x, int n) {
        if(n == 0)
            return 1;
        double half = myPow(x, n/2);
        if(n%2 == 0)
            return half * half;
        else {
            if(n > 0)
                return x * half * half;
            else
                return (half * half) / x;
        }
    }

    /////
    public boolean isPalindrome(int x) {
        if(x == 0)
            return true;
        if(x < 0)
            return false;
        if(x == reverse(x))
            return true;
        return false;
    }

    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            rev = rev * 10 + (x % 10);
            x = x / 10;
        }
        return rev;
    }


    ////

    public int[] plusOne(int[] digits) {

        int c = 0;
        int sum = 0;

        for(int i = digits.length - 1; i >=0; i--) {
            if(i == digits.length - 1) {
                sum = digits[i] + 1;
                digits[i] = sum % 10;
                c = sum / 10;
            } else {
                sum = digits[i] + c;
                digits[i] = sum % 10;
                c = sum / 10;
                if(c == 0)
                    break;
            }
        }

        if(c == 0)
            return digits;
        else {
            int[] res = new int[digits.length + 1];
            res[0] = 1;
            for(int i = 1; i < res.length; i++) {
                res[i] = digits[i - 1];
            }
            return res;
        }
    }

    ////


    public int mySqrt(int x) {
        if(x == 0 || x == 1)
            return x;

        int start = 1;
        int end = x;

        while(start <= end) {
            int mid = start + (end - start)/2;
            if((long)mid * mid > x) {
                end = mid - 1;
            } else if((long)mid * mid == x){
                return mid;
            } else {
                start = mid + 1;
            }
        }
        return end;
    }

    ///

    public int trailingZeroes(int n) {
        int result = 0;
        int div = 5;
        while (n / div > 0) {
            result += n /div;
            div = div * 5;
        }
        return result;
    }



    public boolean isPalindrome(String s) {

        int i = 0;
        int j = s.length() - 1;

        while(i <= j) {
            while (i<=j && !isAlphaNumeric(s.charAt(i)))
                i++;
            if(i > j)
                break;
            while (i <= j && !isAlphaNumeric(s.charAt(j)))
                j--;
            if(i > j)
                break;
            if(s.substring(i, i + 1).toLowerCase().charAt(0) != s.substring(j, j+ 1).toLowerCase().charAt(0)) {
                return false;
            }
            i++;
            j--;

        }
        return true;
    }

    public boolean isAlphaNumeric(char a) {
        int ascii = a;
        if((ascii >= 65 && ascii <= 90) || (ascii >= 97 && ascii <= 122) || (ascii >= 48 && ascii <= 57)) {
            return true;
        }
        return false;
    }


    public int minSubArrayLen(int target, int[] nums) {

        int i = 0;
        int prevWindowSum = nums[0];

        int result = Integer.MAX_VALUE;

        for(int k = 1; k < nums.length; k++) {
            if (prevWindowSum < target) {
                prevWindowSum += nums[k];
            } else {
                while (i < k && prevWindowSum >= target) {
                    result = Math.min(result, k - i);
                    prevWindowSum = prevWindowSum - nums[i];
                    i++;
                }
            }
        }

        while (i < nums.length && prevWindowSum >= target) {
            result = Math.min(result, nums.length - i);
            prevWindowSum = prevWindowSum - nums[i];
            i++;
        }


        if(result == Integer.MAX_VALUE)
            return 0;
        return result;
    }
}
