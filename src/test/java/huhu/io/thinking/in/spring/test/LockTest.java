package huhu.io.thinking.in.spring.test;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    private static final Lock lock = new ReentrantLock();

    @Test
    public void test() {
        Thread t1 = new Thread(this::longTimeJob, "thread-1");
        t1.start();

        try {
            TimeUnit.MILLISECONDS.sleep(3000L);
        }
        catch (InterruptedException e) {
            System.out.println("主线程睡眠被打断");
        }

        t1.interrupt();

        lock.lock();
        System.out.println("主线程获取锁成功");
        lock.unlock();
    }

    private void longTimeJob() {
        String name = Thread.currentThread().getName();
        try {
            lock.lockInterruptibly();
            while (!Thread.interrupted()) { }
        }
        catch (InterruptedException e) {
            System.out.printf("%s: 我被打断了%n", name);
        }
        finally {
            lock.unlock();
        }
        System.out.printf("%s: 我运行结束%n", name);
    }

}
