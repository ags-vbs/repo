package com.mycompany.vgtu.page;

import java.io.Serializable;
import org.apache.wicket.util.string.StringValue;

public class PageParameter implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Integer index;
    private final String name;
    private final StringValue stringValue;

    public PageParameter(Integer index, String name, StringValue stringValue) {
        this.index = index;
        this.name = name;
        this.stringValue = stringValue;
    }

    public String name() {
        return name;
    }

    public Integer index() {
        return index;
    }

    public long asLong() {
        try {
            return stringValue.toLong();
        } catch (Exception ex) {
            throw pageParametersException("asLong", ex);
        }
    }

    public String asString() {
        try {
            return stringValue.toString();
        } catch (Exception ex) {
            throw pageParametersException("asString", ex);
        }
    }

    @Override
    public String toString() {
        return name + (index == null ? "" : "[" + index + "]") + "=" + stringValue;
    }

    private PageParametersException pageParametersException(String asType, Exception ex) {
        return new PageParametersException("Failed to read page parameter '" + this + "'"
                + " " + asType + ":\n" + ex);
    }
}
