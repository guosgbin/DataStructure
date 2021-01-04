package com.peijun;

/**
 * @author: Kwok Simon GSGB
 * @date: 2020/7/6 19:18
 * 宠辱不惊，看庭前花开花落；去留无意，望天上云卷云舒--陈继儒
 * <p>
 * 线性表 链状存储形式 双向链表
 */
public class DoubleLinkedList<E> {
    /**
     * 链表长度，这里是为了方便
     * 其实不需要这个变量也是可以的，只不过每次变量的时候要判空
     */
    private int size;
    /**
     * 链表头结点，一般不存放信息 (也可以存放链表的公共信息，如长度)
     */
    private Node<E> head = new Node<>();

    /**
     * 判断链表是否为空
     *
     * @return 链表长度为0，则返回true，反之返回false
     */
    public boolean isEmpty() {
//        return size == 0;
        return head.next == null;
    }

    /**
     * 获取链表的长度
     *
     * @return 返回链表的长度
     */
    public int size() {
        return size;
    }

    /**
     * 添加到链表末尾（尾插法）
     *
     * @param element 待添加的元素
     */
    public void addLast(E element) {
        final Node<E> newNode = new Node<>(null, element, null);
        Node<E> cur = head; // 指针，用于遍历
        for (int i = 0; i < size; i++) {
            cur = cur.next;
        }
        // 因为cur初始是在head处，所以指针后移size次后
        // cur指向的是链表最后一个元素
        cur.next = newNode;
        newNode.prev = cur;
        size++;
    }

    /**
     * 添加到链表开头（头插法）
     *
     * @param element 待添加的元素
     */
    public void addFirst(E element) {
        final Node<E> newNode = new Node<>(null, element, null);
        Node<E> cur = head;
        // 因为双向链表，有一个指针直线前驱，一个指针指向后驱
        // 所以头插法赋值的顺序就不像单链表那样严格
        newNode.next = cur.next;
        if (cur.next != null) {
            // 当插入的不是第一个节点时
            cur.next.prev = newNode; // 新节点后面指针接好了
        }
        cur.next = newNode;
        newNode.prev = cur; // 新节点前面指针接好了（保证这条语句在最后即可）
        size++; // 代表链表长度的size自增，这个变量就是为了方便获取链表长度，要不然要遍历链表太麻烦了
    }


    /**
     * 在双链表指定位置插入元素
     * 假如这个方法不允许插入到链表最后，也就是index小于size，此时可以将指针指向插入元素的位置
     * 假如这个方法允许插入到链表最后，也就是index小于等于size，这时还是需要指针指向插入位置的前一个位置
     *
     * @param index 位置
     * @param element 待插入的元素
     * @exception IndexOutOfBoundsException 索引有误时
     */
    public void add(int index, E element) {
        checkPositionIndex(index);
        final Node<E> newNode = new Node<>(null, element, null);
        Node<E> cur = head; // 用于遍历
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        if (cur.next != null) {
            // 表示插入的不是链表的最后
            cur.next.prev = newNode;
            newNode.next = cur.next;
        }
        cur.next = newNode;
        newNode.prev = cur;
       /* // （假如插入的位置不允许链表最后，则可以用下面的步骤）此时cur指针，指向就是待插入的位置
        cur.prev.next = newNode; // 然后将当前节点的地址赋值给前一节点的next域
        newNode.prev = cur.prev; // 将前一个节点的地址赋值给当前节点的prev域
        newNode.next = cur; // 将本来在此位置的原始后移
        cur.prev = newNode; // 将本来在此位置的元素的prev指针指向新节点*/
        size++;
    }

    /**
     * 删除链表指定位置的元素，返回删除的元素
     * 在之前单链表删除元素的时候，需要将遍历的指针停在待删除位置的前一个元素
     * 而在双链表的时候，则不需要这样，直接将指针停在指定位置即可
     * 因为双向链表有前后两个指针，可以完成自我删除
     *
     * @param index 位置
     * @return 返回删除之前的元素
     * @exception IndexOutOfBoundsException 索引有误时
     */
    public E remove(int index) {
        checkElementIndex(index);
        Node<E> cur = head.next; // 指向第一个元素，用于遍历
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        // 此时cur指向的元素就是要删除的元素
        // 自我删除，将自己跳过即可，让垃圾回收器去捡垃圾
        E oldValue = cur.element;
        cur.prev.next = cur.next;
        if (cur.next != null) { // 在删除最后一个节点的时候，就不需要下面的操作了
            cur.next.prev = cur.prev;
        }
        size--;
        return oldValue;
    }

    /**
     * 修改链表元素
     *
     * @param index 索引位置
     * @param element 要修改的值
     * @return 返回修改之前的元素
     * @exception IndexOutOfBoundsException 索引有误时
     */
    public E set(int index, E element) {
        checkElementIndex(index);
        Node<E> cur = head.next; // 指向第一个元素，用于遍历
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        E oldValue = cur.element; // 保存旧值
        cur.element = element;
        return oldValue;
    }

    /**
     * 获取链表的第index个元素
     *
     * @param index
     * @return
     * @exception IndexOutOfBoundsException 索引有误时
     */
    public E get(int index){
        checkElementIndex(index);
        Node<E> cur = head.next; // 指向第一个元素，用于遍历
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.element;
    }

    /**
     * 获取指定元素，存在则返回索引，不存在返回-1
     *
     * @param obj
     * @return
     */
    public int get(Object obj){
        Node<E> cur = head.next; // 指向第一个元素，用于遍历
        if(obj == null){
            for (int i = 0; i < size; i++) {
                if(cur.element == null){
                    return i;
                }
                cur = cur.next;
            }
        }else{
            for (int i = 0; i < size; i++) {
                if(obj.equals(cur.element)) {
                    return i;
                }
                cur = cur.next;
            }
        }
        return -1;
    }

    /**
     * 判断增加元素的索引的正确性的方法
     *
     * @param index
     */
    private void checkPositionIndex(int index) {
        // 插入位置可以为size，可看出插入链表最后
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("索引有误");
        }
    }

    /**
     * 判断删除和修改元素的索引的正确性的方法
     *
     * @param index
     */
    private void checkElementIndex(int index) {
        // 插入位置可以为size，可看出插入链表最后
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("索引有误");
        }
    }

    /**
     * 打印链表元素
     */
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

    private static class Node<E> {
        E element; // 数据域
        Node<E> next; // 指针域，指向下一个元素
        Node<E> prev; // 指针域，指向上一个元素

        Node() {
        }

        Node(Node<E> prev, E element, Node<E> next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }

        @Override
        public String toString() {
            return element.toString();
        }
    }

    public static void main(String[] args) {
        DoubleLinkedList<Student> list = new DoubleLinkedList<>();
//        list.addLast(new com.peijun.Student("夏侯惇", "男", 35));
//        list.addLast(new com.peijun.Student("张辽", "男", 30));
//        list.addLast(new com.peijun.Student("徐晃", "男", 28));
//        list.addLast(new com.peijun.Student("许褚", "男", 25));

        list.addFirst(new Student("夏侯惇", "男", 35));
        list.addFirst(new Student("张辽", "男", 30));
        list.addFirst(new Student("徐晃", "男", 28));
        list.addFirst(new Student("许褚", "男", 25));

        list.add(5, new Student("司马懿", "男", 22));
        System.out.println(list.toString());

//        System.out.println("======");
//        System.out.println(list.remove(4));
//        System.out.println("=========");
//        System.out.println(list.toString());

//        list.set(1,new com.peijun.Student("张辽", "男", 90));
//        System.out.println(list.toString());
//
//        System.out.println("==========");
//        System.out.println(list.get(new com.peijun.Student("许褚", "男", 25)));
    }
}
