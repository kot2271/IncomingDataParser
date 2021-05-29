package json;

import application.FileParser;
import application.HandlerExecutor;
import org.junit.Test;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

public class JsonFileParserTest {

    @Test
    public void run() {
        JsonFileParser jsonFileParserMock = mock(JsonFileParser.class);
        HandlerExecutor handlerExecutorMock = mock(HandlerExecutor.class);
        Queue<FileParser> parsersPool = new ConcurrentLinkedQueue<>();
        parsersPool.add(jsonFileParserMock);
        handlerExecutorMock.execute(parsersPool);
        doNothing().when(jsonFileParserMock).run();
    }
}
