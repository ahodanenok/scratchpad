
// Explore threads that was run by jvm
public class ThreadGroupsInfo {

    public static void main(String[] args) {
        ThreadGroup root = Thread.currentThread().getThreadGroup();
        while (root.getParent() != null) {
            root = root.getParent();
        }

        print(root, 0);
    }

    private static void print(ThreadGroup group, int level) {
        String prefix = " ".repeat(level);
        System.out.printf("%s+%s (max priority = %d)%n",
            prefix, group.getName(), group.getMaxPriority());
        
        // print child threads of the current group
        Thread[] threads = new Thread[group.activeCount()];
        int threadCount = group.enumerate(threads, false);
        for (int i = 0; i < threadCount; i++) {
            Thread t = threads[i];
            System.out.printf("%s -%s (id=%s, priority=%d, daemon=%s, state=%s)",
                prefix, t.getName(), t.getId(), t.getPriority(), t.isDaemon(), t.getState());

            if (t == Thread.currentThread()) {
                System.out.print(" ".repeat(20));
                System.out.print("<----- We're here!");
            }
            System.out.println();
        }

        // print child groups of the current group
        ThreadGroup[] groups = new ThreadGroup[group.activeGroupCount()];
        int groupCount = group.enumerate(groups, false);
        for (int i = 0; i < groupCount; i++) {
            print(groups[i], level + 1);
        }
    }
}