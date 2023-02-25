public class ThreadLife {

    public static void main(String[] args) throws Exception {
        Object lock = new Object();

        Thread t = new Thread() {
            @Override
            public synchronized void run() {
                try {
                    wait(300);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    wait();
                } catch (InterruptedException e) {
                    // as planned
                }

                synchronized (lock) {
                    // passing the gate...
                }
            }
        };
        // here thread is a brand new one - NEW
        System.out.println(t.getState());

        t.start();
        // now its running - RUNNABLE
        System.out.println(t.getState());

        Thread.sleep(100);
        // it should be waiting now - TIMED_WAITING
        System.out.println(t.getState());

        // waiting forever! - WAITING
        Thread.sleep(300);
        System.out.println(t.getState());
        synchronized (lock) { // let's block it after it awakes :)
            synchronized (t) {
                t.notify(); // enough for him!
            }

            // it can't pass the gate until allowed to - BLOCKED
            System.out.println(t.getState());
        }

        t.join();
        // and it's over, bye-bye - TERMINATED
        System.out.println(t.getState());
    }
}
