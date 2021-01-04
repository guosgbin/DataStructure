package com.peijun.queue;


/**
 * @author: Dylan Kwok GSGB
 * @date: 2020/11/26 23:13
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 * <p>
 * 链式队列
 */
public class LinkedQueue<E> {

    /**
     * 队首指针 指向头节点 队首元素其实是front.next
     */
    private Node<E> front;

    /**
     * 队尾指针
     */
    private Node<E> rear;

    /**
     * 初始化队列 使front指针和rear指针都指向头节点
     */
    public LinkedQueue() {
        front = rear = new Node<>(null);
    }

    /**
     * 判断队列是否有元素
     */
    public boolean isEmpty() {
        return front == rear;
    }


    /**
     * 入队 队尾入队
     *
     * @param element 元素
     * @return
     */
    public boolean offer(E element) {
        // 插入元素到队尾
        final Node<E> newNode = new Node<>(element);
        // 设置新的节点到队尾元素的下一个元素
        rear.next = newNode;
        rear = newNode; // rear指针后移
        return true;
    }

    /**
     * 出队 队首出队
     */
    public E poll() {
        if(isEmpty()){
            throw new RuntimeException("队列为空, 无法出队");
        }
        // 移除队首元素，即移除front.next的结点
        Node<E> firstNode = front.next;
        front.next =firstNode.next;
        // 只有一个元素时，需要将队尾指针指向头节点
        if (front.next == null) {
            rear = front;
        }
        return firstNode.data;
    }

    /**
     * 查看队首元素, 不移除元素
     */
    public E peek() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        return front.next.data;
    }

    /**
     * 查看队列有多少元素
     */
    public int size() {
        int size = 0;
        Node<E> cur = front.next;
        for (; cur != null; cur = cur.next) {
            size++;
        }
        return size;
    }

    /**
     * 打印队列
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> cur = front.next;
        for (; cur != null; cur = cur.next) {
            if (cur.next == null) {
                sb.append(cur.data).append("]");
            } else {
                sb.append(cur.data).append(" -> ");
            }
        }
        return sb.toString();
    }

    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.data = element;
            this.next = next;
        }

        Node(E element) {
            this(element, null);
        }
    }
}
