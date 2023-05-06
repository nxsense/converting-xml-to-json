import dataFormat.DataFormat;
import dataFormat.DataFormatFactory;
import dataFormat.formatImpl.JsonDataFormat;
import dataFormat.formatImpl.XmlDataFormat;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class XmlConverter extends Converter {
    private JsonDataFormat jsonDataFormat;

    public XmlConverter(DataFormatFactory dataFormatFactory) {
        super(dataFormatFactory);
        jsonDataFormat = (JsonDataFormat) dataFormatFactory.createDataFormat();
    }

    @Override
    public String convert(String data) throws ParserConfigurationException, IOException, SAXException {
        // Парсимо XML
        DataFormat xmlDataFormat = dataFormatFactory.createDataFormat();

        //XmlDataFormat xmlDataFormat = (XmlDataFormat) dataFormatFactory.createDataFormat();
        xmlDataFormat.parse(data);

        // Отримуємо сконвертовані JSON дані
        return jsonDataFormat.render(data);
    }
}
