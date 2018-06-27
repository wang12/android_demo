package com.example.xiaoqiang.myapplication.java;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: [xiaoqiang]
 * @Description: [ThreadSync]
 * @CreateDate: [2018/5/30]
 * @UpdateDate: [2018/5/30]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class ThreadSync {

    volatile  static ReentrantReadWriteLock mLock = new ReentrantReadWriteLock();

    AtomicBoolean
    public void get() {
        mLock.readLock().lock();
        System.out.println(System.currentTimeMillis() +"  get(),线程:"+Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mLock.readLock().unlock();
    }

    public void set() {
        mLock.writeLock().lock();
        System.out.println(System.currentTimeMillis() +"  set(),线程:"+Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mLock.writeLock().unlock();
    }

    public static void main(String[] args) {
        final ThreadSync threadSync = new ThreadSync();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                threadSync.get();
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                threadSync.set();
            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                threadSync.get();
            }
        });
        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                threadSync.get();
            }
        });
        thread1.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("锁队列长度:"+mLock.getQueueLength());
        thread2.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("锁队列长度:"+mLock.getQueueLength());
        thread3.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("锁队列长度:"+mLock.getQueueLength());
        thread4.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("锁队列长度:"+mLock.getQueueLength());

    }
}
