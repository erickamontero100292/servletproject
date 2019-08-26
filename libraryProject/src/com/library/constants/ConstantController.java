package com.library.constants;

public enum ConstantController {
    USUARIO("usuario"),
    MESSAGE("message");


    private String property;

    ConstantController(String property) {
        this.property = property;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }
}
