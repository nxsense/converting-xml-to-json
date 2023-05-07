package convert;

import dataFormat.DataFormat;
import dataFormat.DataFormatFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public abstract class Converter {
    protected DataFormatFactory dataFormatFactory;

    protected Converter(DataFormatFactory dataFormatFactory) {
        this.dataFormatFactory = dataFormatFactory;
    }

    public final String convert(String data) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        DataFormat dataFormat = dataFormatFactory.createDataFormat();
        parseData(dataFormat, data);
        return renderData(dataFormat);
    }

    protected abstract void parseData(DataFormat dataFormat, String data) throws ParserConfigurationException, IOException, SAXException, TransformerException;

    protected abstract String renderData(DataFormat dataFormat) throws ParserConfigurationException, IOException, SAXException, TransformerException;
}

