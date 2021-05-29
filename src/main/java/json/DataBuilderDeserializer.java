package json;

import com.google.gson.*;
import data.DataBuilder;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;

@Service("builderDeserializer")
@Scope("prototype")
public class DataBuilderDeserializer implements JsonDeserializer<DataBuilder>, ApplicationContextAware {

    private ApplicationContext applicationContext;
    private String fileName;

    void setFile(String fileName){
        this.fileName = fileName;
    }

    @Override
    public DataBuilder deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        DataBuilder dataBuilder = applicationContext.getBean("dataBuilder", DataBuilder.class);
        dataBuilder.setFileName(fileName);
        dataBuilder.setDataId(jsonObject.get("dataId").getAsString());
        dataBuilder.setAmount(jsonObject.get("amount").getAsString());
        dataBuilder.setCurrency(jsonObject.get("currency").getAsString());
        dataBuilder.setComment(jsonObject.get("comment").getAsString());
        return dataBuilder;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
