/**
 * @author: Kwok Simon GSGB
 * @date: 2020/7/6 00:10
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志--苏轼
 * <p>
 * 线性表 顺序存储形式
 */
public class SequentialList<E> {
    /**
     * 代表线性表的数组
     */
    private Object[] elementData;
    /**
     * 线性表达大小
     */
    private int size;

    /**
     * 构造函数，创建指定大小的数组
     *
     * @param maxSize
     */
    public SequentialList(int maxSize) {
        elementData = new Object[maxSize];
    }

    /**
     * 空参构造函数，默认创建数组长度为16
     */
    public SequentialList() {
        this(16);
    }

    /**
     * 判断传入的索引i是否合法
     *
     * @param i 索引
     * @throws IndexOutOfBoundsException 传入的索引不合法时
     */
    public void checkElementIndex(int i) {
        if (i > size || i < 0) {
            throw new IndexOutOfBoundsException("索引不合法");
        }
    }

    /**
     * 判断线性表是否为空
     *
     * @return 返回true则表示线性表为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 判断线性表是否满
     *
     * @return 返回true则表示线性表已经满了
     */
    public boolean isFull() {
        return size == elementData.length;
    }

    /**
     * 获取线性表长度
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 添加元素
     *
     * @param element 添加的元素
     * @throws IndexOutOfBoundsException 当线性表已经满了
     */
    public void add(E element) {
        if (isFull()) {
            throw new IndexOutOfBoundsException("线性表已满");
        }
        // 给size位置赋值为element，然后线性表长度自增
        elementData[size++] = element;
    }

    /**
     * 获取索引i的值
     *
     * @param i 索引
     * @return 线性表索引i处的值
     * @throws IndexOutOfBoundsException 当线性表已经满了
     */
    public E get(int i) {
        checkElementIndex(i);
        return (E) elementData[i];
    }


    /**
     * 修改第i个元素的值
     *
     * @param i 索引
     * @param e 修改后的值
     * @return 返回修改之前的值
     * @throws IndexOutOfBoundsException 当线性表已经满了
     */
    public E set(int i, E e) {
        checkElementIndex(i);
        Object oldValue = elementData[i]; // 存储旧值
        elementData[i] = e; // 赋新值
        return (E) oldValue;
    }

    /**
     * 线性表的字符串形式
     *
     * @return 线性表的字符串形式
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                sb.append(elementData[i]);
            } else {
                sb.append(elementData[i] + ", ");
            }
        }
        return sb.append("]").toString();
    }

    /**
     * 在指定索引处插入数据
     *
     * @param index 索引
     * @param e     插入的值
     * @throws IndexOutOfBoundsException 线性表满和索引不合法的时候抛出异常
     */
    public void add(int index, E e) {
        // 在i的位置插入数据，需要将之前在i位置的数据挨个后移一位
        if (isFull()) {
            throw new IndexOutOfBoundsException("线性表已满");
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("索引不合法");
        }
        // 从最后一个元素移动，移动size - index个元素
        for (int i = size; i >= index; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[index] = e;
        size++;
    }

    /**
     * 查找线性表中是否存在传入的值
     *
     * @param o 查找的值
     * @return 返回传入的值第一次出现在线性表中的索引
     */
    public int get(Object o) {
        if (o == null) {
            // 传入的元素为null
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        } else {
            // 传入的元素不为null
            for (int i = 0; i < size; i++) {
                if (o.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 删除索引为i的元素
     *
     * @param index
     * @return 返回删除之前的数据
     * @throws IndexOutOfBoundsException 索引不合法的时候抛出异常
     */
    public E remove(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("索引有误");
        }
        Object oldValue = elementData[index]; // 保存旧值
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        elementData[--size] = null; // 置空，垃圾回收器回收
        return (E) oldValue;
    }

    /**
     * 删除指定的数据
     *
     * @param o 需要删除的数据
     * @return 是否删除成功，成功则为true，反之fasle
     */
    public boolean remove(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    remove(i); // 调用上面的删除方法
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elementData[i])) {
                    remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SequentialList<Integer> list = new SequentialList<>();
        System.out.println(list.toString());
        list.add(11);
        list.add(12);
        list.add(13);
        list.add(14);
//        System.out.println(list.toString());
//        list.add(1,100);
        System.out.println(list.toString());

//        System.out.println(list.set(2, 100));
//        System.out.println(list.toString());
//        list.add(1, 100);
//        System.out.println(list.toString());
//        System.out.println(list.size());
        System.out.println(list.size());
        System.out.println(list.remove(0));
        System.out.println(list.toString());
        System.out.println(list.size());
    }
}