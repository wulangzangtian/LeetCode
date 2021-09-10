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
    private int val;
    private ListNode next;

    public ListNode() {

    }

    public ListNode(int x) {
        val = x;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
