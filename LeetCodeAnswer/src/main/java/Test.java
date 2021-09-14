import LeetCode.answer.LeetCodeUtil;

/**
 * @author 许炼江
 * @CreatTime 2021/9/9-14:54
 */
public class Test {
    public static void main(String[] args) {
        String s = "anagram";
        String t = "anan";
        String[] strings = new String[]{"anagram", "anan","s"};
        int[] chalk = new int[]{0};
        int n = Integer.MAX_VALUE;
        int[] nums = new int[]{6,2,3,5,1,1,5};


        long start = System.currentTimeMillis();
        System.out.println(LeetCodeUtil.rob(nums));
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
