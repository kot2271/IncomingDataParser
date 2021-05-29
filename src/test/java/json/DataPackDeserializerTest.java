package json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import data.DataPack;
import org.junit.Test;

import java.lang.reflect.Type;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DataPackDeserializerTest {

    @Test
    public void deserialize() {
        DataPackDeserializer deserializerMock = mock(DataPackDeserializer.class);
        JsonElement jsonElementMock = mock(JsonElement.class);
        Type typeMock = mock(Type.class);
        JsonDeserializationContext contextMock = mock(JsonDeserializationContext.class);
        DataPack dataPackMock = mock(DataPack.class);
        when(deserializerMock.deserialize(jsonElementMock, typeMock, contextMock)).thenReturn(dataPackMock);
    }
}
