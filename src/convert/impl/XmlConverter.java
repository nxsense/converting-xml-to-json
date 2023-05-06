package convert.impl;

import convert.Converter;
import dataFormat.DataFormat;
import dataFormat.DataFormatFactory;
import dataFormat.formatImpl.JsonDataFormat;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class XmlConverter extends Converter {

    public XmlConverter(DataFormatFactory dataFormatFactory) {
        super(dataFormatFactory);
    }

    @Override
    public String convert(String data) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        // Парсимо XML
        DataFormat xmlDataFormat = dataFormatFactory.createDataFormat();
        xmlDataFormat.parse(data);

        // Отримуємо сконвертовані JSON дані
        return xmlDataFormat.render(data);
    }
}
