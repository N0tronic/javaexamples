package lockstep.phaser;

import lockstep.LockStepExample;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Phaser;

import static java.util.concurrent.Executors.newFixedThreadPool;

public class LockStepPhaser extends LockStepExample {

    public static void main(String[] args) {
        LockStepPhaser lsp = new LockStepPhaser();
        ExecutorService pool = newFixedThreadPool(TASK_PER_BATCH);
        Phaser phaser = new Phaser(TASK_PER_BATCH);
        for (int batch = 0; batch < BATCHES; batch++) {
            for (int i = 0; i < TASK_PER_BATCH; i++) {
                pool.submit(() -> lsp.task(phaser));
            }
        }
        pool.shutdown();
    }

    public void task(Phaser phaser){
        phaser.arriveAndAwaitAdvance();
        doTask(phaser.getPhase());
    }
}
