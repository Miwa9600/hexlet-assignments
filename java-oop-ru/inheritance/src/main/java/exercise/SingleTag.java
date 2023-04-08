package exercise;

import java.util.Map;

public class SingleTag extends Tag {
    public SingleTag(String name, Map<String, String> attributes) {
        super(name, attributes);
    }

    protected String getTagName() {
        return name;
    }

    protected boolean isSingleTag() {
        return true;
    }
}
