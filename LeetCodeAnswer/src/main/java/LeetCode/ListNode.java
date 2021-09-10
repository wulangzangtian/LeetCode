package LeetCode;


import lombok.Getter;
import lombok.Setter;

/**
 * 链表
 *
 * @author 许炼江
 * @CreatTime 2021/9/10-17:57
 */
@Getter
@Setter
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }
}
