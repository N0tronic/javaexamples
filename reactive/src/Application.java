import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

public class Application {

    private static final int SUBMISSIONS = 5;

    public static void main(String[] args) {
        SubmissionPublisher<String> logPublisher = new SubmissionPublisher<>();
        //exampleWithDefaultSubscriber(logPublisher);
        exampleWithOwnSubscriber(logPublisher);
    }

    private static void exampleWithDefaultSubscriber(SubmissionPublisher<String> publisher) {
        publisher.consume(System.out::println);
        publisher.submit("Text");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void exampleWithOwnSubscriber(SubmissionPublisher<String> publisher) {
        LogSubscriber subscriber1 = new LogSubscriber("Subscriber 1");
        LogSubscriber subscriber2 = new LogSubscriber("Subscriber 2");
        publisher.subscribe(subscriber1);
        publisher.subscribe(subscriber2);
        System.out.println(publisher.getNumberOfSubscribers());

        for (int i = 1; i <= SUBMISSIONS ; i++) {
            publisher.offer("Submission Number " + i, 50, TimeUnit.MILLISECONDS, (s, el) -> true);
        }

        publisher.close();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
