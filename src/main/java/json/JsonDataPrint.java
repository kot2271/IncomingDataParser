package json;

import com.google.gson.Gson;
import data.DataPack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Service("dataPrint")
public class JsonDataPrint implements DataPrint {

    private static final Logger LOGGER = LogManager.getLogger(JsonDataPrint.class.getName());

    private DataPack dataPack;
    private Gson gson;
    private CountDownLatch countDownLatch;

    @Autowired
    public void setDataPack(DataPack dataPack){
        this.dataPack = dataPack;
    }

    @Autowired
    public void setGson(Gson gson){
        this.gson = gson;
    }

    @Override
    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try{
            countDownLatch.await();
            stdOutPrint();
        } catch (InterruptedException e) {
            LOGGER.error("Thread" + Thread.currentThread().getName() + Thread.currentThread().getId() + "is interrupted", e);
        }
    }

    private void stdOutPrint(){
    gson.toJsonTree(dataPack.getDataList()).getAsJsonArray().forEach(System.out::println);
    }
}
