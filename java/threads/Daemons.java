public class Daemons {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            Thread d = new Thread() {
                @Override
                public synchronized void run() {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            d.setDaemon(true);
            d.start();
        }

        Thread t = new Thread() {
            @Override
            public void run() {
                System.out.println("Sleeping...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Time to go!");
            }
        };
        // main thread is not daemon, so this thead won't be either
        t.start();

        System.out.println(Thread.currentThread().getThreadGroup().activeCount() + " active threads");
        // but daemons won't prevent jvm from exiting! except the sleeping one...
    }
}
