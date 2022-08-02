package com.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author zhxy
 * @Date 2021/6/16 12:53 下午
 */
public class PreTraversal {

    public static void main(String[] args) {
        List list = new ArrayList();
//        recursion(TreeNode.createRoot(), list);
        iteration(TreeNode.createRoot(),list);
        list.forEach(System.out::print);
    }


    /**
     * 递归  FBADCEGIH
     *
     * @param root
     */
    public static void recursion(TreeNode root, List list) {
        if (root == null) return;
        list.add(root.val);
        if (root.left != null) recursion(root.left, list);
        if (root.right != null) recursion(root.right, list);
    }


    /**
     * 迭代  FBADCEGIH
     * @param root
     * @param list
     */
    public static void iteration(TreeNode root, List list) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode curr;
        while (!stack.empty()) {
            curr = stack.pop();
            if(curr == null) continue;
            list.add(curr.val);

            stack.push(curr.right);
            stack.push(curr.left);
        }

    }


}
