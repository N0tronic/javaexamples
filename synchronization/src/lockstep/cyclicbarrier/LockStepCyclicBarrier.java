package lockstep.cyclicbarrier;

import lockstep.LockStepExample;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import static java.util.concurrent.Executors.newFixedThreadPool;

public class LockStepCyclicBarrier extends LockStepExample {

    public static void main(String[] args) {
        LockStepCyclicBarrier lse = new LockStepCyclicBarrier();
        ExecutorService pool = newFixedThreadPool(TASK_PER_BATCH);
        CyclicBarrier barrier = new CyclicBarrier(TASK_PER_BATCH);
        for (int batch = 0; batch < BATCHES; batch++) {
            barrier.reset();
            for (int i = 0; i < TASK_PER_BATCH; i++) {
                int batchNumber = batch + 1;
                pool.submit(() -> lse.task(barrier, batchNumber));
            }
        }
        pool.shutdown();
    }

    public void task(CyclicBarrier barrier, int batch){
        boolean interrupted = Thread.interrupted();
        while (true){
            try {
                barrier.await();
                break;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (BrokenBarrierException e) {
               throw new AssertionError(e);
            }
        }
        if(interrupted){
            Thread.currentThread().interrupt();
        }
        doTask(batch);
    }
}
