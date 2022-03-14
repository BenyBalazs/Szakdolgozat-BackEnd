package com.benyovszki.szakdolgozat.util;

import org.apache.commons.lang3.EnumUtils;

public class EnumConverter {

    public static <B extends Enum<B>> B convert(Enum<?> source, Class<B> targetClass) {
        if (source == null || targetClass == null) {
            return null;
        }
        return EnumUtils.getEnum(targetClass, source.name());
    }

    public static <E extends Enum<E>> E getEnum(final Class<E> enumClass, final String enumName) {
        if (enumName == null) {
            return null;
        }
        try {
            return Enum.valueOf(enumClass, enumName);
        } catch (final IllegalArgumentException ex) {
            return null;
        }
    }
}
