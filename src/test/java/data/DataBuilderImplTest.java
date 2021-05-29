package data;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DataBuilderImplTest {

  @Test
  public void buildData() {
    DataBuilderImpl dataBuilderMock = mock(DataBuilderImpl.class);
    Data dataMock = mock(Data.class);
    when(dataBuilderMock.buildData()).thenReturn(dataMock);
  }
}
