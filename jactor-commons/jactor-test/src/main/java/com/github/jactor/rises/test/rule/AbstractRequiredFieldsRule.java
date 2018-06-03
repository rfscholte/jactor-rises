package com.github.jactor.rises.test.rule;

import com.github.jactor.rises.test.extension.validate.fields.AbstractRequiredFieldsExtension;
import org.junit.rules.ExternalResource;

public abstract class AbstractRequiredFieldsRule extends ExternalResource {

    @Override protected void before() {
        getExtension().beforeEach(null);
    }

    @Override protected void after() {
        getExtension().afterEach(null);
    }

    protected abstract AbstractRequiredFieldsExtension getExtension();
}
