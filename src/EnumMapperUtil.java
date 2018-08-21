import annotation.EnumTypeMapper;
import mapper.MapperClass;
import mapper.MapperEnum;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class EnumMapperUtil {

    private static final String ENUM_MAPPER_PACKAGE_NAME = "mapper";
    private static final int CACHE_MAX_SIZE = 1 << 5;

    private static ConcurrentHashMap<Integer, MapperClass> cacheMap = new ConcurrentHashMap<>();

    private EnumMapperUtil() {}

    private static void addToCache(Integer key, MapperClass value) {
        if (cacheMap.size() > CACHE_MAX_SIZE) {
            cacheMap.clear();
        }
        cacheMap.put(key, value);
    }

    public static Map<Integer, String> getCodeMsgMapByEnumClass(Class enumClass) {
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
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return codeMsgMap;
    }

    public static MapperClass getMapperClassByFieldName(Object obj, String fieldName) {
        MapperClass mc;
        String mapperEnumName = getEnumTypeNameByFieldName(obj.getClass(), fieldName);
        Field field;
        try {
            field = obj.getClass().getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        }
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        Object fieldValue;
        Class enumClass;
        try {
            fieldValue = field.get(obj);
            enumClass = getClassByClassName(mapperEnumName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if (enumClass!=null && enumClass.isEnum()) {
            Integer enumTypeCode = Integer.class.cast(fieldValue);
            if (cacheMap.containsKey(enumTypeCode)) {
                return cacheMap.get(enumTypeCode);
            }
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

    private static Class getClassByClassName(String className){
        Class clazz = null;
        try {
            clazz = Class.forName(ENUM_MAPPER_PACKAGE_NAME + "." + className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clazz;
    }

    private static String getEnumTypeNameByFieldName(Class clazz, String fieldName) {
        String enumTypeName = "";
        Field field;
        try {
            field = clazz.getDeclaredField(fieldName);
            enumTypeName = getEnumTypeNameByField(field);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return enumTypeName;
    }

    private static boolean isEnumTypePresentField(Field field) {
        return field.isAnnotationPresent(EnumTypeMapper.class);
    }

    private static String getEnumTypeNameByField(Field field) {
        if (isEnumTypePresentField(field)) {
            return field.getAnnotation(EnumTypeMapper.class).type();
        }
        else {
            return null;
        }
    }
}
