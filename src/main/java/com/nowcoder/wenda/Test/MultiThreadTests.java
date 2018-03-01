package com.nowcoder.wenda.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

class MyThread extends Thread{
    private int tid;

    public MyThread(int tid) {
        this.tid = tid;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(1000);
                System.out.println(String.format("%d:%d", tid, i));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class Consumer implements Runnable {
    BlockingQueue<String> q;
    public Consumer(BlockingQueue<String> q) {
        this.q = q;
    }

    @Override
    public void run() {
        try{
            while (true) {
                System.out.println(Thread.currentThread().getName() + ":" + q.take());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Producer implements Runnable {
    BlockingQueue<String> q;
    public Producer(BlockingQueue<String> q) {
        this.q = q;
    }

    @Override
    public void run() {
        try{
            for (int i = 0; i < 100; i++) {
                Thread.sleep(1000);
                q.put(String.valueOf(i));
                System.out.println(Thread.currentThread().getName()+ ":" + i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}




public class MultiThreadTests {
//    public static void testThread() {
//        for (int i = 0; i < 10; i++) {
//            final int z = i;
////            new MyThread(i).start();
//            new Thread(() -> {
//                for (int j = 0; j < 10; j++) {
//                    try {
//                        Thread.sleep(1000);
//                        System.out.println(String.format("T2 %d:", z));
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
//        }
//    }
//
//    private static Object object = new Object();
//
//    public static void testSynchronized1() {
//        synchronized (object) {
//            try {
//                for (int i = 0; i < 10; i++) {
//                    Thread.sleep(1000);
//                    System.out.println(String.format("T3 %d", i));
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//
//    public static void testSynchronized2() {
//        synchronized (new Object()) {
//            try {
//                for (int i = 0; i < 10; i++) {
//                    Thread.sleep(1000);
//                    System.out.println(String.format("T4 %d", i));
//
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static void testSynchronized() {
//        for (int i = 0; i < 10; i++) {
//            new Thread(() -> {
//                testSynchronized1();
//                testSynchronized2();
//            }).start();
//        }
//    }


    public static void testBlockingQueue() {
        BlockingQueue<String> q = new ArrayBlockingQueue<>(10);
        new Thread(new Producer(q), "Producer").start();
        new Thread(new Consumer(q), "Consumer1").start();
        new Thread(new Consumer(q), "Consumer2").start();
    }

    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    private static int userId;

    public static void testThreadLocal() {
        for (int i = 0; i < 10; i++) {
            final int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    threadLocal.set(finalI);
                    System.out.println("set:" + finalI);
                    try {
                        Thread.sleep(1000);
                        System.out.println("ThreadLocal:" + threadLocal.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public static void testExecutor() {
//        ExecutorService service = Executors.newSingleThreadExecutor();
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try{
                        Thread.sleep(1000);
                        System.out.println("Executor1:" + i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        service.submit(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try{
                        Thread.sleep(1000);
                        System.out.println("Executor2:" + i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        service.shutdown();

        while (!service.isTerminated()) {
            try {
                Thread.sleep(1000);
                System.out.println("wait for termination");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static int counter = 0;
    private static AtomicInteger atomicInteger = new AtomicInteger();

    public static void testWithoutAtomic() {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10000);
                        for (int j = 0; j < 10; j++) {
                            counter++;
                            System.out.println(counter);
                            System.out.println(atomicInteger.incrementAndGet());
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }


    public static void testFucture() {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<Integer> future = service.submit(new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                Thread.sleep(1000);
                return 1 ;
            }
        });

        service.shutdown();
        try {
//            System.out.println(future.get());
            System.out.println(future.get(100, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
//        testThread();
//        testSynchronized();
//        testThreadLocal();
//        testExecutor();
//        testWithoutAtomic();
        testFucture();
    }

}
