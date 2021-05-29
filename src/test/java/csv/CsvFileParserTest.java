package csv;

import application.FileParser;
import application.HandlerExecutor;
import org.junit.Test;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

public class CsvFileParserTest {

    @Test
    public void run() {
        CsvFileParser csvFileParserMock = mock(CsvFileParser.class);
        HandlerExecutor handlerExecutorMock = mock(HandlerExecutor.class);
        Queue<FileParser> parsersPool = new ConcurrentLinkedQueue<>();
        parsersPool.add(csvFileParserMock);
        handlerExecutorMock.execute(parsersPool);
        doNothing().when(csvFileParserMock).run();
    }
}
