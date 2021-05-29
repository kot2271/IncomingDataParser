package csv;

import application.FileParser;
import com.opencsv.CSVParser;
import data.DataBuilder;
import data.DataPack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@Service("csvFileParser")
@Scope("prototype")
 class CsvFileParser implements FileParser, ApplicationContextAware {

  private static final Logger LOGGER = LogManager.getLogger(CsvFileParser.class.getName());

  private ApplicationContext context;
  private CSVParser csvParser;
  private String fileName;
  private CountDownLatch countDownLatch;
  private DataPack dataPack;

  @Autowired
  public void setCsvParser(CSVParser csvParser) {
    this.csvParser = csvParser;
  }

  @Autowired
  public void setDataPack(DataPack dataPack) {
    this.dataPack = dataPack;
  }

  @Override
  public void setCountDownLatch(CountDownLatch countDownLatch) {
    this.countDownLatch = countDownLatch;
  }

  @Override
  public void setParsedFile(String fileName) {
    this.fileName = fileName;
  }

  @Override
  public void run() {
    parseFile(fileName);
    countDownLatch.countDown();
  }

  private void parseFile(String fileName) {
    try (Stream<String> lines = Files.lines(Paths.get(fileName), StandardCharsets.UTF_8)) {
      AtomicInteger integer = new AtomicInteger(1);
      lines.skip(1).forEach(line -> parseLine(line, fileName, integer.getAndIncrement()));
    } catch (IOException e) {
      LOGGER.error("File " + fileName + "nothing found that can be included in the parsing.");
    }
  }

  private void parseLine(String line, String fileName, int lineIndex) {
    try {
      String[] parseLine = csvParser.parseLine(line);
      DataBuilder dataBuilder = context.getBean("dataBuilder", DataBuilder.class);
      dataBuilder.setDataId(parseLine[0]);
      dataBuilder.setAmount(parseLine[1]);
      dataBuilder.setCurrency(parseLine[2]);
      dataBuilder.setComment(parseLine[3]);
      dataBuilder.setFileName(fileName);
      dataBuilder.setLineIndex(lineIndex);
      dataPack.addData(dataBuilder.buildData());

    } catch (IOException e) {
      LOGGER.error("line " + lineIndex + "not parsed from file: " + fileName);
    }
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.context = applicationContext;
  }
}
