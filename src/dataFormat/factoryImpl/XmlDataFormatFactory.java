public class XmlDataFormatFactory implements DataFormatFactory {
    @Override
    public DataFormat createDataFormat() {
        return new XmlDataFormat();
    }
}