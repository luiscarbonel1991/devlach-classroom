package com.devlach.classroom.api.exception;

public enum ParamName {
    RESPONSE_ERROR("responseError");

    private final String nameParam;

    ParamName(String nameParam) {
        this.nameParam = nameParam;
    }

    public String getNameParam() {
        return nameParam;
    }
}
