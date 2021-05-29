package parser;

import application.FileParser;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FileParserFactoryTest {

    @Test
    public void createParserJsonFile() throws NoSuchFieldException {
        FileParserFactory fileParserFactoryMock = mock(FileParserFactory.class);
        FileParser fileParserMock = mock(FileParser.class);
        String jsonFile = "testJsonData.json";
        when(fileParserFactoryMock.createParser(jsonFile)).thenReturn(fileParserMock);
    }


    @Test
    public void createParserCsvFile() throws NoSuchFieldException {
        FileParserFactory fileParserFactoryMock = mock(FileParserFactory.class);
        FileParser fileParserMock = mock(FileParser.class);
        String csvFile = "testCsvData.csv";
        when(fileParserFactoryMock.createParser(csvFile)).thenReturn(fileParserMock);
    }

    @Test
    public void createParserOtherFile() throws NoSuchFieldException {
        FileParserFactory fileParserFactoryMock = mock(FileParserFactory.class);
        String otherFile = "testOther.test";
        when(fileParserFactoryMock.createParser(otherFile)).thenThrow(new NoSuchFieldException());
    }

}
