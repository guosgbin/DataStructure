package stack;

/**
 * @author: Dylan Kwok GSGB
 * @date: 2020/11/15 23:26
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 * <p>
 * 顺序栈
 */
public class ArrayStack<E> {

    /**
     * 因为是线性表，所以需要限制数组的大小。
     * 但是也可以做出ArrayList那样扩容处理
     */
    private Integer maxSize;

    /**
     * 指向栈顶的指针
     */
    private Integer top = -1;

    /**
     * 表示栈的数组
     */
    private Object[] stack;

    /**
     * 空参默认10
     */
    public ArrayStack() {
        this(10);
    }

    /**
     * 指定数组的最大容量
     *
     * @param maxSize
     */
    public ArrayStack(Integer maxSize) {
        this.maxSize = maxSize;
        stack = new Object[maxSize];
    }

    /**
     * 判断栈是否为空
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 判断栈是否满了
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 压栈
     *
     * @param element
     */
    public E push(E element) {
        // 判断栈是否满
        if (isFull()) {
            throw new RuntimeException("stack is full");
        }
        stack[++top] = element;
        return element;
    }

    /**
     * 弹栈
     */
    public E pop() {
        // 判断栈是否有元素
        if (isEmpty()) {
            throw new RuntimeException("stack is empty");
        }
        E oldValue = (E) stack[top];
        stack[top] = null;
        top--;
        return oldValue;
    }

    /**
     * 获取栈顶的元素,并不弹栈
     */
    public E peek() {
        // 判断栈是否有元素
        if (isEmpty()) {
            throw new RuntimeException("stack is empty");
        }
        return (E) stack[top];
    }

    /**
     * 遍历栈的元素
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        // 判断栈是否有元素
        if (isEmpty()) {
            return sb.append("]").toString();
        }
        for (int i = top; i >= 0; i--) {
            sb = i == 0 ?
                    sb.append(stack[i]).append("]") :
                    sb.append(stack[i]).append(",");
        }
        return sb.toString();
    }

}
