package application;

import json.DataPrint;
import org.junit.Test;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import static org.mockito.Mockito.*;

public class HandlerExecutorTest {

  @Test
  public void executeFileParserPool() {
    Queue<FileParser> parsersPool = spy(new ConcurrentLinkedQueue<>());
    HandlerExecutorImpl handlerExecutorMock = mock(HandlerExecutorImpl.class);
    doNothing().when(handlerExecutorMock).execute(parsersPool);
  }

  @Test
  public void executeDataPrint() {
    DataPrint mock = mock(DataPrint.class);
    HandlerExecutorImpl handlerExecutorMock = mock(HandlerExecutorImpl.class);
    doNothing().when(handlerExecutorMock).execute(mock);
  }
}
