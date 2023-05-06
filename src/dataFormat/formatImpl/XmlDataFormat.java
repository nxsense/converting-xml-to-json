import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class XmlDataFormat extends DataFormat {
    @Override
    public void parse(String data) {
        Deque<String> tags = new ArrayDeque<>();
        String tag = "";
        String value = "";
        boolean inTag = false;

        for (int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);

            if (c == '<') {
                inTag = true;
            } else if (c == '>') {
                inTag = false;
                processTag(tag, tags);
                tag = "";
            } else if (inTag) {
                processTagChar(c, tag);
            } else {
                processValueChar(c, value);
            }
        }

    }

    @Override
    public String render() {
        return null;
    }

    private void processTag(String tag, Deque<String> tags) {
        if (tag.startsWith("/")) {
            tags.pop();
        } else {
            tags.push(tag);
            this.tag = tag.substring(1);
        }
    }

    private void processTagChar(char c, String tag) {
        tag += c;
    }

    private void processValueChar(char c, String value) {
        value += c;
    }

    private void addChild(XmlNode node) {
        this.children.add(node);
        node.setParent(this);
    }

    private void setParent(XMLNode node) {
        this.parent = node;
    }

    private void addAttribute(String name, String value) {
        this.attributes.put(name, value);
    }

}