package com.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author zhxy
 * @Date 2021/6/16 1:46 下午
 */
public class PostTraversal {

    public static void main(String[] args) {

        List list = new ArrayList<>();
//        recursion(TreeNode.createRoot(), list);
//        iteration(TreeNode.createRoot(), list);
//        doubleStack(TreeNode.createRoot(), list);
        LinkedList(TreeNode.createRoot(),list);
        list.forEach(System.out::print);
    }


    /**
     * 递归  ACEDBHIGF
     *
     * @param root
     * @param list
     */
    public static void recursion(TreeNode root, List list) {
        if (root == null) return;

        if (root.left != null) recursion(root.left, list);
        if (root.right != null) recursion(root.right, list);
        list.add(root.val);

    }


    /**
     * 迭代法   ACEDBHIGF
     *
     * @param root
     * @param list
     */
    public static void iteration(TreeNode root, List list) {
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        TreeNode pre = null;

        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.peek();
            if (curr.right == null || curr.right == pre) {
                stack.pop();
                list.add(curr.val);
                pre = curr;
                curr = null;
            } else {
                curr = curr.right;
            }
        }
    }


    /**
     * 双栈法
     * 栈1：根、右、左
     * 栈2：左、右、根
     *
     * @param root
     * @param list
     */
    public static void doubleStack(TreeNode root, List list) {
        if (root == null) return;
        Stack<TreeNode> preStack = new Stack<>();
        Stack<TreeNode> reverseStack = new Stack<>();
        preStack.push(root);
        TreeNode curr;

        while (!preStack.isEmpty()) {
            curr = preStack.pop();
            reverseStack.push(curr);
            if (curr.left != null) preStack.push(curr.left);
            if (curr.right != null) preStack.push(curr.right);
        }

        while (!reverseStack.isEmpty()) {
            list.add(reverseStack.pop().val);
        }
    }


    /**
     * 使用Deque
     * @param root
     * @param list
     */
    public static void LinkedList(TreeNode root, List list) {
        if (root == null) return;

        LinkedList linkedList = new LinkedList();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode curr;

        while (!stack.isEmpty()) {
            curr = stack.pop();
            linkedList.addFirst(curr.val);
            if (curr.left != null) stack.push(curr.left);
            if (curr.right != null) stack.push(curr.right);
        }
        list.addAll(linkedList);
    }

}
