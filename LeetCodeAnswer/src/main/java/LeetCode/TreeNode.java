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

    /**
     * 最大深度
     *
     * @param root 根节点
     * @return 深度
     */
    public int maxDepth(TreeNode root) {
        return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * 二叉搜索树
     *
     * @param root 根节点
     * @return 是否是
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        if ((root.right == null || root.val < root.right.val) &&
                (root.left == null || root.val > root.left.val)) {
            return isValidBST(root.right) && isValidBST(root.left);
        } else {
            return false;
        }
    }
}
