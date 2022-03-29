package com.benyovszki.szakdolgozat.util;

import org.apache.commons.lang3.EnumUtils;

public class EnumConverter {

    public static <B extends Enum<B>> B convert(Enum<?> source, Class<B> targetClass) {
        if (source == null || targetClass == null) {
            return null;
        }
        return EnumUtils.getEnum(targetClass, source.name());
    }
}
