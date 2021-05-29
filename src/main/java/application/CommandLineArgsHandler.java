package application;

import json.DataPrint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import picocli.CommandLine;

import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;


@Service("commandLine")
 class CommandLineArgsHandler implements Runnable{

    private static final Logger LOGGER = LogManager.getLogger(CommandLineArgsHandler.class.getName());

    @CommandLine.Parameters(index = "0..*")
    private String[] files;
    private HandlerExecutorImpl handlerExecutor;
    private ParserFactory parserFactory;
    private DataPrint dataPrint;
    private CountDownLatch countDownLatch;

    @Autowired
    public void setParserFactory(ParserFactory parserFactory){
        this.parserFactory = parserFactory;
    }

    @Autowired
    public void setParserExecutor(HandlerExecutorImpl handlerExecutor){
        this.handlerExecutor = handlerExecutor;
    }

    @Autowired
    public void setDataPrint(DataPrint dataPrint) {
        this.dataPrint = dataPrint;
    }

    @Override
    public void run() {
        if (files.length != 0) {
            Queue<FileParser> parsersPool = createParsersPool(files);
            setCountDownLatch(parsersPool);
            handlerExecutor.execute(parsersPool);
            handlerExecutor.execute(dataPrint);
        }
    }

    private Queue<FileParser> createParsersPool(String[] files){
        Queue<FileParser> parsersPool = new ConcurrentLinkedQueue<>();
        Arrays.stream(files).forEach(file ->{
            try{
                FileParser fileParser = parserFactory.createParser(file);
                fileParser.setParsedFile(file);
                parsersPool.add(fileParser);
            } catch (NoSuchFieldException e){
                LOGGER.error("There are no methods for parsing the file:" + file);
            }
        });
        return parsersPool;
    }

    private void setCountDownLatch(Queue<FileParser> parsersPool) {
        int filesCount = parsersPool.size();
        countDownLatch = new CountDownLatch(filesCount);
        parsersPool.forEach(parser -> parser.setCountDownLatch(countDownLatch));
        dataPrint.setCountDownLatch(countDownLatch);
    }

}
