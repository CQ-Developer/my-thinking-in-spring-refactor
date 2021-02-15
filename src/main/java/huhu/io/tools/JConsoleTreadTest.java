package huhu.io.tools;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class JConsoleTreadTest {

    public static void createBusyThread() {
        new Thread(() -> {
            while (true) {}
        }, "testBusyThread").start();
    }

    public static void createLockThread(final Object lock) {
        new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "testLockThread").start();
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        createBusyThread();

        br.readLine();
        createLockThread(new Object());
    }

}
