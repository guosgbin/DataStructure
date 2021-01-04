package com.peijun.stack;

import java.util.EmptyStackException;

/**
 * @author: Dylan Kwok GSGB
 * @date: 2020/11/16 8:35
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 * <p>
 * 链栈
 */
public class LinkedStack<E> {

    /**
     * 指针，指针栈顶，也就是链表的第一个元素
     */
    private Node<E> top;

    /**
     * 判断是否是空栈。
     */
    public boolean isEmpty() {
        return top == null;
    }

    /**
     * 压栈 压入第一个元素和后面的操作是一样的
     */
    public E push(E element) {
        final Node<E> newNode = new Node<>(element);
        Node<E> t = top; // 指向旧头结点的指针
        top = newNode; // 将top指针指向新的头结点
        top.next = t; // 将新的节点的next域指向旧的头结点
        return newNode.element;
    }


    /**
     * 弹栈
     */
    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        E oldValue = top.element; // 获取旧元素
        top = top.next; // top指针后移
        return oldValue;
    }


    /**
     * 查看栈顶元素
     */
    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return top.element;
    }

    /**
     * 遍历栈
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        if (isEmpty()) {
            return sb.append("]").toString();
        }
        Node<E> t = top;
        for (; t != null; t = t.next) {
            if (t.next == null) {
                sb.append(t.element).append("]");
            } else {
                sb.append(t.element).append(", ");
            }
        }
        return sb.toString();
    }

    /**
     * 结点的静态内部类
     *
     * @param <E> 结点数据类型的类型
     */
    public static class Node<E> {
        E element; // 数据域
        Node<E> next; // 指针域

        Node(E element) {
            this.element = element;
        }

        @Override
        public String toString() {
            return "element=" + element;
        }
    }


}
