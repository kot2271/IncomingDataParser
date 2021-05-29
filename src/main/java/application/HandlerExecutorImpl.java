package application;

import json.DataPrint;
import org.springframework.stereotype.Service;

import java.util.Queue;
import java.util.concurrent.ExecutorService;

@Service("handlersExecutor")
public class HandlerExecutorImpl implements HandlerExecutor {

    private final ExecutorService printService;

    private final ExecutorService parseService;

    public HandlerExecutorImpl(ExecutorService printService, ExecutorService parseService) {
        this.printService = printService;
        this.parseService = parseService;
    }

    @Override
    public void execute(Queue<FileParser> parserPool) {
        parserPool.forEach(parseService::execute);
        parseService.shutdown();
    }

    @Override
    public void execute(DataPrint dataPrint) {
        printService.execute(dataPrint);
        printService.shutdown();
    }
}
