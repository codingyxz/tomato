package com.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author zhxy
 * @Date 2021/6/16 1:10 下午
 */
public class InTraversal {

    public static void main(String[] args) {

        List list = new ArrayList();
//        recursion(TreeNode.createRoot(), list);
//        iteration(TreeNode.createRoot(), list);
        mirrors(TreeNode.createRoot(), list);
        list.forEach(System.out::print);
    }

    /**
     * 递归 ABCDEFGHI
     *
     * @param root
     * @param list
     */
    public static void recursion(TreeNode root, List list) {
        if (root == null) return;
        recursion(root.left, list);
        list.add(root.val);
        recursion(root.right, list);
    }

    /**
     * 迭代
     *
     * @param root
     * @param list
     */
    public static void iteration(TreeNode root, List list) {
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            list.add(curr.val);
            curr = curr.right;
        }
    }


    /**
     * 消除左子树，转为 根、右
     *
     * @param root
     * @param list
     */
    public static void mirrors(TreeNode root, List list) {
        if (root == null) return;
        TreeNode curr = root;
        TreeNode oldRoot = null;

        while (curr != null) {
            if (curr.left == null) {
                list.add(curr.val);
                curr = curr.right;
            } else {
                TreeNode rightmost = curr.left;
                while (rightmost.right != null) {
                    rightmost = rightmost.right;
                }
                rightmost.right = oldRoot = curr;
                curr = curr.left;
                oldRoot.left = null;
            }
        }
    }
}
