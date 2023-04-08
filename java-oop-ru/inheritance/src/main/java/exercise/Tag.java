package exercise;

import java.util.Map;

public abstract class Tag {
    protected String name;
    protected Map<String, String> attributes;

    public Tag(String name, Map<String, String> attributes) {
        this.name = name;
        this.attributes = attributes;
    }

    protected abstract String getTagName();

    protected abstract boolean isSingleTag();

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(getTagName());
        for (String attrName : attributes.keySet()) {
            sb.append(" ");
            sb.append(attrName);
            sb.append("=\"");
            sb.append(attributes.get(attrName));
            sb.append("\"");
        }
        if (isSingleTag()) {
            sb.append(" />");
        } else {
            sb.append(">");
            sb.append(getBody());
            for (Tag child : children()) {
                sb.append(child.toString());
            }
            sb.append("</");
            sb.append(getTagName());
            sb.append(">");
        }
        return sb.toString();
    }

    protected String getBody() {
        return "";
    }

    protected Iterable<Tag> children() {
        return new Iterable<Tag>() {
            @Override
            public java.util.Iterator<Tag> iterator() {
                return new java.util.Iterator<Tag>() {
                    @Override
                    public boolean hasNext() {
                        return false;
                    }

                    @Override
                    public Tag next() {
                        return null;
                    }
                };
            }
        };
    }
}
