import sun.misc.Cleaner;

import javax.sound.midi.Soundbank;
import java.util.Objects;

/**
 * @author: Kwok Simon GSGB
 * @date: 2020/7/6 12:05
 * 不以物喜，不以己悲——范仲淹
 * <p>
 * 线性表 链状存储形式 单向链表
 */
public class SingleLinkedList<E> {
    /**
     * 链表长度，这里是为了方便
     * 其实不需要这个变量也是可以的，只不过每次变量的时候要判空
     */
    private int size;

    /**
     * 头结点，不存放信息 (也可以存放链表的公共信息，如长度)
     */
    private Node<E> head = new Node(null);

    /**
     * 判断链表是否为空
     *
     * @return 链表为空则返回true，反之返回false
     */
    public boolean isEmpty() {
        return head.next == null;
    }

    /**
     * 获取链表的长度
     *
     * @return 链表长度
     */
    public int size() {
        return size;
    }

    /**
     * 添加到链表末尾 (尾插法)
     *
     * @param element 待添加的元素
     */
    public void addLast(E element) {
        final Node<E> newNode = new Node<>(element);
        Node<E> cur = head; // 设一个头指针，用于遍历
        while (true) {
            if (cur.next == null) {
                // 到达链表最后了
                break;
            }
            cur = cur.next;
        }
        // cur指针现在指向的就是最后一个结点了
        // 将待添加的结点直接放在cur指向的结点后即可
        cur.next = newNode;
        size++;
    }

    /**
     * 添加到链表开头 (头插法)
     *
     * @param element 待添加的元素
     */
    public void addFirst(E element) {
        final Node<E> newNode = new Node<>(element);
        newNode.next = head.next;
        head.next = newNode;
        size++;
    }

    /**
     * 添加到链表的指定位置
     *
     * @param index   位置
     * @param element 待插入的元素
     * @throws IndexOutOfBoundsException 位置不合法时
     */
    public void addUseSize(int index, E element) {
        checkPositionIndex(index);
        final Node<E> newNode = new Node<>(element);
        Node<E> cur = head; // 设一个指针，用于遍历
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        // 经过上面的操作指针移动了index次
        // 注意：实际上此时cur指针指向的是插入位置的前一个元素
        newNode.next = cur.next;
        cur.next = newNode;
        size++;
    }

    /**
     * 添加到链表的指定位置
     *
     * @param index   位置
     * @param element 待插入的元素
     * @throws IndexOutOfBoundsException 位置不合法时
     */
    public void add(int index, E element) {
        checkPositionIndex(index);
        final Node<E> newNode = new Node<>(element);
        Node<E> cur = head; // 当前的cur指针指向头节点
        int count = 0;
        for (; cur != null; cur = cur.next) {
            if (count == index) {
                newNode.next = cur.next;
                cur.next = newNode;
                break;
            }
            count++;
        }
        size++;
    }

    /**
     * 按照指定规则从小到大 添加元素
     * 需要注意的是，我么需要将指针停在添加元素的前一个位置
     *
     * @param element 实现了Comparable接口的对象
     */
    public void addByOrder(Comparable<E> element) {
        final Node<E> newNode = new Node<>((E) element);
        Node<E> cur = head; // 设一个指针，用于遍历
        Node<E> next; // 保存cur指针的指向的下一个元素的地址
        while (true) {
            if (cur.next == null) {
                // 表明此时链表是空链表 或者 是比链表中所有元素都大
                cur.next = newNode;
                break;
            }
            if (element.compareTo(cur.next.element) < 0) {
                // 传入的element小于当前指针指向下一个元素的数据域
                // 插入元素
                newNode.next = cur.next;
                cur.next = newNode;
                break; // 已经找到位置了，退出循环
            }
            cur = cur.next; // 后移
        }
        size++;
    }

    /**
     * 删除线性表指定位置的元素
     *
     * @param index 位置
     * @return 返回删除的元素
     * @throws IndexOutOfBoundsException 位置不合法时
     */
    public E remove(int index) {
        checkElementIndex(index);
        Node<E> cur = head; // 设一个指针，用于遍历
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        // 注意：实际上此时cur指针指向的是删除位置的前一个元素
        E oldValue = cur.next.element; // 保存旧值
        cur.next = cur.next.next; // 删除操作

        size--;
        return oldValue;
    }


    /**
     * 获取指定位置的元素
     *
     * @param index 位置
     * @return 返回指定位置的元素
     * @throws IndexOutOfBoundsException 位置不合法时
     */
    public E get(int index) {
//        checkElementIndex(index);
        Node<E> cur = head.next; // 当前指针指向第一个元素
        int count = 0;
        // 退出循环条件是当前cur指针指向位置为null
        while (cur != null) {
            if (count == index) {
                return cur.element;
            }
            count++; // 计数器自增
            cur = cur.next; // 指针后移
        }
        return null;
    }

    /**
     * 查找指定元素在链表中的位置，没找到返回-1
     *
     * @param o 指定元素
     * @return 返回指定元素第一次在链表中的位置
     */
    public int get(Object o) {
        Node<E> cur = head.next; // 当前指针指向第一个元素
        int count = 0;
        if (o == null) {
            for (; cur != null; cur = cur.next) {
                if (cur.element == null) {
                    return count;
                }
                count++;
            }
        } else {
            for (; cur != null; cur = cur.next) {
                if (Objects.equals(cur.element, o)) {
                    return count;
                }
                count++;
            }
        }
        return -1;
    }

    /**
     * 修改指定位置的node节点的数据
     *
     * @param index   位置
     * @param element 待修改元素的值
     * @return 修改前的元素
     * @throws IndexOutOfBoundsException 位置不合法时
     */
    public E set(int index, E element) {
        checkElementIndex(index);
        Node<E> cur = head; // 设一个指针，用于遍历
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        E oldValue = cur.next.element;
        cur.next.element = element;
        return oldValue;
    }

    /**
     * 删除指定元素
     *
     * @param obj 待删除的元素
     * @return 删除指定元素，假如线性表有该元素，删除成功则true,反之false
     */
    public boolean remove(Object obj) {
        Node<E> cur = head; // 设一个指针，用于遍历
        boolean flag = false;
        if (obj == null) {
            for (; cur.next != null;cur = cur.next){
                if (cur.next.element == null) {
                    flag = true;
                    break;
                }
            }
        } else {
            for (; cur.next != null;cur = cur.next){
                if (Objects.equals(cur.next.element, obj)) {
                    flag = true;
                    break;
                }
            }
        }

        if (flag) {
            // 此时cur指向的是 待删除节点的上一个节点
            cur.next = cur.next.next; // 删除操作
            size--;
        }
        return flag;
    }

    /**
     * 清空链表，help GC
     */
    public void clear() {
        Node<E> cur = head.next; // 设一个指针，用于遍历
        Node<E> next; // next指针，指向下一个元素
        while (cur != null) {
            next = cur.next; // 保存cur指针的下一个元素
            cur.element = null;
            cur.next = null;
            cur = next; // cur指针后移
        }
        head.next = null;
        size = 0;
    }

    /**
     * 判断传入待添加元素的位置是否合法
     * 需要注意的是，传入的位置可以等于长度{@link #size}
     * 此时可以看成是插入到链表结尾
     *
     * @param index 位置
     * @throws IndexOutOfBoundsException 位置不合法时
     */
    private void checkPositionIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("位置不合法");
        }
    }

    /**
     * 判断传入待修改、删除、查找的元素的位置是否合法
     *
     * @param index 位置
     * @throws IndexOutOfBoundsException 位置不合法时
     */
    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("位置不合法");
        }
    }


    /**
     * 打印链表元素
     */
    @Override
    public String toString() {
        Node<E> cur = head.next; // 设一个指针，用于遍历
        StringBuilder sb = new StringBuilder();
        int count = 0; // 计数而已
        while (cur != null) {
            if (cur.next == null) {
                // 最后一个结点打印不换行
                sb.append(count++ + "_" + cur.toString());
            } else {
                sb.append(count++ + "_" + cur.toString() + System.getProperty("line.separator"));
            }
            cur = cur.next;
        }
        return sb.toString();
    }

    public Node<E> getHead() {
        return head;
    }

    public void setHead(Node<E> head) {
        this.head = head;
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

    // main方法，测试
    public static void main(String[] args) {
        SingleLinkedList<Student> list = new SingleLinkedList<>();
        System.out.println(list.isEmpty());
        System.out.println(list.size());

        list.addLast(new Student("夏侯惇", "男", 35));
        list.addLast(new Student("张辽", "男", 30));
        list.addLast(null);
        list.addLast(new Student("徐晃", "男", 28));
        list.addLast(new Student("许褚", "男", 25));
//        System.out.println(list.isEmpty());
//        System.out.println(list.size());
//        System.out.println(list.toString());
//        System.out.println(list.get(new Student("徐晃", "男", 28)));
        System.out.println(list);
        System.out.println("===");
//        list.add(4, new Student("夏侯霸", "男", 25));
        System.out.println(list.remove(null));
        System.out.println(list);

//        System.out.println("============");
//
//        list.addFirst(new Student("曹爽", "男", 10));
//        System.out.println(list.size());
//        System.out.println(list.toString());
//        System.out.println("============");
//
//        list.add(2, new Student("甄姬", "女", 20));
//        System.out.println(list.size());
//        System.out.println(list.toString());
//        System.out.println("============");
//
//        System.out.println("删除的节点为：" + list.remove(2));
//        System.out.println(list.size());
//        System.out.println(list.toString());
//        System.out.println("============");
//
//        System.out.println("删除节点是否成功：" + list.remove(new Student("张辽", "男", 30)));
//        System.out.println(list.size());
//        System.out.println(list.toString());
//        System.out.println("============");
//
//        System.out.println("修改前的元素：" + list.set(1, new Student("邢道荣", "男", 30)));
//        System.out.println("索引1处的元素为："+list.get(1));
//        System.out.println(list.size());
//        System.out.println(list.toString());
//        System.out.println("============");
//
//        // 清空
//        list.clear();
//        System.out.println(list.isEmpty());
//        System.out.println(list.size());
//        System.out.println(list.toString());
    }
}

