package convert;

import dataFormat.DataFormatFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public abstract class Converter {
    protected DataFormatFactory dataFormatFactory;

    public Converter(DataFormatFactory dataFormatFactory) {
        this.dataFormatFactory = dataFormatFactory;
    }

    public abstract String convert(String data) throws ParserConfigurationException, IOException, SAXException, TransformerException;
}
