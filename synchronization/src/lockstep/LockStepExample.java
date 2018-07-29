package lockstep;

import java.util.concurrent.ThreadLocalRandom;

public class LockStepExample {

    protected final static int TASK_PER_BATCH = 3;
    protected final static int BATCHES = 5;

    protected final void doTask(int batch){
        System.out.printf("Task start %d%n",batch);
        int ms = ThreadLocalRandom.current().nextInt(500,3000);
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.printf("Task in Batch %d tool %dms%n",batch,ms);
    }
}
