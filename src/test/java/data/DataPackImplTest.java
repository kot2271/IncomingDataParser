package data;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class DataPackImplTest {

    @Test
    public void getDataList(){
        DataPackImpl dataPackSpy = spy(new DataPackImpl());
        Data mockData = mock(Data.class);
        dataPackSpy.addData(mockData);
        List<Data> dataList = new ArrayList<>();
        dataList.add(mockData);
        when(dataPackSpy.getDataList()).thenReturn(dataList);
    }

    @Test
    public void addData() {
        Data mockData = mock(Data.class);
        DataPackImpl dataPackSpy = spy(new DataPackImpl());
        dataPackSpy.addData(mockData);
        List<Data> dataList = dataPackSpy.getDataList();
        assertEquals(dataList.get(0), mockData);
    }
}
