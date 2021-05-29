package json;

import application.FileParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import data.DataBuilder;
import data.DataPack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.CountDownLatch;

@Service("jsonFileParser")
@Scope("prototype")
 class JsonFileParser implements FileParser, ApplicationContextAware {

    private static final Logger LOGGER = LogManager.getLogger(JsonFileParser.class.getName());
    private String fileName;
    private ApplicationContext context;
    private CountDownLatch countDownLatch;

    @Override
    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void setParsedFile(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void run() {
        parseFile(fileName);
        countDownLatch.countDown();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    private void parseFile(String fileName) {
        JsonDeserializer<DataPack> dataPackJsonDeserializer = context.getBean("packDeserializer", DataPackDeserializer.class);
        DataBuilderDeserializer dataBuilderDeserializer = context.getBean("builderDeserializer", DataBuilderDeserializer.class);
        dataBuilderDeserializer.setFile(fileName);
        GsonBuilder gsonBuilder = context.getBean("gsonBuilder", GsonBuilder.class);
        Gson gson = gsonBuilder
                .registerTypeAdapter(DataBuilder.class, dataBuilderDeserializer)
                .registerTypeAdapter(DataPack.class, dataPackJsonDeserializer)
                .create();
        BufferedReader reader = readFile(fileName);
        if (reader != null) {
            gson.fromJson(reader, DataPack.class);
        }
    }

    private BufferedReader readFile(String fileName){
        try{
            return Files.newBufferedReader(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            LOGGER.error("File " + fileName + "nothing found that can be included in the parsing.");
        }
        return null;
    }
}
