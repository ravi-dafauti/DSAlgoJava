package hashtable;

import java.util.*;

public class LeetCode {
    public boolean canConstruct(String ransomNote, String magazine) {

        Map<Character, Integer> map = new HashMap<>();

        for(int i = 0; i < magazine.length(); i++) {
            map.put(magazine.charAt(i), map.getOrDefault(magazine.charAt(i), 0) + 1);
        }

        for(int i = 0; i < ransomNote.length(); i++) {
            if(!map.containsKey(ransomNote.charAt(i)))
                return false;
            else {
                int freq = map.get(ransomNote.charAt(i));
                freq = freq - 1;
                if(freq == 0)
                    map.remove(ransomNote.charAt(i));
                else
                    map.put(ransomNote.charAt(i), freq);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCode l = new LeetCode();
        l.wordPattern("abba", "dog dog dog dog");
    }

    public boolean wordPattern(String pattern, String s) {

        Map<Character, String> map = new HashMap<>();
        Map<String, Character> map2 = new HashMap<>();

        int j = 0;
        for(int i = 0; i < pattern.length(); i++, j++) {
            StringBuilder subString = new StringBuilder();
            while (j < s.length() && s.charAt(j) != ' ') {
                subString.append(s.charAt(j));
                j++;
            }
            if(map.containsKey(pattern.charAt(i))) {
                if(!map.get(pattern.charAt(i)).equals(subString.toString()))
                    return false;
            } else {
                if(map2.containsKey(subString.toString())) {
                    if(map2.get(subString.toString()) != pattern.charAt(i))
                        return false;
                }
                map.put(pattern.charAt(i), subString.toString());
                map2.put(subString.toString(), pattern.charAt(i));
            }
        }
        if(j < s.length())
            return false;
        return true;
    }


    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length())
            return false;
        Map<Character, Integer> map = new HashMap<>();

        for(int i = 0; i < s.length(); i++) {
            if(map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
            } else {
                map.put(s.charAt(i), 1);
            }
        }

        for(int i = 0; i < t.length(); i++) {
            if(!map.containsKey(t.charAt(i))) {
                return false;
            } else {
                int freq = map.get(t.charAt(i));
                freq = freq - 1;
                if(freq == 0)
                    map.remove(t.charAt(i));
                else
                    map.put(t.charAt(i), freq);
            }
        }
        if(map.size() > 0)
            return false;
        return true;
    }

    public int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            if(map.containsKey(target - nums[i])) {
                return new int[] {i, map.get(target - nums[i])};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[]{};
    }


    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0 ; i < nums.length; i++) {
            if(map.containsKey(nums[i])) {
                int index = map.get(nums[i]);
                if(Math.abs(index - i) <= k)
                    return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }


    public boolean isIsomorphic(String s, String t) {
        if(s.length() != t.length())
            return false;
        HashMap<Character, Character> st = new HashMap<>();
        HashMap<Character, Character> ts = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            char charAtS = s.charAt(i);
            char charAtT = t.charAt(i);
            if(st.containsKey(charAtS)) {
                if(st.get(charAtS) != charAtT)
                    return false;
            } else {
                if(ts.containsKey(charAtT))
                    return false;
                st.put(charAtS, charAtT);
                ts.put(charAtT, charAtS);
            }
        }

        return true;
    }


    public boolean isHappy(int n) {

        Set<Integer> seen = new HashSet<>();

        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            int sum = 0;
            while(n != 0) {
                int digit = n % 10;
                n = n /10;
                sum += digit * digit;
            }
            n = sum;
        }
        if(n == 1)
            return true;
        return false;
    }


    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int i = 0 ; i < nums.length; i++)
            set.add(nums[i]);
        int result = 0;
        for(int i = 0; i < nums.length; i++) {
            if(set.contains(nums[i] - 1))
                continue;
            int val = nums[i];
            int count = 0;
            while (set.contains(val)) {
                count++;
                set.remove(val);
                val = val + 1;
            }
            if(result < count)
                result = count;
        }
        return result;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for(int i = 0; i < strs.length; i++) {
            char[] word = strs[i].toCharArray();
            Arrays.sort(word);
            String sortedWord = new String(word);
            if(!map.containsKey(sortedWord)) {
                map.put(sortedWord, new ArrayList<>());
            }
            map.get(sortedWord).add(strs[i]);
        }
        return new ArrayList<>(map.values());
    }


    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int count = 0;
        int sum = 0;
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if(map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
