package data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Currency;

@Service("dataBuilder")
@Scope("prototype")
public class DataBuilderImpl implements DataBuilder, ApplicationContextAware {

    private static final Logger LOGGER = LogManager.getLogger(DataBuilderImpl.class.getName());
    private final StringBuffer errorString;
    private ApplicationContext applicationContext;
    private String dataId;
    private String amount;
    private String currency;
    private String comment;
    private String fileName;
    private int lineIndex;

    public DataBuilderImpl() {
        errorString = new StringBuffer("ERROR: ");
    }

    @Override
    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    @Override
    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void setLineIndex(int lineIndex) {
        this.lineIndex = lineIndex;
    }

    @Override
    public Data buildData() {
        Data data = applicationContext.getBean("data", Data.class);
        data.setResult("OK");
        data.setDataId(Integer.parseInt(dataId));
        data.setFilename(fileName);
        data.setComment(comment);
        data.setLine(lineIndex);
        setCheckedValue(data);
        return data;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private void setCheckedValue(Data data) {
        double parseAmount = parseAmount(amount, data);
        Currency parseCurrency = parseCurrency(currency, data);
        if (parseAmount == -1 || parseCurrency == null) {
            data.setResult(errorString.toString().trim());
        }
        if (parseAmount != -1) {
            data.setAmount(parseAmount);
        }
        if (currency != null) {
            data.setCurrency(parseCurrency);
        }
    }

    private Currency parseCurrency(String currency, Data data) {
        try{
            return Currency.getInstance(currency);
        } catch (IllegalArgumentException e) {
            errorString.append("incorrectly currency code");
            LOGGER.info("currency is missing in" + data.getDataId() + "from file: " + data.getFilename());
        }
        return null;
    }

    private double parseAmount(String amount, Data data) {
        try {
            return Double.parseDouble(amount);
        } catch (NumberFormatException e) {
            errorString.append("incorrectly entered amount");
            LOGGER.info("amount is not readable in " + data.getDataId() + "from file: " + data.getFilename());
        }
        return -1;
    }

}
