package data;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Currency;

@Component("data")
@Scope("prototype")
public class Data {

    private int id;
    private int line;
    private double amount;
    private Currency currency;
    private String comment;
    private String filename;
    private String result;


    public Data() {
    }

    public int getDataId() {
        return id;
    }

    public void setDataId(int dataId) {
        this.id = dataId;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
