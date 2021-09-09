package LeetCode.answer;

import LeetCode.enumUtil.DayEnum;

import java.util.*;

/**
 * @author 许炼江
 * @CreatTime 2021/6/4-13:25
 */
@SuppressWarnings("")
public class LeetCodeUtil {
    /**
     * 加密，前面为原始数据
     */
    public static final HashMap<Character, Character> ENCODE_CHARACTERS = new HashMap<>();

    /**
     * 解密，后面为原始数据
     */
    public static final HashMap<Character, Character> DECODE_CHARACTERS = new HashMap<>();

    static {
        for (char original = 48; original <= 57; original++) {
            if (original != '9') {
                ENCODE_CHARACTERS.put(original, (char) (original + 1));
            } else {
                ENCODE_CHARACTERS.put(original, '0');
            }
        }

        for (char original = 64; original <= 90; original++) {
            if (original != 90) {
                ENCODE_CHARACTERS.put(original, (char) (original + 1));
            } else {
                ENCODE_CHARACTERS.put(original, 'A');
            }
        }

        for (char original = 97; original <= 122; original++) {
            if (original != 122) {
                ENCODE_CHARACTERS.put(original, (char) (original + 1));
            } else {
                ENCODE_CHARACTERS.put(original, 'a');
            }
        }
    }

    public LeetCodeUtil() {
    }

    /**
     * 找出0到sumNumber中每组最大中的最小数
     *
     * @param loop        循环次数
     * @param sumNumber   数字在0到sumNumber中
     * @param groupNumber 每组个数
     * @param output      数字循环出现次数
     */
    public static void RandomMaxThenMin(int loop, int sumNumber, int groupNumber, int output) {
        if (sumNumber % groupNumber != 0) {
            return;
        }
        int partNumber = sumNumber / groupNumber;
        int count = 0;
        int min = Integer.MAX_VALUE;
        while (loop-- > 0) {
            Map<Integer, Boolean> start = new HashMap<>();
            for (int i = 0; i < sumNumber; i++) {
                start.put(i, false);
            }
            int[] res = new int[partNumber];
            for (int i = 0; i < partNumber; i++) {
                res[i] = -1;
            }
            while (!start.isEmpty()) {
                for (int i = 0; i < partNumber; i++) {
                    int groupInstance = groupNumber;
                    while (groupInstance > 0) {
                        int a = (int) (Math.random() * sumNumber);
                        if (checkNumber(start, a)) {
                            groupInstance--;
                            res[i] = Math.max(a, res[i]);
                            start.remove(a);
                        }
                    }
                }
            }
            for (int re : res) {
                min = Math.min(min, re);
            }
            if (min == output) count++;
        }
        System.out.println("数字" + output + "出现次数:" + count);
    }

    /**
     * @param sumNumber   数字在0到sumNumber中
     * @param groupNumber 每组个数
     */
    public static int RandomMaxThenMin(int sumNumber, int groupNumber) {
        if (sumNumber % groupNumber != 0) {
            return -1;
        }
        int min = Integer.MAX_VALUE;
        int partNumber = sumNumber / groupNumber;
        Map<Integer, Boolean> start = new HashMap<>();
        for (int i = 0; i < sumNumber; i++) {
            start.put(i, false);
        }
        int[] res = new int[partNumber];
        for (int i = 0; i < partNumber; i++) {
            res[i] = -1;
        }
        while (!start.isEmpty()) {
            for (int i = 0; i < partNumber; i++) {
                int groupInstance = groupNumber;
                while (groupInstance > 0) {
                    int a = (int) (Math.random() * sumNumber);
                    if (checkNumber(start, a)) {
                        groupInstance--;
                        res[i] = Math.max(a, res[i]);
                        start.remove(a);
                    }
                }
            }
        }
        for (int re : res) {
            min = Math.min(min, re);
        }
        return min;
    }

    private static boolean checkNumber(Map<Integer, Boolean> start, int randomNumber) {
        return start.containsKey(randomNumber);
    }

    /**
     * 加密
     *
     * @param text 待加密文字
     * @return 加密文字
     */
    public static String EncodeCharacters(String text) {
        char[] text1 = text.toCharArray();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text1.length; i++) {
            if (ENCODE_CHARACTERS.containsKey(text1[i])) {
                text1[i] = ENCODE_CHARACTERS.get(text1[i]);
            }
            result.append(text1[i]);
        }
        return result.toString();
    }

    /**
     * 解密
     *
     * @param text 已经加密文字
     * @return 解密文字
     */
    public static String DecodeCharacters(String text) {
        return "";
    }

    /**
     * 计算日期星期几
     *
     * @param day   日
     * @param month 月
     * @param year  年
     * @return 0 ~ 6 对应 星期日到星期六
     */
    public static String DayOfTheWeek(int day, int month, int year) {
        int m = month;
        int y = year;
        if (month <= 2) {
            m += 12;
            y -= 1;
        }
        int c = y / 100;
        y = y % 100;
        int w = (y + y / 4 + c / 4 - 2 * c + (26 * (m + 1) / 10) + day - 1) % 7;
        return DayEnum.getById((w + 7) % 7).getDesc();
    }

    /***
     * KMP算法求解next数组
     * @param need 求解字符串
     * @return next数组
     */
    public static int[] KMPNext(String need) {
        int needLength = need.length();
        int[] next = new int[needLength];
        for (int i = 1, j = 0; i < needLength; i++) {
            while (j > 0 && need.charAt(j) != need.charAt(i)) {
                j = next[j - 1];
            }
            if (need.charAt(i) == need.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    /**
     * KMP算法
     *
     * @param haystack 母串
     * @param needle   子串
     * @return 找到子串的位置
     */
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) return 0;
        int needLength = needle.length();
        int mainLength = haystack.length();
        int[] next = new int[needLength];
        for (int i = 1, j = 0; i < needLength; i++) {
            while (j > 0 && needle.charAt(i) != needle.charAt(j)) {
                j = next[j - 1];
            }
            if (needle.charAt(i) == needle.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        for (int i = 0, j = 0; i < mainLength; i++) {
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                j = next[j - 1];
            }
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
            }
            if (j == needLength) {
                return i - needLength + 1;
            }
        }
        return -1;
    }

    /**
     * 数组前移K位
     *
     * @param nums 数组
     * @param k    前移位数
     */
    public static void rotate(int[] nums, int k) {
        int length = nums.length;
        k = k % length;
        reverse(nums, 0, length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, length - 1);
    }

    private static void reverse(int[] nums, int start, int end) {
        int temp;
        while (start < end) {
            temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }

    /**
     * @param nums 数组
     * @return 是否存在重复数字
     */
    public static boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }

    /**
     * 找出单一元素
     *
     * @param nums
     * @return 单一元素
     */
    public static int singleNumber(int[] nums) {
        int reduce = 0;
        for (int num : nums) {
            reduce = reduce ^ num;
        }
        return reduce;
    }

    /**
     * 构建大根堆
     *
     * @param nums  数组
     * @param index 加入的索引
     * @return 排序数组
     */
    public static void bigHeapSort(int[] nums, int index) {
        while (nums[index] > nums[(index - 1) / 2]) {
            swap(nums, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    /**
     * 构建小根堆
     *
     * @param nums  数组
     * @param index 加入的索引
     * @return 排序数组
     */
    public static void smallHeapSort(int[] nums, int index) {
        while (nums[index] < nums[(index - 1) / 2]) {
            swap(nums, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    /**
     * 调整大根堆
     *
     * @param nums     数组
     * @param index    从哪里开始
     * @param headSize 到哪里结束
     */
    public static void bigHeapify(int[] nums, int index, int headSize) {
        int left = index * 2 + 1;
        while (left < headSize) {
            int last = left + 1 < headSize && nums[left] < nums[left + 1] ? left + 1 : left;
            last = nums[last] > nums[index] ? last : index;
            if (last == index) {
                break;
            }
            swap(nums, last, index);
            index = last;
            left = index * 2 + 1;
        }
    }

    /**
     * 调整小根堆
     *
     * @param nums     数组
     * @param index    从哪里开始
     * @param headSize 到哪里结束
     */
    public static void smallHeapify(int[] nums, int index, int headSize) {
        int left = index * 2 + 1;
        while (left < headSize) {
            int last = left + 1 <= headSize && nums[left] > nums[left + 1] ? left + 1 : left;
            last = nums[last] < nums[index] ? last : index;
            if (last == index) {
                break;
            }
            swap(nums, last, index);
            index = last;
            left = index * 2 + 1;
        }
    }

    /**
     * 交换两个元素
     *
     * @param nums 数组
     * @param j    j
     * @param i    i
     */
    public static void swap(int[] nums, int j, int i) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 大根堆排序
     *
     * @param nums 排序数组
     */
    public static void sortTypeBigHeap(int[] nums) {
        int size = nums.length;
        if (size < 2) {
            return;
        }
        for (int i = 0; i < size; i++) {
            bigHeapSort(nums, i);
        }
        swap(nums, 0, --size);
        while (size > 0) {
            bigHeapify(nums, 0, size);
            swap(nums, 0, --size);
        }
    }

    /**
     * 小根堆排序
     *
     * @param nums 排序数组
     */
    public static void sortTypeSmallHeap(int[] nums) {
        int size = nums.length;
        if (size < 2) {
            return;
        }
        for (int i = 0; i < size; i++) {
            smallHeapSort(nums, i);
        }
        swap(nums, 0, --size);
        while (size > 0) {
            smallHeapify(nums, 0, size);
            swap(nums, 0, --size);
        }
    }

    /**
     * 平衡串
     *
     * @param s 一定是平衡串
     * @return 平衡串个数
     */
    public static int balancedStringSplit(String s) {
        if (s.length() == 0) return 0;
        char[] str = s.toCharArray();
        int LLength = 0;
        int RLength = 0;
        int num = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == 'R') {
                RLength++;
            }
            if (str[i] == 'L') {
                LLength++;
            }
            if (RLength == LLength) {
                num++;
                LLength = 0;
                RLength = 0;
            }
        }
        return num;
    }

    /**
     * 最大利润
     *
     * @param prices 每天价格
     * @return 总利润
     */
    public static int maxProfit(int[] prices) {
        int w = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            w += Math.max(prices[i + 1] - prices[i], 0);
        }
        return w;
    }

    /**
     * leetcode502 IPO
     *
     * @param k
     * @param w
     * @param profits
     * @param capital
     * @return
     */
    public static int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        int[][] arr = new int[n][2];
        int cur = 0;
        for (int i = 0; i < n; i++) {
            arr[i][0] = capital[i];
            arr[i][1] = profits[i];
        }
        Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> queue = new PriorityQueue<>((x, y) -> y - x);
        for (int i = 0; i < k; i++) {
            while (cur < n && arr[cur][0] <= w) {
                queue.add(arr[cur][1]);
                cur++;
            }
            if (!queue.isEmpty()) {
                w += queue.poll();
            } else {
                break;
            }
        }
        return w;
    }

    /**
     * 求两个数组的交集
     *
     * @param nums1 数组1
     * @param nums2 数组2
     * @return 交集
     */
    public static int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int p1 = 0;
        int p2 = 0;
        int p3 = 0;
        int[] anw = new int[Math.min(nums1.length, nums2.length)];
        while (p1 < nums1.length && p2 < nums2.length) {
            if (nums1[p1] == nums2[p2]) {
                anw[p3++] = nums1[p1];
                p1++;
                p2++;
            } else if (nums1[p1] > nums2[p2]) {
                p2++;
            } else {
                p1++;
            }
        }
        int[] anw1 = new int[p3];
        if (p3 >= 0) System.arraycopy(anw, 0, anw1, 0, p3);
        return anw1;
    }

    /**
     * 移动0
     *
     * @param nums 数组
     */
    public static void moveZeroes(int[] nums) {
        int p1 = 0;
        int p2 = 1;
        while (p1 < nums.length && p2 < nums.length) {
            if (nums[p1] == 0 && nums[p2] != 0) {
                swap(nums, p1++, p2++);
            } else if (nums[p1] == 0 && nums[p2] == 0) {
                p2++;
            } else {
                p1++;
                p2++;
            }
        }
    }

    /**
     * 数独是否一眼望去没错
     *
     * @param board 数独 空白用.
     * @return 是否正确
     */
    public static boolean isValidSudoku(char[][] board) {
        int boardLength = board.length;
        int[][] line = new int[boardLength][boardLength];
        int[][] row = new int[boardLength][boardLength];
        int[][] cell = new int[boardLength][boardLength];
        for (int i = 0; i < boardLength; i++) {
            for (int j = 0; j < boardLength; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                int num = board[i][j] - '0' - 1;
                int k = i / 3 * 3 + j / 3;
                if (line[i][num] != 0 || row[j][num] != 0 || cell[k][num] != 0) {
                    return false;
                }
                line[i][num] = row[j][num] = cell[k][num] = 1;
            }
        }
        return true;
    }

    /**
     * 旋转图像：先上下交换再对称交换
     *
     * @param matrix 二维数组
     */
    public void rotate(int[][] matrix) {
        for (int i = 0; i < matrix.length / 2; i++) {
            int[] temp = matrix[i];
            matrix[i] = matrix[matrix.length - i - 1];
            matrix[matrix.length - i - 1] = temp;
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    /**
     * 反转字符串
     *
     * @param s 字符串
     */
    public void reverseString(char[] s) {
        int length = s.length;
        int head = 0;
        int end = length - 1;
        char temp = ' ';
        while (head < end) {
            temp = s[head];
            s[head++] = s[end];
            s[end--] = temp;
        }
    }

    /**
     * 反转整数
     *
     * @param x 整数
     * @return 反转之后的数
     */
    public int reverse(int x) {
        long res = 0;
        while (x != 0) {
            res = res * 10 + x % 10;
            x /= 10;
        }
        return (int) res == res ? (int) res : 0;
    }


}
