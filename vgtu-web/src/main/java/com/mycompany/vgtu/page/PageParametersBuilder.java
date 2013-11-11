package com.mycompany.vgtu.page;

import org.apache.wicket.request.mapper.parameter.PageParameters;

public class PageParametersBuilder {
    private final PageParameters pageParameters;
    private int parameterIndex;

    public static PageParametersBuilder buildPageParameters() {
        return new PageParametersBuilder();
    }

    private PageParametersBuilder() {
        pageParameters = new PageParameters();
        parameterIndex = 0;
    }

    public PageParameters instance() {
        return pageParameters;
    }

    public PageParametersBuilder withSegment(Object indexedValue) {
        pageParameters.set(parameterIndex, indexedValue);
        parameterIndex += 1;
        return this;
    }

    public PageParametersBuilder with(String name, Object namedValue) {
        pageParameters.set(name, namedValue);
        return this;
    }
}
