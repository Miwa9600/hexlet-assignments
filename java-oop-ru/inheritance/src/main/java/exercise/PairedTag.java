package exercise;

import java.util.List;
import java.util.Map;

public class PairedTag extends Tag {
    private String body;
    private List<Tag> children;

    public PairedTag(String name, Map<String, String> attributes, String body, List<Tag> children) {
        super(name, attributes);
        this.body = body;
        this.children = children;
    }

    protected String getTagName() {
        return name;
    }

    protected boolean isSingleTag() {
        return false;
    }

    protected String getBody() {
        return body;
    }

    protected Iterable<Tag> children() {
        return children;
    }
}
