public class Main {

    public static void main(String[] args) {
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    /**
     * TASK 2. Add Two Numbers
     * You are given two non-empty linked lists representing two non-negative integers.
     * The digits are stored in reverse order, and each of their nodes contains a single digit.
     * Add the two numbers and return the sum as a linked list.
     * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
     * <p>
     * Example 1:
     * Input: l1 = [2,4,3], l2 = [5,6,4]
     * Output: [7,0,8]
     * Explanation: 342 + 465 = 807.
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode l3 = new ListNode();
        ListNode tmp = l3;
        int to_add = 0;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + to_add;
            to_add = sum / 10;
            tmp.val = sum % 10;
            tmp.next = new ListNode();
            tmp = tmp.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        if (l1 != null) {
            while (l1 != null) {
                int sum = l1.val + to_add;
                tmp.val = sum % 10;
                to_add = sum / 10;
                l1 = l1.next;
                tmp = tmp.next;
            }
        } else if (l2 != null) {
            while (l2 != null) {
                int sum = l2.val + to_add;
                tmp.val = sum % 10;
                to_add = sum / 10;
                l2 = l2.next;
                tmp.next = new ListNode();
                tmp = tmp.next;
            }
        }
        if (to_add != 0) {
            tmp.val = to_add;
            tmp.next = null;
        } else tmp = null;
        return l3;
    }

    /**
     * TASK 19. Remove Nth Node From End of List
     * Given the head of a linked list, remove the n-th node from the end of the list and return its head.
     * Example 1:
     * Input: head = [1,2,3,4,5], n = 2
     * Output: [1,2,3,5]
     * Example 2:
     * Input: head = [1], n = 1
     * Output: []
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode tmp = head;
        int count = 0;
        while (tmp != null) {
            count++;
            tmp = tmp.next;
        }
        if (count == n && head != null) return head.next;
        ListNode ptr = head;
        int k = 0;
        while (ptr != null) {
            k++;
            if (count - k == n) {
                if (ptr.next != null) ptr.next = ptr.next.next;
                else ptr.next = null;
                return head;
            }
            ptr = ptr.next;
        }
        return head;
    }

    /**
     * TASK 21. Merge two sorted lists
     * You are given the heads of two sorted linked lists list1 and list2.
     * Merge the two lists in a one sorted list.
     * The list should be made by splicing together the nodes of the first two lists.
     * Return the head of the merged linked list.
     * Example:
     * Input: list1 = [1,2,4], list2 = [1,3,4]
     * Output: [1,1,2,3,4,4]
     */
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null) return null;

        ListNode merged = new ListNode();
        ListNode tmp = merged;
        while (list1 != null && list2 != null) {

            if (list1.val <= list2.val) {
                tmp.next = list1;
                list1 = list1.next;
            } else {
                tmp.next = list2;
                list2 = list2.next;
            }
            tmp = tmp.next;
        }

        if (list2 == null) {
            tmp.next = list1;
            return merged.next;
        }

        tmp.next = list2;
        return merged.next;
    }

    /**
     * TASK 82. Remove Duplicates from Sorted List II
     * Given the head of a sorted linked list,
     * delete all nodes that have duplicate numbers,
     * leaving only distinct numbers from the original list.
     * Return the linked list sorted as well.
     * <p>
     * Example:
     * Input: head = [1,2,3,3,4,4,5]
     * Output: [1,2,5]
     **/
    public static ListNode deleteDuplicatesII(ListNode head) {
        if (head == null) return null;
        ListNode tmp = head;
        ListNode prev = null;

        while (tmp != null && tmp.next != null) {
            if (tmp.next.val != tmp.val) {
                prev = tmp;
                tmp = tmp.next;
            } else {
                /*Двигаемся до элемента, предшествующего элементу с другим значением
                или до последнего жлемента списка и останавливаемся на нем
                Т.е. если было 1 1 1 2, то остановимся и выйдем на 1 2
                если было 1 1 1 1, то выйдем на 1*/
                while (tmp.next != null && tmp.next.val == tmp.val) {
                    tmp = tmp.next;
                }
                /*Случай, когда все элменты - одинаковы (ни разу не зашли в самое первое услове и
                дошли до конца списка)
                 */
                if (prev == null && tmp.next == null) return null;

                /*Случай, когда в начале списка идут одинаковые значения,
                тогда мы сдвигаем голову к первому отличному значению
                (еще не заходили в самое первое условие, но дальше есть элементы, отличные от первого)
                 */
                else if (prev == null && tmp.next != null) head = tmp.next;
                else prev.next = tmp.next;

                tmp = tmp.next;
            }
        }
        return head;
    }

    /**
     * TASK 83. Remove Duplicates from Sorted List
     * Given the head of a sorted linked list,
     * delete all duplicates such that each element appears only once. Return the linked list sorted as well.
     * <p>
     * Example:
     * Input: head = [1,1,2,3,3]
     * Output: [1,2,3]
     */
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode tmp = head;
        while (tmp != null) {
            if (tmp.next != null && tmp.next.val == tmp.val) {
                tmp.next = tmp.next.next;
            } else tmp = tmp.next;
        }
        return head;
    }

    /**
     * TASK 92. Reverse Linked List II
     * Given the head of a singly linked list and two integers left and right where left <= right,
     * reverse the nodes of the list from position left to position right, and return the reversed list.
     * <p>
     * Example:
     * Input: head = [1,2,3,4,5], left = 2, right = 4
     * Output: [1,4,3,2,5]
     */
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || head.next == null || left == right) return head;
        ListNode right_part = head;
        int k = 1;
        while (right_part != null && k < left) {
            right_part = right_part.next;
            k++;
        }

        ListNode reversed = null;
        int count = 0;

        /*
         * Because of the right_part.next = reversed on the first step we have right_part.next = null
         * (here right_part is a start position for reverse)
         * And so in head it also changes
         * But reversed list also includes pointer right_part (last position of the reversed list)
         * So, there can be a cycle, if incorrectly change head.next (where head == right_part) = reversed
         */
        while (right_part != null && count <= right - left) {
            ListNode tmp = right_part.next;
            right_part.next = reversed;
            reversed = right_part;
            right_part = tmp;
            count++;
        }

        ListNode ptr = reversed;
        while (ptr != null && ptr.next != null) {
            ptr = ptr.next;
        }
        ListNode head_tmp = head;
        while (head_tmp.next != null && head_tmp.next.next != null) {
            head_tmp = head_tmp.next;
        }
        if (ptr != null) ptr.next = right_part;
        /*
        in this case of left==1 we start reverse from the head.
        After reversion, we have a reversed list,
        but the head is still not null
        so using head.next = reversed we will have endless cycle
        */
        if (head_tmp != null && left > 1) head_tmp.next = reversed;
        else head = reversed;
        return head;
    }

    /**
     * TASK 141. Linked List Cycle
     * Given head, the head of a linked list, determine if the linked list has a cycle in it.
     * There is a cycle in a linked list if there is some node in the list
     * that can be reached again by continuously following the next pointer.
     * Internally, pos is used to denote the index of the node that tail's next pointer is connected to.
     * Note that pos is not passed as a parameter.
     * Return true if there is a cycle in the linked list. Otherwise, return false.
     * <p>
     * Example:
     * Input: head = [3,2,0,-4], pos = 1
     * Output: true
     * Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).
     */
    public static boolean hasCycle(ListNode head) {
//        ListNode tmp = head;
//        while(tmp != null){
//            if (tmp.val == tmp.hashCode()+1) return true;
//            tmp.val = tmp.hashCode() + 4;
//            tmp = tmp.next;
//        }
//        return false;

        ListNode tmp = head;
        while (tmp != null && tmp.next != null) {
            head = head.next;
            tmp = tmp.next.next;
            if (head == tmp) return true;
        }
        return false;
    }

    /**
     * TASK 143. Reorder List
     * You are given the head of a singly linked-list. The list can be represented as:
     * L0 → L1 → … → Ln - 1 → Ln
     * Reorder the list to be on the following form:
     * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
     * You may not modify the values in the list's nodes. Only nodes themselves may be changed.
     * <p>
     * Example:
     * Input: head = [1,2,3,4,5]
     * Output: [1,5,2,4,3]
     */
    public static void reorderList(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode rev = reverse(slow);
        int count = 1;
        ListNode ptr = head.next; //почему когда мы меняем хед, птр не меняется
        ListNode tmp = head;
        while (rev != null && ptr != null) {
            if (count % 2 == 1) {
                tmp.next = rev;
                rev = rev.next;
            } else {
                tmp.next = ptr;
                ptr = ptr.next;

            }
            tmp = tmp.next;
            count++;
        }
    }

    /**
     * TASK 206. Reverse Linked List
     * Given the head of a singly linked list, reverse the list, and return the reversed list.
     * <p>
     * Example:
     * Input: head = [1,2,3,4,5]
     * Output: [5,4,3,2,1]
     */
    public static ListNode reverse(ListNode l) {
        ListNode reversed = null;
        while (l != null) {
            ListNode tmp = l.next;
            l.next = reversed;
            reversed = l;
            l = tmp;
        }
        return reversed;
    }

    /**
     * Task 234. Palindrome Linked List
     * Given the head of a singly linked list, return true if it is a palindrome or false otherwise.
     * <p>
     * Example:
     * Input: head = [1,2,2,1]
     * Output: true
     */
    static public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;

        ListNode reversed = null;
        ListNode slow = head;
        ListNode fast = head;

        // found middle of list
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // reversed second half of list
        while (slow != null) {

            ListNode next = slow.next;
            slow.next = reversed;
            reversed = slow;
            slow = next;
        }

        // compared first and second half
        while (reversed != null) {
            if (head.val != reversed.val) return false;
            head = head.next;
            reversed = reversed.next;
        }

        return true;
    }

    /**
     * TASK 876. Middle of the Linked List
     * Given the head of a singly linked list, return the middle node of the linked list.
     * If there are two middle nodes, return the second middle node.
     * <p>
     * Example:
     * Input: head = [1,2,3,4,5,6]
     * Output: [4,5,6]
     * Explanation: Since the list has two middle nodes with values 3 and 4, we return the second one.
     */
    public static ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
