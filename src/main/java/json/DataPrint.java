package json;

import java.util.concurrent.CountDownLatch;

public interface DataPrint extends Runnable {
    void setCountDownLatch(CountDownLatch countDownLatch);
}
