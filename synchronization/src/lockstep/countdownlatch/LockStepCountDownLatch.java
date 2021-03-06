package lockstep.countdownlatch;

import lockstep.LockStepExample;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import static java.util.concurrent.Executors.newFixedThreadPool;

public class LockStepCountDownLatch extends LockStepExample {

    public static void main(String[] args) {
        LockStepCountDownLatch lse = new LockStepCountDownLatch();
        ExecutorService pool = newFixedThreadPool(TASK_PER_BATCH);
        for (int batch = 0; batch < BATCHES; batch++) {
            CountDownLatch latch = new CountDownLatch(TASK_PER_BATCH);
            for (int i = 0; i < TASK_PER_BATCH; i++) {
                int batchNumber = batch + 1;
                pool.submit(() -> lse.task(latch, batchNumber));
            }
        }
        pool.shutdown();
    }

    public void task(CountDownLatch latch, int batch){
        latch.countDown();
        boolean interrupted = Thread.interrupted();
        while (true){
            try {
                latch.await();
                break;
            } catch (InterruptedException e) {
                interrupted = true;
            }
        }
        if(interrupted){
            Thread.currentThread().interrupt();
        }
        doTask(batch);
    }
}
