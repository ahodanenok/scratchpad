import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorWithOneThread {

    private static long n = 0;

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 100; i++) {
            executor.submit(() -> {
                // Only today! concurrent increment! and on a long variable!
                n++; // but there is only one such thread :)
            });
        }

        executor.shutdown();
        executor.awaitTermination(100, TimeUnit.SECONDS);

        if (n != 100) {
            throw new IllegalStateException(n + "");
        }

        System.out.println(n);
    }
}