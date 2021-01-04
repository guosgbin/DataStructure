package stack;

/**
 * @author: Dylan Kwok GSGB
 * @date: 2020/11/15 23:48
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class StackTest {
    public static void main(String[] args) {
//        testArrayStack();
        testLinkedStack();
    }

    /**
     * 测试链栈
     */
    private static void testLinkedStack() {
        LinkedStack<String> stack = new LinkedStack<>();
        System.out.println(stack.isEmpty());
//        stack.peek();
//        stack.pop();
        System.out.println(stack);

        stack.push("你好");
        System.out.println(stack.peek());
        stack.push("nihao");
        System.out.println(stack.peek());
        System.out.println(stack);
        stack.pop();
        System.out.println(stack);
    }

    /**
     * 测试顺序栈
     */
    public static void testArrayStack() {
        ArrayStack<Integer> stack = new ArrayStack<>();
        System.out.println(stack.toString());

        System.out.println(stack.isEmpty());
        System.out.println(stack.isFull());
//        System.out.println(stack.peek());
//        System.out.println(stack.pop());

        System.out.println("============");
        stack.push(1);
        stack.push(2);
        System.out.println(stack.toString());
        System.out.println(stack.isEmpty());
        System.out.println(stack.isFull());
        System.out.println(stack.peek());
        stack.pop();
        System.out.println(stack.peek());
        System.out.println(stack.toString());
    }
}
