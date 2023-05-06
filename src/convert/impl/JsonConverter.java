package convert.impl;

import convert.Converter;
import dataFormat.DataFormat;
import dataFormat.DataFormatFactory;
import dataFormat.formatImpl.XmlDataFormat;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class JsonConverter extends Converter {


    public JsonConverter(DataFormatFactory dataFormatFactory) {
        super(dataFormatFactory);
    }


    public String convert(String data) throws TransformerException, ParserConfigurationException, IOException, SAXException {
        // Парсимо JSON
        DataFormat jsonDataFormat = dataFormatFactory.createDataFormat();
        jsonDataFormat.parse(data);

        // Отримуємо сконвертовані XML дані
        return jsonDataFormat.render(data);
    }
}
