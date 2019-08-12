package com.springboot.framework.study.javasenior.thread;

/**
 * 测试Thread中的常用方法：
 * 1.start():启动当前线程；调用当前线程的run()
 * 2.run()：通常需要重写Thread类中的此方法，将创建的线程要执行的操作声明在此方法中
 * 3.currentThread()：静态方法，返回执行当前代码的线程
 * 4.getName()：获取当前线程的名字
 * 5.setName()：设置当前线程的名字
 * 6.yield()：释放当前cpu的执行权
 * 7.join()：在线程a中调用线程b的join()，此时线程a就进入阻塞状态，直到线程b完全执行完以后，线程a才结束阻塞状态。
 * 8.stop()：已过时。当执行此方法时，强制结束当前线程。
 * 9.sleep(long millis)：当前线程“睡眠”指定的millis毫秒。在指定的millis毫米时间内，当前线程是阻塞状态。
 * 10.isAlive()：判断当前线程是否存活
 * <p>
 * 线程的优先级：
 * 1.MAX_PRIORITY、MIN_PRIORITY
 * 2.如何设置线程的优先级
 * 说明：高优先级的线程要抢占低优先级线程cpu的执行权。但是只是从概率上讲。并不意味着高线程先执行完后再执行低优先级。
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/12 18:35
 */
public class ThreadMethodTest {
    public static void main(String[] args) {
        HelloThread thread = new HelloThread("Thread：1");
//        thread.setName("线程一");

        // 设置分线程的优先级
        thread.setPriority(Thread.MAX_PRIORITY);

        thread.start();

        // 给主线程命名
        Thread.currentThread().setName("主线程");
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        for (int i = 0; i < 100; i++) {
            // “按位与”运算，打印奇数
            if ((i & 1) == 1) {
                System.out.println(Thread.currentThread().getName() + "优先级:" + Thread.currentThread().getPriority() + "奇数:" + i);
            }
//            if (i == 20) {
//                try {
//                    thread.join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
        }

        System.out.println(thread.isAlive());
    }
}

class HelloThread extends Thread {
    public HelloThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            // “取模”运算，打印偶数
            if ((i % 2) == 0) {

//                try {
//                    sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

                System.out.println(getName() + "优先级:" + getPriority() + "偶数:" + i);
            }
        }
    }
}