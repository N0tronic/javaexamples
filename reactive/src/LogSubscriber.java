import java.util.concurrent.Flow;

public class LogSubscriber implements Flow.Subscriber<String> {

    private String name = null;

    public LogSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(String item) {
        System.out.println(name + " => " + item);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("Error occured");
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println(name + " => Completed");
    }
}
