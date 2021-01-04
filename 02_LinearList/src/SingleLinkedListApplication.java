import java.util.Stack;

/**
 * 单链表的应用
 * 1.获取链表长度
 * 2.获取倒数第n个元素
 * 3.反转链表
 * 4.倒序打印链表（不改变数据结构）
 * 5.合并两个有序链表
 * 6.获取链表的中间元素
 */
public class SingleLinkedListApplication {
    public static void main(String[] args) {
        SingleLinkedList<Student> list = new SingleLinkedList<>();
        list.addFirst(new Student("夏侯惇", "男", 35));
        list.addFirst(new Student("张辽", "男", 30));
        list.addFirst(new Student("徐晃", "男", 28));
        list.addFirst(new Student("许褚", "男", 25));
        list.addFirst(new Student("甄姬", "女", 20));
        list.addFirst(new Student("曹爽", "男", 10));
        System.out.println(list);

        SingleLinkedList<Student> list1 = new SingleLinkedList<>();
        list1.addByOrder(new Student("张辽", "男", 30));
        list1.addByOrder(new Student("许褚", "男", 25));
        list1.addByOrder(new Student("夏侯惇", "男", 35));
        list1.addByOrder(new Student("曹爽", "男", 10));
        list1.addByOrder(new Student("徐晃", "男", 28));
        list1.addByOrder(new Student("甄宓", "女", 20));
//        System.out.println(list1);

        SingleLinkedList<Student> list2 = new SingleLinkedList<>();
        list2.addByOrder(new Student("邢道荣", "男", 59));
        list2.addByOrder(new Student("曹真", "男", 30));
        list2.addByOrder(new Student("曹丕", "男", 26));
        list2.addByOrder(new Student("曹睿", "男", 8));
        list2.addByOrder(new Student("司马懿", "男", 22));
        list2.addByOrder(new Student("夏侯渊", "男", 50));
//        System.out.println(list2);


//        System.out.println(length(list));
//        System.out.println(getReciprocalElement(3, list));
//        System.out.println(getReciprocalElement2(-1, list));
//        System.out.println(reverseLinkedList(list));
//        reversePrint(list);
//        System.out.println(getMiddleElement(list));
//        System.out.println(mergeLinkedList(list1, list2));
    }

    /**
     * 1.获取输入的单链表的长度
     *
     * @param list 输入的单链表
     * @return 返回输入的单链表的长度
     */
    public static int length(SingleLinkedList list) {
        SingleLinkedList.Node cur = list.getHead().next; // 用于遍历的指针，指向第一个元素
        int length = 0;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

    /**
     * 2. 获取倒数第n个元素（第一种笨笨的方法）
     *
     * @param n    位置
     * @param list 链表
     * @return 返回倒数第n个元素
     */
    public static Object getReciprocalElement(int n, SingleLinkedList list) {
        if (n > list.size() || n <= 0) {
            throw new RuntimeException("输入位置有误");
        }
        SingleLinkedList.Node cur = list.getHead().next; // 指向第一个节点，用于遍历
        // 需要先获取链表的长度
        int index = list.size() - n; // 定位倒数第n个元素的正序位置
        for (int i = 0; i < index; i++) {
            cur = cur.next; // 后移
        }
        return cur;
    }

    /**
     * 2. 获取倒数第n个元素（第二种，快慢指针法）
     * 1) 设置两个指针，一个叫快指针，一个叫慢指针
     * 2) 先让快指针走n步，然后慢指针再一起走
     * 3) 等快指针到达终点了，此时慢指针正好指再倒数第n个元素
     *
     * @param n    位置
     * @param list 链表
     * @return 返回倒数第n个元素
     */
    public static Object getReciprocalElement2(int n, SingleLinkedList list) {
        if (n <= 0) {
            throw new RuntimeException("输入位置有误");
        }
        SingleLinkedList.Node slow = list.getHead(); // 慢指针
        SingleLinkedList.Node fast = list.getHead(); // 快指针
        // 先让快指针走n步
        int count;
        for (count = 0; count < n && fast.next != null; count++) {
            fast = fast.next;
        }
        if (count != n) {
            // 假如没有走满n步链表就走完了，则说明输入的n比链表长度还大
            throw new RuntimeException("输入位置有误");
        }
        // 快慢指针一起走
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow.element;
    }

    /**
     * 3. 反转链表
     * 链表反转类似于我们的头插法，也就是将链表遍历一遍，
     * 利用头插法插入也就完成了链表的反转
     * 需要设置一个临时的头指针用于保存反转之后的链表
     *
     * @param list 链表
     * @return 返回反转之后的链表
     */
    public static SingleLinkedList reverseLinkedList(SingleLinkedList list) {
        SingleLinkedList.Node reverseHead = new SingleLinkedList.Node(null); // 存放反转之后的头指针
        SingleLinkedList.Node cur = list.getHead().next; // 指向第一个节点，用于遍历的指针
        SingleLinkedList.Node next; // 临时保存遍历到的节点的指针域
        // 遍历链表，cur指针为null时退出循环
        while (cur != null) {
            // 1.从head节点正序获取链表节点
            // 2.使用类似头插法的方式放到reverseHead
            next = cur.next; // 因为cur指针要参与后续操作，我们需要将cur指针指向的下一个元素地址保存，否则后续元素会丢失
            if (reverseHead.next == null) {
                // 表示在插入第一个节点，需要将cur的指针域置null
                cur.next = null;
            } else {
                // 表示插入的不是第一个节点
                cur.next = reverseHead.next; // 将reverseHead头节点的下一个节点赋值给cur的next指针域
            }

//            cur.next = reverseHead.next == null ? null: reverseHead.next; // 三元代替if...else...
            reverseHead.next = cur; // 将cur赋值给reverseHead头节点的指针域
            cur = next; // 不要忘了后移操作
        }
        // 遍历完之后，将reverseHead赋值给head就OK了
        list.setHead(reverseHead);
        return list;
    }

    /**
     * 4.倒序打印链表（不改变数据结构）
     * 使用 栈 数据结构，先进后出，就倒序打印了
     *
     * @param list 需要打印的链表
     */
    public static void reversePrint(SingleLinkedList list) {
        // 遍历链表，存入 栈
        Stack stack = new Stack();
        SingleLinkedList.Node cur = list.getHead().next; // 指向第一个节点，用于遍历的指针
        // cur为空则退出
        while (cur != null) {
            stack.push(cur); // 压栈
            cur = cur.next; // 后移
        }
        // 打印
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    /**
     * 5.合并两个有序链表，合并之后仍然有序
     * 除了这个，还有一种递归的方法，有兴趣自己去找找
     * @param l1
     * @param l2
     * @return
     */
    public static SingleLinkedList mergeLinkedList(SingleLinkedList l1, SingleLinkedList l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        SingleLinkedList.Node cur1 = l1.getHead().next; // 指向l1第一个节点
        SingleLinkedList.Node cur2 = l2.getHead().next; // 指向l2第一个节点
        SingleLinkedList.Node newCur = new SingleLinkedList.Node(null); // 新链表的指针
        SingleLinkedList.Node newHead = newCur; // 新的头节点
        // 有一个链表遍历完就退出
        while(cur1 != null && cur2 != null){
            if(((Comparable)cur1.element).compareTo(cur2.element) <= 0){
                // cur1指向的元素 小于等于 比cur2指向的元素
                // 既然小于，那就把cur1的指向的元素放到新链表的后面
                newCur.next = cur1;
                cur1 = cur1.next;
            }else{
                // cur1指向的元素 大于 比cur2指向的元素
                // 既然大于，那就把cur2指向的元素放在新链表的后面
                newCur.next = cur2;
                cur2 = cur2.next;
            }
            newCur = newCur.next;
        }
        /*if(cur1 == null){
           // 链表l1遍历完了，直接将l2放到新链表后面
            newCur.next = cur2;
        }else{
           // 链表l2遍历完了，直接将l1放到新链表后面
            newCur.next = cur1;
        }*/
        newCur.next = cur1 == null ? cur2 : cur1; // 三元运算符代替if...else...
        SingleLinkedList<Object> list = new SingleLinkedList<>();
        list.setHead(newHead);
        return list;
    }

    /**
     * 6.获取链表的中间元素
     * 使用快慢指针来解决这个问题，无论是中间还是1/4这些问题都可以用快慢指针
     *
     * @return 返回链表的中间元素
     */
    public static Object getMiddleElement(SingleLinkedList list) {
        // 快指针一次走两步，慢指针一次走一步，等到快指针走到终点，慢指针刚好到中间
        SingleLinkedList.Node slow = list.getHead(); // 慢指针
        SingleLinkedList.Node fast = list.getHead(); // 快指针
        if (list.isEmpty()) {
            throw new RuntimeException("链表为空");
        }
        // 快指针为null，或者快指针的指针域指向的为null，则退出
        // 假如快指针走两步刚好走到最后一个节点，此时fast.next == null，退出
        // 假如快指针走两步走到最后一个节点的next域，此时fast == null，退出
        while (fast != null && fast.next != null) {
            fast = fast.next.next; // 快指针走两步
            slow = slow.next; // 慢指针走一步
        }
        return slow.element;
    }
}
