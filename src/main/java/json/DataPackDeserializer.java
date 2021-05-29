package json;

import com.google.gson.*;
import data.DataBuilder;
import data.DataPack;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Map;

@Service("packDeserializer")
class DataPackDeserializer implements JsonDeserializer<DataPack> {

    private DataPack dataPack;


    @Override
    public DataPack deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {

    if (jsonElement.isJsonArray()){
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        readAsArray(jsonArray, context);
    }
    else if (jsonElement.isJsonObject()){
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        readAsObject(jsonObject, context);
    }
    return dataPack;
    }

    private void readAsArray(JsonArray jsonArray, JsonDeserializationContext context) {
        for (int i = 1; i <= jsonArray.size(); i++) {
            deserializeElement(jsonArray.get(i -1), context, i);
        }
    }

    private void readAsObject(JsonObject jsonObject, JsonDeserializationContext context){
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()){
            if (entry.getValue().isJsonArray()) {
                JsonArray jsonArray = entry.getValue().getAsJsonArray();
                readAsArray(jsonArray, context);
            }
            else if (entry.getValue().isJsonPrimitive()){
                deserializeElement(jsonObject, context, 1);
                return;
            }
        }
    }

    private void deserializeElement(JsonElement jsonElement, JsonDeserializationContext context, int lineIndex){
        DataBuilder dataBuilder = context.deserialize(jsonElement, DataBuilder.class);
        dataBuilder.setLineIndex(lineIndex);
    dataPack.addData(dataBuilder.buildData());
    }
}
