import java.util.*;

public class Main {

    public static void main(String[] args) {
    }
}

class MyArrays {
    /**
     * TASK 26. Remove Duplicates from Sorted Array
     * Given an integer array nums sorted in non-decreasing order,
     * remove the duplicates in-place such that each unique element appears only once.
     * The relative order of the elements should be kept the same.
     * Then return the number of unique elements in nums.
     * Consider the number of unique elements of nums to be k, to get accepted, you need to do the following things:
     * -Change the array nums such that the first k
     * elements of nums contain the unique elements in the order they were present in nums initially.
     * The remaining elements of nums are not important as well as the size of nums.
     * -Return k.
     * <p>
     * Example:
     * Input: nums = [1,1,2]
     * Output: 2, nums = [1,2,_]
     * Explanation: Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
     * It does not matter what you leave beyond the returned k (hence they are underscores).
     */
    public static int removeDuplicates(int[] nums) {
        if (nums.length < 2) return nums.length;
        int insertUniqueIndex = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) nums[insertUniqueIndex++] = nums[i];
        }
        return insertUniqueIndex;
    }

    /**
     * TASK 27. Remove Element
     * Given an integer array nums and an integer val, remove all occurrences of val in nums in-place.
     * The order of the elements may be changed. Then return the number of elements in nums which are not equal to val.
     * Consider the number of elements in nums which are not equal to val be k,
     * to get accepted, you need to do the following things:
     * -Change the array nums such that the first k elements of nums contain the elements which are not equal to val.
     * The remaining elements of nums are not important as well as the size of nums.
     * -Return k.
     * <p>
     * Example:
     * Input: nums = [0,1,2,2,3,0,4,2], val = 2
     * Output: 5, nums = [0,1,4,0,3,_,_,_]
     * Explanation: Your function should return k = 5, with the first five elements of nums containing 0, 0, 1, 3, and 4.
     * Note that the five elements can be returned in any order.
     * It does not matter what you leave beyond the returned k (hence they are underscores).
     */
    public static int removeElement(int[] nums, int val) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) nums[index++] = nums[i];
        }

        return index;
    }

    /**
     * TASK 35. Search Insert Position
     * Given a sorted array of distinct integers and a target value,
     * return the index if the target is found.
     * If not, return the index where it would be if it were inserted in order.
     * You must write an algorithm with O(log n) runtime complexity.
     * <p>
     * Example:
     * Input: nums = [1,3,5,6], target = 2
     * Output: 1
     */
    public static int searchInsert(int[] nums, int target) {
        int beg = 0;
        int end = nums.length - 1;
        while (beg <= end) {
            int middle = (beg + end) / 2;
            if (target == nums[middle]) return middle;
            else if (target < nums[middle]) {
                end = middle - 1;
            } else beg = middle + 1;
        }
        return beg;
    }

    /**
     * TASK 66. Plus One
     * You are given a large integer represented as an integer array digits,
     * where each digits[i] is the i-th digit of the integer.
     * The digits are ordered from most significant to least significant in left-to-right order.
     * The large integer does not contain any leading 0's.
     * Increment the large integer by one and return the resulting array of digits.
     * <p>
     * Example:
     * Input: digits = [9]
     * Output: [1,0]
     * Explanation: The array represents the integer 9.
     * Incrementing by one gives 9 + 1 = 10.
     * Thus, the result should be [1,0].
     */
    public static int[] plusOne(int[] digits) {
        int l = digits.length;
        if (digits[l - 1] + 1 < 10) {
            digits[l - 1] = digits[l - 1] + 1;
            return digits;
        } else {
            HashMap<Integer, Integer> mapa = new HashMap<>();
            int ptr = 0;

            for (int j = l - 1; j >= 0; j--) {
                int sum;
                if (j == l - 1) sum = digits[j] + 1 + ptr;
                else sum = digits[j] + ptr;

                mapa.put(l - j - 1, sum % 10);
                ptr = sum / 10;
            }
            if (ptr != 0) mapa.put(l, ptr);

            int sz = mapa.size();
            int[] end = new int[sz];
            for (int k = 0; k < sz; k++) {
                end[k] = mapa.get(sz - 1 - k);
            }
            return end;
        }
    }

    /**
     * TASK 88. Merge Sorted Array
     * You are given two integer arrays nums1 and nums2, sorted in non-decreasing order,
     * and two integers m and n, representing the number of elements in nums1 and nums2 respectively.
     * Merge nums1 and nums2 into a single array sorted in non-decreasing order.
     * The final sorted array should not be returned by the function,
     * but instead be stored inside the array nums1.
     * To accommodate this, nums1 has a length of m + n,
     * where the first m elements denote the elements that should be merged,
     * and the last n elements are set to 0 and should be ignored. nums2 has a length of n.
     * <p>
     * Example:
     * Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
     * Output: [1,2,2,3,5,6]
     * Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
     * The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
     */
    public static int[] merge(int[] nums1, int m, int[] nums2, int n) {
        int j = n - 1;
//        if (m == 0){
//            for (int i =0; i<n; i++){
//                nums1[i] = nums2[i];
//            }
//            return nums1;
//        }
        for (int i = m - 1; i >= 0; i--) {
            while (j >= 0) {
                if (nums1[i] <= nums2[j]) {
                    nums1[i + j + 1] = nums2[j];
                    i++;
                    j--;
                } else {
                    nums1[i + j + 1] = nums1[i];
                    nums1[i] = 0;
                }
                break;
            }
            if (j < 0) return nums1;
        }
        if (j >= 0) {
            while (j >= 0) {
                nums1[j] = nums2[j];
                j--;
            }

        }
        return nums1;
    }

    /**
     * TASK 121. Best Time to Buy and Sell Stock
     * You are given an array prices where prices[i] is the price of a given stock on the i-th day.
     * You want to maximize your profit by choosing a single day to buy one stock and
     * choosing a different day in the future to sell that stock.
     * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
     * <p>
     * Example:
     * Input: prices = [7,1,5,3,6,4]
     * Output: 5
     * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
     * Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
     */
    public int maxProfit(int[] prices) {
        int profit = 0;
        int min = 10001;
        for (int i = 0; i < prices.length; i++) {
            int localMin = prices[i];
            if (localMin < min) {
                min = localMin;
                for (int j = i + 1; j < prices.length; j++) {
                    if ((prices[j] - localMin) > profit) profit = (prices[j] - localMin);
                }
            }
        }
        return profit;
    }

    /**
     * TASK 167. Two Sum II - Input Array Is Sorted
     * Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order,
     * find two numbers such that they add up to a specific target number.
     * Let these two numbers be numbers[index1] and numbers[index2] where 1 <= index1 < index2 <= numbers.length.
     * Return the indices of the two numbers, index1 and index2,
     * added by one as an integer array [index1, index2] of length 2.
     * The tests are generated such that there is exactly one solution.
     * You may not use the same element twice.
     * Your solution must use only constant extra space.
     * <p>
     * Example:
     * Input: numbers = [2,3,4], target = 6
     * Output: [1,3]
     * Explanation: The sum of 2 and 4 is 6. Therefore index1 = 1, index2 = 3. We return [1, 3].
     */
    public static int[] twoSum(int[] numbers, int target) {
        int[] arr = new int[2];

        int beg, end;
        int i = 0;
        while (i < numbers.length) {
            beg = i;
            end = numbers.length - 1;
            arr[0] = i;

            while (beg <= end) {
                int middle = beg + (end - beg) / 2;
                if (target - numbers[i] == numbers[middle] && middle != arr[0]) {
                    arr[0]++;
                    arr[1] = middle + 1;
                    return arr;
                } else if (target - numbers[i] >= numbers[middle]) beg = middle + 1;
                else end = middle - 1;
            }

            i++;
        }
        arr[0]++;
        arr[1]++;
        Arrays.sort(arr);
        return arr;
    }

    /**
     * TASK 169. Majority Element
     * Given an array nums of size n, return the majority element.
     * The majority element is the element that appears more than ⌊n / 2⌋ times.
     * You may assume that the majority element always exists in the array.
     * <p>
     * Example:
     * Input: nums = [2,2,1,1,1,2,2]
     * Output: 2
     */
    public static int majorityElement(int[] nums) {
        /*
        Arrays.sort(nums);
        return nums[nums.length/2];
        */
        if (nums.length == 1) return nums[0];
        HashMap<Integer, Integer> mapa = new HashMap<>();
        for (int i : nums) {
            if (!mapa.containsKey(i)) {
                mapa.put(i, 1);
            } else {
                mapa.replace(i, mapa.get(i) + 1);
                if (mapa.get(i) > nums.length / 2) return i;
            }
        }
        return 0;
    }

    /**
     * TASK 189. Rotate Array
     * Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
     * <p>
     * Example:
     * Input: nums = [-1,-100,3,99], k = 2
     * Output: [3,99,-1,-100]
     * Explanation:
     * rotate 1 steps to the right: [99,-1,-100,3]
     * rotate 2 steps to the right: [3,99,-1,-100]
     */
    public static void rotate(int[] nums, int k) {
        int len = nums.length;
        if (k >= len) k = k % len;
        if (k == 0 || len == 1) return;

        int[] left = Arrays.copyOfRange(nums, len - k, len);
        int[] right = Arrays.copyOfRange(nums, 0, k);
        int[] middle = new int[0];
        if (k <= len - k) middle = Arrays.copyOfRange(nums, k, len - k);
        for (int i = 0; i < left.length; i++) {
            nums[i] = left[i];
            if (i + k < len) nums[i + k] = right[i];
        }

        for (int i = 0; i < middle.length; i++) nums[i + 2 * k] = middle[i];
    }

    /**
     * TASK 217. Contains Duplicate
     * Given an integer array nums, return true if any value
     * appears at least twice in the array, and return false if every element is distinct.
     * <p>
     * Example:
     * Input: nums = [1,2,3,1]
     * Output: true
     */
    public static boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) return true;
        }
        return false;
    }

    /**
     * TASK 228. Summary Ranges
     * You are given a sorted unique integer array nums.
     * A range [a,b] is the set of all integers from a to b (inclusive).
     * Return the smallest sorted list of ranges that cover all the numbers in the array exactly.
     * That is, each element of nums is covered by exactly one of the ranges,
     * and there is no integer x such that x is in one of the ranges but not in nums.
     * Each range [a,b] in the list should be output as:
     * -"a->b" if a != b
     * -"a" if a == b
     * <p>
     * Example:
     * Input: nums = [0,2,3,4,6,8,9]
     * Output: ["0","2->4","6","8->9"]
     * Explanation: The ranges are:
     * [0,0] --> "0"
     * [2,4] --> "2->4"
     * [6,6] --> "6"
     * [8,9] --> "8->9"
     */
    public static List<String> summaryRanges(int[] nums) {
        List<String> list = new ArrayList<>();
        int beg;
        for (int i = 0; i < nums.length; i++) {
            beg = nums[i];
            while (i < nums.length - 1 && nums[i] + 1 == nums[i + 1]) i++;
            if (beg == nums[i]) list.add(beg + "");
            else list.add(beg + "->" + nums[i]);
        }
        return list;
    }

    /**
     * TASK 268. Missing Number (Xor)
     * Given an array nums containing n distinct numbers in the range [0, n],
     * return the only number in the range that is missing from the array.
     * <p>
     * Example:
     * Input: nums = [0,1]
     * Output: 2
     * Explanation: n = 2 since there are 2 numbers,
     * so all numbers are in the range [0,2]. 2 is the missing number in the range since it does not appear in nums.
     */
    public static int missingNumber(int[] nums) {
        int missing = 0;
        for (int i = 0; i < nums.length; i++) missing ^= nums[i];
        for (int i = 0; i <= nums.length; i++) missing ^= i;
        return missing;
    }

    /**
     * TASK 283. Move Zeroes
     * Given an integer array nums, move all 0's to the end of it
     * while maintaining the relative order of the non-zero elements.
     * Note that you must do this in-place without making a copy of the array.
     * <p>
     * Example:
     * Input: nums = [0,1,0,3,12]
     * Output: [1,3,12,0,0]
     */
    public static void moveZeroes(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) nums[index++] = nums[i];
        }

        while (index < nums.length) {
            nums[index] = 0;
            index++;
        }
    }

    /**
     * TASK 349. Intersection of Two Arrays
     * Given two integer arrays nums1 and nums2,
     * return an array of their intersection.
     * Each element in the result must be unique and you may return the result in any order.
     * <p>
     * Example:
     * Input: nums1 = [1,2,2,1], nums2 = [2,2]
     * Output: [2]
     */
    public static int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<Integer>();
        HashSet<Integer> set2 = new HashSet<Integer>();
        for (int i : nums1) set1.add(i);
        for (int i : nums2) set2.add(i);

        if (set1.size() < set2.size()) return intersectionSets(set1, set2);
        else return intersectionSets(set2, set1);

    }

    public static int[] intersectionSets(HashSet<Integer> set1, HashSet<Integer> set2) {
        int[] result = new int[set1.size()];
        int index = 0;
        for (int n : set1) {
            if (set2.contains(n)) result[index++] = n;
        }
        return result;
    }

    /**
     * TASK 349. Intersection of Two Arrays
     * Given two integer arrays nums1 and nums2,
     * return an array of their intersection.
     * Each element in the result must be unique and you may return the result in any order.
     * <p>
     * Example:
     * Input: nums1 = [1,2,2,1], nums2 = [2,2]
     * Output: [2]
     */
    public static int[] intersect(int[] nums1, int[] nums2) {
        LinkedList<Integer> list1 = new LinkedList<>();
        LinkedList<Integer> list2 = new LinkedList<>();
        for (int n : nums1) list1.add(n);
        for (int n : nums2) list2.add(n);
        if (list1.size() <= list2.size()) return intersectList(list1, list2);
        else return intersectList(list2, list1);

    }

    /**
     * TASK 704. Binary Search
     * Given an array of integers nums which is sorted in ascending order,
     * and an integer target, write a function to search target in nums.
     * If target exists, then return its index. Otherwise, return -1.
     * You must write an algorithm with O(log n) runtime complexity.
     * <p>
     * Example:
     * Input: nums = [-1,0,3,5,9,12], target = 9
     * Output: 4
     * Explanation: 9 exists in nums and its index is 4
     */
    public static int search(int[] nums, int target) {
        int begin = 0;
        int end = nums.length - 1;
        while (end >= begin) {
            int middle = begin + (end - begin) / 2;
            if (nums[middle] == target) return middle;
            else if (nums[middle] > target) end = middle - 1;
            else begin = middle + 1;
        }
        return -1;
    }

    /**
     * TASK 724. Find Pivot Index
     * Given an array of integers nums, calculate the pivot index of this array.
     * The pivot index is the index where the sum of all the numbers strictly to the left
     * of the index is equal to the sum of all the numbers strictly to the index's right.
     * If the index is on the left edge of the array, then the left sum is 0
     * because there are no elements to the left. This also applies to the right edge of the array.
     * Return the leftmost pivot index. If no such index exists, return -1.
     * <p>
     * Example 1:
     * Input: nums = [1,7,3,6,5,6]
     * Output: 3
     * Explanation:
     * The pivot index is 3.
     * Left sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11
     * Right sum = nums[4] + nums[5] = 5 + 6 = 11
     * <p>
     * Example 2:
     * Input: nums = [2,1,-1]
     * Output: 0
     * Explanation:
     * The pivot index is 0.
     * Left sum = 0 (no elements to the left of index 0)
     * Right sum = nums[1] + nums[2] = 1 + -1 = 0
     */
    public static int pivotIndex(int[] nums) {
        int leftSum = 0;
        int rightSum = 0;
        int pivot = -1;
        for (int i = 0; i < nums.length; i++) rightSum += nums[i];
        if (rightSum - nums[0] == 0) pivot = 0;
        leftSum += nums[0];
        rightSum -= nums[0];
        for (int i = 1; i < nums.length; i++) {
            rightSum -= nums[i];
            if (leftSum == rightSum) pivot = i;
            leftSum += nums[i];
        }
        return pivot;
    }

    /**
     * TASK 977. Squares of a Sorted Array
     * Given an integer array nums sorted in non-decreasing order,
     * return an array of the squares of each number sorted in non-decreasing order.
     * <p>
     * Example:
     * Input: nums = [-4,-1,0,3,10]
     * Output: [0,1,9,16,100]
     * Explanation: After squaring, the array becomes [16,1,0,9,100].
     * After sorting, it becomes [0,1,9,16,100].
     */
    public static int[] sortedSquares(int[] nums) {
        /*
        IntBinaryOperator bin = (num1, num2) -> (int)Math.pow(num2, 2);
        Arrays.parallelPrefix(nums, bin);
        nums[0] = (int)Math.pow(nums[0], 2);
        Arrays.parallelSort(nums);
         */

        int[] array = new int[nums.length];
        int i = 0;
        int j = nums.length - 1;
        int k = nums.length - 1;
        while (i <= j) {
            int val1 = nums[i] * nums[i];
            int val2 = nums[j] * nums[j];
            if (val1 > val2) {
                array[k] = val1;
                i++;
            } else {
                array[k] = val2;
                j--;
            }
            k--;
        }
        return array;
    }

    /**
     * TASK 1480. Running Sum of 1d Array
     * Given an array nums. We define a running sum of an array as runningSum[i] = sum(nums[0]…nums[i]).
     * Return the running sum of nums.
     * <p>
     * Example:
     * Input: nums = [1,2,3,4]
     * Output: [1,3,6,10]
     * Explanation: Running sum is obtained as follows: [1, 1+2, 1+2+3, 1+2+3+4].
     */
    public int[] runningSum(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }
        return nums;
    }

    /**
     * TASK 1512. Number of Good Pairs
     * Given an array of integers nums, return the number of good pairs.
     * A pair (i, j) is called good if nums[i] == nums[j] and i < j.
     * <p>
     * Example:
     * Input: nums = [1,2,3,1,1,3]
     * Output: 4
     * Explanation: There are 4 good pairs (0,3), (0,4), (3,4), (2,5) 0-indexed.
     */
    public int numIdenticalPairs(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) count++;
            }
        }
        return count;
    }

    /**
     * TASK 1588. Sum of All Odd Length Subarrays
     * Given an array of positive integers arr, return the sum of all possible odd-length subarrays of arr.
     * A subarray is a contiguous subsequence of the array.
     * <p>
     * Example:
     * Input: arr = [1,4,2,5,3]
     * Output: 58
     * Explanation: The odd-length subarrays of arr and their sums are:
     * [1] = 1
     * [4] = 4
     * [2] = 2
     * [5] = 5
     * [3] = 3
     * [1,4,2] = 7
     * [4,2,5] = 11
     * [2,5,3] = 10
     * [1,4,2,5,3] = 15
     * If we add all these together we get 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58
     */
    public static int sumOddLengthSubarrays(int[] A) {
        int res = 0, n = A.length;
        for (int i = 0; i < n; ++i) {
            res += ((i + 1) * (n - i) + 1) / 2 * A[i];
        }
        return res;
    }

    public static int[] findErrorNums(int[] nums) {
        int index = 0;
        int[] arr = new int[2];
        arr[0] = index;
        arr[1] = -1;
        return arr;
    }

    public static int[] intersectList(LinkedList<Integer> list1, LinkedList<Integer> list2) {
        int[] res = new int[list1.size()];
        int index = 0;
        for (int n : list1) {
            if (list2.contains(n)) res[index++] = n;
        }
        return Arrays.copyOf(res, index);
    }

    public static void subsets(int[] nums) {
        List<List<Integer>> total = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(null);
        total.add(list);
        Arrays.sort(nums);
        int numUnique = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) nums[numUnique++] = nums[i];
        }
        int k = 0;
        int listsNum = 0;
        for (int i = 0; i < numUnique; i++) {
            while (listsNum < numUnique) {
                List<Integer> list1 = new ArrayList<>();
                while (k < numUnique) {
                    list1.add(nums[k++]);
                }
                total.add(list1);
                listsNum++;
                k = listsNum;
            }
        }

        return;
    }
}