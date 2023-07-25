package com.device.common.utils;

import cn.hutool.core.util.ReflectUtil;

import java.util.LinkedHashMap;
import java.util.List;

public class EnumUtil {

    public static LinkedHashMap<String, Object> getNameFieldMap(Class<? extends Enum<?>> clazz, String fieldName) {
        Enum<?>[] enums = (Enum[]) clazz.getEnumConstants();
        if (null == enums) {
            return null;
        } else {
            LinkedHashMap<String, Object> map = new LinkedHashMap<>();
            Enum[] var4 = enums;
            int var5 = enums.length;
            for (int var6 = 0; var6 < var5; ++var6) {
                Enum<?> e = var4[var6];
                map.put(e.name(), ReflectUtil.getFieldValue(e, fieldName));
            }
            return map;
        }
    }

    public static LinkedHashMap<String, Object> getNameFieldMap(List<? extends Enum<?>> enums) {
        if (null == enums) {
            return null;
        } else {
            LinkedHashMap<String, Object> map = new LinkedHashMap<>(enums.size());
            for (Enum<?> theEnum : enums) {
                map.put(theEnum.name(), ReflectUtil.getFieldValue(theEnum, "description"));
            }
            return map;
        }
    }
}
