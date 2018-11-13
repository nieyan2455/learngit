package com.ny.enums.config;

import com.ny.enums.BaseEnum;

/**
 *
 */
class EnumUtil {
    static <E extends Enum<?> & BaseEnum> E codeOf(Class<E> enumClass, int code) {
        E[] enumConstants = enumClass.getEnumConstants();
        for (E e : enumConstants) {
            if (e.getCode() == code) {
                return e;
            }
        }
        return null;
    }
}
