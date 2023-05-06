import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonDataFormat extends DataFormat {
    private Map<String, Object> keyValueMap;
    public JsonDataFormat() {
        keyValueMap = new HashMap<>();
    }
    @Override
    public void parse(String data) {
        String[] elements = splitDataIntoElements(data);
        for (String element : elements) {
            String[] keyValue = splitElementIntoKeyValue(element);
            String key = keyValue[0];
            String valueString = keyValue[1];
            Object value = parseValue(valueString);
            keyValueMap.put(key, value);
        }
    }

    private String[] splitDataIntoElements(String data) {
        return data.replaceAll("[{}\"]", "").split(",");
    }

    private String[] splitElementIntoKeyValue(String element) {
        return element.split(":");
    }

    private Object parseValue(String valueString) {
        if (valueString.equals("true")) {
            return true;
        } else if (valueString.equals("false")) {
            return false;
        } else if (valueString.equals("null")) {
            return null;
        } else if (isString(valueString)) {
            return parseString(valueString);
        } else if (isDouble(valueString)) {
            return parseDouble(valueString);
        } else {
            return parseInteger(valueString);
        }
    }

    private boolean isString(String valueString) {
        return valueString.startsWith("\"") && valueString.endsWith("\"");
    }

    private String parseString(String valueString) {
        return valueString.substring(1, valueString.length() - 1);
    }

    private boolean isDouble(String valueString) {
        return valueString.contains(".");
    }

    private Integer parseInteger(String valueString) throws NumberFormatException {
        return Integer.parseInt(valueString);
    }

    private Double parseDouble(String valueString) throws NumberFormatException {
        return Double.parseDouble(valueString);
    }



    @Override
    public String render() {
        Map<String, Object> keyValueMap = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (Map.Entry<String, Object> entry : keyValueMap.entrySet()) {
            sb.append("\"").append(entry.getKey()).append("\":");
            renderValue(entry.getValue(), sb);
            sb.append(",");
        }
        removeLastCommaIfPresent(sb);
        sb.append("}");
        return sb.toString();
    }

    private void renderValue(Object value, StringBuilder sb) {
        if (value == null) {
            sb.append("null");
        } else if (value instanceof String) {
            renderString((String) value, sb);
        } else if (value instanceof Number) {
            sb.append(value);
        } else if (value instanceof Boolean) {
            sb.append(value);
        } else if (value instanceof Map) {
            renderMap((Map<String, Object>) value, sb);
        } else if (value instanceof List) {
            renderList((List<Object>) value, sb);
        }
    }

    private void renderString(String value, StringBuilder sb) {
        sb.append("\"").append(value).append("\"");
    }

    private void renderMap(Map<String, Object> map, StringBuilder sb) {
        sb.append("{");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            sb.append("\"").append(entry.getKey()).append("\":");
            renderValue(entry.getValue(), sb);
            sb.append(",");
        }
        removeLastCommaIfPresent(sb);
        sb.append("}");
    }

    private void renderList(List<Object> list, StringBuilder sb) {
        sb.append("[");
        for (Object obj : list) {
            renderValue(obj, sb);
            sb.append(",");
        }
        removeLastCommaIfPresent(sb);
        sb.append("]");
    }

    private void removeLastCommaIfPresent(StringBuilder sb) {
        if (sb.charAt(sb.length() - 1) == ',') {
            sb.deleteCharAt(sb.length() - 1);
        }
    }

}