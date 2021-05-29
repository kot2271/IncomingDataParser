package data;

public interface DataBuilder {

    void setDataId(String dataId);

    void setAmount(String amount);

    void setCurrency(String currency);

    void setComment(String comment);

    void setFileName(String fileName);

    void setLineIndex(int lineIndex);

    Data buildData();
}
