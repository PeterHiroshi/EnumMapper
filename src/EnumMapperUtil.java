import enums.EnumType;
import enums.EnumTypeInstance;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

class EnumMapperUtil {

    private static final int CACHE_MAX_SIZE = 1 << 6;

    private static ConcurrentHashMap<Object, Object> cacheMap = new ConcurrentHashMap<>();

    public static String getMsgFromEnumType(EnumType enumType) {
        if (enumType == null) {
            return null;
        }
        return enumType.getMsg();
    }

    public static String getDescFromEnumType(EnumType enumType) {
        if (enumType == null) {
            return null;
        }
        return enumType.getDesc();
    }

    static EnumType getEnumTypeByCodeFromEnumClass(Class enumClass, Integer code) {
        String key = buildKey(enumClass, code);
        if (cacheMap.containsKey(key)) {
            return (EnumType) cacheMap.get(key);
        }
        List<EnumType> enumTypeList = getEnumTypeListByEnumClass(enumClass);
        if (enumTypeList != null && !enumTypeList.isEmpty()) {
            EnumType enumType = enumTypeList.stream().filter(e->e.getCode().equals(code)).findFirst().orElse(null);
            String desc = (enumType == null) ? enumTypeList.get(0).getDesc() : enumType.getDesc();
            String msg = (enumType == null) ? "<invalid code>" : enumType.getMsg();
            EnumType enumTypeInstance = new EnumTypeInstance(code, msg, desc);
            addToCache(key, enumTypeInstance);
            return enumTypeInstance;
        }
        return null;

    }

    private static List<EnumType> getEnumTypeListByEnumClass(Class enumClass) {
        if (cacheMap.containsKey(enumClass)) {
            return (List<EnumType>)cacheMap.get(enumClass);
        }
        if (!enumClass.isEnum()) {
            return null;
        }
        List<EnumType> enumTypeList = new ArrayList<>();
        try {
            Method getCode = enumClass.getMethod("getCode");
            Method getMsg = enumClass.getMethod("getMsg");
            Method getDesc = enumClass.getMethod("getDesc");
            Object[] objs = enumClass.getEnumConstants();
            for (Object obj : objs) {
                enumTypeList.add(new EnumTypeInstance((Integer)getCode.invoke(obj), (String)getMsg.invoke(obj), (String)getDesc.invoke(obj)));
            }
            cacheMap.put(enumClass, enumTypeList);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
        return enumTypeList;
    }

    private EnumMapperUtil() {}

    private static void addToCache(Object key, Object value) {
        if (cacheMap.size() > CACHE_MAX_SIZE-1) {
            cacheMap.clear();
        }
        cacheMap.put(key, value);
    }

    private static String buildKey(Class clazz, Integer code) {
        return String.format("%s::%s", clazz.toString(), code);
    }
}
