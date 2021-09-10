package LeetCode;

import lombok.Getter;
import lombok.Setter;

/**
 * 二叉树
 *
 * @author 许炼江
 * @CreatTime 2021/9/10-19:17
 */
@Getter
@Setter
public class TreeNode {
    private int val;
    private TreeNode left;
    private TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
