package queue;

/**
 * @author: Dylan Kwok GSGB
 * @date: 2020/11/22 17:45
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class ArrayQueue<E> {

    // 队列的最大容量
    private int maxSize;
    // 队首指针
    private int front;
    // 队尾指针
    private int rear;
    // 底层数组
    private Object[] elementData;

    /**
     * 构建指定长度的数组
     *
     * @param maxSize
     */
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        elementData = new Object[maxSize];
    }

    /**
     * 给个默认长度
     */
    public ArrayQueue() {
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
     * 入队
     *
     * @param element 元素
     * @return
     */
    public boolean offer(E element) {
        if (isFull()) {
            throw new RuntimeException("队列已满, 无法入队");
        }
        elementData[rear] = element;
        // rear指针后移
        rear = (rear + 1) % maxSize;
        return true;
    }

    /**
     * 出队
     */
    public E poll() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        // 保存旧值
        E offerValue = (E) elementData[front];
        // front指针后移
        front = (front + 1) % maxSize;
        return offerValue;
    }

    /**
     * 查看队首元素, 不移除元素
     */
    public E peek() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        return (E) elementData[front];
    }

    /**
     * 查看队列有多少元素
     */
    public int size() {
        return (rear - front + maxSize) % maxSize;
    }

    /**
     * 打印队列
     *
     * @return
     */
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
