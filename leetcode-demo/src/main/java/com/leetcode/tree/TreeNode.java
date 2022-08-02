package com.leetcode.tree;

/**
 *
 * @author zhxy
 * @Date 2021/6/16 12:42 下午
 */
public class TreeNode {

    TreeNode left;
    TreeNode right;
    String val;

    public TreeNode(String val) {
        this.val = val;
    }

    public static TreeNode createRoot(){

        TreeNode root = new TreeNode("F");

        root.left = new TreeNode("B");
        root.left.left = new TreeNode("A");
        root.left.right = new TreeNode("D");
        root.left.right.left = new TreeNode("C");
        root.left.right.right = new TreeNode("E");

        root.right = new TreeNode("G");
        root.right.right = new TreeNode("I");
        root.right.right.left = new TreeNode("H");

        return root;
    }

}
