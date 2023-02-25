// Options for creating a thread object
public class ThreadShow {

    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Thread Show!");

        System.out.println("Here is the Empty Thread!");
        Thread a = new Thread();
        a.start();
        a.join();
        System.out.println("...");

        System.out.println("Okay, let's move to the next - Run Override Thread!");
        Thread b = new Thread() {
            @Override
            public void run() {
                System.out.println("-- Hello! I'm Run Override Thread");
            }
        };
        b.start();
        b.join();

        System.out.println("And here is the most common kind - Runnable Thread");
        Thread c = new Thread(() -> System.out.println("-- Running and running..."));
        c.start();
        c.join();

        System.out.println("And that is all for now :(");
    }
}
