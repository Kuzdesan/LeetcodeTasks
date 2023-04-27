import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {}
}

class Maths {
    /**
     * TASK 7. Reverse integer
     * Given a signed 32-bit integer x, return x with its digits reversed.
     * If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.
     * Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
     */
    public static int reverse(int x) {
        if (Math.abs(x) > Math.pow(2, 31)) return 0;
        long rev = 0;
        while (x / 10 != 0) {
            rev = rev * 10 + x % 10;
            x = x / 10;
        }

        rev = rev * 10 + x;
        if (rev < Math.pow(-2, 31) || rev > Math.pow(2, 31)) return 0;
        return (int) rev;
    }

    /**
     * TASK 9. Palindrome number
     * Given an integer x, return true if x is a palindrome, and false otherwise.
     * Example:
     * Input: x = -121
     * Output: false
     * Explanation: From left to right, it reads -121.
     * From right to left, it becomes 121-. Therefore it is not a palindrome.
     */
    public static boolean isPalindrome(int x) {
        if (x < 0) return false;
        if (x / 10 == 0) return true;

        int rev = 0;
        int beg = x;
        while (x / 10 != 0) {
            rev = rev * 10 + x % 10;
            x = x / 10;
        }
        rev = rev * 10 + x;
        return (rev == beg);
    }

    /**
     * TASK 13. Roman to Integer
     * Given a roman numeral, convert it to an integer.
     * Example:
     * Input: s = "MCMXCIV"
     * Output: 1994
     * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
     */
    public static int romanToInt(String s) {
        /*Map<Character, Integer> lit = new HashMap<>();
         lit.put('I', 1);
         lit.put('V', 5);
         lit.put('X', 10);
         lit.put('L', 50);
         lit.put('C', 100);
         lit.put('D', 500);
         lit.put('M', 1000);
         char[] str = s.toCharArray();
         int sum =0;
         for (int i = str.length-1; i>=0; i--){
         if(i!=0 && lit.get(str[i])>lit.get(str[i-1]) ){
         sum+= lit.get(str[i])-lit.get(str[i-1]);
         if (i==1) break;
         else  i = i-1;

         }
         else sum+=lit.get(str[i]);
         }
         return sum;*/

        int current = 0;
        int prev = 0;
        int answer = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            switch (s.charAt(i)) {
                case 'I':
                    current = 1;
                    break;
                case 'V':
                    current = 5;
                    break;
                case 'X':
                    current = 10;
                    break;
                case 'L':
                    current = 50;
                    break;
                case 'C':
                    current = 100;
                    break;
                case 'D':
                    current = 500;
                    break;
                case 'M':
                    current = 1000;
                    break;
            }


            if (current >= prev) answer += current;
            else answer -= current;

            prev = current;
        }
        return answer;

    }

    /**
     * TASK 50. Pow(x, n)
     * Implement pow(x, n), which calculates x raised to the power n (i.e., x^n).
     * Constraints:
     * -100.0 < x < 100.0
     * -2^31 <= n <= 231^-1
     * n is an integer.
     * -10^4 <= x^n <= 10^4
     */
    public static double myPow(double x, int n) {
        if (x == 0.0 || n == 1 || (x == 1.0 && n % 2 == 0) || n == Integer.MAX_VALUE) return x;
        else if (x == -1.0 && n % 2 == 0) return 1.0;
        if (n == 0) return 1.0;
        double res;
        long i = 1;
        if (n < 0) {
            res = 1 / x;
            if (n == Integer.MIN_VALUE) {
                while (i < n * (-1) - 1) {
                    res = res * (1 / x);
                    i++;
                }
            } else {
                while (i < n * (-1)) {
                    res = res * (1 / x);
                    i++;
                }
            }
        } else {
            res = x;
            while (i < n) {
                res = res * x;
                i++;
            }
        }

        return res;
    }

    /**
     * TASK 69. Sqrt(x)
     * Given a non-negative integer x, return the square root of x rounded down to the nearest integer.
     * The returned integer should be non-negative as well.
     * You must not use any built-in exponent function or operator.
     * <p>
     * Example:
     * Input: x = 8
     * Output: 2
     * Explanation: The square root of 8 is 2.82842..., and since we round it down to the nearest integer, 2 is returned.
     */
    public static int mySqrt(int x) {
        int beg = 0;
        int end = x;
        while (beg <= end) {
            int middle = (end + beg) / 2;
            if (middle * middle == x || (middle * middle < x && (middle + 1) * (middle + 1) > x)) return middle;
            else if (middle * middle < x) beg = middle + 1;
            else end = middle - 1;
        }
        return -1;
    }

    /**
     * TASK 70. Climbing Stairs (Fibonacci Sequence)
     * You are climbing a staircase. It takes n steps to reach the top.
     * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
     * <p>
     * Example:
     * Input: n = 3
     * Output: 3
     * Explanation: There are three ways to climb to the top.
     * 1. 1 step + 1 step + 1 step
     * 2. 1 step + 2 steps
     * 3. 2 steps + 1 step
     */
    public static int climbStairs(int n) {
        if (n >= 0 && n <= 3) return n;
        int[] arr = new int[n + 1];
        arr[0] = 0;
        arr[1] = 1;
        arr[2] = 2;
        for (int i = 3; i < arr.length; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr[n];
    }

    /**
     * TASK 118. Pascal's Triangle
     * Given an integer numRows, return the first numRows of Pascal's triangle.
     * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
     * <p>
     * Example:
     * Input: numRows = 5
     * Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
     */
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<>();
        LinkedList<Integer> integers = new LinkedList<>();

//        int n = numRows - 1;
        int n = 1;
        List<Integer> row_first = new ArrayList<>();
        row_first.add(1);
        list.add(row_first);
        if (numRows == 1) return list;
        while (n < numRows) {
            List<Integer> row = new ArrayList<>();
            int k = 0;
            while (k < (n + 1) / 2) {
                int factorial = C_from_n_to_k(k, n);
                row.add(k, factorial);
                k++;
            }
            int i = 1;
            while (i <= k) {
                row.add(row.get(k - i));
                i++;
            }
            if (n % 2 == 0) row.add(n / 2, C_from_n_to_k(k, n));
            list.add(row);
            n++;
        }
        return list;
    }

    /**
     * TASK 119. Pascal's Triangle II
     * Given an integer rowIndex, return the rowIndexth (0-indexed) row of the Pascal's triangle.
     * <p>
     * Example:
     * Input: rowIndex = 3
     * Output: [1,3,3,1]
     */
    public static List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        int k = 0;
        while (k < (rowIndex + 1) / 2) {
            int factorial = C_from_n_to_k(k, rowIndex);
            row.add(k, factorial);
            k++;
        }
        int i = 1;
        while (i <= k) {
            row.add(row.get(k - i));
            i++;
        }
        if (rowIndex % 2 == 0) row.add(rowIndex / 2, C_from_n_to_k(k, rowIndex));
        return row;
    }

    public static int C_from_n_to_k(int k, int n) {
        if (k == 0) return 1;
        if (k == 1) return n;
        long up = 1;
        long down = 1;

        int i = k;

        if (up % 2 == 0) {
            up = up / 2;

        }

        while (k > 0) {
            if (up % 2 == 0 && down % 2 == 0) {
                up = up / 2;
                down = down / 2;
            }
            up = up * (n - k + 1);
            down = down * k;
            k--;
        }
        return (int) (up / down);
    }

    /**
     * TASK 136. Single Number (Xor)
     * Given a non-empty array of integers nums, every element appears twice except for one.
     * Find that single one.
     * You must implement a solution with a linear runtime complexity and use only constant extra space.
     * <p>
     * Example:
     * Input: nums = [4,1,2,1,2]
     * Output: 4
     */
    public static int singleNumber(int[] nums) {
        int xored = 0;
        for (int i = 0; i < nums.length; i++) xored ^= nums[i];
        return xored;
    }

    /**
     * TASK 202. Happy Number
     * Write an algorithm to determine if a number n is happy.
     * A happy number is a number defined by the following process:
     * -Starting with any positive integer, replace the number by the sum of the squares of its digits.
     * -Repeat the process until the number equals 1 (where it will stay),
     * or it loops endlessly in a cycle which does not include 1.
     * -Those numbers for which this process ends in 1 are happy.
     * Return true if n is a happy number, and false if not.
     * <p>
     * Example:
     * Input: n = 19
     * Output: true
     * Explanation:
     * 12 + 92 = 82
     * 82 + 22 = 68
     * 62 + 82 = 100
     * 12 + 02 + 02 = 1
     */
    public static boolean isHappy(int n) {
        HashMap<Integer, Integer> mapa = new HashMap<>();
        int res = 0;
        while (n > 0) {
            while (n > 0) {
                res += Math.pow((n % 10), 2);
                n = n / 10;
            }
            if (mapa.containsKey(res)) return false;
            mapa.put(res, 0);
            if (res == 1) return true;
            n = res;
            res = 0;
        }
        return true;
    }

    /**
     * TASK 263. Ugly Number
     * An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.
     * Given an integer n, return true if n is an ugly number.
     * <p>
     * Example 1:
     * Input: n = 6
     * Output: true
     * Explanation: 6 = 2 × 3
     * <p>
     * Example 2:
     * Input: n = 1
     * Output: true
     * Explanation: 1 has no prime factors, therefore all of its prime factors are limited to 2, 3, and 5.
     */
    public static boolean isUgly(int n) {
        if (n <= 0) return false;
        if (n == 1) return true;
        if (n % 2 != 0 && n % 3 != 0 && n % 5 != 0) return false;
        int k = 2;
        while (k < 8 && n != 0) {
            if (k == 7) return false;
            if (n % k != 0) k = k + 1;
            else {
                n = n / k;
                if (n == 1) return true;
            }
        }
        return (n == 0);
    }

    /**
     * TASK 509. Fibonacci Number
     * The Fibonacci numbers, commonly denoted F(n) form a sequence,
     * called the Fibonacci sequence, such that each number is the sum of the two preceding ones,
     * starting from 0 and 1.
     * That is,
     * F(0) = 0, F(1) = 1
     * F(n) = F(n - 1) + F(n - 2), for n > 1.
     * Given n, calculate F(n).
     * <p>
     * Example:
     * Input: n = 4
     * Output: 3
     * Explanation: F(4) = F(3) + F(2) = 2 + 1 = 3.
     */
    public static int fib(int n) {
        if (n == 0 || n == 1) return n;
        int[] arr = new int[n + 1];
        arr[0] = 0;
        arr[1] = 1;
        for (int i = 2; i < arr.length; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr[n];
    }

    /**
     * TASK 1342. Number of Steps to Reduce a Number to Zero
     * Given an integer num, return the number of steps to reduce it to zero.
     * In one step, if the current number is even, you have to divide it by 2, otherwise, you have to subtract 1 from it.
     * <p>
     * Example:
     * Input: num = 14
     * Output: 6
     * Explanation:
     * Step 1) 14 is even; divide by 2 and obtain 7.
     * Step 2) 7 is odd; subtract 1 and obtain 6.
     * Step 3) 6 is even; divide by 2 and obtain 3.
     * Step 4) 3 is odd; subtract 1 and obtain 2.
     * Step 5) 2 is even; divide by 2 and obtain 1.
     * Step 6) 1 is odd; subtract 1 and obtain 0.
     */
    public static int numberOfSteps(int num) {
        int steps = 0;
        if (num == 0) return 0;
        if (num == 1) return 1;
        while (num != 0) {
            if (num % 2 == 0) num = num / 2;
            else num--;
            steps++;
        }
        return steps;
    }

    /**
     * TASK 1518. Water Bottles
     * There are numBottles water bottles that are initially full of water.
     * You can exchange numExchange empty water bottles from the market with one full water bottle.
     * The operation of drinking a full water bottle turns it into an empty bottle.
     * Given the two integers numBottles and numExchange, return the maximum number of water bottles you can drink.
     * <p>
     * Example:
     * Input: numBottles = 15, numExchange = 4
     * Output: 19
     * Explanation: You can exchange 4 empty bottles to get 1 full water bottle.
     * Number of water bottles you can drink: 15 + 3 + 1 = 19.
     */
    public static int numWaterBottles(int numBottles, int numExchange) {
        int sum = 0;
        int empty = 0;
        int full = numBottles;
        while (empty + full >= numExchange) {
            sum += full;
            empty += full;
            full = 0;
            full += empty / numExchange;
            empty -= full * numExchange;
        }
        sum += full;
        return sum;
    }

    /**
     * TASK 1688. Count of Matches in Tournament
     * You are given an integer n, the number of teams in a tournament that has strange rules:
     * If the current number of teams is even, each team gets paired with another team.
     * A total of n / 2 matches are played, and n / 2 teams advance to the next round.
     * If the current number of teams is odd, one team randomly advances in the tournament, and the rest gets paired.
     * A total of (n - 1) / 2 matches are played, and (n - 1) / 2 + 1 teams advance to the next round.
     * Return the number of matches played in the tournament until a winner is decided.
     * <p>
     * Example:
     * Input: n = 7
     * Output: 6
     * Explanation: Details of the tournament:
     * - 1st Round: Teams = 7, Matches = 3, and 4 teams advance.
     * - 2nd Round: Teams = 4, Matches = 2, and 2 teams advance.
     * - 3rd Round: Teams = 2, Matches = 1, and 1 team is declared the winner.
     * Total number of matches = 3 + 2 + 1 = 6.
     */
    public static int numberOfMatches(int n) {
        int matches = 0;
        while (n > 1) {
            if (n % 2 == 0) {
                matches += n / 2;
                n = n / 2;
            } else {
                matches += n / 2;
                n = n / 2 + 1;
            }
        }
        return matches;
    }

    /**
     * TASK 1716. Calculate Money in Leetcode Bank
     * Hercy wants to save money for his first car. He puts money in the Leetcode bank every day.
     * He starts by putting in $1 on Monday, the first day.
     * Every day from Tuesday to Sunday, he will put in $1 more than the day before.
     * On every subsequent Monday, he will put in $1 more than the previous Monday.
     * Given n, return the total amount of money he will have in the Leetcode bank at the end of the n-th day.
     * <p>
     * Example:
     * Input: n = 10
     * Output: 37
     * Explanation: After the 10th day,
     * the total is (1 + 2 + 3 + 4 + 5 + 6 + 7) + (2 + 3 + 4) = 37.
     * Notice that on the 2nd Monday, Hercy only puts in $2.
     */
    public static int totalMoney(int n) {
        int sum = 0;
        int a1 = 1;
        int full_week = n / 7;
        int days = n % 7;
        while (full_week > 0) {
            sum += ((2 * a1 + (7 - 1)) * 7) / 2;
            a1++;
            full_week--;
        }
        sum += ((2 * a1 + (days - 1)) * days) / 2;
        return sum;
    }

    /**
     * TASK 1945. Sum of Digits of String After Convert
     * You are given a string s consisting of lowercase English letters, and an integer k.
     * First, convert s into an integer by replacing each letter with its position in the alphabet
     * (i.e., replace 'a' with 1, 'b' with 2, ..., 'z' with 26).
     * Then, transform the integer by replacing it with the sum of its digits.
     * Repeat the transform operation k times in total.
     * For example, if s = "zbax" and k = 2, then the resulting integer would be 8 by the following operations:
     * Convert: "zbax" ➝ "(26)(2)(1)(24)" ➝ "262124" ➝ 262124
     * Transform #1: 262124 ➝ 2 + 6 + 2 + 1 + 2 + 4 ➝ 17
     * Transform #2: 17 ➝ 1 + 7 ➝ 8
     * Return the resulting integer after performing the operations described above.
     * <p>
     * Example:
     * Input: s = "iiii", k = 1
     * Output: 36
     * Explanation: The operations are as follows:
     * - Convert: "iiii" ➝ "(9)(9)(9)(9)" ➝ "9999" ➝ 9999
     * - Transform #1: 9999 ➝ 9 + 9 + 9 + 9 ➝ 36
     * Thus the resulting integer is 36.
     */
    public static int getLucky(String s, int k) {
        int sum = 0;
        int count = 0;
        for (char c : s.toCharArray()) {
            int val = c - 'a' + 1;
            while (val > 0) {
                sum += val % 10;
                val = val / 10;
            }
        }
        count++;
        if (k == 1 || sum < 10) return sum;
        int res = 0;
        while (sum > 0 && count < k) {
            if (sum < 10) return 0;
            while (sum > 0) {
                res += sum % 10;
                sum = sum / 10;
            }
            sum = res;
            res = 0;
            count++;
        }
        return sum;
    }
}