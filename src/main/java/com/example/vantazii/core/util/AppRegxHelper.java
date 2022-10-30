package com.example.vantazii.core.util;


public enum AppRegxHelper {
    PHONE_NUMBER("^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$");

    private final String regex;


    AppRegxHelper(String regex) {
        this.regex = regex;
    }
    public final String getRegex() {
        return regex;
    }
}
