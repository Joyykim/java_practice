package Thread;

import java.util.Scanner;

public class SkipThread extends Thread {

    SkipThread(MainThread mainThread){
        this.mainThread = mainThread;
    }
    private final MainThread mainThread;
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {
        synchronized (mainThread){
            scanner.next();
            mainThread.interrupt();
        }
    }
}