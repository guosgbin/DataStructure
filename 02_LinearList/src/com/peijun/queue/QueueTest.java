package com.peijun.queue;

/**
 * @author: Dylan Kwok GSGB
 * @date: 2020/11/23 0:25
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class QueueTest {
    public static void main(String[] args) {

//        testArrayQueue();
//        testLinkedQueue();
        testArrayDeque();
    }

    /**
     * 测试双端队列
     */
    private static void testArrayDeque() {

        ArrayDeque<String> deque = new ArrayDeque<>();
//        System.out.println(deque.isEmpty());
//        System.out.println(deque.isFull());
//        System.out.println(deque.size());

//        System.out.println("============");
//        deque.offerFirst("1");
//        System.out.println(deque.peekFirst());
//        System.out.println(deque.peekLast());
//        deque.offerFirst("2");
//        System.out.println(deque.peekFirst());
//        System.out.println(deque.peekLast());
//        deque.offerFirst("3");
//        System.out.println(deque.peekFirst());
//        System.out.println(deque.peekLast());
//        deque.offerFirst("4");
//        System.out.println(deque.peekFirst());
//        System.out.println(deque.peekLast());
//        System.out.println(deque.isEmpty());
//        System.out.println(deque.isFull());
//        System.out.println(deque.size());
//        System.out.println(deque);

//        System.out.println("============");
//        deque.offerLast("1");
//        System.out.println(deque.peekFirst());
//        System.out.println(deque.peekLast());
//        deque.offerLast("2");
//        System.out.println(deque.peekFirst());
//        System.out.println(deque.peekLast());
//        deque.offerLast("3");
//        System.out.println(deque.peekFirst());
//        System.out.println(deque.peekLast());
//        deque.offerLast("4");
//        System.out.println(deque.peekFirst());
//        System.out.println(deque.peekLast());
//        System.out.println(deque.isEmpty());
//        System.out.println(deque.isFull());
//        System.out.println(deque.size());
//        System.out.println(deque);

        deque.offerLast("越");
        deque.offerFirst("真");
        deque.offerLast("俎");
        deque.offerFirst("的");
        deque.offerLast("代");
        deque.offerFirst("脑");
        deque.offerLast("庖");
        deque.offerFirst("瘫");
        deque.offerFirst("瘫");
        System.out.println(deque);

    }

    /**
     * 测试链队列
     */
    private static void testLinkedQueue() {
        LinkedQueue<String> queue = new LinkedQueue<>();

//        System.out.println(com.peijun.queue.isEmpty());
//        System.out.println(com.peijun.queue.peek());
//        System.out.println(com.peijun.queue.size());

        System.out.println(queue.offer("你"));
        System.out.println(queue.offer("是"));
        System.out.println(queue.offer("傻"));
        System.out.println(queue.offer("逼"));

        System.out.println(queue.isEmpty());
        System.out.println(queue.peek());
        System.out.println(queue.size());
        System.out.println(queue);

        System.out.println("=====");

        System.out.println(queue.poll());

        System.out.println(queue.isEmpty());
        System.out.println(queue.peek());
        System.out.println(queue.size());
        System.out.println(queue);

    }


    /**
     * 测试顺序循环队列
     */
    private static void testArrayQueue() {
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();

//        System.out.println(arrayQueue.isEmpty());
//        System.out.println(arrayQueue.isFull());
//        System.out.println(arrayQueue);

        System.out.println(arrayQueue.size());

        arrayQueue.offer(0);
        arrayQueue.offer(1);
        arrayQueue.offer(2);
        arrayQueue.offer(3);
        arrayQueue.offer(4);
        arrayQueue.offer(5);
        arrayQueue.offer(6);
        arrayQueue.offer(7);
        arrayQueue.offer(8);
        System.out.println(arrayQueue.isEmpty());
        System.out.println(arrayQueue.isFull());
        System.out.println(arrayQueue);
        System.out.println(arrayQueue.size());

        System.out.println("============");

        System.out.println(arrayQueue.poll());
        System.out.println(arrayQueue.poll());
        System.out.println(arrayQueue.poll());
        System.out.println(arrayQueue.peek());
        System.out.println(arrayQueue);
        System.out.println(arrayQueue.size());

        System.out.println("============");

        arrayQueue.offer(9);
        arrayQueue.offer(10);
        arrayQueue.offer(11);
        arrayQueue.offer(11);
    }
}
