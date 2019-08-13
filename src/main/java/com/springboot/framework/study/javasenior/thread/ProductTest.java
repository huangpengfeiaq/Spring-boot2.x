package com.springboot.framework.study.javasenior.thread;

import lombok.Data;

/**
 * 线程通信：生产者消费者例题
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/13 11:29
 */
public class ProductTest {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();

        Producer producer = new Producer(clerk);
        Thread pro = new Thread(producer);
        pro.setName("生产者");
        pro.start();

        Customer customer = new Customer(clerk);
        Thread cus = new Thread(customer);
        cus.setName("消费者");
        cus.start();

        Thread cus2 = new Thread(customer);
        cus2.setName("消费者2");
        cus2.start();
    }
}

class Producer implements Runnable {
    private Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
                clerk.produceProduct();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Customer implements Runnable {
    private Clerk clerk;

    public Customer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(200);
                clerk.consumeProduct();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Clerk {
    private static int productNum = 0;

    public synchronized void produceProduct() throws InterruptedException {
        notify();
        if (productNum < 20) {
            productNum++;
            System.out.println(Thread.currentThread().getName() + "：完成生成第" + productNum + "个产品");
        } else {
            wait();
        }
    }

    public synchronized void consumeProduct() throws InterruptedException {
        notify();
        if (productNum > 0) {
            productNum--;
            System.out.println(Thread.currentThread().getName() + "：消费后剩余" + productNum + "个产品");
        } else {
            wait();
        }
    }
}