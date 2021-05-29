package application;

import json.DataPrint;

import java.util.Queue;

public interface HandlerExecutor {

    void execute(Queue<FileParser> parserPool);

    void execute(DataPrint dataPrint);
}
