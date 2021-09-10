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
        int n = 7;
        System.out.println(LeetCodeUtil.longestCommonPrefix(strings));
    }
}
