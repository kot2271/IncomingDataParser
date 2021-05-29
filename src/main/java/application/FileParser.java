package application;

import java.util.concurrent.CountDownLatch;

public interface FileParser extends Runnable {

    void setCountDownLatch(CountDownLatch countDownLatch);

    void setParsedFile(String fileName);

}
