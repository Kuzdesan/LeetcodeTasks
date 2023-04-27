import java.util.HashMap;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
    }
}

class MyStrings {
    /**
     * TASK 3. Longest Substring Without Repeating Characters
     * Given a string s, find the length of the longest substring without repeating characters.
     * Example 3:
     * Input: s = "pwwkew"
     * Output: 3
     * Explanation: The answer is "wke", with the length of 3.
     * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
     */
    public static int lengthOfLongestSubstring(String s) {
        String toCompare = "";
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            int k = res.indexOf(s.charAt(i));
            if (k == -1) {
                res = res.concat(String.valueOf(s.charAt(i)));
            } else {
                s = s.substring(k + 1);
                i = -1;
                if (res.length() > toCompare.length()) toCompare = res;
                res = "";
            }
        }
        if (res.length() > toCompare.length()) return res.length();
        return toCompare.length();
    }

    /**
     * TASK 3. Longest Substring Without Repeating Characters (faster solution)
     * Given a string s, find the length of the longest
     * substring without repeating characters.
     * Example 1:
     * Input: s = "pwwkew"
     * Output: 3
     * Explanation: The answer is "wke", with the length of 3.
     * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
     */
    public static int lengthOfLongestSubstringFaster(String s) {
        HashMap<Character, Integer> mapa = new HashMap<>();
        int max = 0;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (!mapa.containsKey(c)) {
                mapa.put(c, i);
                count++;
            } else {
                if (max < count) max = count;
                mapa.clear();
                s = s.substring(s.indexOf(s.charAt(i)) + 1);
                i = -1;
                count = 0;
            }
        }
        int k = max - count >= 0 ? max : count;
        return k;
    }

    /**
     * TASK 14. Longest Common Prefix
     * Write a function to find the longest common prefix string amongst an array of strings.
     * If there is no common prefix, return an empty string "".
     * Example:
     * Input: strs = ["flower","flow","flight"]
     * Output: "fl"
     */
    public static String longestCommonPrefix(String[] strs) {
        String s = "";
        int sz = strs.length;
        int min = 0;
        for (int i = 0; i < sz; i++) {
            if (i == 0) min = strs[i].length();
            else if (strs[i].length() < min) min = strs[i].length();
            else continue;
        }
        for (int j = 0; j < min; j++) {
            int count = 1;
            char sym = strs[0].charAt(j);
            for (int i = 1; i < sz; i++) {
                if (strs[i].charAt(j) == sym) count++;
            }
            if (count == sz) s = s + String.valueOf(sym);
            else return s;
        }
        return s;
    }

    /**
     * TASK 20. Valid Parentheses
     * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']',
     * determine if the input string is valid.
     * An input string is valid if:
     * Open brackets must be closed by the same type of brackets.
     * Open brackets must be closed in the correct order.
     * Every close bracket has a corresponding open bracket of the same type.
     * Example 1:
     * Input: s = "()[]{}"
     * Output: true
     */
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            switch (c) {
                case '(':
                case '[':
                case '{':
                    stack.push(c);
                    break;
                case ')': {
                    if (stack.isEmpty() || stack.pop() != '(') return false;
                    break;
                }
                case ']': {
                    if (stack.isEmpty() || stack.pop() != '[') return false;
                    break;
                }
                case '}': {
                    if (stack.isEmpty() || stack.pop() != '{') return false;
                    break;
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     * TASK 205. Isomorphic Strings
     * Given two strings s and t, determine if they are isomorphic.
     * Two strings s and t are isomorphic if the characters in s can be replaced to get t.
     * All occurrences of a character must be replaced with another character
     * while preserving the order of characters.
     * No two characters may map to the same character, but a character may map to itself.
     * <p>
     * Example:
     * Input: s = "egg", t = "add"
     * Output: true
     */
    public static boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> mapa = new HashMap<>();
        char[] tt = t.toCharArray();
        char[] ss = s.toCharArray();

        for (int i = 0; i < ss.length; i++) {
            if (!mapa.containsKey(ss[i])) {
                if (!mapa.containsValue(tt[i])) {
                    mapa.put(ss[i], tt[i]);
                    ss[i] = tt[i];
                } else return false;
            } else {
                ss[i] = mapa.get(ss[i]);
                if (ss[i] != tt[i]) return false;
            }
        }
        return true;
    }

    /**
     * TASK 344. Reverse String
     * Write a function that reverses a string. The input string is given as an array of characters s.
     * You must do this by modifying the input array in-place with O(1) extra memory.
     * <p>
     * Example:
     * Input: s = ["H","a","n","n","a","h"]
     * Output: ["h","a","n","n","a","H"]
     */
    public static void reverseString(char[] s) {
        for (int i = 0; i < s.length / 2; i++) {
            char tmp = s[i];
            s[i] = s[s.length - 1 - i];
            s[s.length - 1 - i] = tmp;
        }
    }

    /**
     * TASK 383. Ransom Note
     * Given two strings ransomNote and magazine,
     * return true if ransomNote can be constructed by using the letters from magazine and false otherwise.
     * Each letter in magazine can only be used once in ransomNote.
     * <p>
     * Example:
     * Input: ransomNote = "aa", magazine = "aab"
     * Output: true
     */
    public static boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) return false;
        int[] alphabets_counter = new int[26];

        for (char c : magazine.toCharArray())
            alphabets_counter[c - 'a']++;

        for (char c : ransomNote.toCharArray()) {
            if (alphabets_counter[c - 'a'] == 0) return false;
            alphabets_counter[c - 'a']--;
        }
        return true;

        /* 2-d solution */
//        char[] s1 = magazine.toCharArray();
//        int count = 0;
//        for (int j = 0; j < ransomNote.length(); j++) {
//            for (int i = 0; i < magazine.length(); i++) {
//                if (magazine.charAt(i) == ransomNote.charAt(j)) {
//                    magazine.charAt(i)= '-';
//                    count ++;
//                    break;
//                }
//            }
//        }
//        return (count == ransomNote.length());

        /* 3-rd solution */
//        if (ransomNote.length() > magazine.length()) return false;
//        HashMap<Character, Integer> mapa = new HashMap<>();
//        for (int i = 0; i < magazine.length(); i++){
//            if (mapa.containsKey(magazine.charAt(i))){
//                mapa.replace(magazine.charAt(i), mapa.get(magazine.charAt(i)) + 1);
//            }
//            else mapa.put(magazine.charAt(i), 1);
//        }
//
//        for (int j = 0; j < ransomNote.length(); j++){
//            if (!mapa.containsKey(ransomNote.charAt(j)) ||
//                    mapa.get(ransomNote.charAt(j)) < 1) return false;
//
//            mapa.replace(ransomNote.charAt(j), mapa.get(ransomNote.charAt(j)) -1);
//        } return true;
    }

    /**
     * TASK 392. Is Subsequence
     * Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
     * A subsequence of a string is a new string that is formed from the original string
     * by deleting some (can be none) of the characters
     * without disturbing the relative positions of the remaining characters.
     * (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
     * <p>
     * Example:
     * Input: s = "abc", t = "ahbgdc"
     * Output: true
     */
    public static boolean isSubsequence(String s, String t) {
        if (t.length() < s.length()) return false;
        int k = 0;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            while (k < t.length()) {
                if (s.charAt(i) == t.charAt(k)) {
                    count++;
                    k++;
                    break;
                }
                k++;
            }
        }
        return (count == s.length());
    }

    /**
     * TASK 557. Reverse Words in a String III
     * Given a string s, reverse the order of characters in each word within
     * a sentence while still preserving whitespace and initial word order.
     * <p>
     * Example:
     * Input: s = "Let's take LeetCode contest"
     * Output: "s'teL ekat edoCteeL tsetnoc"
     */
    public static String reverseWords(String s) {
        char[] arr = s.toCharArray();
        int len = arr.length;
        int prev_space = -1;
        for (int i = 0; i <= len; i++) {
            if (i == len || arr[i] == ' ') {
                int begin = prev_space + 1;
                int end = i - 1;
                while (begin < end) {
                    char tmp = arr[begin];
                    arr[begin] = arr[end];
                    arr[end] = tmp;
                    begin++;
                    end--;
                }
                prev_space = i;
            }
        }

        return new String(arr);
    }

    public static boolean checkInclusion(String s1, String s2) {
        int remember;
        char[] arr2 = s2.toCharArray();
        char[] arr1 = s1.toCharArray();
        for (int i = arr2.length - 1; i >= 0; i--) {
            remember = i;
            int counter = 0;
            for (char c : arr1) {
                if (i < 0 || c != arr2[i]) break;
                i--;
                counter++;
            }
            if (counter == arr1.length) return true;
            i = remember;
        }
        return false;
    }
}