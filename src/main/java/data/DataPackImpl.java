package data;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component("dataPack")
public class DataPackImpl implements DataPack {

    private final List<Data> pack;

    public DataPackImpl() {
        pack = new CopyOnWriteArrayList<>();
    }

    @Override
    public List<Data> getDataList() {
        return pack;
    }

    @Override
    public void addData(Data data) {
        pack.add(data);
    }
}
