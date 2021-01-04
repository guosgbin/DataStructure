package com.peijun.queue;

/**
 * @author: Dylan Kwok GSGB
 * @date: 2020/11/29 15:58
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 双端队列
 */
public class ArrayDeque<E> {
    /**
     * 队列的最大容量
     */
    private int maxSize;
    /**
     * 队首指针
     */
    private int front;
    /**
     * 队尾指针
     */
    private int rear;
    /**
     * 底层数组
     */
    private Object[] elementData;

    /**
     * 构建指定长度的数组
     *
     * @param maxSize
     */
    public ArrayDeque(int maxSize) {
        this.maxSize = maxSize;
        elementData = new Object[maxSize];
    }

    /**
     * 给个默认长度
     */
    public ArrayDeque() {
        this(10);
    }

    /**
     * 判断队列是否为空
     */
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * 判断队列是否满
     */
    public boolean isFull() {
        return front == (rear + 1) % maxSize;
    }

    /**
     * 队尾入队
     */
    public boolean offerLast(E element) {
        if (isFull()) {
            throw new RuntimeException("队列满，无法入队");
        }
        elementData[rear] = element; // rear指针是指向原队尾元素的下一个位置
        rear = (rear + 1) % maxSize; // rear指针后移
        return true;
    }

    /**
     * 队尾出队
     */
    public E pollLast() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，无法出队");
        }
        // 获取rear指针的上一个位置
        rear = (rear - 1 + maxSize) % maxSize;
        E oldValue = (E) elementData[rear];
        // 将rear位置置null
        elementData[rear] = null;
        return oldValue;
    }

    /**
     * 队首入队
     */
    public boolean offerFirst(E element) {
        if (isFull()) {
            throw new RuntimeException("队列满，无法入队");
        }
        front = (front - 1 + maxSize) % maxSize; // front指针前移
        elementData[front] = element;
        return true;
    }

    /**
     * 队首出队
     */
    public E pollFirst() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，无法出队");
        }
        // 将rear位置置null help GC
        E oldValue = (E) elementData[front];
        elementData[front] = null;
        // front指针后移
        front = (front + 1) % maxSize;
        return oldValue;
    }

    /**
     * 查看队首元素
     */
    public E peekFirst() {
        if (isEmpty()) {
            throw new RuntimeException("队列空");
        }
        return (E) elementData[front];
    }

    /**
     * 查看队尾元素
     */
    public E peekLast() {
        if (isEmpty()) {
            throw new RuntimeException("队列空");
        }
        int index = (rear - 1 + maxSize) % maxSize;
        return (E) elementData[index];
    }

    /**
     * 队列元素个数
     */
    public int size() {
        return (rear - front + maxSize) % maxSize;
    }

    /**
     * 遍历
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        if (isEmpty()) {
            return sb.append("]").toString();
        }

        for (int i = front; i != rear; i = (i + 1) % maxSize) {
            if ((i + 1) % maxSize == rear) {
                sb.append(elementData[i]).append("]");
            } else {
                sb.append(elementData[i]).append(", ");
            }
        }
        return sb.toString();
    }
}
