import annotation.EnumTypeMapper;
import mapper.MapperClass;
import mapper.MapperEnum;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

class EnumMapperUtil {

    private static final String ENUM_MAPPER_PACKAGE_NAME = "mapper";
    private static final int CACHE_MAX_SIZE = 1 << 6;

    private static ConcurrentHashMap<Object, Object> cacheMap = new ConcurrentHashMap<>();

    private EnumMapperUtil() {}

    private static void addToCache(Object key, Object value) {
        if (cacheMap.size() > CACHE_MAX_SIZE) {
            cacheMap.clear();
        }
        cacheMap.put(key, value);
    }

    static Map<Integer, String> getCodeMsgMapByEnumClass(Class enumClass) {
        if (cacheMap.containsKey(enumClass)) {
            return (Map<Integer, String>)cacheMap.get(enumClass);
        }
        Map<Integer, String> codeMsgMap = new HashMap<>();
        if (!enumClass.isEnum()) {
            return codeMsgMap;
        }
        try {
            Method getCode = enumClass.getMethod("getCode");
            Method getMsg = enumClass.getMethod("getMsg");
            Object[] objs = enumClass.getEnumConstants();
            for (Object obj : objs) {
                codeMsgMap.put((Integer)getCode.invoke(obj), (String)getMsg.invoke(obj));
            }
            cacheMap.put(enumClass, codeMsgMap);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return codeMsgMap;
    }

    static MapperClass getMapperClass(Object obj, Integer enumTypeCode) {
        if (cacheMap.containsKey(enumTypeCode)) {
            return (MapperClass)cacheMap.get(enumTypeCode);
        }
        MapperClass mc;
        Field field = getEnumTypeMapperField(obj.getClass());
        if (field == null) {
            return null;
        }
        Class enumClass;
        String mapperEnumName = getEnumTypeNameByField(field);
        try {
            enumClass = getClassByClassName(mapperEnumName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if (enumClass!=null && enumClass.isEnum()) {
            Object[] enumMapperObjs = enumClass.getEnumConstants();
            List<MapperEnum> enumMappers = new ArrayList<>();
            for (Object o : enumMapperObjs) {
                enumMappers.add(MapperEnum.class.cast(o));
            }
            MapperEnum mapperConstant = enumMappers.stream().
                    filter(mapperEnum -> enumTypeCode.equals(mapperEnum.getTypeCode())).findFirst().orElse(null);
            if (mapperConstant != null) {
                mc = new MapperClass(mapperConstant.getEnumClass(), mapperConstant.getMsg());
                addToCache(enumTypeCode, mc);
                return mc;
            } else {
                System.out.println("<invalid enum type code>");
                return null;
            }
        }
        return null;
    }

    private static Field getEnumTypeMapperField(Class clazz) {
        List<Field> fields = java.util.Arrays.asList(clazz.getDeclaredFields());
        return fields.stream().filter(field -> field.isAnnotationPresent(EnumTypeMapper.class)).findFirst().orElse(null);
    }

    private static Class getClassByClassName(String className){
        Class clazz = null;
        try {
            clazz = Class.forName(ENUM_MAPPER_PACKAGE_NAME + "." + className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clazz;
    }

    private static String getEnumTypeNameByField(Field field) {
        return field.getAnnotation(EnumTypeMapper.class).type();
    }
}
