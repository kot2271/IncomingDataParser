package json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import data.DataBuilder;
import org.junit.Test;

import java.lang.reflect.Type;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DataBuilderDeserializerTest {

    @Test
    public void deserialize() {
        DataBuilderDeserializer deserializerMock = mock(DataBuilderDeserializer.class);
        JsonElement jsonElementMock = mock(JsonElement.class);
        Type typeMock = mock(Type.class);
        JsonDeserializationContext contextMock = mock(JsonDeserializationContext.class);
        DataBuilder dataBuilderMock = mock(DataBuilder.class);
        when(deserializerMock.deserialize(jsonElementMock, typeMock, contextMock)).thenReturn(dataBuilderMock);
    }
}
