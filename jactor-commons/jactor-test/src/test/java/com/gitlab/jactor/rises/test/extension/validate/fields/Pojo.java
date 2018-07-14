package com.gitlab.jactor.rises.test.extension.validate.fields;

public class Pojo {
    private Object aField;
    private String strField;

    Object getObjField() {
        return aField;
    }

    @SuppressWarnings("unused") // used by reflection
    public void setObjField(Object objField) {
        this.aField = objField;
    }

    String getStrField() {
        return strField;
    }

    @SuppressWarnings("unused") // used by reflection
    public void setStrField(String strField) {
        this.strField = strField;
    }
}
